//package uz.ataboyev.warehouse.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import uz.ataboyev.warehouse.exception.RestException;
//import uz.ataboyev.warehouse.payload.*;
//import uz.ataboyev.warehouse.repository.TradeRepository;
//import uz.ataboyev.warehouse.service.base.BaseService;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class TradeServiceImpl implements TradeService {
//
// private final TradeRepository tradeRepository;
// private final BaseService baseService;
//
//
// @Override
// public ApiResult<?> add(TradeReqDto tradeReqDto) {
//
//  checkingWhId(tradeReqDto);
//
//
//
//
//  return null;
// }
//
// @Override
// public TradeResDto getItemsByTradeId(Long tradeId) {
//  return null;
// }
//
// @Override
// public CustomPage<TradeResDto> getAllPageable(int page, int size, Long whId) {
//  return null;
// }
//
// @Override
// public TradePriceResDto generalPriceTrades(Long whId) {
//  return null;
// }
//
// @Override
// public List<TradeResDto> getAllTrade(Long whId) {
//  return null;
// }
//
// private void checkingWhId(TradeReqDto tradeReqDto) {
//  if (!baseService.existsWarehouse(tradeReqDto.getWarehouseId()))
//   throw RestException.restThrow("SAVDONI SAQLAMOQCHI BO'LGAN OMBORXONA TOPILMADI", HttpStatus.NOT_FOUND);
//
// }
//}
