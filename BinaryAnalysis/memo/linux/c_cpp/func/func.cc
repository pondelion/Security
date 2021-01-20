#include <iostream>


int func_add(int a, int b) {
  int c = 10;
  return a + b + c;
}


int main() {
  int a = 3;
  int b = 4;
  int res;

  res = func_add(a, b);

  std::cout << res;

  return 0;
}