#include <windows.h>
#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
#include <tchar.h>
#pragma comment(lib, "user32.lib")
using namespace std;

typedef int (__stdcall *OrgSend)(SOCKET, const char *, int, int);
typedef int (__stdcall *OrgRecv)(SOCKET, char *buf, int, int);

typedef struct {
    string    symbolName;
    BYTE*    address;
    BYTE*    addressInIAT;
    DWORD    ordinal;
} SYMBOLINFO, *PSYMBOLINFO;

typedef struct {
    string dllName;
    vector<SYMBOLINFO> symbolInfo;
} IMPORTDLLINFO, *PIMPORTDLLINFO;

class IATHooker {

private:
    vector<IMPORTDLLINFO> mImportDllInfo;
    BYTE* mBaseAddr;
    IMAGE_DOS_HEADER *mpImageDosHeader;
    IMAGE_NT_HEADERS *mpImageNtHeader;
    IMAGE_FILE_HEADER *mpImageFileHeader;
    IMAGE_OPTIONAL_HEADER *mpImageOptionalHeader;
    IMAGE_SECTION_HEADER *mpImageSectionHeader;
    IMAGE_DATA_DIRECTORY *mpImageDataDirectory;
    IMAGE_IMPORT_DESCRIPTOR *mpImageImportDescriptor;
    //OrgSend mpOrgSend;
    //OrgRecv mpOrgRecv;

public:
    IATHooker();
    virtual ~IATHooker();
    void analyzeIAT();
    DWORD hookBySymbol(string dllname, string symbol, DWORD hookProc);
    DWORD hookByOrdinal(string dllname, DWORD ordinal, DWORD hookProc);
    //DWORD hookSendBySymbol(DWORD hookProc);
    //DWORD hookRecvBySymbol(DWORD hookProc);
    void showImportInfo();
    SYMBOLINFO* findSymbol(string dllname, string symbol);
    SYMBOLINFO* findOrdinal(string dllname, DWORD ordinal);
    bool rewriteIAT(DWORD addr, DWORD hookProc);

};
