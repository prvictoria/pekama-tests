Sleep(1000)
#include <WinAPIFiles.au3>
Local Const $sPath1 = 'src/test/java/UploadFiles/zip.zip'
Local Const $fullPath = _WinAPI_GetFullPathName($sPath1)
WinWaitActive("File Upload")
Send($fullPath)
Send("{ENTER}")
Sleep(2000)
Exit