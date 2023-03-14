package uz.ataboyev.warehouse.payload;

public interface SoldProducts {
    String getRowNum();
    String getProductId();
    String getProductName();
    String getSoldCount();
    String getOriginalTotalPrice();
    String getTotalPrice();
    String getDifference();
}
