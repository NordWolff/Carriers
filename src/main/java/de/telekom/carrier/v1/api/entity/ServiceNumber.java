package de.telekom.carrier.v1.api.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import de.telekom.carrier.v1.api.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "serviceNumbers")
public class ServiceNumber {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer providerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
	@Enumerated(EnumType.STRING)
	private ProductEnum product;
	
	@ManyToOne
	@JoinColumn(name = "carrier_ref")
	@JsonIgnore
	private Carrier carrier;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		ServiceNumber serviceNumber = new ServiceNumber();

		public Builder id(Long id) {
			serviceNumber.id = id;
			return this;
		}

		public Builder providerId(Integer providerId) {
			serviceNumber.providerId = providerId;
			return this;
		}

		public Builder createDate(Date createDate) {
			serviceNumber.createDate = createDate;
			return this;
		}

		public Builder updateDate(Date updateDate) {
			serviceNumber.updateDate = updateDate;
			return this;
		}

		public Builder product(ProductEnum product) {
			serviceNumber.product = product;
			return this;
		}

		public Builder carrier(Carrier carrier) {
			serviceNumber.carrier = carrier;
			return this;
		}

		public ServiceNumber build() {
			return serviceNumber;
		}

	}

}
