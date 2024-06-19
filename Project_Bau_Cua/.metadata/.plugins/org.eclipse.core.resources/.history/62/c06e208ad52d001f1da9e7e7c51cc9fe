package controller;

import java.io.IOException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.BetMoney;
import model.Dice;
import model.DiceHistory;
import model.DiceHistoryData;
import model.Person;
import model.DiceInGame;

public class MainController {
    private Person person = new Person("Nguyễn Thành Đạt", 0);
    private Dice dice = new Dice();
    private BetMoney[] betMoneyInGame = new BetMoney[6];
    private DiceInGame[] arrDiceInGame = new DiceInGame[6];
    private String[] arrFaceDiceInGame = new String[3];
    private Timeline timeUpdater;
    
    private RechargeController rechargeController;

    @FXML
    private TextField firstDiceTextField;
    @FXML
    private TextField secondDiceTextField;
    @FXML
    private TextField thirdDiceTextField;
    @FXML
    private Text faceDiceLastTurnText1;
    @FXML
    private Text faceDiceLastTurnText2;
    @FXML
    private Text faceDiceLastTurnText3;
    @FXML
    private Text nameText;
    @FXML
    private Text moneyText;
    @FXML
    private Text countdownTimeText;
    @FXML
    private Text presentTimeText;
    @FXML
    private Text dayOfWeekText;
    @FXML
    private Text naiBetMoneyText;
    @FXML
    private Text bauBetMoneyText;
    @FXML
    private Text gaBetMoneyText;
    @FXML
    private Text caBetMoneyText;
    @FXML
    private Text cuaBetMoneyText;
    @FXML
    private Text tomBetMoneyText;
    @FXML
    private ChoiceBox<String> moneyValueChoiceBox;
    @FXML
    private Button naiButton;
    @FXML
    private Button bauButton;
    @FXML
    private Button gaButton;
    @FXML
    private Button caButton;
    @FXML
    private Button cuaButton;
    @FXML
    private Button tomButton;
    @FXML
    private Button cancelBetButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button rechargeButton;
    
    @FXML
    private void initialize() throws IOException {
    	setUserName();
    	setUserMoney();
    	setInitialFaceDiceLastTurn();
    	resetFaceDiceInGame();
        setPresentTime();
        setBetMoneyInGame(); 
        countdownTime();
        moneyValueChoice();
        cancelBet();
    }

    private void setUserMoney() {
    	moneyText.setText(formatNumber(person.getMoney()));
    }
    
    private void setUserName() {
        nameText.setText(person.getName());
    }
    
    private void setInitialFaceDiceLastTurn() {
    	faceDiceLastTurnText1.setText("");
    	faceDiceLastTurnText2.setText("");
    	faceDiceLastTurnText3.setText("");
    }
    
    //Thêm các trường của tiền cược vào trò chơi
    @FXML
    private void setBetMoneyInGame() {
        for (int i = 0; i < betMoneyInGame.length; i++) {
            betMoneyInGame[i] = new BetMoney();
            betMoneyInGame[i].setBetMoney(0);
        }
        betMoneyInGame[0].setName("Nai");
        betMoneyInGame[1].setName("Bầu");
        betMoneyInGame[2].setName("Gà");
        betMoneyInGame[3].setName("Cá");
        betMoneyInGame[4].setName("Cua");
        betMoneyInGame[5].setName("Tôm");
        firstDiceTextField.setEditable(false);
        secondDiceTextField.setEditable(false);
        thirdDiceTextField.setEditable(false);
    }

    //Cài đặt lặp cho các vòng chơi
    @FXML
    private void countdownTime() {
        countdownTimeHelper(Integer.MAX_VALUE);
    }

 // Cài đặt thời gian đếm ngược
    private void countdownTimeHelper(final int count) {
        if (count > 0) {
            final int[] time = {15};
            Timeline timeline = new Timeline(
                new KeyFrame(
                    Duration.seconds(1),
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (time[0] > 0) {
                                time[0]--;
                            	resetFaceDiceInGame();
                                countdownTimeText.setText(String.valueOf(time[0]));
                                placeABet();
                            }
                        }
                    }
                )
            );
            timeline.setCycleCount(15);
            timeline.setOnFinished(event -> {
                randomDice(() -> {
                	plusMoney();
                	autoAddDiceHistory();
                	waitBeforeNextRound(count - 1);
                });
            });
            timeline.play();
        }
    }

   // Phương thức chờ 3 giây trước khi bắt đầu lượt tiếp theo
    private void waitBeforeNextRound(final int count) {
        Timeline waitTimeline = new Timeline(
            new KeyFrame(
                Duration.seconds(3),  
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        countdownTimeHelper(count); 
                    }
                }
            )
        );
        waitTimeline.setCycleCount(1);
        waitTimeline.play();
    }
    
    // Cài đặt chức năng tung xúc xắc
    @FXML
    private void randomDice(Runnable onFinished) {
        final int[] time = {20};
        Random random = new Random();
        Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.millis(50),
                new EventHandler<ActionEvent>() {
                    int count = 0;
                    @Override
                    public void handle(ActionEvent event) {
                        if (time[0] > 0 && count < 20) {
                            time[0]--;
                            firstDiceTextField.setText(dice.getDiceFace(random.nextInt(1000000)%6));
                            secondDiceTextField.setText(dice.getDiceFace(random.nextInt(1000000)%6));
                            thirdDiceTextField.setText(dice.getDiceFace(random.nextInt(1000000)%6));
                            count++;
                        }
                    	setFaceDiceLastTurn();
                    }
                }
            )
        );
        
        timeline.setCycleCount(20);
        timeline.setOnFinished(event -> {
    //    	test();
            onFinished.run();
        });
        timeline.play();
    }
    
    private void resetFaceDiceInGame() {
    	firstDiceTextField.setText("?");
        secondDiceTextField.setText("?");
        thirdDiceTextField.setText("?");
    }
    
    private void setFaceDiceLastTurn() {
    	faceDiceLastTurnText1.setText(firstDiceTextField.getText());
    	faceDiceLastTurnText2.setText(secondDiceTextField.getText());
    	faceDiceLastTurnText3.setText(thirdDiceTextField.getText());
    }
    
    //Cài đặt hiện thị ngày giờ hiện tại
    @FXML
    private void setPresentTime() {
        timeUpdater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            String formattedTime = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            presentTimeText.setText(formattedTime);
            DayOfWeek dayOfWeek = now.getDayOfWeek();
            dayOfWeekText.setText(convertToVietnamese(dayOfWeek));
        }));
        timeUpdater.setCycleCount(Timeline.INDEFINITE);
        timeUpdater.play();
    }
    
    //Chuyển đổi E -> V
    private static String convertToVietnamese(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "Thứ hai";
            case TUESDAY:
                return "Thứ ba";
            case WEDNESDAY:
                return "Thứ tư";
            case THURSDAY:
                return "Thứ năm";
            case FRIDAY:
                return "Thứ sáu";
            case SATURDAY:
                return "Thứ bảy";
            case SUNDAY:
                return "Chủ nhật";
            default:
                return "";
        }
    }
    
    //Cài đặt chức năng trừ tiền khi đặt cược và hiển thị số tiền đã đặt cược
    @FXML
    private void placeABet() {
        naiButton.setOnAction(event -> {
            placeABetHelper(naiBetMoneyText);
        });

        bauButton.setOnAction(event -> {
            placeABetHelper(bauBetMoneyText);
        });

        gaButton.setOnAction(event -> {
            placeABetHelper(gaBetMoneyText);
        });

        caButton.setOnAction(event -> {
            placeABetHelper(caBetMoneyText);
        });

        cuaButton.setOnAction(event -> {
            placeABetHelper(cuaBetMoneyText);
        });

        tomButton.setOnAction(event -> {
            placeABetHelper(tomBetMoneyText);
        });
    }

    private void placeABetHelper(Text betMoneyText) {
        if (moneyValueChoiceToInteger(moneyValueChoiceBox.getValue()) > person.getMoney()) {
            setBetButton(true);
        } else {
            int betAmount = moneyValueChoiceToInteger(moneyValueChoiceBox.getValue());
            person.setMoney(person.getMoney() - betAmount);
            int totalBet = betAmount + rechangeMoneyUnit(betMoneyText.getText());
            betMoneyText.setText(formatNumber(totalBet));
            setUserMoney();
        }
    }
    
    //Cài đặt mệnh giá cho tiền cược
    private String moneyValueChoice() {
        ObservableList<String> options = FXCollections.observableArrayList(
            "1 K", "2 K", "5 K", "10 K", "20 K", "50 K", "100 K", "200 K", "500 K", "1 M", "2 M", "5 M"
        );
        moneyValueChoiceBox.setItems(options);
        moneyValueChoiceBox.setValue("0");
        moneyValueChoiceBox.setOnAction(event -> {
        	checkBetMoney();
        });
        return moneyValueChoiceBox.getValue();
    }
    
    //
    private int moneyValueChoiceToInteger(String moneyValueChoice) {
    	switch (moneyValueChoice) {
    	case "1 K": 
    		return 1000;
    	case "2 K": 
    		return 2000;
    	case "5 K": 
    		return 5000;
    	case "10 K": 
    		return 10000;
    	case "20 K": 
    		return 20000;
    	case "50 K": 
    		return 50000;
    	case "100 K": 
    		return 100000;
    	case "200 K": 
    		return 200000;
    	case "500 K": 
    		return 500000;
    	case "1 M": 
    		return 1000000;
    	case "2 M": 
    		return 2000000;
    	case "5 M": 
    		return 5000000;
    	default:
    		return 0;
    	}
    }
    
    //Hàm kiểm tra số tiền của người dùng với mệnh giá đặt cược
    private void checkBetMoney() {
    	if (moneyValueChoiceToInteger(moneyValueChoiceBox.getValue()) <= person.getMoney()) {
            setBetButton(false);
        } else {
            setBetButton(true);
        }
    }
    //Hàm cài đặt nút Đặt cược
    private void setBetButton(boolean b) {
        naiButton.setDisable(b);
        bauButton.setDisable(b);
        gaButton.setDisable(b);
        caButton.setDisable(b);
        tomButton.setDisable(b);
        cuaButton.setDisable(b);
    }
    
    //Chức năng cộng tiền nếu thắng và cài lại trước khi đến lượt mới
    @FXML
    private void plusMoney() {
        saveBetMoney();
        person.setMoney(person.getMoney() + plusMoneyHelper() + refundIfWin());
        resetBetMoney();
        setUserMoney();
    }
    
    private int refundIfWin() {
    	int refWin = 0;
    	for (int i=0; i<6; i++) {
    		if (arrDiceInGame[i].getQuantity() != 0) {
    			refWin += betMoneyInGame[i].getBetMoney();
    		}
    	}
    	return refWin;
    }
    
    //Hàm tính tiền nhận được khi thắng
    private int plusMoneyHelper() {
        int moneyWon = 0;
        arrDiceInGame[0] = new DiceInGame("Nai", 0);
        arrDiceInGame[1] = new DiceInGame("Bầu", 0);
        arrDiceInGame[2] = new DiceInGame("Gà", 0);
        arrDiceInGame[3] = new DiceInGame("Cá", 0);
  	    arrDiceInGame[4] = new DiceInGame("Cua", 0);
  	    arrDiceInGame[5] = new DiceInGame("Tôm", 0);
        arrFaceDiceInGame[0] = firstDiceTextField.getText();
        arrFaceDiceInGame[1] = secondDiceTextField.getText();
        arrFaceDiceInGame[2] = thirdDiceTextField.getText();
        
        for (int i=0; i<3; i++) {
        	for (int j=0; j<6; j++) {
        		if ((arrDiceInGame[j]).getName().equals(arrFaceDiceInGame[i])) {
        			arrDiceInGame[j].setQuantity(arrDiceInGame[j].getQuantity()+1);
        			break;
        		}
        	}
        }
        for (int i=0; i<6; i++) {
        	moneyWon += arrDiceInGame[i].getQuantity()*betMoneyInGame[i].getBetMoney();
        }
        return moneyWon;
    }
    
    //Hàm lưu tiền cược vào Đối tượng BetMoney
    @FXML
    private void saveBetMoney() {
        betMoneyInGame[0].setBetMoney(rechangeMoneyUnit(naiBetMoneyText.getText()));
        betMoneyInGame[1].setBetMoney(rechangeMoneyUnit(bauBetMoneyText.getText()));
        betMoneyInGame[2].setBetMoney(rechangeMoneyUnit(gaBetMoneyText.getText()));
        betMoneyInGame[3].setBetMoney(rechangeMoneyUnit(caBetMoneyText.getText()));
        betMoneyInGame[4].setBetMoney(rechangeMoneyUnit(cuaBetMoneyText.getText()));
        betMoneyInGame[5].setBetMoney(rechangeMoneyUnit(tomBetMoneyText.getText()));
    }
    
    //Đặt lại tiền đã cược
    @FXML
    private void resetBetMoney() {
        naiBetMoneyText.setText("0");
        bauBetMoneyText.setText("0");
        gaBetMoneyText.setText("0");
        caBetMoneyText.setText("0");
        cuaBetMoneyText.setText("0");
        tomBetMoneyText.setText("0");
    }
    
    //Chức năng hủy cược
    @FXML
    private void cancelBet() {
    	cancelBetButton.setOnAction(envent -> {
    		refund();
    		resetBetMoney();
    	});
    }
    
    //Hoàn tiền khi hủy cược
    @FXML
    private void refund() {
    	saveBetMoney();
    	for (int i=0; i<6; i++) {
    		person.setMoney(person.getMoney()+betMoneyInGame[i].getBetMoney());
    	}
    	setUserMoney();
    }
    
    //Mở màn hình Lịch sử các lần xúc xắc
    @FXML
    private HistoryController showDiceHistory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DiceHistory.fxml"));
            Parent root = loader.load();

            HistoryController historyController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lịch sử các lần xúc xắc");
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.show();

            return historyController;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Chức năng lưu lại lịch sử xúc xắc
    @FXML
    public void autoAddDiceHistory() {
        LocalDateTime now = LocalDateTime.now();
        String timeDice = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy     HH:mm:ss"));
        String faceDice1 = firstDiceTextField.getText();
        String faceDice2 = secondDiceTextField.getText();
        String faceDice3 = thirdDiceTextField.getText();
        String userBet = "";
        for (int i=0; i<6; i++) {
        	if (betMoneyInGame[i].getBetMoney() != 0) {
    			userBet = userBet + betMoneyInGame[i].getName() + ": " + formatNumber(betMoneyInGame[i].getBetMoney()) + ", ";
    		}
        }
        if (userBet.length() > 2) {
            String userBetNew = userBet.substring(0, userBet.length() - 2);
            userBet = userBetNew;
        } else {
            userBet = "Không đặt";
        }
        int receiveMoney = plusMoneyHelper() + refundIfWin();

        DiceHistory diceHistory = new DiceHistory(timeDice, faceDice1, faceDice2, faceDice3, userBet, receiveMoney);
        DiceHistoryData.addDiceHistory(diceHistory);
    }
    
    @FXML
    private RechargeController showRechargeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Recharge.fxml"));
            Parent root = loader.load();
            RechargeController rechargeController = loader.getController();
            //======================================
            rechargeController.setMainController(this);
            //=========================================
            String verificationCode = createVerificationCode();
            rechargeController.setVerificationCode(verificationCode);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Nạp tiền");
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.show();

            return rechargeController;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Nạp tiền
    public void updateRechargeMoney(int rechanrgeMoney) {
    	person.setMoney(person.getMoney()+rechanrgeMoney);
    	setUserMoney();
    }
    
    //Đổi tiền từ xâu thành int (có dấu cách)
    public int rechangeMoneyUnit(String stringMoney) {
    	String stringRechanged = "";
    	int lengthStringMoney = stringMoney.length();
    	for (int i=0; i<lengthStringMoney; i++) {
    		if (stringMoney.charAt(i) != ' ') {
    			stringRechanged = stringRechanged + stringMoney.charAt(i);
    		}
    	}
    	return Integer.parseInt(stringRechanged);
    }
    
    //Hàm đưa về kiểu tiền
    private String formatNumber(Integer number) {
        if (number == null) {
            return "";
        }
        return String.format("%,d", number).replace(",", " ");
    }
    
    //Tạo ngẫu nhiên mã xác thực
    public String createVerificationCode() {
    	String code = "";
    	Random randomType = new Random();
    	for (int i=0; i<6; i++) {
	    	switch (randomType.nextInt(1000000)%3) {
	    	case 0:
	        	Random randomNumber = new Random();
	        	code = code + String.valueOf(randomNumber.nextInt(1000000)%10);
	        	break;
	    	case 1:
	        	Random randomUpperCharacter = new Random();
	        	code = code + (char) ((randomUpperCharacter.nextInt(1000000)%26+65));
	        	break;
	    	case 2:
	        	Random randomLowerCharacter = new Random();
	        	code = code + (char) ((randomLowerCharacter.nextInt(1000000)%26+97));
	        	break;
	    	}
	    }
    	return code;
    }
}
