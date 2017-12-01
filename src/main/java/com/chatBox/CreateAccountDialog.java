package com.chatBox;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class CreateAccountDialog extends JFrame {

    private final ChatBoxPresenter presenter;

    public CreateAccountDialog(ChatBoxPresenter presenter) {

        super("Create your account");

        setLayout(null);

        JLabel chatBoxLabel = new JLabel("ChatBox");
        chatBoxLabel.setFont(new Font(chatBoxLabel.getFont().getName(), chatBoxLabel.getFont().getStyle(), 60));

        JLabel loginLabel = new JLabel("Login");
        JTextField loginField = new JTextField(10);
        loginLabel.setLabelFor(loginField);
        loginField.setToolTipText("Write in here your ChatBox name.");

        JLabel passwordLabel = new JLabel("Password");
        JTextField passwordField = new JTextField(10);
        passwordLabel.setLabelFor(passwordLabel);
        passwordField.setToolTipText("Write in here your password.");

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        JPanel jpanUser = new JPanel();

        GroupLayout layout = new GroupLayout(jpanUser);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(chatBoxLabel)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(loginLabel)
                                        .addComponent(passwordLabel))
                                .addGroup(
                                        layout.createParallelGroup(LEADING)
                                                .addComponent(loginField)
                                                .addComponent(passwordField)
                                                .addComponent(okButton)
                                                .addComponent(cancelButton, GroupLayout.Alignment.TRAILING)))));

        layout.linkSize(SwingConstants.HORIZONTAL, loginLabel, passwordLabel);

        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(BASELINE)
                                .addComponent(chatBoxLabel))
                .addGroup(
                        layout.createParallelGroup(LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(
                                                layout.createParallelGroup(BASELINE)
                                                        .addComponent(loginLabel)
                                                        .addComponent(loginField))
                                        .addGroup(
                                                layout.createParallelGroup(BASELINE)
                                                        .addComponent(passwordLabel)
                                                        .addComponent(passwordField))
                                        .addGroup(
                                                layout.createParallelGroup(BASELINE)
                                                        .addComponent(okButton)
                                                        .addComponent(cancelButton))
                                )));

        jpanUser.setLayout(layout);

        jpanUser.setBorder(BorderFactory.createEmptyBorder(100, 80, 50, 80));

        BoxLayout layoutMain = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(layoutMain);

        add(jpanUser);

        this.presenter = presenter;

        okButton.addActionListener(e -> this.presenter.onOKClicked(loginField.getText(), passwordField.getText()));
        cancelButton.addActionListener(e -> this.presenter.onCancelClicked());

        loginField.requestFocus();
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
