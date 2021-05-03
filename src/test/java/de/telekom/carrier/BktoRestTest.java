package de.telekom.carrier;

import de.telekom.carrier.v1.api.entity.Account;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.service.AccountService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BktoRestTest {

    @Autowired
    AccountService accountService;

    @Autowired
    CarrierService carrierService;

    Account account = new Account();
    Carrier carrier = new Carrier();

    @BeforeEach
    void initTest(){

        Carrier carrier = Carrier.builder()
                .customerNumber(RandomString.make(12))
                .carrierCode(9542)
                .name(RandomString.make(12))
                .wmsTkStarted(true)
                .build();
        carrierService.save(carrier);

//        bkto = Account.builder().ftth(8765432L)
//                .createDate(new Date())
//                .iptv(87657654L)
//                .kvzAp(51423987L)
//                .kvzApTransferConnection(14265874L)
//                .l2bsa(25416987L)
//                .l2bsaTransferConnection(47841239L)
//                .sdsl(98754126L)
//                .tal(254178944L)
//                .xDsl(65231478L)
//                .carrier(carrier)
//                .build();
//        accountService.save(bkto);
    }

//    @Test
//    void deleteTest(){
//        accountService.delete(bkto);
//        List<Account> bktoList = accountService.findAll();
//        assertEquals(0,bktoList.size());
//    }

    @AfterEach
    void deleteAfter() {
        accountService.deleteAll();
    }
}
