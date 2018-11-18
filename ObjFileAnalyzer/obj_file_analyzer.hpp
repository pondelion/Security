#pragma once
#include <windows.h>
#include <iostream>
#include <iomanip>
#include <string>
#include <vector>
#include <wchar.h>
using namespace std;
 

class ObjFileAnalyzer
{
    vector<byte> mObjData;
    vector<byte> mFunctionData;
    vector<byte*> mRelocationData;  // &mFunctionData[0]からの相対位置
    WORD mNumberOfSections;
    DWORD mNumberOfSymbols;
    IMAGE_SYMBOL *mpSymbolTable;
    IMAGE_SECTION_HEADER *mpSectionTable;
    vector<string> mSymbolNames;
     
public:
    ObjFileAnalyzer(const string filepath);
    virtual ~ObjFileAnalyzer();
    virtual void analyzeFileHeader(vector<byte>& objData);
    virtual void analyzeSectionHeader(vector<byte>& objData);
    virtual void analyzeSymbolTableData(vector<byte>& objData);
    void showSymbolNames() const;
    IMAGE_SYMBOL* findSymbol(const string symbolName);
    BYTE* getRelativePointerToSectionData(unsigned int index);
    void dumpTargetFunctionData();
    BYTE* searchTargetFunction(BYTE *pStart, int searchRange);
    static void showFileHeaderInfo(vector<byte>& objData);
    static void showSectionHeaderInfo(vector<byte>& objData);
    static void showSymbolTableDataInfo(vector<byte>& objData);
    static bool loadObjFile(const string filepath, vector<byte>& objData);
    static bool isObjFile(const string filepath);
 
private:
    void analyzeFunctionData(BYTE *pStart, IMAGE_SECTION_HEADER* pSectionHeader, IMAGE_SYMBOL *pSymbol);
};
