#include "pe-file-extractor.h"


namespace binary_feature_extractor {

void PEFileExtractor::LoadFileHeader() {
  if (basic_file_info_.file_path == "") {
    throw std::runtime_error("File path is not set. PEFileExtractor::LoadFileHeader() failed.");
  }
  if (!CheckMagic(file_base_)) {
    throw std::runtime_error("Only PE format file is supported.");
  }
  ParseFileHeader();
}

void PEFileExtractor::ParseFileHeader() {
  ParseFileHeader();
  ParseDosHeader();
  ParseNTHeader();
  ParseFileHeader();
  ParseOptionalHeader();
  ParseSectionHeader();
  ParseImportDescriptor();
  ParseExportDirectory();
  ParseImportByName();
  ParseThunkData32();
}

}