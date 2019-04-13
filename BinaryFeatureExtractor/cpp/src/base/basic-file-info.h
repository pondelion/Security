#ifndef BASIC_FILE_INFO_H_
#define BASIC_FILE_INFO_H_

#include <string>


namespace binary_feature_extractor {

struct BasicFileInfo {
  BasicFileInfo() {}
  BasicFileInfo(uint32_t const file_size,
                std::string const file_path)
      : file_size(file_size),
        file_path(file_path) {}

  uint32_t      file_size;
  std::string   file_path;
};

}

#endif
