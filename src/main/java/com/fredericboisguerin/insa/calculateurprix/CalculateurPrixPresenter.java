package com.fredericboisguerin.insa.calculateurprix;

import javax.swing.*;

public class CalculateurPrixPresenter {
    private final CalculateurPrixView calculateurPrixView;

    public CalculateurPrixPresenter(CalculateurPrixView calculateurPrixView) {
        this.calculateurPrixView = calculateurPrixView;
    }

    public void onComputeButtonClicked(String montantArticleAsText, String quantite, JFormattedTextField montantHTTextField, JFormattedTextField montantTTCTextField, Integer index) {

        Float montantArticle = Float.valueOf(montantArticleAsText);
        Integer quantInt = Integer.valueOf(quantite);

        Float montant = montantArticle*quantInt;

        montantHTTextField.setValue(montant);

        float taxes = 0;

        switch (index){
            case 0:
                taxes = 19;
                break;
            case 1:
                taxes = 25;
                break;
            case 2:
                taxes = 21;
                break;
            case 3:
            default:
                taxes = 20;
        }

        montantTTCTextField.setValue(montant*(taxes/100+1));
    }
}
