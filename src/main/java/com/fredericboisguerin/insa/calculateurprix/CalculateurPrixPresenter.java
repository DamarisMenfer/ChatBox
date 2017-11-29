package com.fredericboisguerin.insa.calculateurprix;

public class CalculateurPrixPresenter {

    private final CalculateurPrixView calculateurPrixView;

    public CalculateurPrixPresenter(CalculateurPrixView calculateurPrixView) {
        this.calculateurPrixView = calculateurPrixView;
        calculateurPrixView.fillComboBox(Country.values());
    }

    public void onComputeButtonClicked(String montantArticleAsText, String quantite, Country country) {

        Float montantArticle = Float.valueOf(montantArticleAsText);
        Integer quantInt = Integer.valueOf(quantite);

        Float montant = montantArticle*quantInt;

        calculateurPrixView.setHTValue(montant);
        calculateurPrixView.setTTCValue(montant*(country.getTaxRate()/100+1));
    }
}
