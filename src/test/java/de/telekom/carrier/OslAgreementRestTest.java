package de.telekom.carrier;

import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.OslAgreement;
import de.telekom.carrier.v1.api.enums.ClusterEnum;
import de.telekom.carrier.v1.api.enums.StatusEnum;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.OslAgreementService;
import lombok.extern.java.Log;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
@ActiveProfiles("test")
@Log
public class OslAgreementRestTest {

    @Autowired
    CarrierService carrierService;

    @Autowired
    OslAgreementService oslAgreementService;

    Carrier carrier = new Carrier();
    OslAgreement oslAgreement = new OslAgreement();

    @BeforeEach
    void init(){
      Carrier  initCarrier = Carrier.builder()
                .customerNumber(RandomString.make(12))
                .carrierCode(9542)
                .name(RandomString.make(12))
                .wmsTkStarted(true)
                .build();
       carrier = carrierService.save(initCarrier);

        Set<ClusterEnum> serviceEnums = new HashSet<>();
        serviceEnums.add(ClusterEnum.ENTSTOERUNG);

        Set<String> usedHardwareSet = new HashSet<>();
        usedHardwareSet.add("Speedport");

        OslAgreement initOslAgreement = OslAgreement.builder()
                .carrier(carrier)
                .createDate(new Date())
                .hotline("08003301000")
                .emailSmn("email@mail.de")
                .oslAgbVersion("ja")
                .simple("SIN/213647")
                .status(StatusEnum.ABGESCHLOSSEN)
                .tal(serviceEnums)
                .dsl(serviceEnums)
                .usedHardware(usedHardwareSet)
                .build();

       oslAgreement = oslAgreementService.save(initOslAgreement);
    }

    @Test
    void logOslAgreementOutputTest(){
        log.info(oslAgreement.toString());
        log.info(oslAgreementService.findById(1L).get().toString());
    }

    @Test
    void testRetrieveByHardware(){
        int count = oslAgreementService.retrieveByHardware("Speedport").size();
        log.info("Found Element: "+count);
        assertEquals(0,count);
    }

    @Test
    void findByIdTest(){
        AtomicReference<String> responseHardware = new AtomicReference<>("");
        OslAgreement response = oslAgreementService.findById(1L).get();
        response.getUsedHardware().forEach(hardware -> {
            responseHardware.set(hardware);
        });
        log.info("Hardware from typ found: "+responseHardware.get());
        assertEquals("Speedport", responseHardware.get());
    }



    void clearConnection(){
        oslAgreementService.deleteAll();
        carrierService.deleteAll();
    }

}
