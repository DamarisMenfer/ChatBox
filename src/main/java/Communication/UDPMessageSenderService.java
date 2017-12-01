package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class UDPMessageSenderService {

    public static void sendMessageOn(String ipAddress, int port, String message) throws Exception {
        DatagramSocket sock = null;

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            sock = new DatagramSocket();
            InetAddress host = InetAddress.getByName(ipAddress);
            byte[] b = message.getBytes();
            DatagramPacket  dp = new DatagramPacket(b , b.length , host , port);
            sock.send(dp);
            sock.close();
        }

        catch(IOException e)
        {
            throw new UnsupportedOperationException();
        }

    }
}
