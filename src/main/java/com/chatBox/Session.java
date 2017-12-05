package com.chatBox;
import Communication.UDPMessageReceiverService;
import Communication.UDPMessageSenderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class Session extends JFrame {

    private static final String ERROR_MESSAGE_SEND = "[ERROR] An error occured while trying to send message";
    private static final String NOTIFICATION_FORMAT = "[INFO] Your message has been sent to %s on port %d";

    private static final String ERROR_MESSAGE_LISTEN = "[ERROR] An error occured while trying to listen on port";

    private final ChatBoxPresenter presenter;

    JTextArea ta;

    UDPMessageReceiverService receiverService;
    Thread thread;

    public Session(ChatBoxPresenter presenter)  {

        super("Session toto");

        JPanel textAreaPane = new JPanel(new GridLayout(0, 1));
        ta = new JTextArea("", 20, 40);
        ta.setLineWrap(true);
        JScrollPane messagePane = new JScrollPane(ta);
        textAreaPane.add(messagePane);

        JButton sendButton = new JButton("Send");
        JPanel sendPanel = new JPanel();
        sendPanel.add(sendButton);

        add(textAreaPane, NORTH);
        add(sendPanel, SOUTH);

        sendButton.addActionListener(e -> sendMessage());

        this.presenter = presenter;

        receiveMessage();
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                pullThePlug();
                System.out.println("Session closed");
                e.getWindow().dispose();
            }
        });

        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void pullThePlug() {
        receiverService.terminate();
    }



    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void actualiseText(String message){
        ta.setText(ta.getText().toString().concat("\n").concat(message));
    }

    private void sendMessage(){
        try {
            UDPMessageSenderService.sendBroadcastMessage("127.0.0.1",2000, ta.getText());
            actualiseText(String.format(NOTIFICATION_FORMAT, "127.0.0.1", 2000));
        } catch (Exception exception) {
            ta.setText(ERROR_MESSAGE_SEND);
        }
    }

    private void receiveMessage(){

        try {
            receiverService = new UDPMessageReceiverService(3000, this);
            thread = new Thread(receiverService);
            thread.start();
        } catch (Exception exception) {
            ta.setText(ERROR_MESSAGE_LISTEN);
        }
    }

}
