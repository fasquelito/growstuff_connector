package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Aleks
 * Date: 28/01/14
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */
public class Wifi {


    private static final String SSID_CMD = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -I";
    private static final String CONNECTION_CMD = "networksetup -setairportnetwork en0";
    private static final String GETIP_CMD = "ifconfig en0 | grep 'inet' | cut -d\" \" -f2 | awk '{ print $1 }' | tail -1";
    private static final String PING_CMD = "ping -c 1 -t 1 ***";

    private static final String IP = "192.168.0.1";

    private Wifi() {}

    public static String getCurrentSSID() {
        Process p;
        StringBuffer output = new StringBuffer();
        String line;
        try {

            //Executing the shell command
            p = Runtime.getRuntime().exec(SSID_CMD);
            p.waitFor();

            //Extracting the SSID from the answer (on the fly)
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine())!= null) {
                line = line .replaceAll(" ", "");
                if(line.startsWith("SSID")) {
                    output.append(line.replace("SSID:", ""));
                }
            }
            //If the extraction failed then, the wifi not connected
             if(output.toString().equals("")) {
                 return null;
             }
            //Else returning the SSID
            return output.toString();

        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    public static boolean connect(String SSID, String PSK) {
        Process p;
        try {
            if(SSID == null || PSK == null) {
                return false;
            }

            //Executing the shell command
            p = Runtime.getRuntime().exec(CONNECTION_CMD + " " + SSID + " " +PSK);
            System.out.println(CONNECTION_CMD + " " + SSID + " " +PSK);
            p.waitFor();


            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (reader.readLine() != null) {
                //If the cmd did return anything then something went wrong
                return false;
            }
            return true;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }


    public static boolean isConnected() {
        for(int i = 0; i < 30 ; i++) {
            if(ping(IP)) {
                return true;
            }
        }
        return false;
    }

    private static boolean ping(String ip) {
        Process p;
        try {

            //Executing the shell command
            p = Runtime.getRuntime().exec(PING_CMD.replace("***", ip));
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ping, res = "";
            while ((ping = reader.readLine()) != null) {
                res += ping;
            }
            if(res.contains("1 packets received")) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            return false;
        }
        return false;
    }

    private static boolean getip() {
        Process p;
        try {

            //Executing the shell command
            p = Runtime.getRuntime().exec(GETIP_CMD);
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (reader.readLine() != null) {
                //If the cmd did return the ip adress then we're good
                return true;
            }
            return true;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

}
