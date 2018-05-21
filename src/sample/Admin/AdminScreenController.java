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

        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Wrong input");
        error.setHeaderText("Input error");

        /*String _department  = "";
        if(department.getValue() == "IT"){
            _department = "1";
        }else if(department.getValue() == "Finance"){
            _department = "2";
        }else if(department.getValue() == "Sales"){
            _department = "3";
        }

        String _skillCategory = "";
        if(skillCategory.getValue() == "Programming"){
            _skillCategory = "1";
        }else if(skillCategory.getValue() == "DB"){
            _skillCategory = "2";
        }*/

        if(ssnTextField.getText().matches("\\d{6}-\\d{4}") && firstNameTextField.getText().matches("[a-zA-Z]+")
                && lastNameTextField.getText().matches("[a-zA-Z]+") && !emailTextField.getText().isEmpty()
                && phoneTextField.getText().matches("[0-9]+") && !adressTextField.getText().isEmpty()
                && salaryTextField.getText().matches("[0-9]+") && startDateTextField.getText().matches("\\d{4}-\\d{2}-\\d{2}")
                && endDateTextField.getText().matches("\\d{4}-\\d{2}-\\d{2}") && employmentType.getValue() != null
                && status.getValue() != null && department.getValue() != null && skillCategory.getValue() != null && skill.getValue() != null
                && level.getValue() != null && experience.getValue() != null && !passwordTextField.getText().isEmpty()){

            System.out.println("Employee added test 121212");

            User newUser = new User(ssnTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
                    adressTextField.getText(), phoneTextField.getText(), "1", passwordTextField.getText(), "User");


            Employment employment = new Employment(salaryTextField.getText(), employmentType.getValue(), status.getValue(),
                    startDateTextField.getText(), endDateTextField.getText());


            Skills skills = new Skills("1", skill.getValue(),level.getValue(), experience.getValue(), "5");

            DatabaseC.getInstance().addEmployee(newUser,employment,skills);
            System.out.println("Employee added");
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("INFORMATION");
            dialog.setHeaderText("Employee created!");
            dialog.setContentText("New employee: "+firstNameTextField.getText() +" "+ lastNameTextField.getText() );
            dialog.showAndWait();
            userTable.setItems(getContacts());

        }else if(!ssnTextField.getText().matches("\\d{6}-\\d{4}")){
            error.setContentText("Input SSN in this format: 'NNNNNN-NNNN'");
            error.showAndWait();
        }else if(!firstNameTextField.getText().matches("[a-zA-Z]+")){
            error.setContentText("Name can only contain letters!");
            error.showAndWait();
        }else if(!lastNameTextField.getText().matches("[a-zA-Z]+")){
            error.setContentText("Lastname can only contain letters");
            error.showAndWait();
        }else if(emailTextField.getText().isEmpty()){
            error.setContentText("Email box may not be empty");
            error.showAndWait();
        }else if(!phoneTextField.getText().matches("[0-9]+")){
            error.setContentText("Phone can only contain numbers");
            error.showAndWait();
        }else if(adressTextField.getText().isEmpty()){
            error.setContentText("Adress box may not be empty");
            error.showAndWait();
        }else if(!salaryTextField.getText().matches("[0-9]+")){
            error.setContentText("Salary box may only contain numbers");
            error.showAndWait();
        }else if(!startDateTextField.getText().matches("\\d{4}-\\d{2}-\\d{2}")){
            error.setContentText("Input start date in this format: 'yyyy-mm-dd'");
            error.showAndWait();
        }else if(!endDateTextField.getText().matches("\\d{4}-\\d{2}-\\d{2}")){
            error.setContentText("Input end date in this format: 'yyyy-mm-dd'");
            error.showAndWait();
        }else if(employmentType.getValue() == null){
            error.setContentText("Choose employment type");
            error.showAndWait();
        }else if(status.getValue() == null){
            error.setContentText("Choose status");
            error.showAndWait();
        }else if(department.getValue() == null){
            error.setContentText("Choose department");
            error.showAndWait();
        }else if(skillCategory.getValue() == null){
            error.setContentText("Choose skill category");
            error.showAndWait();
        }else if(skill.getValue() == null){
            error.setContentText("Choose skill");
            error.showAndWait();
        }else if(level.getValue() == null){
            error.setContentText("Choose level");
            error.showAndWait();
        }else if(experience.getValue() == null){
            error.setContentText("Choose experience");
            error.showAndWait();
        }else if(passwordTextField.getText().isEmpty()){
            error.setContentText("Password box may not be empty");
            error.showAndWait();
        }




}

    @FXML
    public void removeEmployeeButton(ActionEvent event) throws SQLException{
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Wrong input");
        error.setHeaderText("Input error");

        if(removeEmployeeTextField.getText().matches("\\d{6}-\\d{4}")){
            DatabaseC.getInstance().removeEmployee(removeEmployeeTextField.getText());
            userTable.setItems(getContacts());
        }else if(!removeEmployeeTextField.getText().matches("\\d{6}-\\d{4}")){
            error.setContentText("Input SSN in this format: 'NNNNNN-NNNN'");
            error.showAndWait();
        }

    }

    public void viewEmployeeButton(ActionEvent event) throws SQLException{
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Wrong input");
        error.setHeaderText("Input error");

        if(removeEmployeeTextField.getText().matches("\\d{6}-\\d{4}")){
            setTextFieldAccess(true);
            adminTabPane.getSelectionModel().select(editEmployeeTab);
            loadUserInfo();
        }else if(!removeEmployeeTextField.getText().matches("\\d{6}-\\d{4}")){
            error.setContentText("Input SSN in this format: 'NNNNNN-NNNN'");
            error.showAndWait();
        }
    }

    public void editEmployeeButton(ActionEvent event) throws SQLException{
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Wrong input");
        error.setHeaderText("Input error");



        if(removeEmployeeTextField.getText().matches("\\d{6}-\\d{4}")) {
            editEmployeeTab.setDisable(false);
            adminTabPane.getSelectionModel().select(editEmployeeTab);
            setTextFieldAccess(false);
            loadUserInfo();
            userTable.setItems(getContacts());
        }else if(!removeEmployeeTextField.getText().matches("\\d{6}-\\d{4}")){
            error.setContentText("Input SSN in this format: 'NNNNNN-NNNN'");
            error.showAndWait();
        }
    }

    public void saveEditsButton() throws SQLException{
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Wrong input");
        error.setHeaderText("Input error");

        if(!editFirstNameTextField.getText().isEmpty() && !editLastNameTextField.getText().isEmpty() && !editEmailTextField.getText().isEmpty()
                && !editPhoneTextField.getText().isEmpty() && !editAdressTextField.getText().isEmpty() && !editSalaryTextField.getText().isEmpty()
                && !editStartDateTextField.getText().isEmpty() && !editEndDateTextField.getText().isEmpty() && !editEmploymentTextField.getText().isEmpty()
                && !editStatusTextField.getText().isEmpty() && !editPasswordTextField.getText().isEmpty() && !editAccessTextField.getText().isEmpty()){

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

        }else if(editFirstNameTextField.getText().isEmpty() || editLastNameTextField.getText().isEmpty() || editEmailTextField.getText().isEmpty()
                || editPhoneTextField.getText().isEmpty() || editAdressTextField.getText().isEmpty() || editSalaryTextField.getText().isEmpty()
                || editStartDateTextField.getText().isEmpty() || editEndDateTextField.getText().isEmpty() || editEmploymentTextField.getText().isEmpty()
                || editStatusTextField.getText().isEmpty() || editPasswordTextField.getText().isEmpty() || editAccessTextField.getText().isEmpty()){

            error.setContentText("Fill in all the blanks");
            error.showAndWait();
        }



    }

    @FXML
    public void reportsButton(ActionEvent event) throws SQLException{
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Wrong input");
        error.setHeaderText("Input error");

        String emp = removeEmployeeTextField.getText();
        String month = requestedReportMonth.getText();

        if(emp.matches("\\d{6}-\\d{4}") && month.matches("\\d{2}")){

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
        }else if(!emp.matches("\\d{6}-\\d{4}")){
            error.setContentText("Input SSN in this format: 'NNNNNN-NNNN'");
            error.showAndWait();
        }else if(!month.matches("\\d{2}")){
            error.setContentText("Input MONTH in this format: 'MM'");
            error.showAndWait();
        }

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


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Login/LoginSample.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
