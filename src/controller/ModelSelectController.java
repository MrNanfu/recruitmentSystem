package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ModelSelectController {
    public void OnclickAdmButton(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/ServerCtlFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("管理员登陆界面");
        primaryStage.setScene(new Scene(root, 550, 350));
        primaryStage.show();
        ((Button)root.lookup("#advertisementButton")).setVisible(false);
        ((Button)root.lookup("#infoButton")).setVisible(false);
        ((Button)root.lookup("#dataCount")).setVisible(false);
    }

    public void OnclickUserButton(ActionEvent actionEvent) {
        Stage primaryStage1 = new Stage();
        Stage primaryStage2 = new Stage();
        Parent root1 = null;
        Parent root2 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("/ui/AdvertisementFXML.fxml"));
            root2 = FXMLLoader.load(getClass().getResource("/ui/LoginFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdvertisermentController.Init(root1);
        primaryStage1.setTitle("用户登录界面");
        primaryStage2.setScene(new Scene(root2, 550, 350));
        primaryStage1.setScene(new Scene(root1, 550, 350));
        primaryStage2.show();
        primaryStage1.show();
    }
}
