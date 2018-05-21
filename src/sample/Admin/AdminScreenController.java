package sample.Admin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.InformationClasses.Contacts;
import sample.Database.DatabaseC;
import sample.InformationClasses.Employment;
import sample.InformationClasses.Skills;
import sample.User.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminScreenController implements Initializable {

    @FXML private Button addEmployee;
    @FXML private Button removeEmployee;
    @FXML private Button viewEmployees;
    @FXML private Button manageEmployees;
    @FXML private Button reports;

    @FXML private Tab editEmployeeTab;
    @FXML private Tab manageEmployeeTab;
    @FXML private TabPane adminTabPane;

    @FXML private TextField ssnTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField adressTextField;
    @FXML private TextField salaryTextField;
    @FXML private TextField startDateTextField;
    @FXML private TextField endDateTextField;
    @FXML private TextField passwordTextField;

    @FXML private TextField editFirstNameTextField;
    @FXML private TextField editLastNameTextField;
    @FXML private TextField editEmailTextField;
    @FXML private TextField editPhoneTextField;
    @FXML private TextField editAdressTextField;
    @FXML private TextField editSalaryTextField;
    @FXML private TextField editStartDateTextField;
    @FXML private TextField editEndDateTextField;
    @FXML private TextField editEmploymentTextField;
    @FXML private TextField editStatusTextField;
    /*@FXML private TextField editSkillCategoryTextField;
    @FXML private TextField editSkillTextField;
    @FXML private TextField editLevelTextField;
    @FXML private TextField editExperienceTextField;*/

    @FXML private TextField editPasswordTextField;
    @FXML private TextField editAccessTextField;
    @FXML private TextField removeEmployeeTextField;

    @FXML private TextField requestedReportMonth;

    ObservableList employmentTypeBox = FXCollections.observableArrayList();
    ObservableList statusBox = FXCollections.observableArrayList();
    ObservableList departmentBox = FXCollections.observableArrayList();
    ObservableList skillCategoryBox = FXCollections.observableArrayList();
    ObservableList skillBox = FXCollections.observableArrayList();
    ObservableList levelBox = FXCollections.observableArrayList();
    ObservableList experienceBox = FXCollections.observableArrayList();

    @FXML private ChoiceBox<String> employmentType;
    @FXML private ChoiceBox<String> status;
    @FXML private ChoiceBox<String> department;

    @FXML private ChoiceBox<String> skillCategory;
    @FXML private ChoiceBox<String> skill;
    @FXML private ChoiceBox<String> level;
    @FXML private ChoiceBox<String> experience;

    @FXML private TableView<Contacts> userTable;
    @FXML private TableColumn<Contacts, String> ssnColumn;
    @FXML private TableColumn<Contacts, String> firstNameColumn;
    @FXML private TableColumn<Contacts, String> lastNameColumn;

    private User user;
    private Employment employment;
    private Skills skills;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            ssnColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("ssn"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("name"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("lastName"));

            userTable.setItems(getContacts());

            editEmployeeTab.setDisable(true);
            setTextFieldAccess(true);



        }catch(SQLException a){
            System.out.println("Error");
        }catch(NullPointerException s){
            System.out.println("Second error");
        }

        loadData();
    }

    @FXML
    public void addEmployeeButton(ActionEvent event)  throws SQLException{
        User newUser = new User(ssnTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
                adressTextField.getText(), phoneTextField.getText(), "1", passwordTextField.getText(), "User");

        Employment employment = new Employment(salaryTextField.getText(), employmentType.getValue(), status.getValue(),
                startDateTextField.getText(), endDateTextField.getText());

        Skills skills = new Skills("1", skill.getValue(),level.getValue(), experience.getValue(), "5");

        DatabaseC.getInstance().addEmployee(newUser,employment,skills);
    }

    @FXML
    public void removeEmployeeButton(ActionEvent event) throws SQLException{
        DatabaseC.getInstance().removeEmployee(removeEmployeeTextField.getText());
        userTable.setItems(getContacts());
    }

    public void viewEmployeeButton(ActionEvent event) throws SQLException{
        setTextFieldAccess(true);
        adminTabPane.getSelectionModel().select(editEmployeeTab);
        loadUserInfo();
    }

    public void editEmployeeButton(ActionEvent event) throws SQLException{
        editEmployeeTab.setDisable(false);
        adminTabPane.getSelectionModel().select(editEmployeeTab);
        setTextFieldAccess(false);
        loadUserInfo();
    }

    public void saveEditsButton() throws SQLException{
        adminTabPane.getSelectionModel().select(manageEmployeeTab);
        editEmployeeTab.setDisable(true);
        setTextFieldAccess(true);

        User editedUser = new User(removeEmployeeTextField.getText(), editFirstNameTextField.getText(), editLastNameTextField.getText(),
                editEmailTextField.getText(),editPhoneTextField.getText(), editAdressTextField.getText(),
                "1", editPasswordTextField.getText(), editAccessTextField.getText());
        Employment editedEmployment = new Employment(editSalaryTextField.getText(), editEmploymentTextField.getText(),
                editStatusTextField.getText(), editStartDateTextField.getText(), editEndDateTextField.getText());


        DatabaseC.getInstance().editEmployeeInformation(editedUser,editedEmployment);
        System.out.println("edits saved");

    }

    @FXML
    public void reportsButton(ActionEvent event) throws SQLException{

        String emp = removeEmployeeTextField.getText();
        String month = requestedReportMonth.getText();
        employment = DatabaseC.getInstance().getEmploymentInformation(emp);
        int salary = Integer.parseInt(employment.getSalary());
        int hours = DatabaseC.getInstance().totalHours(month,emp);


        String companyName = "Your company\nFinance Department\nBaker Street 221b\n";
        String title    = "----------------------------SalaryReport-----------------------------\n" +
                          "                                      2018-"+requestedReportMonth.getText();
        String fillers2 = "----------------------------------------------------------------------------";
        String description = "Description";
        String hs = "Hours";
        String amount = "Amount";

        Document report = new Document(PageSize.A4);

        try{
            PdfWriter.getInstance(report, new FileOutputStream("SalaryReport.pdf"));
            report.open();
            report.add(new Paragraph(companyName));
            report.add(new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLUE)));
            report.add(new Paragraph(fillers2,FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLACK)));
            report.add(new Paragraph(String.format("%-60s %-60s %-60s", description,hs,amount)));
            report.add(new Paragraph(String.format("%-63s %-63s %-60s", "Salary", hours,hours*salary)));
        }catch(Exception s){
            System.out.println("Error");
        }
        report.close();
    }

    public void loadUserInfo() throws SQLException{
        user = DatabaseC.getInstance().getPersonalInformation(removeEmployeeTextField.getText());
        employment = DatabaseC.getInstance().getEmploymentInformation(removeEmployeeTextField.getText());
        skills = DatabaseC.getInstance().getSkills(removeEmployeeTextField.getText());

        editFirstNameTextField.setText(user.getName());
        editLastNameTextField.setText(user.getLastName());
        editEmailTextField.setText(user.getEmail());
        editPhoneTextField.setText(user.getPhone1());
        editAdressTextField.setText(user.getAddress());

        editSalaryTextField.setText(employment.getSalary());
        editStartDateTextField.setText(employment.getEmploymentDate());
        editEndDateTextField.setText(employment.getLastEmploymentDate());
        editEmploymentTextField.setText(employment.getEmployment());
        editStatusTextField.setText(employment.getStatus());

        /*editSkillCategoryTextField.setText(skills.getSkillCategory());
        editSkillTextField.setText(skills.getSkill());
        editLevelTextField.setText(skills.getLevel());
        editExperienceTextField.setText(skills.getExperience());*/

        editPasswordTextField.setText(user.getPassword());
        editAccessTextField.setText(user.getAccess());

    }

    public void setTextFieldAccess(boolean val){
        editFirstNameTextField.setDisable(val);
        editLastNameTextField.setDisable(val);
        editEmailTextField.setDisable(val);
        editPhoneTextField.setDisable(val);
        editAdressTextField.setDisable(val);
        editSalaryTextField.setDisable(val);
        editStartDateTextField.setDisable(val);
        editEndDateTextField.setDisable(val);
        editEmploymentTextField.setDisable(val);
        editStatusTextField.setDisable(val);
        /*editSkillCategoryTextField.setDisable(val);
        editSkillTextField.setDisable(val);
        editLevelTextField.setDisable(val);
        editExperienceTextField.setDisable(val);*/
        editPasswordTextField.setDisable(val);
        editAccessTextField.setDisable(val);
    }

    public void loadData(){

        String temp = "temporary";
        String indef = "indefinite";
        employmentTypeBox.addAll(temp,indef);
        employmentType.getItems().addAll(employmentTypeBox);

        String active = "active";
        String inactive = "inactive";
        statusBox.addAll(active,inactive);
        status.getItems().addAll(statusBox);

        String itDepartment = "IT";
        String financeDepartment = "Finance";
        String salesDepartment = "Sales";
        departmentBox.addAll(itDepartment,financeDepartment,salesDepartment);
        department.getItems().addAll(departmentBox);

        String programming = "Programming";
        String database = "DB";
        skillCategoryBox.addAll(programming,database);
        skillCategory.getItems().addAll(skillCategoryBox);

        String java = "Java";
        String cpp = "CPP";
        String mysql = "MySQL";
        String python = "Python";
        String ruby = "Ruby";
        skillBox.addAll(java,cpp,mysql,python,ruby);
        skill.getItems().addAll(skillBox);

        String beginner = "1-3 years";
        String pro = "3-5 years";
        String expert = "5+ years";
        levelBox.addAll(beginner,pro,expert);
        level.getItems().addAll(levelBox);

        String junior = "JR";
        String senior = "SR";
        experienceBox.addAll(junior,senior);
        experience.getItems().addAll(experienceBox);

    }

    public ObservableList<Contacts> getContacts() throws SQLException{
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        for(int i = 0; i<DatabaseC.getInstance().getContacts().size(); i++){
            contacts.add(DatabaseC.getInstance().getContacts().get(i));
        }
        return contacts;
    }

    public void SignOut(javafx.event.ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Calender/LoginSample.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
