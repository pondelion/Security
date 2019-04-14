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

bool PEFileExtractor::CheckMagic(LPBYTE const base_addr) const {
  return file_base_[0] == 'M' && file_base_[1] == 'Z';
}

void PEFileExtractor::ParseFileHeader() {
  ParseDosHeader();
  ParseNTHeader();
  ParseOptionalHeader();
  ParseSectionHeader();
  ParseImportDescriptor();
  ParseExportDirectory();
  ParseImportByName();
  ParseThunkData32();
}

void PEFileExtractor::ParseDosHeader() {
  p_image_dos_header = reinterpret_cast<PIMAGE_DOS_HEADER>(file_base_);
  json data = GetDosHeaderJson();
  json_data_["dos_header"] = data;
}

void PEFileExtractor::ParseNTHeader() {
}

void PEFileExtractor::ParseOptionalHeader() {
}

void PEFileExtractor::ParseSectionHeader() {
}

void PEFileExtractor::ParseImportDescriptor() {
}

void PEFileExtractor::ParseExportDirectory() {
}

void PEFileExtractor::ParseImportByName() {
}

void PEFileExtractor::ParseThunkData32() {
}

json PEFileExtractor::GetDosHeaderJson() const {
  if (!p_image_dos_header) {
    throw std::runtime_error("PEFileExtractor::GetDosHeaderJson() failed. File is not loaded yet.");
  }
  return json::object({
    {"e_magic", p_image_dos_header->e_magic},
    {"e_cblp", p_image_dos_header->e_cblp},
    {"e_cp", p_image_dos_header->e_cp},
    {"e_crlc", p_image_dos_header->e_crlc},
    {"e_cparhdr", p_image_dos_header->e_cparhdr},
    {"e_minalloc", p_image_dos_header->e_minalloc},
    {"e_maxalloc", p_image_dos_header->e_maxalloc},
    {"e_ss", p_image_dos_header->e_ss},
    {"e_sp", p_image_dos_header->e_sp},
    {"e_csum", p_image_dos_header->e_csum},
    {"e_ip", p_image_dos_header->e_ip},
    {"e_cs", p_image_dos_header->e_cs},
    {"e_lfarlc", p_image_dos_header->e_lfarlc},
    {"e_ovno", p_image_dos_header->e_ovno},
    {"e_oemid", p_image_dos_header->e_oemid},
    {"e_oeminfo", p_image_dos_header->e_oeminfo},
    {"e_lfanew", p_image_dos_header->e_lfanew}
  });
}

json PEFileExtractor::ToJson() const {
  return json_data_;
}

}
