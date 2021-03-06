package de.telekom.carrier.v1.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OslAgreementCreationDto {

    List<Hardware> hardwareList = new ArrayList<>();
    List<Cluster> clusterList = new ArrayList<>();

    public void addUsedHardware(Hardware hardware){
        this.hardwareList.add(hardware);
    }

    public void addOslCluster(Cluster cluster){
        this.clusterList.add(cluster);
    }
}
