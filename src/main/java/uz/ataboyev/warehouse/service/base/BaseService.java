package uz.ataboyev.warehouse.service.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.*;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.clientDtos.ClientHistoryDto;
import uz.ataboyev.warehouse.payload.clientDtos.ClientOrderDto;
import uz.ataboyev.warehouse.payload.clientDtos.OrderItemByOrderId;
import uz.ataboyev.warehouse.repository.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BaseService {

    private final OrderItemRepository orderItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    public static final Double minus1 = -1D;


    public Warehouse getWarehouseByIdElseThrow(Long whId) {
        return warehouseRepository.findById(whId).orElseThrow(() -> RestException.notFound("Warehouse not found"));
    }

    public boolean existsWarehouse(Long wHId) {
        return warehouseRepository.existsById(wHId);
    }

    public boolean existsWarehouseByCompId(Long compId) {
        return warehouseRepository.existsByCompanyId(compId);
    }

    public boolean existsCompany(Long compId) {
        return companyRepository.existsById(compId);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> RestException.notFound("Company not found"));
    }


    public boolean checkingClientById(Long id) {
        return clientRepository.existsById(id);
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> RestException.restThrow("Client not found"));
    }


    public boolean existsClientById(Long clientId) {
        return clientRepository.existsById(clientId);
    }

    public void checkOrdersOfClient(Long clientId) {
        if (orderRepository.existsByClientId(clientId)) {
            throw RestException.restThrow("Bu mijozni o'chira olmaysiz, uning oldi berdilari bo'lgan ekan siz bn", HttpStatus.CONFLICT);
        }
    }

    public boolean checkCategoryById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    public Product getProductByIdOrElseThrow(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> RestException.restThrow("so'ralayotgan Mahsulot bazada mavjudmas"));
    }

    public void savedProductList(List<Product> productList) {
        try {
            productRepository.saveAll(productList);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Mahsulotlarni saqlashda muommo boldi");
        }
    }


    public String timestampToString_dd_MM_yyyy(Timestamp date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public Long getBossId() {
        try {
            //todo dabdala bo'lyatr sho'ri, lekin zarari deymadi hech yerda :)
//            Client client = clientRepository.findByClientType(Type.BOSS).orElse(new Client(Type.OTHER,"TEST MIJOZ","*******"));
            return 0l;
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    public List<ClientOrderDto> getOrderItemListByOrderId(Long orderId) {
        List<OrderItemByOrderId> allByOrderId = orderItemRepository.findAllByOrderId(orderId);
        return allByOrderId.stream().map(ClientOrderDto::make).collect(Collectors.toList());
    }
}
