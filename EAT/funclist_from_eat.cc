#include <windows.h>
#include <winnt.h>
#include <winternl.h>
#include <iostream>
#include <vector>
#include <string>
#include <wchar.h>


struct FuncInfo {
  PCHAR pName;
  PVOID pFuncAddr;
};


void getFuncInfo(PVOID pBaseAddress, std::vector<FuncInfo>& funcInfos) {
  PIMAGE_DOS_HEADER pImgDosHeader = reinterpret_cast<PIMAGE_DOS_HEADER>(pBaseAddress);
  PIMAGE_NT_HEADERS pImgNtHeader = reinterpret_cast<PIMAGE_NT_HEADERS>(pImgDosHeader + pImgDosHeader->e_lfanew);
  PIMAGE_EXPORT_DIRECTORY pImgExpDir = reinterpret_cast<PIMAGE_EXPORT_DIRECTORY>(
    pImgDosHeader + pImgNtHeader->OptionalHeader.DataDirectory[0].VirtualAddress
  );
  ULONG numberOfNames = pImgExpDir->NumberOfNames;
  PULONG pNameList = reinterpret_cast<PULONG>(pImgDosHeader + pImgExpDir->AddressOfNames);
  
  PCHAR pName = nullptr;

  for (ULONG i = 0; i < numberOfNames; i++) {
    pName = reinterpret_cast<PCHAR>(pImgDosHeader + pNameList[i]);
    std::wcout << pName << std::endl;

    USHORT hint = *reinterpret_cast<USHORT *>(pImgDosHeader + pImgExpDir->AddressOfNameOrdinals + 2*i);
    ULONG funcAddr = *reinterpret_cast<PULONG>(pImgDosHeader + pImgExpDir->AddressOfFunctions + 4*hint);
    FuncInfo funcInfo = {
      pName,
      reinterpret_cast<PVOID>(pImgDosHeader + funcAddr)
    };
    funcInfos.emplace_back(funcInfo);
  }
}

bool endsWith(const std::string& s, const std::string& suffix) {
  if (s.size() < suffix.size()) {
    return false;
  }
  return std::equal(std::rbegin(suffix), std::rend(suffix), std::rbegin(s));
}

void printModuleInfo(PLDR_DATA_TABLE_ENTRY pDataTable) {
  std::cout << "BaseAddress : " << pDataTable->DllBase << std::endl;
  std::wcout << "DllName : " << pDataTable->FullDllName.Buffer << std::endl;

  std::wstring wDllName((wchar_t*)pDataTable->FullDllName.Buffer);
  std::string dllName(wDllName.begin(), wDllName.end());
  std::cout << dllName.c_str() << std::endl;
  if (endsWith(dllName, ".dll")) { 
    std::vector<FuncInfo> funcInfos;
    getFuncInfo(pDataTable->DllBase, funcInfos);

    for (const auto& info : funcInfos) {
      std::wcout << "        " << info.pName << " : " << info.pFuncAddr << std::endl;
    }
  }
}

void main() {
  // Thread Environment Block (TEB)
  #if defined(_M_X64) // x64
  PTEB pTBE = reinterpret_cast<PTEB>(__readgsqword(reinterpret_cast<DWORD_PTR>(&static_cast<NT_TIB*>(nullptr)->Self)));
  std::cout << "x64\n"; 
  #else // x86
  PTEB pTBE = reinterpret_cast<PTEB>(__readfsdword(reinterpret_cast<DWORD_PTR>(&static_cast<NT_TIB*>(nullptr)->Self)));
  std::cout << "x86\n";
  #endif

  // Process Environment Block (PEB)
  PPEB pPEB = pTBE->ProcessEnvironmentBlock;

  PPEB_LDR_DATA pLoaderData = pPEB->Ldr;
  PLIST_ENTRY pHead, pNext;
  pHead = pNext = pLoaderData->InMemoryOrderModuleList.Flink;
  
  while (true) {
    PLDR_DATA_TABLE_ENTRY pDataTable = CONTAINING_RECORD(pNext, LDR_DATA_TABLE_ENTRY, InMemoryOrderLinks);
    printModuleInfo(pDataTable);
    pNext = pNext->Flink;
    if ((pNext == pHead) | !pNext) {
      break;
    }
    std::cout << "==========================================================\n"; 
  }
}
