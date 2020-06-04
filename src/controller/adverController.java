package controller;

import javafx.event.ActionEvent;

public class adverController {
    public void Onclickstop(ActionEvent actionEvent) {
        AdvertisermentController.mediaView.setMediaPlayer(AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index));
        AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index).stop();
    }

    public void Onclickstart(ActionEvent actionEvent) {
        AdvertisermentController.mediaView.setMediaPlayer(AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index));
        AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index).play();
    }

    public void Onclickpre(ActionEvent actionEvent) {
        if(AdvertisermentController.index <2){
            AdvertisermentController.index++;
        }
        else {
            AdvertisermentController.index = 0;
        }
        AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index).stop();
        AdvertisermentController.mediaView.setMediaPlayer(AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index));
        AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index).play();
    }

    public void Onclicknext(ActionEvent actionEvent) {
        if(AdvertisermentController.index <2){
            AdvertisermentController.index++;
        }
        else {
            AdvertisermentController.index = 0;
        }
        AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index).stop();
        AdvertisermentController.mediaView.setMediaPlayer(AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index));
        AdvertisermentController.MediaPlaeyers.get(AdvertisermentController.index).play();
    }
}
