package com.fredericboisguerin.insa.calculateurprix;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

public class CalculateurPrixView extends JFrame {

    private final CalculateurPrixPresenter presenter;
    private final JFormattedTextField montantHTTextField;
    private final JFormattedTextField montantTTCTextField;

    public CalculateurPrixView() throws HeadlessException {
        super("Calculateur de prix");
        this.presenter = new CalculateurPrixPresenter(this);

        JLabel prixArticleLabel = new JLabel("Prix d'un article (€) : ");
        JTextField prixArticleTextField = new JTextField(10);
        prixArticleLabel.setLabelFor(prixArticleTextField);
        prixArticleTextField.setToolTipText("Entrez ici le montant d'un article");

        JLabel quantiteLabel = new JLabel("Quantite : ");
        JTextField quantiteTextField = new JTextField(10);
        quantiteLabel.setLabelFor(quantiteLabel);
        quantiteTextField.setToolTipText("Entrez ici la quantite");

        JLabel montantHTLabel = new JLabel("Montant HT : ");
        montantHTTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantHTTextField.setEditable(false);
        montantHTLabel.setLabelFor(montantHTTextField);

        JLabel paysLabel = new JLabel("Pays : ");
        JComboBox paysComboBox = new JComboBox();
        paysComboBox.addItem("Allemagne");
        paysComboBox.addItem("Danemark");
        paysComboBox.addItem("Belgique");
        paysComboBox.addItem("France");

        JLabel montantTTCLabel = new JLabel("Montant TTC : ");
        montantTTCTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantTTCTextField.setEditable(false);
        montantTTCLabel.setLabelFor(montantTTCTextField);

        JButton computeButton = new JButton("Calculer");
        computeButton.addActionListener(e -> this.presenter.onComputeButtonClicked(prixArticleTextField.getText(), quantiteTextField.getText(), paysComboBox.getSelectedIndex()));

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.add(prixArticleTextField);

        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(prixArticleLabel);
        labelPane.add(quantiteLabel);
        labelPane.add(paysLabel);
        labelPane.add(montantHTLabel);
        labelPane.add(montantTTCLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(prixArticleTextField);
        fieldPane.add(quantiteTextField);
        fieldPane.add(paysComboBox);
        fieldPane.add(montantHTTextField);
        fieldPane.add(montantTTCTextField);

        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, WEST);
        add(fieldPane, EAST);
        add(computeButton, SOUTH);

        prixArticleTextField.requestFocus();

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

    public void setHTValue(Float montant) {
        montantHTTextField.setValue(montant);
    }

    public void setTTCValue(float v) {
        montantTTCTextField.setValue(v);
    }
}
