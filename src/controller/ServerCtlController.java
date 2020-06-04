package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class ServerCtlController {
    //设置全局变量用来数据统计时调用
    public static Parent root = null;
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Button advertisementButton;
    @FXML
    Button infoButton;
    @FXML
    Button dataCount;

    public void serverInfo(ActionEvent actionEvent) {
        Stage serverInfoStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/serverInfoControlFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverInfoStage.setTitle("信息控制台");
        serverInfoStage.setScene(new Scene(root, 600, 400));
        serverInfoStage.show();
        serverInfoController serverInfoControl = new serverInfoController();
        serverInfoControl.showServerInfo(root);
        serverInfoControl.clickToDetail();
    }
    public void adverControl(ActionEvent actionEvent) {
        Stage adverControlStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/adverControlFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        adverControlStage.setTitle("广告控制台");
        adverControlStage.setScene(new Scene(root, 600, 400));
        adverControlStage.show();
    }

    public void dataShow(ActionEvent actionEvent) {
        Stage dataShowStage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        try {
            URL location = getClass().getResource("/ui/serverDataControlFXML.fxml");
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            root = fxmlLoader.load();
            this.root = root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataShowStage.setTitle("数据统计台");
        dataShowStage.setScene(new Scene(root, 600, 400));
        serverDataPaneController serverDataPaneControl =  fxmlLoader.getController ();
        serverDataPaneControl.init ();
        serverDataPaneControl.tagAndDealMoneyChart ();
        serverDataPaneControl.tagAndNumChart ();
        dataShowStage.show();

    }

    public void Onclickstart(ActionEvent actionEvent) {
        advertisementButton.setVisible(true);
        infoButton.setVisible(true);
        dataCount.setVisible(true);
    }

    public void Onclickstop(ActionEvent actionEvent) {
        advertisementButton.setVisible(false);
        infoButton.setVisible(false);
        dataCount.setVisible(false);
    }
}
