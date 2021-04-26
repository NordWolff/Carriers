package de.telekom.carrier.v1.api.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telekom.carrier.v1.api.enums.AddressDescriptionEnum;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Address {
	
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String street;
    private String city;
    private Integer cityCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
    @Enumerated(EnumType.STRING)
    private AddressDescriptionEnum addressDescription;
    
    @ManyToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
    private Carrier carrier;

}
