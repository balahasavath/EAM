package com.brt.ess.agnt.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.OffsetDateTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ESSAgentMonitor {
    private String processName = "ESSRec";

    public static void main(String args[]) {
        System.out.println("first start the process and health check");
        System.out.println("second check the process and start the process if not available");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Running Shutdown Hook");
            }
        });
        ESSAgentMonitor essAgentMonitor = new ESSAgentMonitor();
        essAgentMonitor.checkAndStartProcess();
    }

    public void checkAndStartProcess() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Process process = null;
                boolean isRunning = false;
                try {
                    process = Runtime.getRuntime().exec("sc query " + processName);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scanner reader = new Scanner(process.getInputStream(), "UTF-8");
                while (reader.hasNextLine()) {
                    if (reader.nextLine().contains("RUNNING")) {
                        isRunning = true;
                        break;

                    }
                } if(isRunning) {
                    System.out.println(OffsetDateTime.now() +" Process... " + processName + " is RUNNING");
                }
                 else {
                    System.out.println(OffsetDateTime.now() + " Process... " + processName + " is STOPPED WILL RESTART" );
                    try {
                       // String[] scriptStart = {"cmd.exe", "/c", "sc", "start", processName};
                         // String[] scriptStart = { "net", "start", processName};
                       // ProcessBuilder builder = new ProcessBuilder(
                              //  "cmd.exe", "/c", "sc start "+ processName);
                        //builder.redirectErrorStream(true);
                        //Process p = builder.start();
                       // Process processStart = Runtime.getRuntime().exec(scriptStart);
                       // processStart.waitFor();

                        final Process proc = Runtime.getRuntime().exec("sc start " + processName);
                        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line;
                        int state = -1;

                        while ((line = br.readLine()) != null) // searches for state in the child process output
                        {
                            int p;

                            if ((p = line.indexOf(" STATE ")) != -1)
                            {
                                if ((p = line.indexOf(" : ", p)) != -1)
                                    state = Integer.parseInt(line.substring(p + 3, p + 4));
                            }
                        }

                        int retCode = proc.waitFor();

                        if (retCode != 0)
                            System.out.println("Error code of 'sc' is : " + retCode);


                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 2000);

    }
}
