package de.telekom.carrier.v1.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bktos")
@Builder
public class Bkto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tal;
    private Long xDsl;
    private Long kvzAp;
    private Long kvzApTransferConnection;
    private Long ftth;
    private Long sdsl;
    private Long l2bsa;
    private Long l2bsaTransferConnection;
    private Long iptv;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrier_ref")
    private Carrier carrier;
}
