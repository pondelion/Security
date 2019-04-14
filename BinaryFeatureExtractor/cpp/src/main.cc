#include <iostream>
#include <cstdio>
#include <string>
#include <windows.h>
#include "executable/pe/pe-file-extractor.h"


namespace bfe = binary_feature_extractor;

int main(int argc, char *argv[]) {
  bfe::PEFileExtractor pe;
  LPBYTE base_addr = pe.LoadFile(std::string(argv[1]));
  printf("base_addr : %p\n", base_addr);
  std::cout << pe.ToJson() << std::endl;
}