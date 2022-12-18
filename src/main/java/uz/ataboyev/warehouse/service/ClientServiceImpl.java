package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.clientDtos.*;
import uz.ataboyev.warehouse.repository.ClientRepository;
import uz.ataboyev.warehouse.repository.OrderItemRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final OrderItemRepository orderItemRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> add(ClientReqDto clientReqDto) {

        checkingClientByPhoneNumberOrElseThrow(clientReqDto.getPhoneNumber(),clientReqDto.getName());

        Client client = Client.make(clientReqDto);

        saveClient(client);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto, "success aded");
    }

    @Override
    public ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto) {

        //CLIENTNI BAZADAN OLIB KELADI TOPOLMASA THROW
        Client client = baseService.getClientById(clientId);

        //YANGI TEL RAQAM BAZADA MAVJUD BO'LSA THROW
        if (clientRepository.existsByPhoneNumberAndIdNot(clientReqDto.getPhoneNumber(), client.getId()))
            throw new RestException("Yangi kiritgan raqamiz bazada mavjudligi uchun boshqa raqam kiriting!", HttpStatus.CONFLICT);

        Client saveClient = Client.updateClient(client, clientReqDto);

        saveClient(saveClient);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto);
    }

    @Override
    public ApiResult<?> getOne(Long clientId) {

        Client client = baseService.getClientById(clientId);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto);
    }

    @Override
    public List<ClientResDto> getAllClient() {
        List<Client> clientList = clientRepository.findAll();
        return mapClients(clientList);
    }

    @Override
    public List<OptionResDto> getClientsForOption() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream().map(OptionResDto::make).collect(Collectors.toList());
    }

    @Override
    public ClientHistoryDto clientHistory(Long clientId) {

        List<OrderItem> clientItems = orderItemRepository.findAllByOrder_ClientId(clientId);

        return mapClientHistoryDto(clientItems);
    }

    @Override
    public List<ClientBalanceResDto> getClientsBalance(Long warehouseId) {
        List<ClientBalance> allClientBalance = clientRepository.getALLClientBalance(warehouseId);
        return ClientBalanceResDto.makeList(allClientBalance);
    }

    @Override
    public ApiResult<?> delete(Long clientId) {

        //MIJOZNING ORDERLARI BOR BOLSA OCHIRMAYMIZ
        baseService.checkOrdersOfClient(clientId);

        if (!baseService.existsClientById(clientId)) {
            throw RestException.restThrow("o'chirmoqchi bo'lgan mijozis bazada mavjudmas");
        }

        clientRepository.deleteById(clientId);
        return ApiResult.successResponse("Mijoz o'chirildi");
    }

    @Override
    public List<ClientResDto> getAll() {
        List<Client> clientList = clientRepository.findAll();
        return mapClients(clientList);
    }



    @Override
    public void checkingClientByIdListOrElseThrow(List<Long> clientIdList) {
        for (Long id : clientIdList) {
            if (!clientRepository.existsById(id))
                throw new RestException("Bu mijozni biz bazadan topa olmadik", HttpStatus.NOT_FOUND);
        }
    }
//-----------------------------HELPER METHOD-------------------------------------

    private ClientHistoryDto mapClientHistoryDto(List<OrderItem> clientItems) {
        Double sum = 0D;
        Double dollar = 0D;
        List<ClientOrderDto> list = new ArrayList<>();
        for (OrderItem clientItem : clientItems) {

            ClientOrderDto clientOrderDto = mapClientOrderDto(clientItem);

            if (clientItem.getCurrencyType().equals(CurrencyTypeEnum.SUM))
                sum += clientOrderDto.getPrice()!=null?clientOrderDto.getPrice():0D;
            else dollar += clientOrderDto.getPrice()!=null?clientOrderDto.getPrice():0D;

            list.add(clientOrderDto);
        }

        return new ClientHistoryDto(list, sum, dollar);
    }

    private ClientOrderDto mapClientOrderDto(OrderItem orderItem) {

        String dateFormat = baseService.timestampToString_dd_MM_yyyy(orderItem.getUpdatedAt());

        return new ClientOrderDto(
                dateFormat,
                orderItem.getProduct().getCategory().getName(),
                orderItem.getProduct().getName(),
                orderItem.getProduct().getCode(),
                orderItem.getCount(),
                orderItem.getAmount(),
                orderItem.getCurrencyType(),
                orderItem.getAmount() * orderItem.getCount(),
                orderItem.getPayTypeEnum()
        );
    }

    private void checkingClientByPhoneNumberOrElseThrow(String phoneNumber,String name) {
        if (clientRepository.existsByPhoneNumberAndName(phoneNumber,name))
            throw RestException.restThrow("Bu raqamli mijoz bazada mavjud", HttpStatus.CONFLICT);
    }

    private void saveClient(Client client) {
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Client not saved");
        }
    }


    //CLIENTDAN DTO YASABERADI
    private ClientResDto mapClient(Client client) {
        return ClientResDto.make(client);
    }

    private List<ClientResDto> mapClients(List<Client> clients) {
        return clients.stream().map(ClientResDto::make).collect(Collectors.toList());

    }


}
