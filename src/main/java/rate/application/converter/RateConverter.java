package rate.application.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import rate.application.entities.Rate;

import javax.annotation.PostConstruct;

@Component
public class RateConverter {

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void configMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Rate deserializeJSONToRate(String jsonRate) {
        Rate rate = null;
        try {
            rate = objectMapper.readValue(jsonRate, Rate.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rate;
    }

}
