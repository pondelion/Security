// #define _GNU_SOURCE
#include <link.h>
#include <stdio.h>
#include <dlfcn.h>
#include <string.h>


const char *dyn_tag_to_name(ElfW(Sxword) d_tag) {
    switch (d_tag) {
        case DT_STRTAB: return("DT_STRTAB");
        case DT_SYMTAB: return("DT_SYMTAB");
        case DT_JMPREL: return("DT_JMPREL");
        case DT_HASH: return("DT_HASH");
        case DT_GNU_HASH: return("DT_GNU_HASH");
        default: return("UNKNOWN");
    }
}

void print_symbol_names(const struct link_map *l, ElfW(Word) n_sym_show = 100000) {
  printf("****** symbol names : %s : %p **********\n", l->l_name, (void *)l->l_addr);
  const ElfW(Dyn) *dyn_strtab = NULL;
  const ElfW(Dyn) *dyn_symtab = NULL;
  const ElfW(Dyn) *dyn_jmprel = NULL;
  const ElfW(Dyn) *dyn_hash = NULL;
  int cnt = 0;
  for (const ElfW(Dyn) *dyn = l->l_ld; dyn->d_tag != DT_NULL; ++dyn) {
    printf("[%d] %p : %s\n", cnt, (void *)dyn->d_un.d_ptr, dyn_tag_to_name(dyn->d_tag));
    switch (dyn->d_tag) {
      case DT_STRTAB:
        dyn_strtab = dyn;
        break;
      case DT_SYMTAB:
        dyn_symtab = dyn;
        break;
      case DT_JMPREL:
        dyn_jmprel = dyn;
        break;
      case DT_HASH:
        dyn_hash = dyn;
        break;
    }
    cnt++;
  }

  char* strtab = 0;
  char* sym_name = 0;
  strtab = (char*)dyn_strtab->d_un.d_ptr;

  ElfW(Sym*) symtab;
  symtab = (ElfW(Sym*))dyn_symtab->d_un.d_ptr;

  ElfW(Word) n_sym = 0;
  if (dyn_hash != NULL) {
    ElfW(Word*) hashtab;
    hashtab = (ElfW(Word*))dyn_hash->d_un.d_ptr;
    printf("hashtab: %p\n", hashtab);
    printf("hashtab[1]: %d\n", hashtab[1]);
    n_sym = hashtab[1];
    if (n_sym > n_sym_show) {
      n_sym = n_sym_show;
    }
  } else {
    printf("DT_HASH not found.\n");
    return;
  }

  for (ElfW(Word) sym_index = 0; sym_index < n_sym; sym_index++) {
    sym_name = &strtab[symtab[sym_index].st_name];
    printf("[%d] sym_name : %s : %p\n", sym_index, sym_name, (void *)(l->l_addr + symtab[sym_index].st_value));
  }
  printf("**************************\n");
}

void* find_function_addr_from_symbol(const char *sym_name_to_find) {
  void *handle;

  handle = dlopen(NULL, RTLD_LAZY);
  if (!handle) {
    printf("Error: %s\n", dlerror());
    return NULL;
  }

  struct link_map *l;
  int ld_ret = dlinfo(handle, RTLD_DI_LINKMAP,  &l);
  if (ld_ret) {
      printf("Error: %s\n", dlerror());
      return NULL;
  }

  do {
    printf("%s\n", l->l_name);
    if (strstr(l->l_name, "linux-vdso") != NULL) {
      l = l->l_next;
      continue;
    }

    const ElfW(Dyn) *dyn_strtab = NULL;
    const ElfW(Dyn) *dyn_symtab = NULL;
    const ElfW(Dyn) *dyn_jmprel = NULL;
    const ElfW(Dyn) *dyn_hash = NULL;
    int cnt = 0;
    for (const ElfW(Dyn) *dyn = l->l_ld; dyn->d_tag != DT_NULL; ++dyn) {
      // printf("[%d] %p : %s\n", cnt, (void *)dyn->d_un.d_ptr, dyn_tag_to_name(dyn->d_tag));
      switch (dyn->d_tag) {
        case DT_STRTAB:
          dyn_strtab = dyn;
          break;
        case DT_SYMTAB:
          dyn_symtab = dyn;
          break;
        case DT_JMPREL:
          dyn_jmprel = dyn;
          break;
        case DT_HASH:
          dyn_hash = dyn;
          break;
      }
      cnt++;
    }

    char* strtab = 0;
    char* sym_name = 0;
    strtab = (char*)dyn_strtab->d_un.d_ptr;

    ElfW(Sym*) symtab;
    symtab = (ElfW(Sym*))dyn_symtab->d_un.d_ptr;

    ElfW(Word) n_sym = 0;
    if (dyn_hash != NULL) {
      ElfW(Word*) hashtab;
      hashtab = (ElfW(Word*))dyn_hash->d_un.d_ptr;
      n_sym = hashtab[1];
    } else {
      printf("DT_HASH not found.\n");
      l = l->l_next;
      continue;
    }

    for (ElfW(Word) sym_index = 0; sym_index < n_sym; sym_index++) {
      sym_name = &strtab[symtab[sym_index].st_name];
      if (strcmp(sym_name, sym_name_to_find) == 0) {
        printf("[%d] found sym_name : %s : %p : %s\n", sym_index, sym_name, (void *)(l->l_addr + symtab[sym_index].st_value), l->l_name);
        return (void *)(l->l_addr + symtab[sym_index].st_value);
      }
      // printf("[%d] sym_name : %s : %p\n", sym_index, sym_name, (void *)(l->l_addr + symtab[sym_index].st_value));
    }

    l = l->l_next;
  } while (l != NULL);

  return NULL;
}

void* find_got_addr_from_symbol(const char *sym_name_to_find) {
  void *handle;

  handle = dlopen(NULL, RTLD_LAZY);
  if (!handle) {
    printf("Error: %s\n", dlerror());
    return NULL;
  }

  struct link_map *l;
  int ld_ret = dlinfo(handle, RTLD_DI_LINKMAP,  &l);
  if (ld_ret) {
    printf("Error: %s\n", dlerror());
    return NULL;
  }

  const ElfW(Dyn) *dyn_strtab = NULL;
  const ElfW(Dyn) *dyn_symtab = NULL;
  const ElfW(Dyn) *dyn_jmprel = NULL;
  const ElfW(Dyn) *dyn_hash = NULL;
  int cnt = 0;
  for (const ElfW(Dyn) *dyn = l->l_ld; dyn->d_tag != DT_NULL; ++dyn) {
    // printf("[%d] %p : %s\n", cnt, (void *)dyn->d_un.d_ptr, dyn_tag_to_name(dyn->d_tag));
    switch (dyn->d_tag) {
      case DT_STRTAB:
        dyn_strtab = dyn;
        break;
      case DT_SYMTAB:
        dyn_symtab = dyn;
        break;
      case DT_JMPREL:
        dyn_jmprel = dyn;
        break;
      case DT_HASH:
        dyn_hash = dyn;
        break;
    }
    cnt++;
  }

  char* strtab = 0;
  char* sym_name = 0;
  strtab = (char*)dyn_strtab->d_un.d_ptr;

  ElfW(Sym*) symtab;
  symtab = (ElfW(Sym*))dyn_symtab->d_un.d_ptr;

  Elf64_Rela *p_rela;
  p_rela = (Elf64_Rela *)dyn_jmprel->d_un.d_ptr;
  for (int i = 0; i < 1000; i++) {
    if ((int)(p_rela[i].r_info>>32) == 0) {
      break;
    }
    // printf("%d\n", (int)(p_rela[i].r_info>>32));
    // printf("%d\n", symtab[p_rela[i].r_info>>32].st_name);
    // printf("%s : %p\n", strtab + symtab[p_rela[i].r_info>>32].st_name, l->l_addr + p_rela[i].r_offset);
    if (strcmp(strtab + symtab[p_rela[i].r_info>>32].st_name, sym_name_to_find) == 0) {
      printf("[GOT] found sym_name : %s : %p : %s\n", strtab + symtab[p_rela[i].r_info>>32].st_name, (void *)(l->l_addr + p_rela[i].r_offset), l->l_name);
      return (void *)(l->l_addr + p_rela[i].r_offset);
    }
  }
  
  return NULL;
}

void printf_hook(const char *str) {
  puts("printf_hook called");
  puts(str);
}

int main() {

  void *printf_addr = NULL;
  printf_addr = find_function_addr_from_symbol("printf");

  void *printf_got_addr = NULL;
  printf_got_addr = find_got_addr_from_symbol("printf");

  printf("%p\n", *((uint64_t *)printf_got_addr));
  // getchar();
  *((uint64_t *)printf_got_addr) = (uint64_t)printf_hook;

  printf("%d\n", 50);

  printf("tes\n");
  printf("%d\n", 50);
}

