package spring.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Саша on 02.01.2017.
 */
@Entity
@Table(name="objects")
public class ObjectIncas implements Serializable{

    @ManyToOne
    @JoinColumn(name="req_id",insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Request r;

    @Id
    @GenericGenerator(name="inc" , strategy="increment")
    @GeneratedValue(generator="inc")
    @Column(name="id")
    private int id;

    @Column(name="time")
    private String time;

    @Column(name="typeOfPutting")
    private String typeOfPutting;

    @Column(name="periodOfService")
    private String periodOfService;

    @Column(name="dayOfWeek")
    private String dayOfWeek;

    @Column(name="countOfMoney")
    private String countOfMoney;

    @Column(name="codeOfCurrency")
    private String codeOfCurrency;

    @Column(name="telephoneHead")
    private String telephoneHead;

    @Column(name="date")
    private String date;

    @Column(name="req_id")
    private int req_id;

    public ObjectIncas(){

    }

    public ObjectIncas(String time, String typeOfPutting, String periodOfService, String dayOfWeek, String countOfMoney, String codeOfCurrency, String telephoneHead, String date, int req_id) {
        this.time = time;
        this.typeOfPutting = typeOfPutting;
        this.periodOfService = periodOfService;
        this.dayOfWeek = dayOfWeek;
        this.countOfMoney = countOfMoney;
        this.codeOfCurrency = codeOfCurrency;
        this.telephoneHead = telephoneHead;
        this.date = date;
        this.req_id = req_id;
    }

    public Request getR() {
        return r;
    }

    public void setR(Request r) {
        this.r = r;
    }

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
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
