package Communication;

import com.chatBox.Session;

import java.io.IOException;
import java.net.DatagramPacket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPMessageReceiverService implements Runnable{

    private static int port;
    private Session session;
    private volatile boolean running = true;
    DatagramSocket sock = null;

    public UDPMessageReceiverService(int port, Session session) {
        this.port = port;
        this.session = session;
    }

    public void echo(String msg)
    {
        this.session.actualiseText(msg);
    }

    public void terminate() {
        running = false;
        sock.close();
    }


    @Override
    public void run() {

        try {
            sock = new DatagramSocket(port);
            while (running) {
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
        } catch (SocketException e) {
            System.err.println("IOException " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

