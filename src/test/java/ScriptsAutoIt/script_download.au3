AutoItSetOption("WinTitleMatchMode", "2")
WinWait("File Download")
$title = WinGetTitle("File Download")
WinWaitActive($title)
If @error Then
    ConsoleWrite("Something went wrong")
    Exit
EndIf
Send("s")
WinWait("Save As")
$title = WinGetTitle("Save As")

WinWaitActive($title)
$filename = ControlGetText($title, "", "Edit1")
$fullpath = "C:\Users\Documents\Vendor.html"
ControlSetText($title, "", "Edit1", $fullpath)
Send("{ENTER}")
ConsoleWrite($fullpath)
$title = WinGetTitle("Download Complete")

WinWaitActive($title)
Send("{ENTER}")
WinClose($title)