package com.chatBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;

public class ChatBoxView extends JFrame  {

    private final ChatBoxPresenter presenter;

    public ChatBoxView(ChatBoxPresenter presenter)  {

        super("ChatBox");

        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JMenuItem menuItem2;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Configuration");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        menuItem = new JMenuItem("Change login",
                KeyEvent.VK_T);
        menu.add(menuItem);

        menuItem2 = new JMenuItem("Exit",
                KeyEvent.VK_T);
        menu.add(menuItem2);


        //BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        //add(picLabel);

        JLabel utilisateurLabel = new JLabel("Utilisateur");
        utilisateurLabel.setFont(new Font(utilisateurLabel.getFont().getName(), utilisateurLabel.getFont().getStyle(), 40));

        JButton sessionLabel = new JButton("Toto");
        sessionLabel.setFocusPainted(false);
        sessionLabel.setMargin(new Insets(0, 0, 0, 0));
        sessionLabel.setContentAreaFilled(false);
        sessionLabel.setBorderPainted(false);
        sessionLabel.setOpaque(false);

        JSeparator separator = new JSeparator();

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(utilisateurLabel);
        labelPane.add(separator);
        labelPane.add(sessionLabel);

        labelPane.setBorder(BorderFactory.createEmptyBorder(100, 80, 50, 80));

        add(labelPane, WEST);

        setJMenuBar(menuBar);

        this.presenter = presenter;
        sessionLabel.addActionListener(e -> this.presenter.onSessionClicked());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
