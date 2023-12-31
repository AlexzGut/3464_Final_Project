package application;

import com.lcit.model.*;
import com.lcit.dao.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;

import java.sql.Date;
import java.util.ArrayList;

public class PatientRecordsController {

	@FXML
	private BorderPane bpContainer;
	
	@FXML
	private Button btnInsert;
	
	@FXML
	private Label lblOutputMsg;
	
	@FXML
    private TableView<Patient> tvRecords;
	
	@FXML
    private TableColumn<Patient, Integer> tcId;
	
    @FXML
    private TableColumn<Patient, String> tcFirstName;

    @FXML
    private TableColumn<Patient, String> tcLastName;
    
    @FXML
    private TableColumn<Patient, Date> tcDateOfBirth;
	
    @FXML
    private TableColumn<Patient, String> tcAddress;

    @FXML
    private TableColumn<Patient, String> tcContactNumber;
    
    @FXML
    private TableColumn<Patient, String> tcEmail;

    @FXML
    private TableColumn<Patient, String> tcDetails;	
    
    @FXML
    private TableColumn<Patient, Button> tcActions;	
    
    @FXML
    private TableColumn<Patient, Button> tcEdit;	
    
    @FXML
    private TableColumn<Patient, Button> tcDelete;	

	public void initialize() {
		
		DAO patients = new DAO();
	    ArrayList<Patient> patientRecords = patients.selectRecords();
	    
		for (Patient patient : patientRecords) {
			addButtons(patient);
		}
		
		ObservableList<Patient> data = FXCollections.observableArrayList(patientRecords);
		
		tcId.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
		tcFirstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		tcLastName.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		tcDateOfBirth.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dateOfBirth"));
		tcAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
		tcContactNumber.setCellValueFactory(new PropertyValueFactory<Patient, String>("contactNumber"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
		tcDetails.setCellValueFactory(new PropertyValueFactory<Patient, String>("details"));
		tcDelete.setCellValueFactory(new PropertyValueFactory<Patient, Button>("btnDelete"));
		
		editableCols();
		tvRecords.setItems(data);
	}	
	
	public void addButtons(Patient patient) {
		Button btnDelete = new Button("Delete");
		btnDelete.setPrefWidth(75);
		btnDelete.setOnAction(e -> btnDeleteListener(patient));
		patient.setBtnDelete(btnDelete);
	}

	public void btnDeleteListener(Patient patient) {
		DAO patients = new DAO();
		
		patients.deletRecord(patient);	

		tvRecords.getItems().remove(patient);

	}
	private void editableCols(){
		tcFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		tcFirstName.setOnEditCommit(e->updateFirstName(e));
		
		tcLastName.setCellFactory(TextFieldTableCell.forTableColumn());
		tcLastName.setOnEditCommit(e->updateLastName(e));
		
//		tcDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());
//		tcDateOfBirth.setOnEditCommit(e->updateRow(e));
				
		tcAddress.setCellFactory(TextFieldTableCell.forTableColumn());
		tcAddress.setOnEditCommit(e->updateAddress(e));
		
		tcContactNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		tcContactNumber.setOnEditCommit(e->updateContactNumber(e));
		
		tcEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		tcEmail.setOnEditCommit(e->updateEmail(e));
		
		tcDetails.setCellFactory(TextFieldTableCell.forTableColumn());
		tcDetails.setOnEditCommit(e->updateDetails(e));
		
	    tvRecords.setEditable(true); 

	}
	
	private void updateRecord(int index) {
		Patient SelectedPatient = tvRecords.getItems().get(index);
		DAO patients = new DAO();
		patients.updateRecord(SelectedPatient);
	}
	
	private void updateFirstName(CellEditEvent<Patient, String> e) {
		int index = tvRecords.getSelectionModel().getSelectedIndex();
		tvRecords.getItems().get(index).setFirstName(e.getNewValue());
		updateRecord(index);
	}
	
	private void updateLastName(CellEditEvent<Patient, String> e) {
		int index = tvRecords.getSelectionModel().getSelectedIndex();
		tvRecords.getItems().get(index).setLastName(e.getNewValue());
		updateRecord(index);
	}
	
	private void updateAddress(CellEditEvent<Patient, String> e) {
		int index = tvRecords.getSelectionModel().getSelectedIndex();
		tvRecords.getItems().get(index).setAddress(e.getNewValue());
		updateRecord(index);
	}
	
	private void updateContactNumber(CellEditEvent<Patient, String> e) {
		int index = tvRecords.getSelectionModel().getSelectedIndex();
		tvRecords.getItems().get(index).setContactNumber(e.getNewValue());
		updateRecord(index);
	}
	
	private void updateEmail(CellEditEvent<Patient, String> e) {
		int index = tvRecords.getSelectionModel().getSelectedIndex();
		tvRecords.getItems().get(index).setEmail(e.getNewValue());
		updateRecord(index);
	}
	
	private void updateDetails(CellEditEvent<Patient, String> e) {
		int index = tvRecords.getSelectionModel().getSelectedIndex();
		tvRecords.getItems().get(index).setDetails(e.getNewValue());
		updateRecord(index);
	}
}
