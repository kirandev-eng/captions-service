package com.bmx.comm.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bmx.comm.entity.XTPChargesTopDay;
import com.bmx.comm.entity.XTPChargesBean;

@DataJpaTest
class XTPChargesTopD3ayRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private XTPChargesTopDayRepo xtpChargesTopDayRepo;

    //@Test TODO: H2 database not supporting square brackets which is in SQL query thats why injection is not working need to do RnD on that
    void testFindAllXtpCommByAccountsAndMetal() {
        List<String> accounts = new ArrayList<>();
        accounts.add("account1");
        accounts.add("account2");
        
        XTPChargesTopDay entry1 = new XTPChargesTopDay("account1", "symbol1", BigDecimal.valueOf(100.0), "reportingClass", "LME", "USD");
        XTPChargesTopDay entry2 = new XTPChargesTopDay("account2", "symbol2", BigDecimal.valueOf(200.0), "reportingClass", "LME", "USD");
        entityManager.persist(entry1);
        entityManager.persist(entry2);
        entityManager.flush();

        List<XTPChargesBean> result = xtpChargesTopDayRepo.findAllXtpCommByAccountsAndReportingClass("reportingClass", accounts);

        assertEquals(2, result.size());
        assertEquals("account1", result.get(0).getAccountId());
        assertEquals("account2", result.get(1).getAccountId());
    }
    
}



