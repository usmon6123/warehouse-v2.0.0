package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

import java.util.List;

import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_PAGE_NUMBER;
import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_PAGE_SIZE;

@RequestMapping(path = RestConstant.TRADE_CONTROLLER)
public interface TradeController {


    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid TradeReqDto tradeReqDto);

    @GetMapping("/get-one/{tradeId}")
    TradeResDto getTradeItemsOneById(@PathVariable Long tradeId);

    @GetMapping("/get-all-trade/{whId}")
    List<TradeResDto> getAllTrade(@PathVariable Long whId);

    @GetMapping("get-all-pageable/{whId}")
    CustomPage<TradeResDto> getAllPageable(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size,
                                                  @PathVariable Long whId);

    @GetMapping("general-price-trades/{whId}")
    TradePriceResDto generalPriceTrades(@PathVariable Long whId);


}
