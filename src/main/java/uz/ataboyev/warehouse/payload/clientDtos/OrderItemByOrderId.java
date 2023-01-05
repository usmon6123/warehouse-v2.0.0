package uz.ataboyev.warehouse.payload.clientDtos;

public interface OrderItemByOrderId {

    String getDate();
    String getProductCompanyName();
    String getProductName();
    String getCount();
    String getCountSum();
    String getCurrencyTypeEnum();
    String getPrice() ;//count * countSum
    String getPayType();//to'lov turi

}
