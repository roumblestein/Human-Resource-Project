package sample.InformationClasses;

import java.util.ArrayList;

/**
 * Created by Shpat on 2018-04-22.
 */
public class Department {
    private String address, email, name;
    private ArrayList<String> phone;

    public Department(String address, String email, String name){
        this.address = address;
        this.email = email;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }
}
