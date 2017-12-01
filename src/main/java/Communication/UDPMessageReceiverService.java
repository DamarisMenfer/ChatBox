package Communication;

import java.io.IOException;
import java.net.DatagramPacket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPMessageReceiverService{

    public static void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        DatagramSocket sock = null;

        try
        {
            //1. creating a server socket, parameter is local port number
            sock = new DatagramSocket(port);

            //buffer to receive incoming data
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            //2. Wait for an incoming data
            echo("Server socket created. Waiting for incoming data...");

            sock.receive(incoming);
            byte[] data = incoming.getData();
            String s = new String(data, 0, incoming.getLength());

            //echo the details of incoming data - client ip : client port - client message
            echo(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);

        }

        catch(IOException e) {
            System.err.println("IOException " + e);
        }
    }

    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}

