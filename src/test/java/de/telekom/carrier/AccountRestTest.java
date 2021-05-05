package de.telekom.carrier;

import de.telekom.carrier.v1.api.entity.Account;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.enums.AccountEnum;
import de.telekom.carrier.v1.api.service.AccountService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class AccountRestTest {

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

        account = Account.builder()
                .description(AccountEnum.XDSL)
                .number(87657654L)
                .createDate(new Date())
                .carrier(carrier)
                .build();
        accountService.save(account);
    }

    @Test
    void deleteTest(){
        accountService.delete(account);
        List<Account> bktoList = accountService.findAll();
        assertEquals(0,bktoList.size());
    }

    @AfterEach
    void deleteAfter() {
        accountService.deleteAll();
    }
}
