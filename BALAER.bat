nssm install ESSRec "C:\\Users\\BalaResh\\Documents\\ESSRec\\ESSRec.exe" 
nssm set ESSRec AppDirectory "C:\Users\BalaResh\Documents\ESSRec"
nssm set ESSRec AppExit default Restart
nssm set ESSRec AppPriority NORMAL_PRIORITY_CLASS
nssm set ESSRec AppRestartDelay 0
nssm set ESSRec AppStdout "C:\\Users\\BalaResh\\Documents\\ESSRec\\app.log"
nssm set ESSRec AppStderr "C:\\Users\\BalaResh\\Documents\\ESSRec\\app.log"
nssm set ESSRec DisplayName ESSRec
nssm set ESSRec Start SERVICE_AUTO_START
nssm start ESSRec
