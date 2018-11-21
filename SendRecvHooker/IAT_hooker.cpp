#include "IAT_hooker.h"

IATHooker::IATHooker()
{
    mBaseAddr = (BYTE *)GetModuleHandle(NULL);
    if (mBaseAddr == NULL)
    {
        MessageBox(NULL, "GetModuleHandle() failed", "", MB_OK);
        return;
    }
    mpImageDosHeader = (IMAGE_DOS_HEADER*)mBaseAddr;
    mpImageNtHeader = (IMAGE_NT_HEADERS*)(mBaseAddr + mpImageDosHeader->e_lfanew);
    mpImageFileHeader = (IMAGE_FILE_HEADER*)((BYTE*)mpImageNtHeader + sizeof(DWORD));
    mpImageOptionalHeader = (IMAGE_OPTIONAL_HEADER*)(mpImageFileHeader + 1);
    mpImageSectionHeader = (IMAGE_SECTION_HEADER*)(mpImageNtHeader + 1);
    mpImageDataDirectory = (IMAGE_DATA_DIRECTORY*)(mpImageOptionalHeader->DataDirectory);
    mpImageImportDescriptor = (IMAGE_IMPORT_DESCRIPTOR*)(mBaseAddr + mpImageDataDirectory[IMAGE_DIRECTORY_ENTRY_IMPORT].VirtualAddress);

    analyzeIAT();

}

IATHooker::~IATHooker()
{

}

void IATHooker::analyzeIAT()
{
    IMAGE_IMPORT_DESCRIPTOR *piid;
    IMAGE_THUNK_DATA *pIAT;
    IMAGE_THUNK_DATA *pINT;
    piid = mpImageImportDescriptor;

    while(piid->Name)
    {

        IMPORTDLLINFO dllinfo;
        SYMBOLINFO symbolinfo;
        dllinfo.dllName = (LPSTR)(mBaseAddr + piid->Name);

        pIAT = (IMAGE_THUNK_DATA*)(mBaseAddr + piid->FirstThunk);
        pINT = (IMAGE_THUNK_DATA*)(mBaseAddr + piid->OriginalFirstThunk);

        while (pINT->u1.Function)
        {
            symbolinfo.address = (BYTE*)(pIAT->u1.Function);
            symbolinfo.addressInIAT = (BYTE*)(&pIAT->u1.Function);
            if ( pINT->u1.AddressOfData & 0x80000000 )
            {
                symbolinfo.ordinal = (DWORD)(pINT->u1.AddressOfData ^ 0x80000000);
            }
            else
            {
                symbolinfo.symbolName = (LPSTR)(((IMAGE_IMPORT_BY_NAME*)(mBaseAddr + pINT->u1.AddressOfData))->Name);
            }

            dllinfo.symbolInfo.push_back(symbolinfo);
            pIAT++;
            pINT++;
        }

        mImportDllInfo.push_back(dllinfo);
        piid++;
    }
}

void IATHooker::showImportInfo()
{
    cout << hex << showbase << uppercase;
    cout << "BASEADDR : " << (DWORD)mBaseAddr << endl;
    for (int i = 0; i < mImportDllInfo.size(); i++)
    {
        cout << mImportDllInfo[i].dllName << endl;

        for (int j = 0; j < mImportDllInfo[i].symbolInfo.size(); j++)
        {
            cout << setw(10) << left << " " << setw(16) << left  << (DWORD)mImportDllInfo[i].symbolInfo[j].address << setw(16) << left  << (DWORD)mImportDllInfo[i].symbolInfo[j].addressInIAT << setw(30) << left << mImportDllInfo[i].symbolInfo[j].symbolName << endl;
        }
    }
}

SYMBOLINFO* IATHooker::findSymbol(string dllname, string symbol)
{
    for (int i = 0; i < mImportDllInfo.size(); i++)
    {    
        if( mImportDllInfo[i].dllName != dllname)
        {
            continue;
        }
        for (int j = 0; j < mImportDllInfo[i].symbolInfo.size(); j++)
        {
            MessageBox(NULL, mImportDllInfo[i].symbolInfo[j].symbolName.c_str(), "", MB_OK);
            if (mImportDllInfo[i].symbolInfo[j].symbolName == symbol)
            {
                return &mImportDllInfo[i].symbolInfo[j];
            }
        }
    }
    return NULL;
}

SYMBOLINFO* IATHooker::findOrdinal(string dllname, DWORD ordinal)
{
    for (int i = 0; i < mImportDllInfo.size(); i++)
    {    
        if( mImportDllInfo[i].dllName != dllname)
        {
            continue;
        }
        for (int j = 0; j < mImportDllInfo[i].symbolInfo.size(); j++)
        {
            if (mImportDllInfo[i].symbolInfo[j].ordinal == ordinal)
            {
                return &mImportDllInfo[i].symbolInfo[j];
            }
        }
    }
    return NULL;
}

DWORD IATHooker::hookBySymbol(string dllname, string symbol, DWORD hookProc)
{
    SYMBOLINFO *si;
    DWORD dwOrgProc;

    si = findSymbol(dllname, symbol);
    if(si == NULL)
    {
        MessageBox(NULL, "Symbol not found.", "", MB_OK);
        return 0;
    }

    dwOrgProc = (DWORD)si->address;
    if(!rewriteIAT((DWORD)si->addressInIAT, hookProc))
    {
        MessageBox(NULL, "IATHooker::rewriteIAT() failed", "", MB_OK);
        return 0;
    }

    return dwOrgProc;
}

DWORD IATHooker::hookByOrdinal(string dllname, DWORD ordinal, DWORD hookProc)
{
    SYMBOLINFO *si;
    DWORD dwOrgProc;

    si = findOrdinal(dllname, ordinal);
    if(si == NULL)
    {
        MessageBox(NULL, "Ordinal not found.", "", MB_OK);
        return 0;
    }

    dwOrgProc = (DWORD)si->address;
    if(!rewriteIAT((DWORD)si->addressInIAT, hookProc))
    {
        MessageBox(NULL, "IATHooker::rewriteIAT() failed", "", MB_OK);
        return 0;
    }

    return dwOrgProc;
}

/*
DWORD IATHooker::hookSendBySymbol(DWORD hookProc)
{
    DWORD retval;
    retval = hookBySymbol("WSOCK32.dll", "send", hookProc);
    if(retval)
    {
        mpOrgSend = (OrgSend)retval;
    }
    return retval;
}

DWORD IATHooker::hookRecvBySymbol(DWORD hookProc)
{

    DWORD retval;
    retval = hookBySymbol("WSOCK32.dll", "recv", hookProc);
    if(retval)
    {
        mpOrgRecv = (OrgRecv)retval;
    }
    return retval;
}
*/

bool IATHooker::rewriteIAT(DWORD addr, DWORD newProc)
{
    DWORD dwOldProtect;

    VirtualProtect((LPVOID)addr, 4, PAGE_READWRITE, &dwOldProtect);
    *((DWORD*)addr) = newProc;
    VirtualProtect((LPVOID)addr, 4, dwOldProtect, &dwOldProtect);

    return true;
}
