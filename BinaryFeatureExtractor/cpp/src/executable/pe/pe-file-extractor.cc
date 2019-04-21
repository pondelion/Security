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
  p_image_dos_header_ = reinterpret_cast<PIMAGE_DOS_HEADER>(file_base_);
  json_data_["dos_header"] = GetDosHeaderJson();
}

void PEFileExtractor::ParseNTHeader() {
  if (!p_image_dos_header_) {
    return;
  }
  p_image_nt_header_ = reinterpret_cast<PIMAGE_NT_HEADERS32>(
    reinterpret_cast<uint64_t>(p_image_dos_header_) + p_image_dos_header_->e_lfanew
  );
  json_data_["nt_header"] = GetNTHeaderJson();
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
  if (!p_image_dos_header_) {
    throw std::runtime_error("PEFileExtractor::GetDosHeaderJson() failed. File is not loaded yet.");
  }
  return json::object({
    {"e_magic", std::string{reinterpret_cast<char *>(&p_image_dos_header_->e_magic)[0], reinterpret_cast<char *>(&p_image_dos_header_->e_magic)[1]}},
    {"e_cblp", p_image_dos_header_->e_cblp},
    {"e_cp", p_image_dos_header_->e_cp},
    {"e_crlc", p_image_dos_header_->e_crlc},
    {"e_cparhdr", p_image_dos_header_->e_cparhdr},
    {"e_minalloc", p_image_dos_header_->e_minalloc},
    {"e_maxalloc", p_image_dos_header_->e_maxalloc},
    {"e_ss", p_image_dos_header_->e_ss},
    {"e_sp", p_image_dos_header_->e_sp},
    {"e_csum", p_image_dos_header_->e_csum},
    {"e_ip", p_image_dos_header_->e_ip},
    {"e_cs", p_image_dos_header_->e_cs},
    {"e_lfarlc", p_image_dos_header_->e_lfarlc},
    {"e_ovno", p_image_dos_header_->e_ovno},
    {"e_oemid", p_image_dos_header_->e_oemid},
    {"e_oeminfo", p_image_dos_header_->e_oeminfo},
    {"e_lfanew", p_image_dos_header_->e_lfanew}
  });
}

json PEFileExtractor::GetNTHeaderJson() const {
  if (!p_image_nt_header_) {
    throw std::runtime_error("PEFileExtractor::GetNTHeaderJson() failed. File is not loaded yet.");
  }
  return json::object({
    {"Signature", p_image_nt_header_->Signature},
    {"FileHeader", {
      {"Machine", p_image_nt_header_->FileHeader.Machine},
      {"NumberOfSections", p_image_nt_header_->FileHeader.NumberOfSections},
      {"TimeDateStamp", ToTimeString(p_image_nt_header_->FileHeader.TimeDateStamp)},
      {"PointertoSymbolTable", p_image_nt_header_->FileHeader.NumberOfSymbols},
      {"SizeOfOptionalHeader", p_image_nt_header_->FileHeader.Characteristics}
    }},
    {"ImageOptionalHeader", {
      {"Magic", p_image_nt_header_->OptionalHeader.Magic},
      {"MajorLinkerVersion", p_image_nt_header_->OptionalHeader.MajorLinkerVersion},
      {"MinorLinkerVersion", p_image_nt_header_->OptionalHeader.MinorLinkerVersion},
      {"SizeOfCode", p_image_nt_header_->OptionalHeader.SizeOfCode},
      {"SizeOfInitializedData", p_image_nt_header_->OptionalHeader.SizeOfInitializedData},
      {"AddressOfEntryPoint", p_image_nt_header_->OptionalHeader.AddressOfEntryPoint},
      {"BaseOfCode", p_image_nt_header_->OptionalHeader.BaseOfCode},
      {"BaseOfData", p_image_nt_header_->OptionalHeader.BaseOfData},
      {"SectionAlignment", p_image_nt_header_->OptionalHeader.SectionAlignment},
      {"MajorOperatingSystemVersion", p_image_nt_header_->OptionalHeader.MajorOperatingSystemVersion},
      {"MinorOperatingSystemVersion", p_image_nt_header_->OptionalHeader.MinorOperatingSystemVersion},
      {"MajorImageVersion", p_image_nt_header_->OptionalHeader.MajorImageVersion},
      {"MinorImageVersion", p_image_nt_header_->OptionalHeader.MinorImageVersion},
      {"MajorSubsystemVersion", p_image_nt_header_->OptionalHeader.MajorSubsystemVersion},
      {"MinorSubsystemVersion", p_image_nt_header_->OptionalHeader.MinorSubsystemVersion},
      {"Win32VersionValue", p_image_nt_header_->OptionalHeader.Win32VersionValue},
      {"SizeOfImage", p_image_nt_header_->OptionalHeader.SizeOfImage},
      {"SizeOfHeaders", p_image_nt_header_->OptionalHeader.SizeOfHeaders},
      {"CheckSum", p_image_nt_header_->OptionalHeader.CheckSum},
      {"Subsystem", p_image_nt_header_->OptionalHeader.Subsystem},
      {"DllCharacteristics", p_image_nt_header_->OptionalHeader.DllCharacteristics},
      {"SizeOfStackReserve", p_image_nt_header_->OptionalHeader.SizeOfStackReserve},
      {"SizeOfStackCommit", p_image_nt_header_->OptionalHeader.SizeOfStackCommit},
      {"SizeOfHeapReserve", p_image_nt_header_->OptionalHeader.SizeOfHeapReserve},
      {"SizeOfHeapCommit", p_image_nt_header_->OptionalHeader.SizeOfHeapCommit},
      {"LoaderFlags", p_image_nt_header_->OptionalHeader.LoaderFlags},
      {"NumberOfRvaAndSizes", p_image_nt_header_->OptionalHeader.NumberOfRvaAndSizes},
    }}
  });
}

json PEFileExtractor::ToJson() const {
  return json_data_;
}

std::string PEFileExtractor::ToTimeString(DWORD time_date_stamp) const {
  auto tm = *std::localtime(reinterpret_cast<time_t*>(&time_date_stamp));
  std::ostringstream oss;
  oss << std::put_time(&tm, "%Y/%m/%d %H:%M:%S");
  return oss.str();
}

}
