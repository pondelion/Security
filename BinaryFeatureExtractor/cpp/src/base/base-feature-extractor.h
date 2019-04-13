#ifndef BASE_FEATURE_EXTRACTOR_H_
#define BASE_FEATURE_EXTRACTOR_H_
#include <iostream>
#include <string>
#include <windows.h>
#include "basic-file-info.h"


namespace binary_feature_extractor {

class BaseFeatureExtractor {

 public:
  BaseFeatureExtractor() {}
  explicit BaseFeatureExtractor(std::string const filepath);
  virtual ~BaseFeatureExtractor();
  LPBYTE LoadFile(std::string const filepath);
  LPBYTE GetFileBaseAddr() const { return file_base_; }

 protected:
  BasicFileInfo basic_file_info_;
  HANDLE file_handle_;
  HANDLE file_map_handle_;
  LPBYTE file_base_;

  virtual void LoadFileHeader() = 0;
};

}

#endif
