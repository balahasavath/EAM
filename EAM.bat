nssm install ESSAGMonitor "C:\\Users\\BalaResh\\Documents\\ESSRec\\ESSAGMonitor.exe" 
nssm set ESSAGMonitor AppDirectory "C:\Users\BalaResh\Documents\ESSRec"
nssm set ESSAGMonitor AppExit default Restart
nssm set ESSAGMonitor AppPriority NORMAL_PRIORITY_CLASS
nssm set ESSAGMonitor AppRestartDelay 0
nssm set ESSAGMonitor AppStdout "C:\\Users\\BalaResh\\Documents\\ESSRec\\essagmonitor.log"
nssm set ESSAGMonitor AppStderr "C:\\Users\\BalaResh\\Documents\\ESSRec\\essagmonitor.log"
nssm set ESSAGMonitor DisplayName ESSRec
nssm set ESSAGMonitor Start SERVICE_AUTO_START
nssm start ESSAGMonitor
