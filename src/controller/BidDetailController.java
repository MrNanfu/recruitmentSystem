package controller;

import dao.BidDao;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BidDetailController {
    public static String name;
    @FXML
    ListView<String> BidDetailListView = null;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        BidDetailController.name = name;
    }

    public void Init(Parent root) {
        //设置编辑器
        ObservableList<String> dataList = FXCollections.observableArrayList();
        //设置列表的对象指向界面的列表，其中名字为ui中的fx:id   !!!!!!
        BidDetailListView = (ListView<String>) root.lookup("#BidDetailListView");
        //设置编辑列表的对象，将列表的编辑器赋给这个对象
        BidDetailListView.setItems(dataList);
        //创建数据库类并调用其中的整体遍历方法将招标表的所有内容遍历出来
        BidDao bidDao = new BidDao();
        List<Map<String, Object>> list = bidDao.BidDetailAllShow(MainWindowController.getName());
        //遍历所有元素，将列表中的相关内容显示到界面上
        for (int i = 0; i < list.size(); i++) {
            //设置一个临时的map变量存储遍历到的条目内容
            Map map = list.get(i);
            System.out.println("listNum:" + list.size() + " numOfbid:" +   bidDao.numOfAllBid());
            dataList.add("招标项目id:" + map.get("numOfBid") + " 招标项目标题:" + map.get("topic") + " 招标项目类型:" + map.get("tag"));
        }
    }
    public void OnclickBidDetail(){
        BidDetailListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    Stage BidSoloInfo = new Stage();
                    Parent root = null;
                    BidDao bidDao = new BidDao();
                    try {
                        root = FXMLLoader.load(getClass().getResource("/ui/BidAgrFXML.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BidAgrController.setBidId(Integer.parseInt(newValue.split(" ")[0].split(":")[1]));
                    BidAgrController.setRoot(root);
                    BidSoloInfo.setTitle("招标具体信息");
                    BidSoloInfo.setScene(new Scene(root, 800, 600));
                    BidAgrController bidAgrController = new BidAgrController();
                    bidAgrController.Init(root,Integer.parseInt(newValue.split(" ")[0].split(":")[1]));
                    bidAgrController.OnclickBidAgrListView();
                    BidSoloInfo.show();
                });
    }

    public void OnclickReturnMainWin(ActionEvent actionEvent) {
        Stage stage = (Stage) BidDetailListView.getScene().getWindow();
        stage.close();
    }
}
