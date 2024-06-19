package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VerificationCodeErrorNotificationController {
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private void confirmAction() {
		Stage stage = (Stage) confirmButton.getScene().getWindow();
		stage.close();
	}
}