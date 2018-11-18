#include "obj_file_analyzer.hpp"

 
ObjFileAnalyzer::ObjFileAnalyzer(const string filepath)
{
    if(!ObjFileAnalyzer::isObjFile(filepath))
    {
        cout << "Only obj file format is supported\n";
        return;
    }
     
    if(!loadObjFile(filepath, mObjData))
    {
        cout << "loadObjFile() failed\n";
        return;
    }
     
    analyzeFileHeader(mObjData);
    ObjFileAnalyzer::showFileHeaderInfo(mObjData);
     
    analyzeSectionHeader(mObjData);
    ObjFileAnalyzer::showSectionHeaderInfo(mObjData);
     
    analyzeSymbolTableData(mObjData);
    //showSymbolTableDataInfo(mObjData);
    showSymbolNames();
}
 
ObjFileAnalyzer::~ObjFileAnalyzer()
{
}
 
void ObjFileAnalyzer::analyzeFileHeader(vector<byte>& objData)
{
    if(objData.size() == 0)
    {
        cout << "target data is empty\n";
        return;
    }
     
    IMAGE_FILE_HEADER *ifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&objData[0]);
     
    mNumberOfSections = (WORD)ifh->NumberOfSections;
    mNumberOfSymbols = (DWORD)ifh->NumberOfSymbols;
    mpSymbolTable = (IMAGE_SYMBOL*)ifh->PointerToSymbolTable;
     
    return;
}
 
void ObjFileAnalyzer::analyzeSectionHeader(vector<byte>& objData)
{
    if(objData.size() == 0)
    {
        cout << "target data is empty\n";
        return;
    }
     
    IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&objData[0]);
     
    mpSectionTable = reinterpret_cast<IMAGE_SECTION_HEADER*>(pifh + 1);
}
 
void ObjFileAnalyzer::analyzeSymbolTableData(vector<byte>& objData)
{
    IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&objData[0]);
    IMAGE_SYMBOL *pSymbolTable = (IMAGE_SYMBOL*)(&objData[0] + pifh->PointerToSymbolTable);
     
    mSymbolNames.resize(pifh->NumberOfSymbols);
     
    for(int i = 0; i < pifh->NumberOfSymbols; i++)
    {
        if( (pSymbolTable + i)->N.Name.Short )
        {
            if( (pSymbolTable + i)->N.ShortName[7] != '\0' )
            {
                char symbolName[sizeof((pSymbolTable + i)->N.ShortName) + 1] = {0};
                memcpy(symbolName, (pSymbolTable + i)->N.ShortName, sizeof((pSymbolTable + i)->N.ShortName));
                mSymbolNames[i] = symbolName;
            }
            else
            {
                mSymbolNames[i] = (const char *)(pSymbolTable + i)->N.ShortName;
            }
        }
        else
        {
            mSymbolNames[i] = (const char *)( (BYTE*)(pSymbolTable + pifh->NumberOfSymbols) + (pSymbolTable + i)->N.Name.Long);
        }
         
        i += (pSymbolTable + i)->NumberOfAuxSymbols;
    }
}
 
void ObjFileAnalyzer::showSymbolNames() const
{
    cout << "************* SYMBOL NAMES ****************\n";
    for(int i = 0; i < mSymbolNames.size(); i++)
    {
        cout << i+1 << " : " << mSymbolNames[i].c_str() << "\n";
    }
}
 
IMAGE_SYMBOL* ObjFileAnalyzer::findSymbol(const string symbolName)
{
 
    if(mObjData.size() == 0)
    {
        return NULL;
    }
     
    for(int i = 0; i < mSymbolNames.size(); i++)
    {
        if(mSymbolNames[i] == symbolName)
        {
            IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&mObjData[0]);
            IMAGE_SECTION_HEADER *pish = reinterpret_cast<IMAGE_SECTION_HEADER*>(pifh + 1);
            IMAGE_SYMBOL *pSymbolTable = (IMAGE_SYMBOL*)(&mObjData[0] + pifh->PointerToSymbolTable);
            printf("findSymbol : %d\n", (pSymbolTable+i)->SectionNumber);
             
            BYTE* pTargetFunc = (BYTE*)(&mObjData[0] + (int)getRelativePointerToSectionData((pSymbolTable+i)->SectionNumber) + (pSymbolTable + i)->Value);
            printf("%b\n", *(pTargetFunc+1));
            printf("%p\n", &mObjData[0]);
            printf("%p\n", pTargetFunc);

            printf("findSymbol : %d\n", (int)getRelativePointerToSectionData((pSymbolTable+i)->SectionNumber));
            printf("findSymbol : %d\n", (pSymbolTable + i)->Value);
            printf("findSymbol : %d\n", (pSymbolTable + i)->StorageClass);
            analyzeFunctionData(pTargetFunc, pish+((pSymbolTable+i)->SectionNumber)-1, pSymbolTable + i);
            cout << "BBB";
            return (pSymbolTable + i);
        }
    }
    return NULL;
}
 
BYTE* ObjFileAnalyzer::getRelativePointerToSectionData(unsigned int index) //index : 1~
{
    if(mObjData.size() == 0)
    {
        cout << "target data is empty\n";
        return NULL;
    }
     
    IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&mObjData[0]);
     
    IMAGE_SECTION_HEADER *pish = reinterpret_cast<IMAGE_SECTION_HEADER*>(pifh + 1);
     
    if(index > pifh->NumberOfSections)
    {
        cout << "getPointerToSectionData() インデックスが不正です\n";
        return NULL;
    }
     
    return (BYTE*)((pish+index-1)->PointerToRawData);
}
 
void ObjFileAnalyzer::dumpTargetFunctionData()
{
    if(mFunctionData.size() == 0)
    {
        cout << "function data is empty\n";
        return;
    }
     
    cout << hex << showbase;
    cout << "********** function raw data **************\n";
    for(int i = 0; i < mFunctionData.size(); i++)
    {
        if(i % 10 == 0)
        {
            cout << "\n";
        }
         
        cout << setw(5) << left << (int)mFunctionData[i] << " ";
    }
    cout << "\n**************Relocation data******************\n";
     
    for(int i = 0; i < mRelocationData.size(); i++)
    {
        cout << (int)mRelocationData[i] << "\n";
    }
     
     
     
    cout << "********** function raw data without relocation**************\n";
    for(int j = 0; j < (int)mRelocationData[0]; j++)
    {
        cout << setw(5) << left << (int)mFunctionData[j] << " ";
    }
    for(int i = 0; i < mRelocationData.size()-1; i++)
    {
        for(int j = 0; j < (int)mRelocationData[i+1] - (int)mRelocationData[i] - 4; j++)
        {
            cout << setw(5) << left << (int)mFunctionData[(int)mRelocationData[i]+4+j] << " ";
        }
    }
    for(int j = 0; j < mFunctionData.size() - (int)mRelocationData[mRelocationData.size()-1] - 4; j++)
    {
        cout << setw(5) << left << (int)mFunctionData[(int)mRelocationData[mRelocationData.size()-1] + 4 + j] << " ";
    }
    cout << "\n";
}
 
BYTE* ObjFileAnalyzer::searchTargetFunction(BYTE *pStart, int searchRange)
{
     
    BYTE *pTargetFunc = NULL;
     
    for(int i = 0; i < searchRange; i++)
    {
        if( memcmp(&mFunctionData[0], (pStart+i), (int)mRelocationData[0]) != 0)
        {
             
            pTargetFunc = NULL;
            continue;
        }
        if( memcmp(&mFunctionData[(int)mRelocationData[mRelocationData.size()-1] + 4], (pStart + i + (int)mRelocationData[mRelocationData.size()-1] + 4), mFunctionData.size() - ( (int)mRelocationData[mRelocationData.size()-1] + 4)) != 0)
        {
            pTargetFunc = NULL;
            continue;
        }
        for(int j = 0; j < mRelocationData.size() - 1; j++)
        {
            if( memcmp(&mFunctionData[(int)mRelocationData[j] + 4], (pStart + i + (int)mRelocationData[j] + 4), (int)mRelocationData[j+1] - ((int)mRelocationData[j] +4)) != 0)
            {
                pTargetFunc = NULL;
                break;
            }
            pTargetFunc = (BYTE*)(pStart + i);
        }
         
        if(pTargetFunc != NULL)
        {
            return pTargetFunc;
        }
    }
     
    return NULL;
}
         
 
void ObjFileAnalyzer::showSectionHeaderInfo(vector<byte>& objData)
{
    if(objData.size() == 0)
    {
        cout << "target data is empty\n";
        return;
    }
     
    IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&objData[0]);
     
    IMAGE_SECTION_HEADER *pish = reinterpret_cast<IMAGE_SECTION_HEADER*>(pifh + 1);
     
    cout << dec;
    for(int i = 0; i < pifh->NumberOfSections; i++)
    {
         
        cout << "*****" << i+1 << "th IMAGE SECTION HEADER INFO *******" << endl;
        cout << setw(30) << left << "Name (BYTE[8])" <<  ": " << (pish+i)->Name << "\n" <<
                setw(30) << left << "VirtualAddress (DWORD)" << ": " << (WORD)((pish+i)->VirtualAddress) << "\n" <<
                setw(30) << left << "SizeOfRawData (DWORD)" << ": " << (DWORD)((pish+i)->SizeOfRawData) << "\n" <<
                setw(30) << left << "PointerToRawData (DWORD)" << ": " << (DWORD)((pish+i)->PointerToRawData) << "\n" <<
                setw(30) << left << "PointerToRelocations (DWORD)" << ": " << (DWORD)((pish+i)->PointerToRelocations) << "\n" <<
                setw(30) << left << "PointerToLinenumbers (DWORD)" << ": " << (DWORD)((pish+i)->PointerToLinenumbers) << "\n" <<
                setw(30) << left << "NumberOfRelocations (WORD)" << ": " << (WORD)((pish+i)->NumberOfRelocations) << "\n" <<
                setw(30) << left << "NumberOfLinenumbers (WORD)" << ": " << (WORD)((pish+i)->NumberOfLinenumbers) << "\n" <<
                setw(30) << left << "Characteristics (DWORD)" << ": " << (DWORD)((pish+i)->Characteristics) << "\n" <<
                "\n\n";
    }
     
}
     
void ObjFileAnalyzer::showFileHeaderInfo(vector<byte>& objData)
{
    if(objData.size() == 0)
    {
        cout << "target data is empty\n";
        return;
    }
     
    IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&objData[0]);
     
    cout << "*******************IMAGE FILE HEADER INFO**********************\n";
    cout << hex << showbase;
    cout << setw(30) << left << "MACHINE (WORD)" <<  ": " << (WORD)pifh->Machine << "\n" <<
            setw(30) << left << "NumberOfSections (WORD)" << ": " << (WORD)pifh->NumberOfSections << "\n" <<
            setw(30) << left << "TimeDateStamp (DWORD)" << ": " << (DWORD)pifh->TimeDateStamp << "\n" <<
            setw(30) << left << "PointerToSymbolTable (DWORD)" << ": " << (DWORD)pifh->PointerToSymbolTable << "\n" <<
            setw(30) << left << "NumberOfSymbols (DWORD)" << ": " << (DWORD)pifh->NumberOfSymbols << "\n" <<
            setw(30) << left << "SizeOfOptionalHeader (WORD)" << ": " << (WORD)pifh->SizeOfOptionalHeader << "\n" <<
            setw(30) << left << "Characteristics (WORD)" << ": " << (WORD)pifh->Characteristics << "\n" <<
            "****************************************************************\n\n\n";
            ;
     
    return;
}
 
void ObjFileAnalyzer::showSymbolTableDataInfo(vector<byte>& objData)
{
     
 
    if(objData.size() == 0)
    {
        cout << "target data is empty\n";
        return;
    }
     
    IMAGE_FILE_HEADER *pifh = reinterpret_cast<IMAGE_FILE_HEADER*>(&objData[0]);
    DWORD dwSymbolCount = pifh->NumberOfSymbols;
    IMAGE_SYMBOL *pSymbolTable = (IMAGE_SYMBOL*)(&objData[0] + pifh->PointerToSymbolTable);
    for(int i = 0; i < dwSymbolCount; i++)
    {
        cout << "*****" << i+1 << "th SYMBOL DATA INFO *******" << endl;
        cout <<    setw(30) << left << "MACHINE (WORD)" <<  ": " << (pSymbolTable + i)->N.ShortName << "\n" <<
                setw(30) << left << "Value (DWORD)" <<  ": " << (pSymbolTable + i)->Value << "\n" <<
                setw(30) << left << "SectionNumber (SHORT)" <<  ": " << (pSymbolTable + i)->SectionNumber << "\n" <<
                setw(30) << left << "Type (WORD)" <<  ": " << (pSymbolTable + i)->Type << "\n" <<
                setw(30) << left << "StorageClass (BYTE)" <<  ": " << (int)(pSymbolTable + i)->StorageClass << "\n" <<
                setw(30) << left << "NumberOfAuxSymbols (BYTE)" <<  ": " << (int)(pSymbolTable + i)->NumberOfAuxSymbols << "\n" <<
                "\n\n";
    }
}
 
bool ObjFileAnalyzer::loadObjFile(const string filepath, vector<byte>& objData)
{
    HANDLE hFile;
    LARGE_INTEGER filesize;
    DWORD dwRead;
     
    hFile = CreateFile(
        filepath.c_str(),
        GENERIC_READ,
        FILE_SHARE_READ,
        NULL,
        OPEN_EXISTING,
        FILE_ATTRIBUTE_NORMAL,
        NULL
        );
     
    if( hFile == INVALID_HANDLE_VALUE )
    {
        cout << "CreateFile() failed\n";
        return false;
    }
     
    if( GetFileSizeEx(hFile, &filesize) == FALSE)
    {
        cout << "GetFileSizeEx() failed\n";
        CloseHandle(hFile);
        return false;
    }
     
    objData.resize((DWORD)filesize.LowPart, 0);
    if( ReadFile(hFile, &objData[0], (DWORD)filesize.LowPart, &dwRead, NULL) == FALSE )
    {
        cout << "ReadFile() failed\n";
        CloseHandle(hFile);
        return false;
    }
    CloseHandle(hFile);
     
    return true;
}
 
bool ObjFileAnalyzer::isObjFile(const string filepath)
{
    if(filepath.substr(filepath.find_last_of(".")+1) == "obj")
    {
        return true;
    }
    else
    {
        return false;
    }
}
 
void ObjFileAnalyzer::analyzeFunctionData(BYTE *pStart, IMAGE_SECTION_HEADER* pSectionHeader, IMAGE_SYMBOL *pSymbol)
{
     
    mFunctionData.clear();
    printf("pStart : %p\n", pStart);
    printf("%x\n",*pStart );
    while(*pStart != 0xC3)
    {
        mFunctionData.push_back(*pStart);
        pStart++;
    }
    mFunctionData.push_back(*pStart);
     
    BYTE* pRelocation = NULL;
    IMAGE_RELOCATION *pImageRelocation = (IMAGE_RELOCATION*)(&mObjData[0] + pSectionHeader->PointerToRelocations);
     
    for(int i = 0; i < pSectionHeader->NumberOfRelocations; i++)
    {
        if( 0 <= ( (pImageRelocation + i)->VirtualAddress - pSymbol->Value) && ( (pImageRelocation + i)->VirtualAddress - pSymbol->Value) < mFunctionData.size())
        {
            mRelocationData.push_back((BYTE*)( (pImageRelocation + i)->VirtualAddress - pSymbol->Value));
        }
    }
    return;
}
