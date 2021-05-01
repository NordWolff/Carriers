package de.telekom.carrier.v1.api.enums;

public enum ClusterEnum {

    BEREITSTELLUNG("Bereitstellung","B"),
    ENTSTOERUNG("Entstörung","E");


    private String label;
    private String symbol;
    ClusterEnum(String label,String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getSymbol(){
        return symbol;
    }

}
