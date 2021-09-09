package rate.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rate.application.entities.Rate;
import rate.application.service.ImportRateService;

import java.util.Date;

@RestController
public class RatesController {

    @Autowired
    ImportRateService service;

    private boolean dataLoaded = false;

    @GetMapping("/loadData")
    public ResponseEntity loadData() {
        if (!dataLoaded) {
            service.loadData();
            dataLoaded = true;
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/rate/{date}")
    public ResponseEntity getRate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Rate rate = service.getRate(date);
        return rate != null ? new ResponseEntity(service.getRate(date), HttpStatus.OK) :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
