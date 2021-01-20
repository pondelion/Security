#include <iostream>
#include <cstdlib>


void func() {
  std::cout << "func called" << std::endl;
  std::exit(0);
}

int main() {
  long a[5];
  a[0] = 0;
  a[1] = 2;
  a[2] = 4;
  a[3] = 6;
  a[4] = 8;

  func();
  *(&a[4] + 3) = (long)(&func);

  return 0;
}
