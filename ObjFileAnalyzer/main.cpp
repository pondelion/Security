#include "obj_file_analyzer.hpp"


int main(int argc, char *argv[])
{
    wchar_t buf[100];
    swprintf_s( buf, 100, L"%s", "test");
    printf("Start\n");
 
    if(argc != 2)
    {
        cout << "Usage : " << argv[0] << ".exe [obj file path]\n";
        return 1;
    }
 
    HANDLE handle = NULL;
    handle = GetModuleHandle(NULL);
    if(handle == NULL)
    {
        return 1;
    }
     
    ObjFileAnalyzer ofa(argv[1]);
    IMAGE_SYMBOL *pTargetSymbol = NULL;
     
    pTargetSymbol = ofa.findSymbol("_printf");
     
    if(pTargetSymbol)
    {
        BYTE *pTargetFunction = NULL;
        printf("%dセクションのポインター : %p\n", pTargetSymbol->SectionNumber, ofa.getRelativePointerToSectionData(pTargetSymbol->SectionNumber));
        ofa.dumpTargetFunctionData();
        pTargetFunction = ofa.searchTargetFunction((BYTE*)((int)handle + 0x1000), 0x10000);
        if(pTargetFunction == NULL)
        {
            cout << "searchTargetFunction() failed \n";
        }
        else
        {
            printf("TargetFunction : %p\n", pTargetFunction);
            printf("printf : %p\n", printf);
        }
    }
    else
    {
        printf("シンボルが見つかりませんでした\n");
    }

    return 0;
}
