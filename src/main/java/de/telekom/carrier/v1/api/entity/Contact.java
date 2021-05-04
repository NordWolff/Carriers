package de.telekom.carrier.v1.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contacts")
public class Contact {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailSmn;
    private String emailDialog;
    private String emailAlternate;
    private String phone;
    private String fax;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date updateDate;
    
    @OneToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
    private Carrier carrier;


}
