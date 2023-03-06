package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.payload.clientDtos.ClientReqDto;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "phone_number"})})
public class Client extends AbsLongEntity {

    @Column(nullable = false, name = "wh_id")
    private Long whId;

    @Column(nullable = false, name = " client_type")
    @Enumerated(EnumType.STRING)
    private Type clientType;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;




    public static Client make(ClientReqDto clientReqDto) {
        return new Client(clientReqDto.getWhId(),clientReqDto.getClientType(), clientReqDto.getName(), clientReqDto.getPhoneNumber());
    }

    public static Client updateClient(Client client, ClientReqDto clientReqDto) {
        client.setClientType(clientReqDto.getClientType());
        client.setName(clientReqDto.getName());
        client.setPhoneNumber(clientReqDto.getPhoneNumber());
        return client;
    }

//    private Double totalSum;//clientning bir qancha savdolaridagi umumiy PULI

}
