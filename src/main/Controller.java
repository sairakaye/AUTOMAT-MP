package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller {
    @FXML
    ImageView rocket;

    @FXML
    JFXButton transportButton;

    @FXML
    JFXTextArea transportLog;

    @FXML
    JFXTextArea solutionLog;

    private boolean isOnEarth = true;

    public void goToAnotherPlace() {
        TranslateTransition animateRocket = new TranslateTransition(Duration.millis(2000), rocket);

        animateRocket.statusProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == Animation.Status.RUNNING) {
                System.out.println("Animation is still running.");
                transportButton.setDisable(true);
            } else {
                System.out.println("Animation done!");
                transportButton.setDisable(false);

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
            // Testing for clicking to set an effect
            // rocket.setEffect(new DropShadow(20, Color.YELLOW));
        } else {
            animateRocket.setByX(-300f);
            animateRocket.setByY(300f);
            isOnEarth = true;
            // Testing for clicking to set an effect when null
            rocket.setEffect(null);
        }

        animateRocket.setRate(1.5f);
        animateRocket.play();

        // testingJFXArea();

    }

    /*
    public void testingJFXArea() {
        transportLog.appendText("Memaaaaaaaa!\n");
    }
    */
}
