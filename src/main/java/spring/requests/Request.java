package spring.requests;

/**
 * Created by Саша on 05.01.2017.
 */
public class Request {
    private String bank;
    private String inn;
    private String kpp;
    private String nameOrganization;
    private String ogrn;
    private String nameEmploye;
    private String telephoneEmploye;
    private String bankDetails;
    private String accountNumber;
    private String bik;
    private String bankNumber;
    private String nameBank;
    private String swift;
    private int user;

    public Request(){

    }

    public Request(String bank, String inn, String kpp, String nameOrganization, String ogrn, String nameEmploye, String telephoneEmploye, String bankDetails, String accountNumber, String bik, String bankNumber, String nameBank, String swift, int user) {
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

    public String getBank() {
        return bank;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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
