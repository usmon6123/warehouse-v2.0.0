package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalanceResDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientHistoryDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientReqDto;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientResDto;
import uz.ataboyev.warehouse.service.ClientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController{

    private final ClientService clientService;

    @Override
    public ApiResult<?> add(@Valid ClientReqDto clientReqDto) {
        return clientService.add(clientReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long clientId) {
        return clientService.getOne(clientId);
    }

    @Override
    public List<ClientResDto> getAllClient() {
        return clientService.getAllClient();
    }

    @Override
    public List<OptionResDto> getClients() {
        return clientService.getClientsForOption();
    }

    @Override
    public ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto) {
        return clientService.edit(clientId,clientReqDto);
    }

    @Override
    public ApiResult<?> delete(Long clientId) {
        return clientService.delete(clientId);
    }

    @Override
    public ClientHistoryDto clientHistory(Long clientId) {
        return clientService.clientHistory(clientId);
    }

    @Override
    public List<ClientBalanceResDto> getClientsBalance(Long warehouseId) {
        return clientService.getClientsBalance(warehouseId);
    }
}
