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
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class MainWindowController {

    /*
     * cyt
     * */
    private static String name;

    public static void setName(String name) {
        MainWindowController.name = name;
    }

    public static String getName() {
        return name;
    }
    /*
    点击主界面的项目详情开启一个新窗口
    * */

    @FXML private TextField txtsearch;

    public void Onclicksearch(ActionEvent actionEvent) {
        BidDao bidDao = new BidDao();
        List<Bid> anslist = bidDao.search_content(txtsearch.getText());
        for (int i = 0; i < anslist.size(); i++) {
            System.out.println(anslist.get(i).getNumOfBid()+"  "+anslist.get(i).getTag() + "  " + anslist.get(i).getContent() + "  " + anslist.get(i).getCallName() + "  " + anslist.get(i).getThrowName());
        }
        contentListView = (ListView<String>) LoginController.root.lookup("#contentListView");
        int numOfBid = anslist.size();
        //设置编辑器
        ObservableList<String> dataList = FXCollections.observableArrayList();
        //设置编辑列表的对象，将列表的编辑器赋给这个对象
//                    System.out.println (contentListView);
        contentListView.setItems(dataList);
        for (int i = 0; i < numOfBid; i++) {
            Bid bid = anslist.get(i);
            dataList.add(bid.getNumOfBid() + " 【" + bid.getTag() + "】" + bid.getTopic() + " 招标人：" + bid.getCallName() + " 投标人：" + bid.getThrowName());
            System.out.println("a");
//            dataList.add(bid.getContent());
        }
    }

    public void OnclickBidSend(){
        contentListView = (ListView<String>) LoginController.root.lookup("#contentListView");
        contentListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    if(newValue != null){
                        Stage BidSendInfo = new Stage();
                        Parent root = null;
                        BidDao bidDao = new BidDao();
                        try {
                            root = FXMLLoader.load(getClass().getResource("/ui/BidSendFXML.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BidSendInfo.setTitle("招标具体信息");
                        BidSendInfo.setScene(new Scene(root, 800, 600));
                        BidSendController bidSendController = new BidSendController();
                        bidSendController.Init(root,Integer.parseInt(newValue.split(" ")[0]),MainWindowController.getName());
                        BidSendInfo.show();
                    }
                });
    }
        public void Onclickprogramdetail(ActionEvent actionEvent) {
        try {
            Stage patStage = new Stage();
            URL location = getClass().getResource("/ui/BidDetailFXML.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            patStage.setTitle("项目详情");
            Scene scene = new Scene(root, 700, 550);
            patStage.setScene(scene);
            BidDetailController controller = fxmlLoader.getController();   //获取Controller的实例对象
            controller.OnclickBidDetail();
            controller.Init(root);
            patStage.show();
            BidDetailController.setName(MainWindowController.getName());
            System.out.println(BidDetailController.name);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Onclickcallforneed(ActionEvent actionEvent) {
        Stage mainindowstage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/BidCallFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainindowstage.setScene(new Scene(root, 600, 530));
        BidCallController.setUserName(MainWindowController.getName());
        mainindowstage.show();
    }
    /*
     * cyt
     * */




 /*
 * ywj
 * */
 BidDao bidDao = new BidDao();
    //定义List值
    @FXML
    ListView<String> tagListView = null;
    @FXML
    ListView<String> contentListView = null;
    //控制信息显示的函数
    public void setTagListView() {
        //设置编辑器
        ObservableList<String> dataList = FXCollections.observableArrayList ();
        //设置列表的对象指向界面的列表，其中名字为ui中的fx:id   !!!!!!
        tagListView = (ListView<String>) LoginController.root.lookup ("#tagListView");
        //设置编辑列表的对象，将列表的编辑器赋给这个对象
        tagListView.setItems (dataList);
        //创建数据库类并调用其中的整体遍历方法将招标表的所有内容遍历出来
        dataList.add ("知识产权");
        dataList.add ("工商财税");
        dataList.add ("科技服务");
        dataList.add ("法律人事");
        dataList.add ("印刷服务");
        dataList.add ("装修服务");
        dataList.add ("翻译服务");
        dataList.add ("营销咨询");
        dataList.add ("金融服务");
        dataList.add ("品牌设计");
        dataList.add ("影视动漫");
        dataList.add ("游戏");
        dataList.add ("IT-软件");
        dataList.add ("电商服务");
        dataList.add ("工业设计");
    }
    //定义函数来设置listView的选择事件，实现点击Item跳出窗口显示信息
    public void clickToDetail(){
        contentListView = (ListView<String>) LoginController.root.lookup ("#contentListView");
        //首先设置代码与界面的链接
        tagListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    List<Map<String, Object>> list = bidDao.selectTagContent (newValue);
                    int numOfBid = bidDao.numOfAllBidInSpecificTag (newValue);
                    //设置编辑器
                    ObservableList<String> dataList = FXCollections.observableArrayList ();
                    //设置编辑列表的对象，将列表的编辑器赋给这个对象
//                    System.out.println (contentListView);
                    contentListView.setItems (dataList);
                    for(int i = 0;i < numOfBid;i++){
                        Map map = list.get (i);
                        dataList.add (map.get("numOfBid") + " 【" + newValue + "】" + map.get("topic") + " 招标人：" + map.get("callName") + " 投标人：" + map.get("throwName"));
//                        dataList.add ((String) map.get ("content"));
                    }
                });
    }

    public void Onclckpersoninfo(ActionEvent actionEvent) {
        //个人中心
        try {
            Stage patStage = new Stage();
            URL location = getClass().getResource("/ui/PersonInfoFXML.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            patStage.setTitle("个人信息");
            Scene scene = new Scene(root, 600, 400);
            patStage.setScene(scene);
            PersonInfoController controller = fxmlLoader.getController();   //获取Controller的实例对象
            patStage.show();
            controller.giveuserlist(UserDao.userlist,BidDao.specialbidlist);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
     * ywj
     * */

}
