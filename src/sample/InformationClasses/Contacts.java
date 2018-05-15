package sample.InformationClasses;

public class Contacts {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String ssn;

    public Contacts(String name, String lastName, String email, String phone, String ssn) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
