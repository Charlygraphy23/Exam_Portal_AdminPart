package sample.controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class SpashScreenController {

    @FXML
    private Label labelTex;


    @FXML
    void initialize() {

        PauseTransition pauseTransition=new PauseTransition(Duration.seconds(2.5));
        pauseTransition.play();
        pauseTransition.setOnFinished(e->{

            Stage stage=(Stage) labelTex.getScene().getWindow();

            FadeTransition fadeTransition=new FadeTransition(Duration.millis(170));
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setNode(stage.getScene().getRoot());
            fadeTransition.play();
            fadeTransition.setOnFinished(ee->{
                stage.hide();
                Stage primaryStage=new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/view/dashboardview.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(new Scene(root));
                primaryStage.getIcons().add(new Image("/sample/assets/educaion_portal.png"));
                primaryStage.show();


            });

        });

    }
}
