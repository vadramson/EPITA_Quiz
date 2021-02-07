package Epita.fr.BankAccounts.Entities;

import java.util.Date;

public class Customer {

    private Integer customerID;
    private String firstName;
    private String lastName;
    private Date   dateOfBirth;
    private String placeOfBirth;
    private String address;
    private String email;
    private String phone;

    private SavingsAccounts savingsAccounts;
    private InvestmentAccounts investmentAccounts;


    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SavingsAccounts getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(SavingsAccounts savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public InvestmentAccounts getInvestmentAccounts() {
        return investmentAccounts;
    }

    public void setInvestmentAccounts(InvestmentAccounts investmentAccounts) {
        this.investmentAccounts = investmentAccounts;
    }
}
