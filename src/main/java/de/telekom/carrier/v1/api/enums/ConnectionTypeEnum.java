package de.telekom.carrier.v1.api.enums;

public enum ConnectionTypeEnum {
	
    XDSL_WIA("xDSL (WIA)"),
    XDSL_IPBSA("xDSL (IP-BSA)"),
    L2BSA("L2-BSA"),
    TAL("TAL");


    private String label;
    ConnectionTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
