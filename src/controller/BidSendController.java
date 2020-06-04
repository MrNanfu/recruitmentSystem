package controller;

import dao.BidDao;
import entity.Bid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class BidSendController {
    @FXML private Label callNameLabel;
    private int BidId;
    private String userName;
    public void Init(Parent root,int BidId,String name){
        this.BidId = BidId;
        this.userName = name;
        BidDao bidDao = new BidDao();
        Bid bid;
        callNameLabel = (Label)root.lookup("#callNameLabel");
        Label phoneNumLabel = (Label)root.lookup("#phoneNumLabel");
        Label topcicLabel = (Label)root.lookup("#topcicLabel");
        Label expMoneyLabel = (Label)root.lookup("#expMoneyLabel");
        Label limTimeLabel = (Label)root.lookup("#limTimeLabel");
        TextArea contentTxt = (TextArea)root.lookup("#contentTxt");
        bid = bidDao.SelectBid(BidId);
        callNameLabel.setText("招标人:" + bid.getCallName());
        phoneNumLabel.setText("联系方式:" + bid.getPhoneNumber());
        topcicLabel.setText("标题:" + bid.getTopic());
        expMoneyLabel.setText("预期金额:" + bid.getExpectMoney());
        limTimeLabel.setText("预期完成时间:" + bid.getTimeLimit());
        contentTxt.setText(bid.getContent());
    }

    public void throwButton(ActionEvent actionEvent) {
        BidDao bidDao = new BidDao();
        bidDao.UpdateBidAfterSend(BidId,userName);
    }

    public void returnButton(ActionEvent actionEvent) {
        ((Stage)callNameLabel.getScene().getWindow()).close();
    }
}
