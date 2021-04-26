package de.telekom.carrier.v1.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.telekom.carrier.v1.api.enums.AddressDescriptionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class BillAddress extends Address {

	@Builder
	public BillAddress(Long id, String name, String street, String city, Integer cityCode, Date createDate, Date updateDate, AddressDescriptionEnum addressDescription, Carrier carrier) {
		super(id, name, street, city, cityCode, createDate, updateDate, addressDescription, carrier);
		this.setAddressDescription(AddressDescriptionEnum.BILL_ADDRESS);
	}
	

	

}
