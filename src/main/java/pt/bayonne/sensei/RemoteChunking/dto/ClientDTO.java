package pt.bayonne.sensei.RemoteChunking.dto;

import lombok.*;

import java.io.Serializable;

@ToString
public class ClientDTO implements Serializable {
     Long taxNumber;
     String firstName;
     String lastName;
     String email;

    public ClientDTO(){

    }

    public ClientDTO(Long taxNumber, String firstName, String lastName, String email) {
        this.taxNumber = taxNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public Long getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Long taxNumber) {
        this.taxNumber = taxNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
