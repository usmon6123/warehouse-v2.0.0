package uz.ataboyev.warehouse.utils;

import java.sql.Timestamp;

public interface AppConstant {

    String USER = "USER";
    String ADMIN = "ADMIN";

    String BASE_PATH = "/api";

    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "20";

//    Timestamp DEFAULT_START_DATE = Timestamp.valueOf("2000-01-01 12:31:11.230000");
//    Timestamp DEFAULT_END_DATE = Timestamp.valueOf("2200-01-01 12:31:11.230000");;
    String DEFAULT_START_DATE = "946711871230";
    String DEFAULT_END_DATE = "7258145471230";
    String DEFAULT_TIME = "12:31:11.23";
}
