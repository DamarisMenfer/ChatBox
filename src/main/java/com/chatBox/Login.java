package com.chatBox;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseListener;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Login extends JFrame {

    private final ChatBoxPresenter presenter;


    public Login()  {

        super("Welcome to ChatBox!");

        setLayout(null);

        JLabel chatBoxLabel = new JLabel("ChatBox");
        chatBoxLabel.setFont(new Font(chatBoxLabel.getFont().getName(), chatBoxLabel.getFont().getStyle(), 60));
        
        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(10);
        loginLabel.setLabelFor(loginField);
        loginField.setToolTipText("Write in here your ChatBox name.");

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(10);
        passwordLabel.setLabelFor(passwordLabel);
        passwordField.setToolTipText("Write in here your password.");

        JButton loginButton = new JButton("Sign in");

        JLabel orLabel = new JLabel("or");

        JButton acountLabel = new JButton("create account");
        acountLabel.setFocusPainted(false);
        acountLabel.setMargin(new Insets(0, 0, 0, 0));
        acountLabel.setContentAreaFilled(false);
        acountLabel.setBorderPainted(false);
        acountLabel.setOpaque(false);

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
                                                .addComponent(loginButton)
                                                .addComponent(orLabel)
                                                .addComponent(acountLabel, Alignment.TRAILING)))));

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
                                                        .addComponent(loginButton)
                                                        .addComponent(orLabel)
                                                        .addComponent(acountLabel))
                                        )));

        jpanUser.setLayout(layout);

        jpanUser.setBorder(BorderFactory.createEmptyBorder(100, 80, 50, 80));

        BoxLayout layoutMain = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(layoutMain);

        add(jpanUser);

        loginField.requestFocus();

        this.presenter = new ChatBoxPresenter(this);
        loginButton.addActionListener(e -> this.presenter.onLoginClicked());

        acountLabel.addActionListener(e -> this.presenter.onCreateAccountClicked());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void afficherErreur(String message) {
        showMessageDialog(this, message, "Erreur", ERROR_MESSAGE);
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
