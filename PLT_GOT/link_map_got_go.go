package main

/*
#cgo LDFLAGS: -ldl
#define _GNU_SOURCE
#include <link.h>
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
#include <string.h>

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
*/
import "C"

func main() {
	handle := C.dlopen(nil, C.RTLD_LAZY)
	if handle == nil {
		panic("Failed to open shared object.")
	}
	printf_addr := C.find_function_addr_from_symbol(C.CString("printf"))
	if printf_addr == nil {
		panic("printf not found")
	} else {
		println(printf_addr)
	}
}
