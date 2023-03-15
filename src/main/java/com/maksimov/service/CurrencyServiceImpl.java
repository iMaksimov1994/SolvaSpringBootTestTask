package com.maksimov.service;

import com.maksimov.model.Currency;
import com.maksimov.model.CurrencyData;
import com.maksimov.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyRepository currencyRepository;

    @Autowired
    public void setCurrencyDataRepository(CurrencyRepository currencyDataRepository) {
        this.currencyRepository = currencyDataRepository;
    }

    @Override
    public void addCurrency(CurrencyData currencyData) {
        ArrayList<Currency> values = currencyData.getValues();
        if (values.size() != 0) {
            this.currencyRepository.save(values.get(0));
        }
    }
}
