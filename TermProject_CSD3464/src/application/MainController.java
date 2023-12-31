package application;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {
	@FXML
	private BorderPane bpContainer;

	@FXML
	private Button btnInsert;

	@FXML
	private Button btnShowRecords;
	
	@FXML
	private Label lblTitle;
	
	public void initialize() {
		try {
			AnchorPane loader = FXMLLoader.load(getClass().getResource("PatientRecords.fxml"));
			bpContainer.setCenter(loader);
			btnShowRecords.setVisible(false);
			lblTitle.setText("Patient Records");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnInsertListener() {
		try {
			AnchorPane loader = FXMLLoader.load(getClass().getResource("AddRecord.fxml"));
			bpContainer.setCenter(loader);
			btnShowRecords.setVisible(true);
			lblTitle.setText("Add new Patient");
			btnInsert.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnbtnShowRecordsListener() {
		try {
			AnchorPane loader = FXMLLoader.load(getClass().getResource("PatientRecords.fxml"));
			bpContainer.setCenter(loader);
			btnShowRecords.setVisible(false);
			btnInsert.setVisible(true);
			lblTitle.setText("Patient Records");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
