package rate.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rate.application.connector.ExchangeRateConnector;
import rate.application.converter.RateConverter;
import rate.application.entities.Rate;
import rate.application.repository.RatesRepository;
import rate.application.utilities.DateUtilities;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImportRateServiceBean implements ImportRateService {

    @Autowired
    ExchangeRateConnector connector;

    @Autowired
    DateUtilities dateUtilities;

    @Autowired
    RateConverter rateConverter;

    @Autowired
    RatesRepository ratesRepository;

    public void loadData() {
        Thread thread = new Thread(){
            public void run(){
                loadDataMethod();
            }
        };
        thread.start();
    }

    public void loadDataMethod() {
        List<Rate> rateList = dateUtilities.getDates().parallelStream()
                .map(dt -> connector.loadRateExchange(dt))
                .map(rateConverter::deserializeJSONToRate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        ratesRepository.saveAll(rateList);
    }

    public Rate getRate(Date date) {
        return ratesRepository.findByExchangeDate(date);
    }

}
