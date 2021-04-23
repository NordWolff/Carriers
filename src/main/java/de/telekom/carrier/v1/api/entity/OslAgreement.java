package de.telekom.carrier.v1.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private Boolean optionaleServiceLeistungen;
    private String simple; // SIN/10101010
    private String emailSmn;
    private String hotline;
    @ElementCollection(targetClass = String.class)
    private List<String> usedHardware;
    
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ABGESCHLOSSEN;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrier_ref")
    private Carrier carrier;

}
