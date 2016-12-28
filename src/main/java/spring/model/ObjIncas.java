package spring.model;

/**
 * Created by Саша on 23.12.2016.
 */
public class ObjIncas {
    private int id;
    private String time;
    private String typeOfPutting;
    private String periodOfService;
    private String dayOfWeek;
    private String countOfMoney;
    private String codeOfCurrency;
    private String telephoneHead;
    private String date;

    public ObjIncas(int id, String time, String typeOfPutting, String periodOfService, String dayOfWeek, String countOfMoney, String codeOfCurrency, String telephoneHead, String date) {
        this.id = id;
        this.time = time;
        this.typeOfPutting = typeOfPutting;
        this.periodOfService = periodOfService;
        this.dayOfWeek = dayOfWeek;
        this.countOfMoney = countOfMoney;
        this.codeOfCurrency = codeOfCurrency;
        this.telephoneHead = telephoneHead;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypeOfPutting() {
        return typeOfPutting;
    }

    public void setTypeOfPutting(String typeOfPutting) {
        this.typeOfPutting = typeOfPutting;
    }

    public String getPeriodOfService() {
        return periodOfService;
    }

    public void setPeriodOfService(String periodOfService) {
        this.periodOfService = periodOfService;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getCountOfMoney() {
        return countOfMoney;
    }

    public void setCountOfMoney(String countOfMoney) {
        this.countOfMoney = countOfMoney;
    }

    public String getCodeOfCurrency() {
        return codeOfCurrency;
    }

    public void setCodeOfCurrency(String codeOfCurrency) {
        this.codeOfCurrency = codeOfCurrency;
    }

    public String getTelephoneHead() {
        return telephoneHead;
    }

    public void setTelephoneHead(String telephoneHead) {
        this.telephoneHead = telephoneHead;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
