package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.entity.CurrencyPrise;
import uz.ataboyev.warehouse.repository.CurrencyRepository;
import uz.ataboyev.warehouse.utils.RestConstant;

@RequestMapping(path = RestConstant.KASSA)
@RestController
@RequiredArgsConstructor
public class KassaController {

     private final CurrencyRepository currencyRepository;

    @GetMapping("get-kassa")
    public Double currencyPrise(){
        CurrencyPrise prise = currencyRepository.findAll().get(0);
        return prise.getCurrency();
    }
    @PutMapping("put-currency")
    public Double putCurrency(@RequestBody double currencyPrice){
        try {
            CurrencyPrise prise = currencyRepository.findAll().get(0);
            prise.setCurrency(currencyPrice);
            currencyRepository.save(prise);
            return currencyPrice;
        }catch (Exception e){
            e.printStackTrace();
            return -1d;
        }
    }
}
