#include <iostream>


int main() {

  int arr[5] = {0, 2, 4, 5, 8};

  for (size_t i=0; i < sizeof(arr) / sizeof(arr[0]); ++i) {
    std::cout << arr[i] << std::endl;
  }

  return 0;
}