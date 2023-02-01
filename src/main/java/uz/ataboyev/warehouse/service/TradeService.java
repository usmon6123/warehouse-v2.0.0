package uz.ataboyev.warehouse.service;

import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.payload.*;

import java.util.List;

@Service
public interface TradeService {
    ApiResult<?> add(TradeReqDto tradeReqDto);

    TradeResDto getItemsByTradeId(Long tradeId);

    CustomPage<TradeResDto> getAllPageable(int page, int size, Long whId);

    TradePriceResDto generalPriceTrades(Long whId);

    List<TradeResDto> getAllTrade(Long whId);
}
