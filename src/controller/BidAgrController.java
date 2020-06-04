package controller;

import dao.BidDao;
import dao.UserDao;
import entity.Bid;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class BidAgrController {
    private static Parent root;
    private static int BidId;
    private static String userName;

    public static void setBidId(int bidId) {
        BidId = bidId;
    }

    public static int getBidId() {
        return BidId;
    }

    public static void setRoot(Parent root) {
        BidAgrController.root = root;
    }

    public static Parent getRoot() {
        return root;
    }

    @FXML private ListView<String> throwUserListView;
    @FXML private Button confirmButton;
    @FXML private Label stateLabel;
    //初始化投标赞同界面
    public void Init(Parent root,int BidId){
        BidDao bidDao = new BidDao();
        Bid bid;
        bid = bidDao.SelectBid(BidId);
        Label topicLabel = (Label) root.lookup("#topicLabel");
        Label expMoneyLabel = (Label) root.lookup("#expMoneyLabel");
        Label timeLimLabel = (Label) root.lookup("#timeLimLabel");
        TextArea contentTextArea = (TextArea) root.lookup("#contentTextArea");
        topicLabel.setText("标题:" + bid.getTopic());
        contentTextArea.setText(bid.getContent());
        expMoneyLabel.setText("预期金额:" + bid.getExpectMoney());
        timeLimLabel.setText("预期完成时间:" + bid.getTimeLimit());
        //设置编辑器
        ObservableList<String> dataList = FXCollections.observableArrayList();
        //设置列表的对象指向界面的列表，其中名字为ui中的fx:id   !!!!!!
        throwUserListView = (ListView) root.lookup("#throwUserListView");
        stateLabel = (Label) root.lookup("#stateLabel");
        //设置编辑列表的对象，将列表的编辑器赋给这个对象
        throwUserListView.setItems(dataList);
        for(int i = 0 ; i < bid.getThrowName().split(":").length;i++){
            dataList.add(bid.getThrowName().split(":")[i]);
        }
        if(bid.getState() == 1 ||bid.getState() == 2){
            //判断招标信息状态后招标详情的listview不可选中
            BidAgrController.getRoot().lookup("#throwUserListView").setMouseTransparent( true );
            BidAgrController.getRoot().lookup("#throwUserListView").setFocusTraversable( false );
        }
        System.out.println("bid.getState:" + bid.getState());
        confirmButton = (Button) root.lookup("#confirmButton");
        switch (bid.getState()){
            case 0:
            case 1:
                confirmButton.setVisible(false);break;
            case 2:confirmButton.setVisible(true);break;
        }
        switch (bid.getState()){
            case 0:stateLabel.setText("完成情况:暂未合作");break;
            case 1:stateLabel.setText("完成情况:已经完成");break;
            case 2:stateLabel.setText("完成情况:正在进行中");break;
        }
    }
    public void OnclickBidAgrListView(){
        throwUserListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    Stage ThrowUserSoloInfo = new Stage();
                    Parent root = null;
                    UserDao userDao = new UserDao();
                    try {
                        root = FXMLLoader.load(getClass().getResource("/ui/PersonInfoOfThrowBidFXML.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        PersonInfoOfThrowController.setBidId(BidAgrController.getBidId());
                        PersonInfoOfThrowController.setUserName(newValue);
                    System.out.println("PersonInfoOfThrowController.setUserName:" + newValue);
                    ThrowUserSoloInfo.setTitle("投标人具体信息");
                    ThrowUserSoloInfo.setScene(new Scene(root, 800, 600));
                    PersonInfoOfThrowController personInfoController = new PersonInfoOfThrowController();
                    if(newValue != null){
                        personInfoController.Init(root,newValue);
                        ThrowUserSoloInfo.show();
                    }
                });
    }

    public void OnclickReturnBidDetail(ActionEvent actionEvent) {
        Stage stage = (Stage)throwUserListView.getScene().getWindow();
        stage.close();
    }

    public void OnclickconfirmButton(ActionEvent actionEvent) {
        BidDao bidDao = new BidDao();
        bidDao.UpdateBidAfterCom(BidId);
        this.Init(BidAgrController.getRoot(),BidId);
    }
}
