package uz.ataboyev.warehouse.payload;

public interface OrderPriceForPayType {
    String getPayType();
    String getCurrencyType();
    String getPrice();
}