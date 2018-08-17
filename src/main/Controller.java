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
import java.util.ArrayList;

public class Controller {
    @FXML
    ImageView rocket;

    /**
     *  earthBoy - Human # 1
     *  earth Girl - Human # 2
     */

    @FXML
    ImageView earthBoy, earthGirl, earthLion, earthCow, earthGrain;

    @FXML
    ImageView marsBoy, marsGirl, marsLion, marsCow, marsGrain;

    @FXML
    JFXButton transportButton, hintButton, startOverButton;

    @FXML
    JFXTextArea transportLog, solutionLog;

    private boolean isOnEarth = true;
    private TranslateTransition animateRocket;
    private ArrayList<String> tempPassengers;

    // The "Constructor" of JavaFX
    @FXML
    public void initialize() {
        tempPassengers = new ArrayList<>();

        animateRocket = new TranslateTransition(Duration.millis(2000), rocket);

        animateRocket.statusProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == Animation.Status.RUNNING) {
                System.out.println("Going to another place!");
                transportButton.setDisable(true);
            } else {
                System.out.println("Arrived at the destination!");
                transportButton.setDisable(false);

                if (isOnEarth) {
                    rocket.setRotate(50f);
                } else {
                    rocket.setRotate(-130f);
                }
            }
        });

        addEarthMouseListeners();
        addMarsMouseListeners();
    }

    public void goToAnotherPlace() {
        removeEffectsTransport();

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

        // Mauuna talaga yung mga objects tapos susunod na yung rocket (hindi realistic lol).
        addObjectsTransport();

        // Lahat ng mga nangyari, i-append na lang sa text area na ito.
        addTextToTransportLog();


        // For checking of the passengers only
        for (int i = 0; i <  tempPassengers.size(); i++) {
            System.out.println(tempPassengers.get(i));
        }

        /** Insert Back-End Logic Here
         *  Add na lang ng controller for back-end kung overwhelming na rito haha.
         */
        tempPassengers.clear();
    }

    public void addTextToTransportLog() {
        transportLog.appendText("What state?\n");
        transportLog.appendText("Memaaaaaaaa!\n");
    }

    public void addTextToSolutionLog() {
        solutionLog.appendText("Append rito yung pag mag-hihint na ang lola mo. Pak ganern!\n");
    }

    public void setStartOverButton() {

    }

    private void removeEffectsTransport() {
        for (int i = 0;  i < tempPassengers.size(); i++) {
            if (tempPassengers.get(i).equalsIgnoreCase("Boy")) {
                if (isOnEarth) {
                    earthBoy.setVisible(false);
                    earthBoy.setEffect(null);
                } else {
                    marsBoy.setVisible(false);
                    marsBoy.setEffect(null);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Girl")) {
                if (isOnEarth) {
                    earthGirl.setVisible(false);
                    earthGirl.setEffect(null);
                } else {
                    marsGirl.setVisible(false);
                    marsGirl.setEffect(null);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Lion")) {
                if (isOnEarth) {
                    earthLion.setVisible(false);
                    earthLion.setEffect(null);
                } else {
                    marsLion.setVisible(false);
                    marsLion.setEffect(null);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Grain")) {
                if (isOnEarth) {
                    earthGrain.setVisible(false);
                    earthGrain.setEffect(null);
                } else {
                    marsGrain.setVisible(false);
                    marsGrain.setEffect(null);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Cow")) {
                if (isOnEarth) {
                    earthCow.setVisible(false);
                    earthCow.setEffect(null);
                } else {
                    marsCow.setVisible(false);
                    marsCow.setEffect(null);
                }
            }
        }
    }

    private void addObjectsTransport() {
        for (int i = 0;  i < tempPassengers.size(); i++) {
            if (tempPassengers.get(i).equalsIgnoreCase("Boy")) {
                if (isOnEarth) {
                    earthBoy.setVisible(true);
                } else {
                    marsBoy.setVisible(true);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Girl")) {
                if (isOnEarth) {
                    earthGirl.setVisible(true);
                } else {
                    marsGirl.setVisible(true);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Lion")) {
                if (isOnEarth) {
                    earthLion.setVisible(true);
                } else {
                    marsLion.setVisible(true);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Grain")) {
                if (isOnEarth) {
                    earthGrain.setVisible(true);
                } else {
                    marsGrain.setVisible(true);
                }
            } else if (tempPassengers.get(i).equalsIgnoreCase("Cow")) {
                if (isOnEarth) {
                    earthCow.setVisible(true);
                } else {
                    marsCow.setVisible(true);
                }
            }
        }
    }

    private void addEarthMouseListeners() {
        earthBoy.setOnMouseClicked(event -> {
            if (earthBoy.isVisible() && isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Boy") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Boy");
                        System.out.println("Human # 1 in Earth is added.");
                        earthBoy.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Boy") >= 0)
                            tempPassengers.remove("Boy");
                        System.out.println("Human # 1 in Earth is removed.");
                        earthBoy.setEffect(null);
                    }
                }
            }
        });

        earthGirl.setOnMouseClicked(event -> {
            if (earthGirl.isVisible() && isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Girl") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Girl");
                        System.out.println("Human # 2 in Earth is added.");
                        earthGirl.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Girl") >= 0)
                            tempPassengers.remove("Girl");
                        System.out.println("Human # 2 in Earth is removed.");
                        earthGirl.setEffect(null);
                    }
                }
            }
        });

        earthGrain.setOnMouseClicked(event -> {
            if (earthGrain.isVisible() && isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Grain") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Grain");
                        System.out.println("Bag of Grain in Earth is added.");
                        earthGrain.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Grain") >= 0)
                            tempPassengers.remove("Grain");
                        System.out.println("Bag of Grain in Earth is removed.");
                        earthGrain.setEffect(null);
                    }
                }
            }
        });

        earthCow.setOnMouseClicked(event -> {
            if (earthCow.isVisible() && isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Cow") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Cow");
                        System.out.println("Cow in Earth is added.");
                        earthCow.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Cow") >= 0)
                         tempPassengers.remove("Cow");
                        System.out.println("Cow in Earth is removed.");
                        earthCow.setEffect(null);
                    }
                }
            }
        });

        earthLion.setOnMouseClicked(event -> {
            if (earthLion.isVisible() && isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Lion") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Lion");
                        System.out.println("Lion in Earth is added.");
                        earthLion.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Lion") >= 0)
                            tempPassengers.remove("Lion");
                        System.out.println("Lion in Earth is removed.");
                        earthLion.setEffect(null);
                    }
                }
            }
        });

    }

    private void addMarsMouseListeners() {
        marsBoy.setOnMouseClicked(event -> {
            if (marsBoy.isVisible() && !isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Boy") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Boy");
                        System.out.println("Human # 1 in Mars is added.");
                        marsBoy.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Boy") >= 0)
                            tempPassengers.remove("Boy");
                        System.out.println("Human # 1 in Mars is removed.");
                        marsBoy.setEffect(null);
                    }
                }
            }
        });

        marsGirl.setOnMouseClicked(event -> {
            if (marsGirl.isVisible() && !isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Girl") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Girl");
                        System.out.println("Human # 2 in Mars is added.");
                        marsGirl.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Girl") >= 0)
                            tempPassengers.remove("Girl");
                        System.out.println("Human # 2 in Mars is removed.");
                        marsGirl.setEffect(null);
                    }
                }
            }
        });

        marsGrain.setOnMouseClicked(event -> {
            if (marsGrain.isVisible() && !isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Grain") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Grain");
                        System.out.println("Bag of Grain in Mars is added.");
                        marsGrain.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Grain") >= 0)
                            tempPassengers.remove("Grain");
                        System.out.println("Bag of Grain in Mars is removed.");
                        marsGrain.setEffect(null);
                    }
                }
            }
        });

        marsCow.setOnMouseClicked(event -> {
            if (marsCow.isVisible() && !isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Cow") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Cow");
                        System.out.println("Cow in Mars is added.");
                        marsCow.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Cow") >= 0)
                            tempPassengers.remove("Cow");
                        System.out.println("Cow in Mars is removed.");
                        marsCow.setEffect(null);
                    }
                }
            }
        });

        marsLion.setOnMouseClicked(event -> {
            if (marsLion.isVisible() && !isOnEarth) {
                if (tempPassengers.size() <= 2) {
                    if (tempPassengers.indexOf("Lion") < 0 && tempPassengers.size() != 2) {
                        tempPassengers.add("Lion");
                        System.out.println("Lion in Mars is added.");
                        marsLion.setEffect(new DropShadow(15, Color.YELLOW));
                    } else {
                        if (tempPassengers.indexOf("Lion") >= 0)
                            tempPassengers.remove("Lion");
                        System.out.println("Lion in Mars is removed.");
                        marsLion.setEffect(null);
                    }
                }
            }
        });

    }
}