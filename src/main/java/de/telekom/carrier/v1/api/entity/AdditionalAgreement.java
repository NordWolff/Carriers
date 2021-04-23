package de.telekom.carrier.v1.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import de.telekom.carrier.v1.api.enums.AdditionalAgreementsEnum;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "additionalAgreements")
public class AdditionalAgreement {
	
    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
    @Enumerated(value = EnumType.STRING)
    private AdditionalAgreementsEnum additionalAgreement;
    
    @ManyToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
    private Carrier carrier;

}
