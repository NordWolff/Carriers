package de.telekom.carrier.v1.api.enums;

public enum AddressDescriptionEnum {
	
    BILL_ADDRESS("Rechnungsanschrift"),
    CUSTOMER_ADDRESS("Kundenanschrift");

    private String label;


    AddressDescriptionEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
