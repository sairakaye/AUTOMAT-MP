package main;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Controller {
    @FXML ImageView rocket;
    @FXML JFXButton lezzGo;
    private boolean isOnEarth = true;

    public void goToAnotherPlace() {
        TranslateTransition animateRocket = new TranslateTransition(Duration.millis(2000), rocket);

        animateRocket.statusProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == Animation.Status.RUNNING)
                System.out.println("Animation is still running.");
            else {
                System.out.println("Animation done!");

                if (isOnEarth) {
                    rocket.setRotate(50f);
                    // original 50
                    //
                } else {
                    rocket.setRotate(-130f);
                }
            }

        });

        if (isOnEarth) {
            animateRocket.setByX(300f);
            animateRocket.setByY(-300f);
            isOnEarth = false;
        } else {
            animateRocket.setByX(-300f);
            animateRocket.setByY(300f);
            isOnEarth = true;
        }

        animateRocket.setRate(1.5f);
        animateRocket.play();
    }
}
