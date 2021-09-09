package rate.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rate.application.converter.RateConverter;
import rate.application.entities.Rate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateConverterTest {

    private final String response = "{\"success\":true,\"timestamp\":1630540799,\"historical\":true,\"base\":\"EUR\",\"date\":\"2021-09-01\",\"rates\":{\"GBP\":0.859735,\"USD\":1.183996,\"HKD\":9.207996}}";
    private final String gbp = "0.859735";
    private final String hkd = "9.207996";
    private final String usd = "1.183996";

    @Autowired
    RateConverter rateConverter;

    @Test
    public void whenGivenJSONRepresentation_convertToEntity() {
        Rate rate = rateConverter.deserializeJSONToRate(response);
        Assert.assertEquals(rate.getGbp(), gbp);
        Assert.assertEquals(rate.getHkd(), hkd);
        Assert.assertEquals(rate.getUsd(), usd);
    }
}
