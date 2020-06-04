package controller;

import dao.UserDao;
import entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterController {
    @FXML private TextField txtName;
    @FXML private TextField txtFirPwd;
    @FXML private TextField txtSecPwd;
    @FXML private TextField txtWork;
    @FXML private TextField txtPhoneNum;
    @FXML private ComboBox expMoney;
    @FXML private ComboBox place;
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
    private List<CheckBox> boxs = new ArrayList<CheckBox>();
    /*点击注册按钮后触发的事件*/
    public void OnClickRegister(ActionEvent actionEvent) {
        User user = new User();
        UserDao userDao = new UserDao();
        // flag检查插入数据库情况，flag2账户查重，flag3检查密码一致性
        int flag = 0,flag2 = 0,flag3 = 0;
        int count = 0;
        //检查两次密码是否输入一致
        if(txtFirPwd.getText().equals(txtSecPwd.getText()))
            flag3 = 1;
        //账户查重
            flag2 = userDao.selectUser(txtName.getText());
         //当账号不重复并且密码一致时进入深层判断
        if(flag3 == 1 && flag2 == 0){
            //设置实体对象的各个属性
            user.setName(txtName.getText());
            user.setPwd(txtFirPwd.getText());
            user.setWork(txtWork.getText());
            user.setPhoneNum(txtPhoneNum.getText());
            user.setPlace((String) place.getValue());
            user.setExpMoney((String) expMoney.getValue());
            boxs.add(box1);
            boxs.add(box2);
            boxs.add(box3);
            boxs.add(box4);
            boxs.add(box5);
            boxs.add(box6);
            boxs.add(box7);
            boxs.add(box8);
            boxs.add(box9);
            boxs.add(box10);
            boxs.add(box11);
            boxs.add(box12);
            boxs.add(box13);
            boxs.add(box14);
            boxs.add(box15);
            //设置用户自己选择的标签
            for(int i = 0; i < boxs.size();i++){
                if(boxs.get(i).isSelected()){
                    count++;
                    if(count > 1 && count <=4){
                        user.setTag(user.getTag() + ":" + boxs.get(i).getText());
                    }
                    else if(count == 1){
                        user.setTag(boxs.get(i).getText());
                    }
                }
            }
            //判断标签数
            if(count <= 4){
                    flag = userDao.insertUser(user);
                    if (flag > 0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("用户注册信息提示");
                        alert.setContentText("注册成功!");
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("用户注册信息提示");
                        alert.setContentText("注册失败!");
                        alert.showAndWait();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("用户注册信息提示");
                    alert.setContentText("选择的标签不能多于四个，请重新选择标签!");
                    alert.showAndWait();
                }
            }
        //判断密码一致性
        else if(flag3 == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("用户注册信息提示");
            alert.setContentText("两次输入的密码不一致，请检查!");
            alert.showAndWait();
        }
        //判断账户重复
        else if(flag2 == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("用户注册信息提示");
            alert.setContentText("用户名已经存在!");
            alert.showAndWait();
        }
        System.out.println("flag:" + flag + " flag2:"+flag2+ " flag3:" + flag3);
    }

    public void OnClickReture(ActionEvent actionEvent) {
        ((Stage)txtName.getScene().getWindow()).close();
    }
}
