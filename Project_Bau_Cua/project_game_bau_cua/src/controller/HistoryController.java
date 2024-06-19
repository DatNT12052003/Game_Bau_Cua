package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.DiceHistory;
import model.DiceHistoryData;

public class HistoryController {
    @FXML
    public TableView<DiceHistory> diceHistoryTable;
    @FXML
    private TableColumn<DiceHistory, String> timeDiceColumn;
    @FXML
    private TableColumn<DiceHistory, String> faceDice1Column;
    @FXML
    private TableColumn<DiceHistory, String> faceDice2Column;
    @FXML
    private TableColumn<DiceHistory, String> faceDice3Column;
    @FXML
    private TableColumn<DiceHistory, String> userBetColumn;
    @FXML
    private TableColumn<DiceHistory, Integer> receiveMoneyColumn;
    
    @FXML
    private Button closeHistory;
    
    @FXML
    private void initialize() {
    	setProperties();
    	centeredColumn();
    	formatQuantityColumn();
    	diceHistoryTable.setItems(DiceHistoryData.getDiceHistoryList());
    }
    
    
    // Liên kết các cột với các thuộc tính của DiceHistory
    @FXML
    private void setProperties() {
    	timeDiceColumn.setCellValueFactory(new PropertyValueFactory<>("timeDice"));
        faceDice1Column.setCellValueFactory(new PropertyValueFactory<>("faceDice1"));
        faceDice2Column.setCellValueFactory(new PropertyValueFactory<>("faceDice2"));
        faceDice3Column.setCellValueFactory(new PropertyValueFactory<>("faceDice3"));
        userBetColumn.setCellValueFactory(new PropertyValueFactory<>("userBet"));
        receiveMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("receiveMoney"));
    }
    
    //Căn giữa Text trong cột
    @FXML
    private void centeredColumn() {
    	centerAlignColumn(timeDiceColumn);
    	centerAlignColumn(faceDice1Column);
        centerAlignColumn(faceDice2Column);
        centerAlignColumn(faceDice3Column);
        centerAlignColumn(userBetColumn);
        centerAlignColumn(receiveMoneyColumn);
    }
    
    private <T> void centerAlignColumn(TableColumn<DiceHistory, T> column) {
        column.setCellFactory(new Callback<TableColumn<DiceHistory, T>, TableCell<DiceHistory, T>>() {
            public TableCell<DiceHistory, T> call(TableColumn<DiceHistory, T> param) {
                return new TableCell<DiceHistory, T>() {
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
    	receiveMoneyColumn.setCellFactory(column -> new TableCell<DiceHistory, Integer>() {
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
    private void closeHistoryAction() {
        Stage stage = (Stage) closeHistory.getScene().getWindow();
        stage.close();
    }
}