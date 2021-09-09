package rate.application.repository;

import org.springframework.data.repository.CrudRepository;
import rate.application.entities.Rate;

import java.util.Date;

public interface RatesRepository extends CrudRepository<Rate, Long> {

    Rate findByExchangeDate(Date id);
}
