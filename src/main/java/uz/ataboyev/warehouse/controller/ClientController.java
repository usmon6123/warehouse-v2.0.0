package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalanceResDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientHistoryDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientReqDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientResDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = RestConstant.CLIENT_CONTROLLER)
public interface ClientController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ClientReqDto clientReqDto);

    @GetMapping("/get-one/{clientId}")
    ApiResult<?> getOne(@PathVariable Long clientId);

    @GetMapping("/get-all-clients")
    List<ClientResDto> getAllClient();

    @GetMapping("/get-all-workers")
    List<ClientResDto> getAllWorkers();

    @GetMapping("/get-order/{whId}")
    List<ClientResDto> getOrder(@PathVariable Long whId);

    @GetMapping("/get-clients-for-option")
    List<OptionResDto>getClients();

    @PutMapping("/edit/{clientId}")
    ApiResult<?> edit(@PathVariable Long clientId, @RequestBody ClientReqDto clientReqDto);

    @DeleteMapping("/delete/{clientId}")
    ApiResult<?> delete(@PathVariable Long clientId);

    @GetMapping("client-history/{clientId}")
    ClientHistoryDto clientHistory(@PathVariable Long clientId);

    @GetMapping("clients-balance-by-wh-id/{warehouseId}")
    List<ClientBalanceResDto> getClientsBalance(@PathVariable Long warehouseId);

    @GetMapping("get-savda/{whId}")
    OptionResDto getSavdo(@PathVariable Long whId);

}
