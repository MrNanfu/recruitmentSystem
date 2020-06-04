package controller;

import dao.BidDao;
import entity.Bid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BidCallController {
    @FXML private ComboBox tag;
    @FXML private TextField txtPhoneNum;
    @FXML private TextField txtTopic;
    @FXML private TextArea txtContent;
    @FXML private TextField txtExpMoney;
    @FXML private ComboBox timeLimit;
    public static Parent root;
    public static String userName;

    public static void setUserName(String userName) {
        BidCallController.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setRoot(Parent root) {
        BidCallController.root = root;
    }

    public static Parent getRoot() {
        return root;
    }
//    public void Init(Parent root){
//        tag = (ComboBox)root.lookup("#tag");
//        txtPhoneNum = (TextField)root.lookup("#txtPhoneNum");
//        txtTopic = (TextField)root.lookup("#txtTopic");
//        txtContent = (TextArea)root.lookup("#txtContent");
//        expMoney = (ComboBox)root.lookup("#expMoney");
//        timeLimit = (ComboBox)root.lookup("#timeLimit");
//    }

    /* 提交招标信息*/
    public void OnClickCallBid(ActionEvent actionEvent) {
        BidDao bidDao = new BidDao();
        Bid bid = new Bid();
        bid.setTag((String) tag.getValue());
        System.out.println((String) tag.getValue());
        bid.setPhoneNumber(txtPhoneNum.getText());
        bid.setTopic(txtTopic.getText());
        bid.setContent(txtContent.getText());
        bid.setExpectMoney(txtExpMoney.getText());
        bid.setTimeLimit((String)timeLimit.getValue());
        bid.setCallName(BidCallController.getUserName());
        bid.setState(0);
        int flag = 0;
        flag = bidDao.insertBid(bid);
        if(flag > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提交招标信息提示");
            alert.setContentText("提交招标信息成功!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提交招标信息提示");
            alert.setContentText("提交招标信息失败!");
            alert.showAndWait();
        }
    }

    public void OnClickReturn(ActionEvent actionEvent) {
        ((Stage)tag.getScene().getWindow()).close();
    }
}
