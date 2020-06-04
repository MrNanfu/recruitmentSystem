package controller;

import dao.UserDao;
import entity.Bid;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonInfoController {

    //将数据展示出来的区域
    //首先得到所有的控件
    @FXML private Label z_num;
    @FXML private Label z_place;
    @FXML private Label z_dealmoney;
    @FXML private Label z_tag;
    @FXML private Label z_work;
    @FXML private Label z_expmoney;
    @FXML private Label z_remark;

    //渲染表格部分
    @FXML private TableView historybid;
    @FXML private TableColumn timecol;
    @FXML private TableColumn dealnumcol;
    @FXML private TableColumn tagcol;


    List<User> specialuserlist;
    public void giveuserlist(List<User> specialuserlist,List<Map<String ,Object>> specialbidlist){

        List<Bid> temlist = new ArrayList<>();
        ObservableList<Bid> oList= FXCollections.observableArrayList();
        for (int i = 0;i < specialbidlist.size();i++){
            Bid tembid = new Bid((String) specialbidlist.get(i).get("tag"),(String) specialbidlist.get(i).get("expectMoney"),(String) specialbidlist.get(i).get("timelimit"),(int)specialbidlist.get(i).get("numOfBid"));
            temlist.add(tembid);
        }
        System.out.println("_____________ ========="+temlist.size());
        for(int i = 0; i <temlist.size();i++){
            oList.add(temlist.get(i));
        }

//        private int numOfBid;
//        private String tag;    ..
//        private String phoneNumber;
//        private String topic;
//        private String content;
//        private String expectMoney;   ..
//        private String timeLimit;   ..
//        private String callName;
//        private String throwName;
//        private int state;
        //对表格的渲染
        historybid.setEditable(true);
        timecol.setCellValueFactory(new PropertyValueFactory<Bid,String>("timeLimit"));
        dealnumcol.setCellValueFactory(new PropertyValueFactory<Bid,String>("expectMoney"));
        tagcol.setCellValueFactory(new PropertyValueFactory<Bid,String>("tag"));
//        historybid.getColumns().addAll(timecol,dealnumcol,tagcol);
        //把实体类的数据绑定
//        BidDao bidDao = new BidDao();
//        List<Bid> list = bidDao.selectBid();

//        TitlesDao titlesdao=new TitlesDao();
//        List<Titles> list=titlesdao.selectTitles(txtTitle.getText());

        historybid.setItems(oList);



        this.specialuserlist = specialuserlist;
        System.out.println("输出个人信息主页的列表的   "+specialuserlist.size());
        for (int i = 0 ;i < specialuserlist.size(); i++){
            System.out.println(specialuserlist.get(i).getName()+" "+specialuserlist.get(i).getPwd()+" "+specialuserlist.get(i).getWork()+" "+specialuserlist.get(i).getPhoneNum()+" "+specialuserlist.get(i).getPlace()+" "+specialuserlist.get(i).getExpMoney()+" "+specialuserlist.get(i).getTag()+" "+specialuserlist.get(i).getSystag()+" "+specialuserlist.get(i).getDealmoney()+" "+specialuserlist.get(i).getId());
        }
        z_num.setText(specialuserlist.get(0).getName());
        z_place.setText(specialuserlist.get(0).getPlace());
        z_dealmoney.setText(String.valueOf(specialuserlist.get(0).getDealmoney()));
        z_tag.setText(specialuserlist.get(0).getTag());
        z_work.setText(specialuserlist.get(0).getWork());
        z_expmoney.setText(String.valueOf(specialuserlist.get(0).getExpMoney()));
        z_remark.setText("5");

        //在每次初始化的时候都往数据库更新一次
        UserDao userDao = new UserDao();
        userDao.changeInfo(specialuserlist);
    }

    @FXML private Button likeperson;
    @FXML private Button change;
    @FXML private Button mainwindow;


    public void PersonInfoController(ActionEvent actionEvent) {
//        z_num.setText(specialuserlist.get(0).getNum());


        //此区域是对个人信息的赋值
//        System.out.println("已经点击了进入个人中心");
//        LoginController tem = new LoginController();
//        System.out.println("准备得到一个list");
//        List<User> specialuserlist = tem.getSpecialUserList();
//        System.out.println("已经得到了一个list");
//        z_num.setText(UserDao.user.getNum());
//        z_place.setText(specialuserlist.get(0).getPlace());
//        z_dealmoney.setText(String.valueOf(specialuserlist.get(0).getDealmoney()));
//        z_tag.setText(specialuserlist.get(0).getTag());
//        z_work.setText(specialuserlist.get(0).getWork());
//        z_expmoney.setText(String.valueOf(specialuserlist.get(0).getExpmoney()));
//        z_remark.setText(String.valueOf(specialuserlist.get(0).getRemark()));



        Button getbtn = (Button) actionEvent.getSource();
//        <Button fx:id="like" layoutX="470.0" layoutY="209.0" mnemonicParsing="false" text="你可能喜欢" onAction="#PersonInfoController"/>
//        <Button fx:id="change" layoutX="470.0" layoutY="265.0" mnemonicParsing="false" text="修改信息" onAction="#PersonInfoController"/>
//        <Button fx:id="mainwindow" layoutX="470.0" layoutY="323.0" mnemonicParsing="false" text="主界面" onAction="#PersonInfoController"/>
        if (getbtn.getId().toString().equals("likeperson")){
            //可能喜欢
            try {
                Stage patStage = new Stage();
                URL location = getClass().getResource("/ui/likePersonFXML.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                Parent root = fxmlLoader.load();
                patStage.setTitle("个人信息修改");
                Scene scene = new Scene(root, 600, 400);
                patStage.setScene(scene);
                likePersonController controller = fxmlLoader.getController();   //获取Controller的实例对象
                patStage.show();
                controller.init(specialuserlist.get(0).getName());
            }
            catch (Exception e){
                e.printStackTrace();
            }

//            Stage mainindowstage = new Stage();
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("/ui/likePersonFXML.fxml"));
//                System.out.println("root:" + root);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mainindowstage.setScene(new Scene(root, 600, 530));
//            BidCallController.setUserName(MainWindowController.getName());
//            mainindowstage.show();
        }else if (getbtn.getId().toString().equals("change")){
            //修改信息
            try {
                Stage patStage = new Stage();
                URL location = getClass().getResource("/ui/PersonInfoChangeFXML.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                Parent root = fxmlLoader.load();
                patStage.setTitle("个人信息修改");
                Scene scene = new Scene(root, 600, 400);
                patStage.setScene(scene);
                PersonInfoChangeController controller = fxmlLoader.getController();   //获取Controller的实例对象
                patStage.show();
                controller.giveuserlistchange(UserDao.userlist);
//                controller.giveuserlist(UserDao.userlist);
//                controller.giveuserlist(UserDao.userlist);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            //修改信息要把当前界面关掉
            Stage main = (Stage)change.getScene().getWindow();
            main.close();
        }else {
            //返回主界面
            //此时不用再显示主界面
//            Stage returnmainwindowstage = new Stage();
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("/ui/MainWindowFXML.fxml"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            returnmainwindowstage.setScene(new Scene(root, 600, 400));
//            returnmainwindowstage.show();
            Stage main = (Stage)mainwindow.getScene().getWindow();
            main.close();
        }
    }

}
