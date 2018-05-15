package sample.InformationClasses;

/**
 * Created by Shpat on 2018-04-22.
 */
public class Employment {

    private String salary, employment, status, employmentDate, lastEmploymentDate;


    public Employment(String salary, String employment, String status, String employmentDate, String lastEmploymentDate){
        this.salary = salary;
        this.employment = employment;
        this.status = status;
        this.employmentDate = employmentDate;
        this.lastEmploymentDate = lastEmploymentDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getLastEmploymentDate() {
        return lastEmploymentDate;
    }

    public void setLastEmploymentDate(String lastEmploymentDate) {
        this.lastEmploymentDate = lastEmploymentDate;
    }
}
