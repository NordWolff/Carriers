package de.telekom.carrier.v1.api.entity;

import java.util.Date;

import javax.persistence.*;

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
@Table(name = "additional_agreements")
public class AdditionalAgreement {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    @Enumerated(EnumType.STRING)
    private AdditionalAgreementsEnum additionalAgreement;

    @ManyToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
    private Carrier carrier;

}
