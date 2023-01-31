package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;

import java.util.List;

public interface TradeService {
    ApiResult<?> add(TradeReqDto tradeReqDto);

    TradeResDto getItemsByTradeId(Long tradeId);

    CustomPage<TradeResDto> getAllPageable(int page, int size, Long whId);

    TradePriceResDto generalPriceTrades(Long whId);

    List<TradeResDto> getAllTrade(Long whId);
}
