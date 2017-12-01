package com.chatBox;
import Communication.UDPMessageReceiverService;
import Communication.UDPMessageSenderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.Socket;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class Session extends JFrame {

    private static final String ERROR_MESSAGE_SEND = "[ERROR] An error occured while trying to send message";
    private static final String NOTIFICATION_FORMAT = "[INFO] Your message has been sent to %s on port %d";

    private static final String ERROR_MESSAGE_LISTEN = "[ERROR] An error occured while trying to listen on port";

    private final ChatBoxPresenter presenter;

    JTextArea ta;

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
    }


    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void sendMessage(){
        try {
            UDPMessageSenderService.sendMessageOn("127.0.0.1", 200, ta.getText());
            ta.setText(String.format(NOTIFICATION_FORMAT, "127.0.0.1", 200));
        } catch (Exception exception) {
            ta.setText(ERROR_MESSAGE_SEND);
        }
    }

    private void receiveMessage(){
        try {
            //UDPMessageReceiverService.listenOnPort(200, this);
        } catch (Exception exception) {
            ta.setText(ERROR_MESSAGE_LISTEN);
        }
    }

}
