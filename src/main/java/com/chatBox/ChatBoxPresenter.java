package com.chatBox;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ChatBoxPresenter {

    private final Login loginView;
    private ChatBoxView chatBoxView;
    private CreateAccountDialog createAccountDialog;

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private String fileName = "/home/mendesfe/POO/Chat Box/Logins.csv";

    public ChatBoxPresenter(Login loginView) {
        this.loginView = loginView;
    }

    public void onLoginClicked() {
        loginView.setVisible(false);
        chatBoxView = new ChatBoxView(this);
        chatBoxView.getContentPane().setBackground(Color.WHITE);
        chatBoxView.display();
    }

    public void onSessionClicked() {
        Session sessionView = new Session(this);
        sessionView.getContentPane().setBackground(Color.WHITE);
        sessionView.display();
    }

    public void onCreateAccountClicked() {
        loginView.setVisible(false);
        createAccountDialog = new CreateAccountDialog(this);
        createAccountDialog.getContentPane().setBackground(Color.WHITE);
        createAccountDialog.display();
    }

    public void onOKClicked(String login, String password) {
        //Verifie si il est unique.
        //Trouver prochaine identifiant.

        saveLogin(login, password);


        createAccountDialog.setVisible(false);
        loginView.setVisible(true);
    }

    public void onCancelClicked() {
        createAccountDialog.setVisible(false);
        loginView.setVisible(true);
    }

    public void saveLogin(String login, String password) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            fileWriter.append("1");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(login);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(password);
            fileWriter.append(NEW_LINE_SEPARATOR);

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

}
