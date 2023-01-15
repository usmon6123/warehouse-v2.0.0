package uz.ataboyev;

import java.sql.Timestamp;
import java.util.Date;

import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_END_DATE;
import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_START_DATE;

public class TEST {

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Timestamp(Integer.MAX_VALUE));
    }
}
