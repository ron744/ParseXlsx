package model;

import java.util.List;

public class ResultObject {
    private String consultantName;
    private List<String> serviceList;
    private double sumTasksNumber;

    public ResultObject(String name) {
        this.consultantName = name;
    }

    public String getName() {
        return consultantName;
    }

    public void setName(String name) {
        this.consultantName = name;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public double getSumTasksNumber() {
        return sumTasksNumber;
    }

    public void setSumTasksNumber(double sumTasksNumber) {
        this.sumTasksNumber = sumTasksNumber;
    }
}
