package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.ArrayList;
import java.util.List;

public class AdvertisermentController {
    public static int index = 2;
    @FXML private static Pane pane;
    public static List<MediaPlayer> MediaPlaeyers = new ArrayList<>();
//    public static MediaPlayer mediaPlayer;
    public static MediaView mediaView;
    public static void Init(Parent root){
        System.out.println(pane);
        pane = (Pane)root.lookup("#pane");
        //创建媒体对象:包含媒体资源
        String media_URL;
//        = AdvertisermentController.class.getResource("a.mp4").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
        java.io.File file = new java.io.File("/Users/m-mac/Desktop/a.mp4"); //关于这个视频是什么，我只能说无可奉告
        media_URL = file.toURI().toString();
        //or:直接使用网络资源：String media_URL = http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv；
        Media media = new Media(media_URL);
        //创建播放器对象，控制媒体播放行为
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaPlaeyers.add(mediaPlayer);
        java.io.File file2 = new java.io.File("/Users/m-mac/Desktop/b.mp4"); //关于这个视频是什么，我只能说无可奉告
        media_URL = file2.toURI().toString();
        //or:直接使用网络资源：String media_URL = http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv；
        Media media1 = new Media(media_URL);
        //创建播放器对象，控制媒体播放行为
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        MediaPlaeyers.add(mediaPlayer1);
        java.io.File file3 = new java.io.File("/Users/m-mac/Desktop/c.mp4"); //关于这个视频是什么，我只能说无可奉告
        media_URL = file3.toURI().toString();
        //or:直接使用网络资源：String media_URL = http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv；
        Media media2 = new Media(media_URL);
        //创建播放器对象，控制媒体播放行为
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
        MediaPlaeyers.add(mediaPlayer2);
        //创建媒体播放视图
        for(int i = 0; i < MediaPlaeyers.size();i++)
            MediaPlaeyers.get(i).setAutoPlay(true);
        mediaView = new MediaView(MediaPlaeyers.get(index));
        mediaView.fitHeightProperty().bind(pane.heightProperty());
        mediaView.fitWidthProperty().bind(pane.widthProperty());
        System.out.println(pane);
        pane.getChildren().add(mediaView);
        //对媒体播放器的控制（对MediaPlayer进行控制
    }
    public static void starts(){
        mediaView.setMediaPlayer(MediaPlaeyers.get(index));
        MediaPlaeyers.get(index).play();
    }
    public static void stops(){
        mediaView.setMediaPlayer(MediaPlaeyers.get(index));
        MediaPlaeyers.get(index).stop();
    }
    public static void next(){
        if(index <2){
            index++;
        }
        else {
            index = 0;
        }
        MediaPlaeyers.get(index).stop();
        mediaView.setMediaPlayer(MediaPlaeyers.get(index));
        MediaPlaeyers.get(index).play();
//        mediaPlayer.stop();
//        mediaPlayer = new MediaPlayer(Medias.get(index));
//        mediaPlayer.play();
    }
    public static void pre(){
        if(index > 0){
            index--;
        }
        else {
            index = 2;
        }
        MediaPlaeyers.get(index).stop();
        mediaView.setMediaPlayer(MediaPlaeyers.get(index));
        MediaPlaeyers.get(index).play();
//        mediaPlayer.stop();
//        mediaPlayer = new MediaPlayer(Medias.get(index));
//        mediaPlayer.play();
    }

    public void OnclickstartsButton(ActionEvent actionEvent) {
        AdvertisermentController.starts();
    }

    public void OnclickstopsButton(ActionEvent actionEvent) {
        AdvertisermentController.stops();
    }

    public void OnclickpreButton(ActionEvent actionEvent) {
        AdvertisermentController.pre();
    }

    public void OnclicknextButton(ActionEvent actionEvent) {
        AdvertisermentController.next();
    }
}
