package controller;

import dao.BidDao;
import dao.UserDao;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginController {
    // 登陆界面的组件
    @FXML private TextField txtNum;
    @FXML private PasswordField txtPassword;
    @FXML private Button login;
    @FXML private Button register;
    public static Parent root = null;
    public static int ModelState = 0;

    public static void setModelState(int modelState) {
        ModelState = modelState;
    }

    public static int getModelState() {
        return ModelState;
    }

    List<User> specialuserlist;
    List<User> temalluserlist;
    List<Map<String ,Object>> specialbidlist;
    List<Map<String ,Object>> bidlist;
    /*
    点击登陆按钮处理事件的函数
    * */
    public void OnClickLog(ActionEvent actionEvent) {
        int flag = 0;
        User user = new User();
        user.setName(txtNum.getText());
        user.setPwd(txtPassword.getText());
        UserDao userDao = new UserDao();
        flag = userDao.selectAdminLogin(user);
        if (flag > 0){
            //先alert成功
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("正在进入主界面");
            alert.show();
            //然后再显示主界面
            Stage mainindowstage = new Stage();
            Parent root = null;
            BidDao bidDao = new BidDao();
            specialuserlist = userDao.getSpecialUserList(txtNum.getText());
            specialbidlist = bidDao.getspecialbidlist(txtNum.getText());
            bidlist = bidDao.getallbidlist();
            temalluserlist = userDao.getUserList();
            UserDao.userlist = specialuserlist;
            BidDao.specialbidlist = specialbidlist;
            UserDao.alluserlist = temalluserlist;
            BidDao.bidlist = bidlist;
            try {
                root = FXMLLoader.load(getClass().getResource("/ui/MainWindowFXML.fxml"));
                LoginController.root = root;
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainindowstage.setScene(new Scene(root, 600, 400));
            MainWindowController.setName(txtNum.getText());
            MainWindowController mainWindowController = new MainWindowController();
            mainWindowController.OnclickBidSend();
            mainindowstage.show();
            Stage main = (Stage)login.getScene().getWindow();
            alert.close();
            main.close();
            /*
             * ywj
             * */
            MainWindowController mainWindowControl = new MainWindowController ();
            mainWindowControl.setTagListView();
            mainWindowControl.clickToDetail();
            /*
             * ywj
             * */
        }else{
            //先alert失败，没有匹配上
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("warning");
            alert.setContentText("密码错误");
            alert.show();
            //然后将alert关掉后clear 一下两个框
//                txtNum.clear();
//                txtPassword.clear();
        }
    }
    /*
    点击注册按钮处理事件的函数
    * */
    public void OnClickRegister(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/UserRegisterFXMl.fxml"));
            stage.setTitle("注册界面");
            stage.setScene(new Scene(root, 600, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
