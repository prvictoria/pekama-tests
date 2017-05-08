#include <WinAPIFiles.au3>
Local Const $sPath1 = 'src/test/java/UploadFiles/googledoc.gdoc'
Local Const $fullPath = _WinAPI_GetFullPathName($sPath1)
WinWaitActive("File Upload")
Send($fullPath)
Send("{ENTER}")