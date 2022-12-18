package uz.ataboyev.warehouse.payload.clientDtos;

public interface OrderItemByOrderId {

    String getDate();
    String getCategoryName();
    String getProductName();
    String getCode();
    String getCount();
    String getCountSum();
    String getCurrencyTypeEnum();
    String getPrice() ;//count * countSum
    String getPayType();//to'lov turi

}
