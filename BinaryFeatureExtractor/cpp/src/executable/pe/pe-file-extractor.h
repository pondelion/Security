#ifndef PE_FILE_EXTRACTOR_H_
#define PE_FILE_EXTRACTOR_H_
#include <iostream>
#include <string>
#include "base/base-feature-extractor.h"


namespace binary_feature_extractor {

class PEFileExtractor : public BaseFeatureExtractor {
 public:
  explicit PEFileExtractor() {}
  explicit PEFileExtractor(std::string const filepath);
  ~PEFileExtractor() {}

 private:
  void LoadFileHeader() override;
  bool CheckMagic(LPBYTE base_addr);
  void ParseFileHeader();
  void ParseDosHeader();
  void ParseNTHeader();
  void ParseFileHeader();
  void ParseOptionalHeader();
  void ParseSectionHeader();
  void ParseImportDescriptor();
  void ParseExportDirectory();
  void ParseImportByName();
  void ParseThunkData32();
};

}

#endif