#ifndef PE_FILE_EXTRACTOR_H_
#define PE_FILE_EXTRACTOR_H_
#include <iostream>
#include <string>
#include <optional>
#include "nlohmann/json.hpp"
#include "base/base-feature-extractor.h"


using json = nlohmann::json;

namespace binary_feature_extractor {

class PEFileExtractor : public BaseFeatureExtractor {
 public:
  explicit PEFileExtractor() {}
  explicit PEFileExtractor(std::string const filepath);
  ~PEFileExtractor() {}
  json ToJson() const override;

 private:
  bool CheckMagic(LPBYTE const base_addr) const;
  void LoadFileHeader() override;
  void ParseFileHeader();
  void ParseDosHeader();
  void ParseNTHeader();
  void ParseOptionalHeader();
  void ParseSectionHeader();
  void ParseImportDescriptor();
  void ParseExportDirectory();
  void ParseImportByName();
  void ParseThunkData32();
  json GetDosHeaderJson() const;
  json GetNTHeaderJson() const;

 private:
  PIMAGE_DOS_HEADER p_image_dos_header;
};

}

#endif
