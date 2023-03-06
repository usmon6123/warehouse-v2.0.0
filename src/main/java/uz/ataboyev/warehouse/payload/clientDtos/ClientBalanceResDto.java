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
public class ClientBalanceResDto {

    private String clientType;
    List<ClientBalanceForRes> clientBalanceList;

    public static List<ClientBalanceResDto>  makeList(List<ClientBalance> allClientBalance) {
        ArrayList<ClientBalanceForRes> forCostumer = new ArrayList<>();
        ArrayList<ClientBalanceForRes> forConsumer = new ArrayList<>();
//        ArrayList<ClientBalanceForRes> forOther = new ArrayList<>();
//        ArrayList<ClientBalanceForRes> forBoss = new ArrayList<>();
        ArrayList<ClientBalanceResDto> result = new ArrayList<>();
        for (ClientBalance clientBalance : allClientBalance) {

            switch (clientBalance.getClientType()) {

                case "COSTUMER":
                    forCostumer.add(ClientBalanceForRes.make(clientBalance));
                    break;

                case "CONSUMER":
                    forConsumer.add(ClientBalanceForRes.make(clientBalance));
                    break;
//                case "OTHER":
//                    forOther.add(ClientBalanceForRes.make(clientBalance));
//                    break;
//                case "BOSS":
//                    forBoss.add(ClientBalanceForRes.make(clientBalance));
//                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + clientBalance.getClientType());
            }


        }
        result.add(new ClientBalanceResDto(Type.COSTUMER.name(), forCostumer));
        result.add(new ClientBalanceResDto(Type.CONSUMER.name(), forConsumer));
//        result.add(new ClientBalanceResDto(Type.OTHER.name(), forOther));
//        result.add(new ClientBalanceResDto(Type.BOSS.name(), forBoss));
        return result;
    }


}
