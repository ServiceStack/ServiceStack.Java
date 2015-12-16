SET SRC_DIR=..\src\AndroidClient\android\src\main\java\net\servicestack

RMDIR %SRC_DIR%\client /s /q
RMDIR %SRC_DIR%\func /s /q

XCOPY /E ..\src\AndroidClient\client\src\main\java\net\servicestack %SRC_DIR%
