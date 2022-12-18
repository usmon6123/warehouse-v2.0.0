package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.Type;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientBalanceDto {
    private Long clientIdLong;
    private String clientType;
    private String clientName;
    private String balanceDollar;
    private String balanceSum;



}
