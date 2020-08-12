
#include "peb64_asm_call.h"
#include <winnt.h>
#include <winternl.h>
#include <iostream>


void printModuleInfo(PLDR_DATA_TABLE_ENTRY pDataTable) {
  std::cout << "BaseAddress : " << pDataTable->DllBase << std::endl;
  std::wcout << "DllName : " << pDataTable->FullDllName.Buffer << std::endl;
}

void main() {
  PVOID64 peb = GetPeb();

  PPEB pPEB = reinterpret_cast<PPEB>(peb);

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
