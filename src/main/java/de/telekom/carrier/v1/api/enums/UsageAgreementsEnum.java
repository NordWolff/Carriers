package de.telekom.carrier.v1.api.enums;

public enum UsageAgreementsEnum {

	    TAL("TAL"),
	    XDSL("xDSL"),
	    SDSL_B("SDSL B"),
	    FTTH("FTTH");

	    private String label;

	    UsageAgreementsEnum(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
}
