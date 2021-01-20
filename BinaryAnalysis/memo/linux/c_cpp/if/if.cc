#include <iostream>


int main() {
  int a = 1;

  if (a == 0) {
    std::cout << "a is zero" << std::endl;
  } else if (a == 1) {
    std::cout << "a is one" << std::endl;
  } else {
    std::cout << "other" << std::endl;
  }

  return 0;
}
