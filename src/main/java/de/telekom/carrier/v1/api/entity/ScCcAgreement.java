package de.telekom.carrier.v1.api.entity;

import java.util.Date;
import java.util.Set;

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

import de.telekom.carrier.v1.api.enums.ConnectionTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scCcAgreements")
@Builder
public class ScCcAgreement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Boolean courtesyCall;
    private Boolean searchCall;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate = new Date();
    private String region;
    private String description;    
   
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<ConnectionTypeEnum> products;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrier_ref")
    private Carrier carrier;

}
