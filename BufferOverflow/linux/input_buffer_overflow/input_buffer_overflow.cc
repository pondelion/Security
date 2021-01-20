#include <cstdio>
#include <iostream>


int main() {
  char buff[64];

  std::printf("%p", &buff[0]);
  std::scanf("%s", buff);

  return 0;
}
