package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.RechargeMoney;
import model.RechargeMoneyData;

public class RechargeController {

    private MainController mainController;

    @FXML
    private Button cancelButton;
    @FXML
    private Button changeVerificationButton;
    @FXML
    private TextField rechargeTextField;
    @FXML
    private TextField enterVerificationCodeTextField;
    @FXML
    public Text verificationCodeText;
    @FXML
    private Button confirmButton;
    @FXML
    private TableView<RechargeMoney> historyRecharge;
    @FXML
    private TableColumn<RechargeMoney, String> timeRechargeColumn;
    @FXML
    private TableColumn<RechargeMoney, Integer> quantityColumn;

    @FXML
    private void initialize() {
        setProperties();
        centeredColumn();
        formatQuantityColumn();

        historyRecharge.setItems(RechargeMoneyData.getRechargeMoneyList());
    }

    @FXML
    private void setProperties() {
        timeRechargeColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("timeRecharge"));
        quantityColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("quantity"));
    }

    @FXML
    private void centeredColumn() {
        centerAlignColumn(timeRechargeColumn);
        centerAlignColumn(quantityColumn);
    }

    private <T> void centerAlignColumn(TableColumn<RechargeMoney, T> column) {
        column.setCellFactory(new Callback<TableColumn<RechargeMoney, T>, TableCell<RechargeMoney, T>>() {
            public TableCell<RechargeMoney, T> call(TableColumn<RechargeMoney, T> param) {
                return new TableCell<RechargeMoney, T>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                            setGraphic(null);
                            setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
            }
        });
    }

    private String formatNumber(Integer number) {
        if (number == null) {
            return "";
        }
        return String.format("%,d", number).replace(",", " ");
    }

    @FXML
    private void formatQuantityColumn() {
        quantityColumn.setCellFactory(column -> new TableCell<RechargeMoney, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(formatNumber(item));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
    }

    @FXML
    private void rechargeAcction() {
        int quantity;
        try {
            quantity = Integer.parseInt(rechargeTextField.getText());
        } catch (NumberFormatException e) {
            showRechargeMoneyErrorNotification();
            return;
        }
        String verificationCode = verificationCodeText.getText();
        String enterVerificationCode = enterVerificationCodeTextField.getText();
        if (!(enterVerificationCode.equals(verificationCode))) {
        	showVerificationCodeErrorNotification();
        	enterVerificationCodeTextField.clear();
        	return;
        }
        LocalDateTime now = LocalDateTime.now();
        String timeRecharge = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy     HH:mm:ss"));
        RechargeMoney newRechargeMoney = new RechargeMoney(timeRecharge, quantity);
        RechargeMoneyData.addRechargeMoney(newRechargeMoney);
        mainController.updateRechargeMoney(quantity);
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    private RechargeMoneyErrorNotificationController showRechargeMoneyErrorNotification() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RechargeMoneyErrorNotification.fxml"));
            Parent root = loader.load();
            RechargeMoneyErrorNotificationController rechargeMoneyErrorNotificationController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông báo lỗi");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            return rechargeMoneyErrorNotificationController;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private VerificationCodeErrorNotificationController showVerificationCodeErrorNotification() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VerificationCodeErrorNotification.fxml"));
            Parent root = loader.load();
            VerificationCodeErrorNotificationController verificationCodeErrorNotificationController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông báo lỗi");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            return verificationCodeErrorNotificationController;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void cancelAcction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void setVerificationCode(String code) {
        verificationCodeText.setText(code);
    }
    
    public void changeVerificationAction() {
    	String verificationCode = mainController.createVerificationCode();
        setVerificationCode(verificationCode);
    }
}
