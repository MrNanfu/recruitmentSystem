package controller;

import com.sun.org.apache.xerces.internal.xs.StringList;
import dao.BidDao;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class serverInfoController extends Thread{
    BidDao bidDao = new BidDao();
    //定义List值
    @FXML
    ListView<String> serverInfoListView = null;
    //控制信息显示的函数
    public void showServerInfo(Parent root) {
        //设置编辑器
        ObservableList<String> dataList = FXCollections.observableArrayList();
        //设置列表的对象指向界面的列表，其中名字为ui中的fx:id   !!!!!!
        serverInfoListView = (ListView<String>) root.lookup("#serverInfoListView");
        //设置编辑列表的对象，将列表的编辑器赋给这个对象
        serverInfoListView.setItems(dataList);
        //创建数据库类并调用其中的整体遍历方法将招标表的所有内容遍历出来
        List<Map<String,Object>> list = bidDao.serverInfoAllShow();
        //遍历所有元素，将列表中的相关内容显示到界面上
        for (int i = 0; i < bidDao.numOfAllBid(); i++) {
            //设置一个临时的map变量存储遍历到的条目内容
            Map map = list.get(i);
            bidDao.topic = (String) map.get ("topic");
            bidDao.content = (String) map.get("content");
            String rs = "";
            rs += TextExamine.detect (bidDao.content);
            if(!rs.isEmpty ())
            dataList.add(map.get("topic") + " " + map.get("tag") + " " + map.get("callName"));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
    //定义函数来设置listView的选择事件，实现点击Item跳出窗口显示信息
    public void clickToDetail(){
        serverInfoListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    //首先关闭控制界面
                    Stage stage = (Stage) serverInfoListView.getScene ().getWindow ();
                    stage.close ();
                    //再更新单个信息界面的内容
                    Stage serverInfoStage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/ui/serverShowFXML.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    serverInfoStage.setTitle("信息显示");
                    serverInfoStage.setScene(new Scene(root, 800, 600));
                    serverInfoStage.show();
                    //定义对象，用来传数据库查询的关键词
                    serverShowController serverShowControl = new serverShowController();
                    String[] info = newValue.split(" ");
                    try {
                        serverShowControl.showDetails(info[0],info[2],root);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }
}
