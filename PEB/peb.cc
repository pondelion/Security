#include <windows.h>
#include <winnt.h>
#include <winternl.h>
#include <iostream>


void printModuleInfo(PLDR_DATA_TABLE_ENTRY pDataTable) {
  std::cout << "BaseAddress : " << pDataTable->DllBase << std::endl;
  std::wcout << "DllName : " << pDataTable->FullDllName.Buffer << std::endl;
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

  // kernel32.dllのベースアドレスが一致してるか確認
  HMODULE hmod = GetModuleHandle(TEXT("kernel32.dll"));
  std::cout << hmod << std::endl;
}
