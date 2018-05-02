package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class User {


    private String ssn, password, name, lastName, email, address, phone1;

    public User(String ssn, String name, String lastName, String email, String address, String phone1){
        this.ssn = ssn;
        //this.password = password;
        //this.access = access;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone1 = phone1;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }











}
