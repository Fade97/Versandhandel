package de.volkswagen.f73;

public enum TaxRates {
    TAX(19.0), REDUCED_TAX(7.0);

    double percentage;

    TaxRates(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return this.percentage;
    }
}
