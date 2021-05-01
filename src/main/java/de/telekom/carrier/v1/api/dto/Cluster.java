package de.telekom.carrier.v1.api.dto;

import de.telekom.carrier.v1.api.enums.ClusterEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cluster {

    ClusterEnum cluster;

    public void addCluster(ClusterEnum clusterEnum){
        cluster = clusterEnum;
    }

}
