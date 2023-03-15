package com.maksimov.schedulers;

import com.maksimov.model.CurrencyData;
import com.maksimov.service.CurrencyService;
import com.maksimov.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyDataSchedulers {
    private CurrencyService currencyService;

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @Scheduled(fixedRate = 24 * 3600 * 1000)
    public void updateCurrencyData() {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyData result = restTemplate.getForObject(Constants.CURRENCY_API, CurrencyData.class);
        this.currencyService.addCurrency(result);
    }
}
