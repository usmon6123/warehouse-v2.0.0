//package uz.ataboyev.warehouse.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import uz.ataboyev.warehouse.payload.*;
//import uz.ataboyev.warehouse.service.TradeService;
//import uz.ataboyev.warehouse.utils.RestConstant;
//
//import javax.validation.Valid;
//
//import java.util.List;
//
//import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_PAGE_NUMBER;
//import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_PAGE_SIZE;
//@RestController
//@RequiredArgsConstructor
//public class TradeControllerImpl implements TradeController{
//
//    private final TradeService tradeService;
//
//
//    @Override
//    public ApiResult<?> add(@Valid TradeReqDto tradeReqDto) {
//        return tradeService.add(tradeReqDto);
//    }
//
//    @Override
//    public TradeResDto getTradeItemsOneById(Long tradeId) {
//        return tradeService.getItemsByTradeId(tradeId);
//    }
//
//    @Override
//    public List<TradeResDto> getAllTrade(Long whId) {
//        return tradeService.getAllTrade(whId);
//    }
//
//    @Override
//    public CustomPage<TradeResDto> getAllPageable(int page, int size, Long whId) {
//        return tradeService.getAllPageable(page,size,whId);
//    }
//
//    @Override
//    public TradePriceResDto generalPriceTrades(Long whId) {
//        return tradeService.generalPriceTrades(whId);
//    }
//}
