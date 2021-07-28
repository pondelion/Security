#include <stdio.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <stdlib.h>


int main(int argc, char *argv[])
{
  pid_t pid = atoi(argv[1]);
  unsigned long addr = strtoll(argv[2], NULL, 0);
  int orgValue;
  int data = strtol(argv[3], NULL, 0);

  char file[64];
  sprintf(file, "/proc/%ld/mem", (long)pid);
  int fd = open(file, O_RDWR);

  ptrace(PTRACE_ATTACH, pid, 0, 0);
  waitpid(pid, NULL, 0);
  lseek64(fd, addr, SEEK_SET);
  read(fd, &orgValue, sizeof(orgValue));
  lseek64(fd, addr, SEEK_SET);
  write(fd, &data, sizeof(data));
  ptrace(PTRACE_DETACH, pid, 0, 0);

  printf("org value : %p = %d\n", addr, orgValue);
  return 0;
}