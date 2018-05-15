package sample.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import sample.Database.DatabaseC;
import sample.InformationClasses.Contacts;
import sample.InformationClasses.Employment;
import sample.InformationClasses.Skills;
import sample.Login.Login;
import sample.User.User;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by Shpat on 2018-04-25.
 */
public class UserScreenController implements Initializable {
    /*START Personal information variables */
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField ssnTextField;
    @FXML private TextField emailTextFiled;
    @FXML private TextField phoneOneTextField;
    @FXML private TextField addressTextField;
    /*END Personal information variables */

    /*START Employment variables */
    @FXML private TableView<Employment> employmentTable;
    @FXML private TableView<Skills> skillsTable;

    @FXML private TableColumn<Employment, String> salaryColumn;
    @FXML private TableColumn<Employment, String> employmentColumn;
    @FXML private TableColumn<Employment, String> statusColumn;
    @FXML private TableColumn<Employment, String> startDateColumn;
    @FXML private TableColumn<Employment, String> endDateColumn;

    @FXML private TableColumn<Skills, String> skillCategoryColumn;
    @FXML private TableColumn<Skills, String> skillColumn;
    @FXML private TableColumn<Skills, String> levelColumn;
    @FXML private TableColumn<Skills, String> experienceColumn;
    @FXML private TableColumn<Skills, String> performanceColumn;
    /*END Employment variables */

    /*START Contacts variables */
    @FXML private TableView<Contacts> contactsTable;
    @FXML private TableColumn<Contacts, String> nameColumn;
    @FXML private TableColumn<Contacts, String> lastNameColumn;
    @FXML private TableColumn<Contacts, String> phoneColumn;
    @FXML private TableColumn<Contacts, String> emailColumn;
    /*END Contacts variables */

    @FXML
    private Circle checkTimestamp;

    public static User currentUser;
    public static Employment currentUserEmployment;
    public static Skills currentUserSkills;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{



            currentUser = DatabaseC.getInstance().getPersonalInformation(Login.currentUserSsn);
            currentUserEmployment = DatabaseC.getInstance().getEmploymentInformation(Login.currentUserSsn);
            currentUserSkills = DatabaseC.getInstance().getSkills(Login.currentUserSsn);

            ssnTextField.setText(currentUser.getSsn());
            firstNameTextField.setText(currentUser.getName());
            lastNameTextField.setText(currentUser.getLastName());
            emailTextFiled.setText(currentUser.getEmail());
            phoneOneTextField.setText(currentUser.getPhone1());
            addressTextField.setText(currentUser.getAddress());

            salaryColumn.setCellValueFactory(new PropertyValueFactory<Employment,String>("salary"));
            employmentColumn.setCellValueFactory(new PropertyValueFactory<Employment,String>("employment"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<Employment,String>("status"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<Employment,String>("employmentDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<Employment,String>("lastEmploymentDate"));

            skillCategoryColumn.setCellValueFactory(new PropertyValueFactory<Skills, String>("skillCategory"));
            skillColumn.setCellValueFactory(new PropertyValueFactory<Skills, String>("skill"));
            levelColumn.setCellValueFactory(new PropertyValueFactory<Skills, String>("level"));
            experienceColumn.setCellValueFactory(new PropertyValueFactory<Skills, String>("experience"));
            performanceColumn.setCellValueFactory(new PropertyValueFactory<Skills, String>("performance"));

            nameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("name"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("lastName"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("email"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<Contacts, String>("phone"));

            employmentTable.setItems(getEmployment());
            skillsTable.setItems(getSkills());
            contactsTable.setItems(getContacts());

            String workDate;
            Calendar calendar = Calendar.getInstance();
            String day = String.valueOf(calendar.get(Calendar.DATE));
            String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
            int year = calendar.get(Calendar.YEAR);
            if (calendar.get(Calendar.DATE) < 10){
                day = "0"+day;
            }if (calendar.get(Calendar.MONTH) < 10){
                month = "0"+month;
            }

            workDate = year+ "-"+month+"-"+day;
            if (!DatabaseC.getInstance().checkedIn(workDate) && !DatabaseC.getInstance().workDayOver(workDate)){
                checkTimestamp.setFill(Paint.valueOf("#ff0000"));
            }else if (DatabaseC.getInstance().checkedIn(workDate) && !DatabaseC.getInstance().workDayOver(workDate)){
                checkTimestamp.setFill(Paint.valueOf("#f4ff00"));
            }else if (DatabaseC.getInstance().checkedIn(workDate) && DatabaseC.getInstance().workDayOver(workDate)){
                checkTimestamp.setFill(Paint.valueOf("#1dff00"));
            }


        }catch(SQLException s){
            System.out.println("Could not connect to database");
        }catch(NullPointerException a){
            System.out.println("No info accessible");
        }
    }

    public ObservableList<Employment> getEmployment(){
        ObservableList<Employment> jobs = FXCollections.observableArrayList();
        jobs.add(currentUserEmployment);
        return jobs;
    }

    public ObservableList<Skills> getSkills(){
        ObservableList<Skills> skills = FXCollections.observableArrayList();
        skills.add(currentUserSkills);
        return skills;
    }

    public ObservableList<Contacts> getContacts() throws SQLException{
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        for(int i = 0; i<DatabaseC.getInstance().getContacts().size(); i++){
            contacts.add(DatabaseC.getInstance().getContacts().get(i));
        }
        return contacts;
    }

    public void SignOut(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Login/LoginSample.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void timeStampButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Timestamp/TimeStamp.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }public void calenderButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Calender/Calender.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML private void update() throws SQLException{
        DatabaseC.getInstance().updateInformation(Login.currentUserSsn, firstNameTextField.getText(),
                lastNameTextField.getText(), emailTextFiled.getText(),addressTextField.getText(), phoneOneTextField.getText());
        //System.out.println(emailTextFiled.getText() + " " + phoneOneTextField.getText());

    }
}