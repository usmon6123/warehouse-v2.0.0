package uz.ataboyev.warehouse.payload.clientDtos;

public interface ClientBalanceEqualNull {
    String getClientId();

    default Long getClientIdLong(){
        if (getClientId()==null)return null;
        return Long.valueOf(getClientId());
    }

    String getClientType();
    String getClientName();


}
