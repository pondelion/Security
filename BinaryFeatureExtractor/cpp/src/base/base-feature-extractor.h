#ifndef BASE_FEATURE_EXTRACTOR_H_
#define BASE_FEATURE_EXTRACTOR_H_
#include <iostream>
#include <string>
#include <windows.h>
#include "nlohmann/json.hpp"
#include "basic-file-info.h"


using json = nlohmann::json;

namespace binary_feature_extractor {

class BaseFeatureExtractor {

 public:
  BaseFeatureExtractor() :
      file_handle_(nullptr),
      file_map_handle_(nullptr),
      file_base_(nullptr) {}
  explicit BaseFeatureExtractor(std::string const filepath);
  virtual ~BaseFeatureExtractor();
  LPBYTE LoadFile(std::string const filepath);
  LPBYTE GetFileBaseAddr() const { return file_base_; }
  virtual json ToJson() const = 0;

 protected:
  BasicFileInfo basic_file_info_;
  HANDLE file_handle_;
  HANDLE file_map_handle_;
  LPBYTE file_base_;
  json json_data_;

  void Release();
  virtual void LoadFileHeader() = 0;
};

}

#endif
