package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientBalanceForRes {
    private Long clientIdLong;
    private String clientName;
    private String balanceDollar;
    private boolean isDollarPositive;//summa no'ldan baland bo'lsa true aks holda false
    private String balanceSum;
    private boolean isSumPositive;//summa no'ldan baland bo'lsa true aks holda false


    public ClientBalanceForRes(Long clientIdLong, String clientName) {
        this.clientIdLong = clientIdLong;
        this.clientName = clientName;
    }

    public static ClientBalanceForRes make(ClientBalance clientBalance) {
        try {
            return new ClientBalanceForRes(
                    clientBalance.getClientIdLong(),
                    clientBalance.getClientName(),
                    clientBalance.getBalanceDollar(),
                    isPositive(clientBalance.getBalanceDollar()),
                    clientBalance.getBalanceSum(),
                    isPositive(clientBalance.getBalanceSum())
            );
        }catch (Exception e){
            return new ClientBalanceForRes(
                    clientBalance.getClientIdLong(),
                    clientBalance.getClientName(),
                    "0",
                    false,
                    "0",
                    false
            );
        }

    }

    private static boolean isPositive(String summa) {
        float sum = (float)Double.parseDouble(summa);
        return sum >= 0;
    }
    private static String helper (String summa) {
        double sum = Double.parseDouble(summa);
        return (sum*100)/100+"";
    }
}
