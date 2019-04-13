#include <iostream>
#include <cstdio>
#include <string>
#include <windows.h>
#include "executable/pe/pe-file-extractor.h"


namespace bfe = binary_feature_extractor;

int main() {
  bfe::PEFileExtractor pe;
  LPBYTE base_addr = pe.LoadFile(std::string("./out/bin/extractor.ex"));
  printf("%p\n", base_addr);
}