package de.telekom.carrier.v1.api.enums;

public enum StatusEnum {
	
	ABGESCHLOSSEN("abgeschlossen");	
	
    private String label;
	
	StatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
