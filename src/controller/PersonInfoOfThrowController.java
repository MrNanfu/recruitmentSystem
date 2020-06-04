package controller;

import dao.BidDao;
import dao.UserDao;
import entity.Bid;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;

public class PersonInfoOfThrowController {
    @FXML
    TableView historybid;
    private static int BidId;
    private static String UserName;

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setBidId(int bidId) {
        BidId = bidId;
    }

    public static int getBidId() {
        return BidId;
    }

    public void Init(Parent root, String name) {
        User user;
        Label nameLabel = (Label) root.lookup("#name");
        Label placeLabel = (Label) root.lookup("#place");
        Label dealMoneyLabel = (Label) root.lookup("#dealMoney");
        Label tagLabel = (Label) root.lookup("#tag");
        Label dealDetailLabel = (Label) root.lookup("#dealDetail");
        Label workLabel = (Label) root.lookup("#work");
        Label expMoneyLabel = (Label) root.lookup("#expMoney");
        Label remarkLabel = (Label) root.lookup("#remark");
        historybid = (TableView)root.lookup("historybid");
        UserDao userDao = new UserDao();
        user = userDao.getUser(name);
        nameLabel.setText("账号：" + user.getName());
        placeLabel.setText("地点：" + user.getPlace());
        workLabel.setText("单位：" + user.getWork());
        dealMoneyLabel.setText("成交金额：" + user.getDealmoney());
        expMoneyLabel.setText("预期储备" + user.getExpMoney());
        remarkLabel.setText("星级评价：" + user.getRemark());
        String[] tags = user.getTag().split(":");
        for(int i = 0 ; i < tags.length;i++){
            tagLabel.setText(tagLabel.getText() + " " + tags[i]);
        }
    }

    public void OnclickReturnButton(ActionEvent actionEvent) {
        Stage stage = (Stage) historybid.getScene().getWindow();
        stage.close();
    }

    public void OnclickAgreeButton(ActionEvent actionEvent) {
        BidDao bidDao = new BidDao();
        Bid bid;
        bidDao.UpdateBidAfterAgr(PersonInfoOfThrowController.getUserName(),PersonInfoOfThrowController.getBidId());
        bid = bidDao.SelectBid(BidId);
//        //设置编辑器
//        ObservableList<String> dataList = FXCollections.observableArrayList();
//        //设置列表的对象指向界面的列表，其中名字为ui中的fx:id   !!!!!!
//        ListView throwUserListView = (ListView) BidAgrController.getRoot().lookup("#throwUserListView");
//        System.out.println("BidAgrController.getRoot():" + BidAgrController.getRoot());
//        //设置编辑列表的对象，将列表的编辑器赋给这个对象
//        throwUserListView.get
//        dataList.add(bid.getThrowName());
//        System.out.println("PersonInfoOfThrowController.getUserName():" + PersonInfoOfThrowController.getUserName());
        BidAgrController bidAgrController = new BidAgrController();
        bidAgrController.Init(BidAgrController.getRoot(),BidId);
        //同意以后招标详情的listview不可选中
        BidAgrController.getRoot().lookup("#throwUserListView").setMouseTransparent( true );
        BidAgrController.getRoot().lookup("#throwUserListView").setFocusTraversable( false );
//        switch (bid.getState()){
//            case 0:
//            case 1:
//                ((Button)BidAgrController.getRoot().lookup("confirmButton")).setVisible(false);break;
//            case 2:((Button)BidAgrController.getRoot().lookup("confirmButton")).setVisible(true);break;
//        }
//        switch (bid.getState()){
//            case 0:((Label)BidAgrController.getRoot().lookup("stateLabel")).setText("完成情况:暂未合作");break;
//            case 1:((Label)BidAgrController.getRoot().lookup("stateLabel")).setText("完成情况:已经完成");break;
//            case 2:((Label)BidAgrController.getRoot().lookup("stateLabel")).setText("完成情况:正在进行中");break;
//        }
        Stage stage = (Stage)historybid.getScene().getWindow();
        stage.close();
    }
}
