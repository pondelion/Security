#include "base-feature-extractor.h"


namespace binary_feature_extractor {

BaseFeatureExtractor::BaseFeatureExtractor(std::string const file_path)
    : basic_file_info_(0, file_path) {
  LoadFile(file_path);
}

BaseFeatureExtractor::~BaseFeatureExtractor() {
  if (file_base_) {
    UnmapViewOfFile(file_base_);
  }
  if (file_map_handle_) {
    CloseHandle(file_map_handle_);
  }
  if (file_handle_) {
    CloseHandle(file_handle_);
  }
}

LPBYTE BaseFeatureExtractor::LoadFile(std::string const file_path) {

  basic_file_info_.file_path = file_path;

  HANDLE file_handle_ = CreateFile(
    file_path.c_str(),
    GENERIC_READ,
    FILE_SHARE_READ,
    nullptr,
    OPEN_EXISTING,
    FILE_ATTRIBUTE_NORMAL,
    nullptr
  );
  if (file_handle_ == INVALID_HANDLE_VALUE) {
    throw std::runtime_error("BaseFeatureExtractor::LoadFile : file not found.");
  }

  HANDLE file_map_handle_ = CreateFileMapping(
    file_handle_,
    nullptr,
    PAGE_READONLY,
    0,
    0,
    nullptr
  );
  DWORD file_size = GetFileSize(file_handle_, 0);
  basic_file_info_.file_size = file_size;
  file_base_ = (LPBYTE)MapViewOfFile(
    file_map_handle_,
    FILE_MAP_READ,
    0,
    0,
    file_size
  );

  LoadFileHeader();

  return file_base_;
}

}