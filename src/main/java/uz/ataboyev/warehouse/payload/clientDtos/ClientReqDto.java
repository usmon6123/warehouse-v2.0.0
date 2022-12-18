package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientReqDto {

    private Type clientType;

    private String name;

    private String phoneNumber;

}
