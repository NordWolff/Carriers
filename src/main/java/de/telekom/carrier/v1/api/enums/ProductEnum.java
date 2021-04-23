package de.telekom.carrier.v1.api.enums;

public enum ProductEnum {
	
    ADSL_SA("ADSL SA"),
    VDSL_SA("VDSL SA"),
    SDSL_B("SDSL B"),
    KVZ_AP_VDSL_SA("KVZ-AP VDSL-SA"),
    L2BSA_ADSL_SA("L2BSA ADSL SA"),
    L2BSA_VDSL_SA("L2BSA VDSL SA"),
    L2BSA_SDSL_B("L2BSA SDSL B"),
    Layer2BSA_FTTH("Layer2BSA FTTH"),
    L2_BSA_FTTH("L2-BSA FTTH"),
    FTTH_SA_50000("FTTH SA 50000"),
    FTTH_SA_100000("FTTH SA 100000"),
    FTTH_SA_200000("FTTH SA 200000"),
    VDSL_SA_QoS_4K("VDSL-SA  QoS 4K"),
    TAL("TAL");


    private String label;
    ProductEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
