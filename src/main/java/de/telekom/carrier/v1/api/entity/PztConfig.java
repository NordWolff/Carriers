package de.telekom.carrier.v1.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import de.telekom.carrier.v1.api.enums.UsageAgreementsEnum;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "pztConfigs")
public class PztConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
	private List<UsageAgreementsEnum> wmsTkConfig;
	
    @OneToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
	private Carrier carrier;

}
