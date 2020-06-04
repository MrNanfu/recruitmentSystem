package controller;

import dao.BidDao;
import entity.Bid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class serverShowController {
    //初始化，其中名字为按钮的id(fx)，这样可以和FXML中的按钮相对应
    @FXML
    Label callNameLabel;
    @FXML
    Label phoneNumberLabel;
    @FXML
    Label topicLabel;
    @FXML
    TextArea contentLabel;
    @FXML
    Label expectMoneyLabel;
    @FXML
    Label timeLimitLabel;
    @FXML
    Button exitButton;
    //设置全局对象存储信
    BidDao bidDao = new BidDao ();
    //设置全局字符串content用来进行敏感字查询
    String phoneNumber = "";
    String timeLimit = "";
    String expectMoney = "";
    String content = "";
    //服务器信息显示界面的返回按钮的事件监听器
    //因为在打开这个界面时之前的界面没有关闭，因此我们可以直接将这个界面关闭，实现返回的效果
    @FXML
    //获取信息控制界面中点击条目的用户ID并作为投标人存储起来,注意root用来解决不能使用@FXML引入的问题
    public void showDetails(String topic, String callName,Parent root) throws SQLException {
        //将投标人的ID信息存储到实体类中
        Bid bid = new Bid(topic,callName);
        //调用BidDao中的方法暂时存储数据
        bidDao.setInfo (topic,callName);
        //将投标人对应的数据的结果集返回，在这个函数进行数据的提取
        List<Map<String,Object>> list = bidDao.serverInfoSoloShow(bid);
        //将显示界面所需要的投标人，联系方式，标题，正文，预期金额，预期完成时间
        //将List所存储的Map取出来
        Map map = null;
        if(!list.isEmpty()){
            map = list.get(0);
        }
        //赋值操作
        phoneNumber = (String) map.get("phoneNumber");
        content = (String) map.get("content");
        bidDao.content = content;
        expectMoney = (String) map.get("expectMoney");
        timeLimit = (String) map.get("timeLimit");
        //将代码定义控件与界面中的控件分别以fx:id的形式联系起来
        callNameLabel = (Label) root.lookup("#callNameLabel");
        phoneNumberLabel = (Label) root.lookup("#phoneNumberLabel");
        topicLabel = (Label) root.lookup("#topicLabel");
        contentLabel = (TextArea) root.lookup("#contentLabel");
        expectMoneyLabel = (Label) root.lookup("#expectMoneyLabel");
        timeLimitLabel = (Label) root.lookup("#timeLimitLabel");
        //将变量在界面中显示
        callNameLabel.setText (callName);
        phoneNumberLabel.setText (phoneNumber);
        topicLabel.setText (topic);
        contentLabel.setText (content);
        expectMoneyLabel.setText (String.valueOf (expectMoney));
        timeLimitLabel.setText (timeLimit);
        //设置内容不可修改
        contentLabel.setEditable (false);
        }
    public void exit(ActionEvent actionEvent) {
        //将按钮所控制的界面返回并赋到新的Scene上
        Stage stage = (Stage)exitButton.getScene().getWindow();
        //关闭界面！
        stage.close();
        this.showInfoController ();
    }

    public void delete(ActionEvent actionEvent) {
        //再调用删除数据库元素的方法将全局变量存储对应条目删除
        //将投标人的ID信息存储到实体类中
        Bid bid = new Bid(bidDao.topic,bidDao.callName);
        //调用删除方法
        bidDao.deleteItem (bid);
        //删除成功之后关闭界面
        //将按钮所控制的界面返回并赋到新的Scene上
        Stage stage = (Stage)exitButton.getScene().getWindow();
        //关闭界面！
        stage.close();
        this.showInfoController ();

    }

    public void warn(ActionEvent actionEvent) {
        String rs = "";
        rs += TextExamine.detect (bidDao.topic);
        rs += TextExamine.detect (bidDao.content);
        String[] split = rs.split (":");
        for(int i = 0;i < split.length;i++){
            System.out.println (split[i]);
        }
        if(split[0].equals ("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("无违规文本");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("敏感字警告窗");
            alert.setHeaderText("此条目含敏感字，请注意！");
            String text = "";
            for(int i = 0;i < split.length;i++){
                text += split[i] + "\n";
            }
            alert.setContentText(text);
            alert.showAndWait();
        }
    }
    public void showInfoController(){
        //重新打开信息控制界面，实现更新功能
        Stage serverInfoStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/serverInfoControlFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverInfoStage.setTitle("信息控制台");
        serverInfoStage.setScene(new Scene (root, 600, 400));
        serverInfoStage.show();
        serverInfoController serverInfoControl = new serverInfoController();
        serverInfoControl.showServerInfo(root);
        serverInfoControl.clickToDetail();
    }
}
