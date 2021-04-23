package de.telekom.carrier.v1.api.enums;

public enum AdditionalAgreementsEnum {
	
	    CEE_TAL("ZV CEE TAL"),
	    WEE_XDSL("ZV WEE xDSL");

	    private String label;

	    AdditionalAgreementsEnum(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }

}
