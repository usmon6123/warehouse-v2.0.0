package uz.ataboyev.warehouse.payload;


public interface OneWorkerSalary {

    String getDate();
    String getDescription();
    String getSum();
    String getType(); //SUM, DOLLAR

}
