package rate.application;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rate.application.converter.RateConverter;
import rate.application.entities.Rate;
import rate.application.repository.RatesRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RateRepositoryTest {


    @Autowired
    private RatesRepository rateRepository;

    @Test
    public void whenFindByDate_thenReturnRates() {
        Date exchangeDate = new GregorianCalendar(2012, Calendar.DECEMBER, 9).getTime();
        String gbpRate = "0.234";
        Rate rate = new Rate();
        rate.setExchangeDate(exchangeDate);
        rate.setGbp(gbpRate);

        rateRepository.save(rate);

        Rate foundRate = rateRepository.findByExchangeDate(exchangeDate);

        // then
        Assert.assertEquals(foundRate.getGbp(), gbpRate);
    }

}
