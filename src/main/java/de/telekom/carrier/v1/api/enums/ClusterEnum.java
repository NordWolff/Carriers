package de.telekom.carrier.v1.api.enums;

public enum ClusterEnum {
	
	BEREITSTELLUNG("Bereitstellung"),
	ENTSTOERUNG("Entstörung");
	
	private String label;
	
	ClusterEnum(String string) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	

}
