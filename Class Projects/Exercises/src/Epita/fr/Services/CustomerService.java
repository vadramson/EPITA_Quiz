package Epita.fr.Services;

import Epita.fr.BankAccounts.Entities.Customer;

public class CustomerService {

    public Customer customerDetail(Customer customer){
        Customer customerl = new Customer();
        System.out.println("Customer's Details are as follows: ");
        System.out.println("Customer's Firs and Last Name : "  + customer.getFirstName()+ " " + customer.getLastName());
        System.out.println("Customer's Address : " + customer.getAddress());
        return customerl;
    }
}
