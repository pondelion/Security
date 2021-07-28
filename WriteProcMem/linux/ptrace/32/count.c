#include <stdio.h>
#include <unistd.h>

int main() {
  int i = 20;
  while(1) {
    i++;
    printf("%p : %d\n", &i, i);
    sleep(1);
  }
  return 0;
}
