package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.CurrencyPrise;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalanceResDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientHistoryDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientReqDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientResDto;
import uz.ataboyev.warehouse.repository.CurrencyRepository;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static uz.ataboyev.warehouse.component.DataLoader.currencyPrise;

@RequestMapping(path = RestConstant.CURRENCY)
@RestController
@RequiredArgsConstructor
public class CurrencyController {

     private final CurrencyRepository currencyRepository;

    @GetMapping("get-currency")
    public Double currencyPrise(){
        CurrencyPrise prise = currencyRepository.findById(currencyPrise.getId()).get();
        return prise.getCurrency();
    }
    @PutMapping("put-currency")
    public Double putCurrency(@RequestBody double currencyPrice){
        try {
            CurrencyPrise prise = currencyRepository.findById(currencyPrise.getId()).get();
            prise.setCurrency(currencyPrice);
            currencyRepository.save(prise);
            return currencyPrice;
        }catch (Exception e){
            e.printStackTrace();
            return -1d;
        }
    }
}