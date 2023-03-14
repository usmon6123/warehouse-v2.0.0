package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalanceResDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientHistoryDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientReqDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientResDto;

import java.util.List;

public interface ClientService {

    List<ClientResDto> getAll();

    List<ClientResDto> getAllWorkers();

    void checkingClientByIdListOrElseThrow(List<Long> clientIdList);

    ApiResult<?> add(ClientReqDto clientReqDto);

    ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto);

    ApiResult<?> getOne(Long clientId);

    List<ClientResDto> getAllClient();

    ApiResult<?> delete(Long clientId);


    List<OptionResDto> getClientsForOption();

    ClientHistoryDto clientHistory(Long clientId);

    List<ClientBalanceResDto> getClientsBalance(Long warehouseId);

    List<ClientResDto> getOrder(Long whId);

    OptionResDto getSavdo(Long whId);

}
