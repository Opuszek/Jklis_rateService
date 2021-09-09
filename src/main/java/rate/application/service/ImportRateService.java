package rate.application.service;

import rate.application.entities.Rate;

import java.util.Date;

public interface ImportRateService {

    public void loadData();

    public Rate getRate(Date date);

}
