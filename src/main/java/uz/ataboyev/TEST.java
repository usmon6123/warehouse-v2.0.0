package uz.ataboyev;

import jdk.jshell.execution.Util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_END_DATE;
import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_START_DATE;

public class TEST {

    public static void main(String[] args) {
        Long l1 = 128L;
        Long l2 = 128L;
        Long l3 = null;
        System.out.println(Objects.equals(l3,l1));
        System.out.println(Objects.equals(l2,l1));
        System.out.println(l1==l2);
    }

}
