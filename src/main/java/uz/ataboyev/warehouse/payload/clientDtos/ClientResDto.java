package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.enums.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResDto {

    private Long id;

    private Type clientType;

    private String name;

    private String phoneNumber;

    public static ClientResDto make(Client client) {
        return new ClientResDto(client.getId(),client.getClientType(), client.getName(), client.getPhoneNumber());

    }
}
