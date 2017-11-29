package com.fredericboisguerin.insa.calculateurprix;

public enum Country {

    BELGIQUE(0.24),FRANCE(0.2), LUXEMBOURG(0.05);

    private final double taxRate;

    Country(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
