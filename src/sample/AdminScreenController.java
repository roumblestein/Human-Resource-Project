package sample;

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

    @FXML private TextField removeEmployeeTextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{

            ssnColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("ssn"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("name"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("lastName"));

            userTable.setItems(getContacts());

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

    public void viewEmployeeButton(ActionEvent event){

    }

    public void manageEmployeeButton(ActionEvent event){

    }

    public void reportsButton(ActionEvent event){

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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginSample.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
