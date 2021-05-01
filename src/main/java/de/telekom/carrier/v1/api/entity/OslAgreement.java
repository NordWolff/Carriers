package de.telekom.carrier.v1.api.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telekom.carrier.v1.api.enums.ClusterEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import de.telekom.carrier.v1.api.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "oslAgreements")
public class OslAgreement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stateDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate = new Date();
    private Boolean optionalServiceL = true;
    private String oslAgbVersion; //OSL-AGB Stand 2017
    private String simple; // SIN/10101010
    private String emailSmn;
    private String hotline;
    @ElementCollection(targetClass = ClusterEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ClusterEnum> tal;
    @ElementCollection(targetClass = ClusterEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ClusterEnum> dsl;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> usedHardware;
    
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ABGESCHLOSSEN;
    
    @OneToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
    private Carrier carrier;

    @Override
    public String toString() {
        return "OslAgreement{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", stateDate=" + stateDate +
                ", updateDate=" + updateDate +
                ", optionalServiceL=" + optionalServiceL +
                ", oslAgbVersion='" + oslAgbVersion + '\'' +
                ", simple='" + simple + '\'' +
                ", emailSmn='" + emailSmn + '\'' +
                ", hotline='" + hotline + '\'' +
                ", status=" + status +
                ", usedHardware=" + usedHardware.size()+
                ", tal=" + "size:"+tal.size()+
                ", dsl=" + "size:"+dsl.size()+
                '}';

    }

    public void addUsedHardware(String hardware){
        this.usedHardware.add(hardware);
    }

    public void addClusterDsl(ClusterEnum clusterEnum){
        this.dsl.add(clusterEnum);
    }
    public void addClusterTal(ClusterEnum clusterEnum){
        this.tal.add(clusterEnum);
    }
}
