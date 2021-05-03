package de.telekom.carrier.v1.api.enums;

public enum AccountEnum {

    TAL("Tal"),
    XDSL("xDSL"),
    KVZAP("KvzAp"),
    KVZAP_TRANSFER_CONNECTION("KvzApTransferConnection"),
    FTTH("FTTH"),
    SDSL("SDSL"),
    L2BSA("L2BSA"),
    L2BSA_TRANSFER_CONNECTION("L2BSATransferConnection"),
    IPTV("IPTV");

    private String label;
    AccountEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
