package controller;

import dao.BidDao;
import dao.UserDao;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class PersonInfoChangeController {
    @FXML private ComboBox place;
    @FXML private ComboBox expmoney;
    @FXML private TextField work;

    @FXML private CheckBox box1;
    @FXML private CheckBox box2;
    @FXML private CheckBox box3;
    @FXML private CheckBox box4;
    @FXML private CheckBox box5;
    @FXML private CheckBox box6;
    @FXML private CheckBox box7;
    @FXML private CheckBox box8;
    @FXML private CheckBox box9;
    @FXML private CheckBox box10;
    @FXML private CheckBox box11;
    @FXML private CheckBox box12;
    @FXML private CheckBox box13;
    @FXML private CheckBox box14;
    @FXML private CheckBox box15;


    List<User> specialuserlist;
    //初始化
    public void giveuserlistchange(List<User> specialuserlist) {
        this.specialuserlist = specialuserlist;
        //现设置好值       tag 形式1:3:6:8:10
        work.setText(specialuserlist.get(0).getWork());
//        place.setItems();
        //先遍历
        expmoney.setValue(specialuserlist.get(0).getExpMoney());
        place.setValue(specialuserlist.get(0).getPlace());
        //正则表达式遍历所有的数字
        String alltag = specialuserlist.get(0).getTag();
        System.out.println("先输出所有的tag    "+alltag);
        Pattern p = Pattern.compile(":");
        String[] items = p.split(alltag);
        for (String s : items) {
            System.out.println("tag都有  "+s);
            if (s.equals("知识产权")) box1.setSelected(true);
            if (s.equals("工商财税")) box2.setSelected(true);
            if (s.equals("科技服务")) box3.setSelected(true);
            if (s.equals("法律人事")) box4.setSelected(true);
            if (s.equals("印刷服务")) box5.setSelected(true);
            if (s.equals("装修服务")) box6.setSelected(true);
            if (s.equals("翻译服务")) box7.setSelected(true);
            if (s.equals("营销咨询")) box8.setSelected(true);
            if (s.equals("金融服务")) box9.setSelected(true);
            if (s.equals("品牌设计")) box10.setSelected(true);
            if (s.equals("影视动漫")) box11.setSelected(true);
            if (s.equals("电商服务")) box12.setSelected(true);
            if (s.equals("IT/软件")) box13.setSelected(true);
            if (s.equals("游戏")) box14.setSelected(true);
            if (s.equals("工业设计")) box15.setSelected(true);
        }
    }



    @FXML private Button confirmchange;
    @FXML private Button returnpersoninfo;

    public void PersonInfoChangeController(ActionEvent actionEvent) {
        Button getbtn = (Button)actionEvent.getSource();
        if (getbtn.getId().toString().equals("confirmchange")){
            //确认修改

            //获取当前的list的一个信息   ，并展示那个界面的初始化函数
            UserDao.userlist.get(0).setWork(work.getText());
            UserDao.userlist.get(0).setPlace((String)place.getValue());
            UserDao.userlist.get(0).setExpMoney((String)expmoney.getValue());
            //遍历一遍看看所有的tag，更新tag
            String tag_str = "";
            if (box1.isSelected()) tag_str += "知识产权:";
            if (box2.isSelected()) tag_str += "工商财税:";
            if (box3.isSelected()) tag_str += "科技服务:";
            if (box4.isSelected()) tag_str += "法律人事:";
            if (box5.isSelected()) tag_str += "印刷服务:";
            if (box6.isSelected()) tag_str += "装修服务:";
            if (box7.isSelected()) tag_str += "翻译服务:";
            if (box8.isSelected()) tag_str += "营销咨询:";
            if (box9.isSelected()) tag_str += "金融服务:";
            if (box10.isSelected()) tag_str += "品牌设计:";
            if (box11.isSelected()) tag_str += "影视动漫:";
            if (box12.isSelected()) tag_str += "电商服务:";
            if (box13.isSelected()) tag_str += "IT/软件:";
            if (box14.isSelected()) tag_str += "游戏:";
            if (box15.isSelected()) tag_str += "工业设计:";
            tag_str = tag_str.substring(0,tag_str.length()-1);
            UserDao.userlist.get(0).setTag(tag_str);

            //打开个人信息界面并且刷新数据
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
                System.out.println("修改界面往后面的界面跳转");
                controller.giveuserlist(UserDao.userlist, BidDao.specialbidlist);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            //把当前页关闭
            Stage main = (Stage)confirmchange.getScene().getWindow();
            main.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Tip");
            alert.setHeaderText("确认退出？");
            alert.setContentText("如果点击确定，当前页的修改将不会被保存！");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //把当前页关闭
                Stage personinfostage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/ui/PersonInfoFXML.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                personinfostage.setScene(new Scene(root, 600, 400));
                personinfostage.show();
                //再把当前页关掉
                Stage main = (Stage)returnpersoninfo.getScene().getWindow();
                main.close();
            } else {
                //
            }
        }

    }
}
