package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.enums.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoForPageable {

    private Long id;

    private Type clientType;

    private String name;

    private String phoneNumber;


    public static ClientDtoForPageable make(Client client) {
        return new ClientDtoForPageable(
                client.getId(),
                client.getClientType(),
                client.getName(),
                client.getPhoneNumber());
    }
}
