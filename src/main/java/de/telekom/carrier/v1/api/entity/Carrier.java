package de.telekom.carrier.v1.api.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carriers")
public class Carrier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String previousName;
    private Integer carrierCode;
    @Column(nullable = false, unique = true)
    private String customerNumber; // unique und 10 Zeichen lang
    private Boolean wmsTkStarted = false;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "carrier")
    private PztConfig pztConfig;    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date serviceCreateDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
    
    @OneToMany(mappedBy = "carrier")
    private List<AdditionalAgreement> additionalAgreements;  // ZusatzVereinbarungen
    @OneToMany(mappedBy = "carrier")
    private List<UsageAgreement> usageAgreements;  // NutzungsVerträge
    @OneToMany(mappedBy = "carrier",fetch = FetchType.EAGER)
    private List<ServiceNumber> serviceNumbers; // ProviderID with Product
    @OneToMany(mappedBy = "carrier")
    private List<Address> addresses; // Rechnungsadresse und Kundenanschrift
    @OneToMany(mappedBy = "carrier")
    private List<Account> accounts;
    
    @OneToOne(mappedBy = "carrier")
    private OslAgreement oslAgreements;
    @OneToOne(mappedBy = "carrier")
    private ScCcAgreement scCcAgreements;
    @OneToOne(mappedBy = "carrier")
    private Contact contact;
    
    public int getCarrierNameCharacterLength() {
        return this.name.length();
      }

//    public static Builder builder() {
//        return new Builder();
//    }
//
//      public static class Builder {
//
//        Carrier carrier = new Carrier();
//
//        public Builder id(Long id) {
//            carrier.id = id;
//            return this;
//        }
//
//        public Builder name(String name) {
//            carrier.name = name;
//            return this;
//        }
//
//        public Builder previousName(String previousName) {
//            carrier.previousName = previousName;
//            return this;
//        }
//
//        public Builder carrierCode(Integer carrierCode) {
//            carrier.carrierCode = carrierCode;
//            return this;
//        }
//
//        public Builder customerNumber(String customerNumber) {
//            carrier.customerNumber = customerNumber;
//            return this;
//        }
//
//        public Builder wmsTkStarted(boolean wmsTkStarted) {
//            carrier.wmsTkStarted = wmsTkStarted;
//            return this;
//        }
//
//        public Builder pztConfig(PztConfig pztConfig) {
//            carrier.pztConfig = pztConfig;
//            return this;
//        }
//
//        public Builder serviceCreateDate(Date serviceCreateDate) {
//            carrier.serviceCreateDate = serviceCreateDate;
//            return this;
//        }
//
//        public Builder updateDate(Date updateDate) {
//            carrier.updateDate = updateDate;
//            return this;
//        }
//
//        public Builder additionalAgreements(Set<AdditionalAgreement> additionalAgreements) {
//            carrier.additionalAgreements = additionalAgreements;
//            return this;
//        }
//
//        public Builder serviceNumbers(Set<ServiceNumber> serviceNumbers){
//            carrier.serviceNumbers = serviceNumbers;
//            return this;
//        }
//
//        public Carrier build() {
//          return carrier;
//        }
//      }

}
