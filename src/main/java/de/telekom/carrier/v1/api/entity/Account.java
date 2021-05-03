package de.telekom.carrier.v1.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telekom.carrier.v1.api.enums.AccountEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.Date;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AccountEnum description;
    private Long number;

    @ManyToOne
    @JoinColumn(name = "carrier_ref")
    @JsonIgnore
    private Carrier carrier;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
}
