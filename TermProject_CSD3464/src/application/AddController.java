package application;

import com.lcit.model.*;
import com.lcit.dao.*;

import java.sql.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddController {

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnSave;

	@FXML
	private DatePicker dpDateOfBirth;

	@FXML
	private Label lblOutputMsg;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtContactNumber;

	@FXML
	private TextField txtDetails;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtPatientId;
		
	public void btnSaveListener() {
		final String SUCCESS_MSG = "Record inserted succesfully";
		final String FAIL_MSG = "Record Not Inserted";
		final String INTEGRITY_MSG = "Duplicate record for patient Id";
		final String INVALID_ENTRY = "Please digit a numeric Patient ID";
		int patientId = 0;
		int insertStatementStatus = 0;

		DAO patients = new DAO();
		Patient patient;
		Date dateOfBirth = Date.valueOf(dpDateOfBirth.getValue());

		try {
			patientId = Integer.parseInt(txtPatientId.getText());
		} catch (NumberFormatException e) {
			ErrorMsgBox(INVALID_ENTRY);
			return;
		}

		patient = new Patient(patientId, txtFirstName.getText(), txtLastName.getText(), dateOfBirth,
				txtAddress.getText(), txtContactNumber.getText(), txtEmail.getText(), txtDetails.getText());
		insertStatementStatus = patients.insertRecord(patient);
		if (insertStatementStatus == 1) {
			txtPatientId.setText("");
			txtFirstName.setText("");
			txtLastName.setText("");
			dpDateOfBirth.setValue(null);
			txtAddress.setText("");
			txtContactNumber.setText("");
			txtEmail.setText("");
			txtDetails.setText("");
						
			ConfirmationMsgBox(SUCCESS_MSG);
		} else if (insertStatementStatus == -1) {
			warningMsgBox(INTEGRITY_MSG);
		} else {
			ErrorMsgBox(FAIL_MSG);
		}
	}
	
	private void warningMsgBox(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	private void ErrorMsgBox(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Warning");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	private void ConfirmationMsgBox(String msg) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
