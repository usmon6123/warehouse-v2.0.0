package uz.ataboyev.warehouse.payload.clientDtos;

public interface ClientBalance {
    String getClientId();

    default Long getClientIdLong(){
        if (getClientId()==null)return null;
        return Long.valueOf(getClientId());
    }

    String getClientType();
    String getClientName();
    String getBalanceDollar();
    String getBalanceSum();

}
