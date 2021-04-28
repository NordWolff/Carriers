package de.telekom.carrier.v1.api.enums;

public enum ServiceEnum {

    BEREITSTELLUNG("Bereitstellung","B"),
    ENTSTOERUNG("Entstörung","E");

    private String label;
    private String quick;
    ServiceEnum(String label, String quick) {
        this.label = label;
        this.quick = quick;
    }

    public String getLabel() {
        return label;
    }

    public String getQuick(){
        return quick;
    }
}
