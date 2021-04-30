package de.telekom.carrier;

import de.telekom.carrier.v1.api.enums.AdditionalAgreementsEnum;
import de.telekom.carrier.v1.api.enums.ProductEnum;
import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.ServiceNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.telekom.carrier.v1.api.service.AdditionalAgreementService;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.ServiceNumberService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
@ActiveProfiles("test")
class CarriersApplicationTests {

	@Autowired
	private CarrierService carrierService;

	@Autowired
	private ServiceNumberService serviceNumberService;

	@Autowired
	private AdditionalAgreementService additionalAgreementService;

	List<Carrier> carrierList = new ArrayList<>();
	List<AdditionalAgreement> additionalAgreementList = new ArrayList<>();
	List<ServiceNumber> serviceNumberList = new ArrayList<>();
	Random ran = new Random();
	int counter = 20;

	@BeforeEach
	void carrierLoads() {
		for (int i =0; i < counter; i++) {
			int bound = 1;
			Carrier carrier = Carrier.builder()
					.customerNumber(RandomString.make(12))
					.carrierCode(ran.nextInt(402+i))
					.name(RandomString.make(12))
					.wmsTkStarted(true)
					.build();
			carrierList.add(carrier);
			carrierService.save(carrier);
		}
	}

	@BeforeEach
	void additionalAgreementsLoad() {
		carrierList.forEach(carrier -> {
			AdditionalAgreement	additionalAgreement = AdditionalAgreement.builder()
					.additionalAgreement(AdditionalAgreementsEnum.CEE_TAL)
					.carrier(carrier)
					.build();
			additionalAgreement = additionalAgreementService.save(additionalAgreement);
			additionalAgreementList.add(additionalAgreement);
		});
	}

	@BeforeEach
	void serviceNumberLoads() {
		AtomicReference<Integer> bound = new AtomicReference<>(1);
		carrierList.forEach(carrier -> {
			ServiceNumber serviceNumber = ServiceNumber.builder()
					.carrier(carrier)
					.product(ProductEnum.ADSL_SA)
					.providerId(ran.nextInt(bound.get()+1047))
					.build();
			serviceNumber = serviceNumberService.save(serviceNumber);
			serviceNumberList.add(serviceNumber);
		});
	}


	void carrierFindAll() {
		AtomicReference<Integer> count = new AtomicReference<>(0);
		carrierService.findAll().forEach(expected -> {
			assertEquals(expected.getName(),carrierList.get(count.get()).getName());
			count.getAndSet(count.get()+1);
		});
	}


	void carrierFindById() {
//	Carrier resultCarrier =	carrierService.findById(carrier.getId()).orElseThrow();
//		assertEquals(resultCarrier.getName(),carrier.getName());
	}



	void serviceNumberFindAll() {
		serviceNumberService.findAll().forEach(sn -> {
			assertEquals(sn.getProviderId(),serviceNumberList.get(sn.getId().intValue()-(counter+1)).getProviderId());
		});
	}


	void serviceNumberFindById() {
//		ServiceNumber resultServiceNumber = serviceNumberService.findById(serviceNumber.getId()).orElseThrow();
//		assertEquals(resultServiceNumber.getProviderId(),serviceNumber.getProviderId());
	}

	@AfterEach
	void deleteAll() {
		serviceNumberService.deleteAll();
		carrierService.deleteAll();
		additionalAgreementService.deleteAll();
	}

}
