Sleep(1000)
#include <WinAPIFiles.au3>
Local Const $sPath1 = "src/test/java/UploadFiles/"&$CmdLine[1]
Local Const $fullPath = _WinAPI_GetFullPathName($sPath1)
WinWaitActive("File Upload")
Send($fullPath)
Send("{ENTER}")
Sleep(1000)

Func kill()
   Exit 0
EndFunc
Exit