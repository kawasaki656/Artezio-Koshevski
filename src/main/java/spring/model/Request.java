package spring.model; /**
 * Created by Саша on 02.01.2017.
 */

import spring.model.ObjectIncas;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Саша on 25.12.2016.
 */
@Entity
@Table(name="requests")
public class Request implements Serializable{

    @Id
    @GenericGenerator(name="inc" , strategy="increment")
    @GeneratedValue(generator="inc")
    @Column(name="id")
    private int id;

    @OneToMany(mappedBy = "r")
    private Set<ObjectIncas> objectIncases = new HashSet<ObjectIncas>();

    @ManyToOne
    @JoinColumn(name="user",insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private User u;

    @Column(name="bank")
    private String bank;

    @Column(name="inn")
    private String inn;

    @Column(name="kpp")
    private String kpp;

    @Column(name="nameOrganization")
    private String nameOrganization;

    @Column(name="ogrn")
    private String ogrn;

    @Column(name="nameEmploye")
    private String nameEmploye;

    @Column(name="telephoneEmploye")
    private String telephoneEmploye;

    @Column(name="bankDetails")
    private String bankDetails;

    @Column(name="accountNumber")
    private String accountNumber;

    @Column(name="bik")
    private String bik;

    @Column(name="bankNumber")
    private String bankNumber;

    @Column(name="nameBank")
    private String nameBank;

    @Column(name="swift")
    private String swift;

    //скрытые
    @Column(name="date")
    private String date;

    @Column(name="status")
    private String status;

    @Column(name="type")
    private String type;

    @Column(name="user")
    private int user;

    public Request(){

    }

    public Request(String bank, String inn, String kpp, String nameOrganization, String ogrn, String nameEmploye, String telephoneEmploye, String bankDetails, String accountNumber, String bik, String bankNumber, String nameBank, String swift,int user) {
        this.bank = bank;
        this.inn = inn;
        this.kpp = kpp;
        this.nameOrganization = nameOrganization;
        this.ogrn = ogrn;
        this.nameEmploye = nameEmploye;
        this.telephoneEmploye = telephoneEmploye;
        this.bankDetails = bankDetails;
        this.accountNumber = accountNumber;
        this.bik = bik;
        this.bankNumber = bankNumber;
        this.nameBank = nameBank;
        this.swift = swift;
        this.user = user;
    }

    public void addHidden(String date, String status, String type)
    {
        this.date = date;
        this.status = status;
        this.type = type;
    }



    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Set<ObjectIncas> getObjectIncases() {
        return objectIncases;
    }

    public void setObjectIncases(Set<ObjectIncas> objectIncases) {
        this.objectIncases = objectIncases;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getNameEmploye() {
        return nameEmploye;
    }

    public void setNameEmploye(String nameEmploye) {
        this.nameEmploye = nameEmploye;
    }

    public String getTelephoneEmploye() {
        return telephoneEmploye;
    }

    public void setTelephoneEmploye(String telephoneEmploye) {
        this.telephoneEmploye = telephoneEmploye;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}
