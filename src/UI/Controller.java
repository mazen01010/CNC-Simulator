package UI;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.paint.Color;


public class Controller implements Runnable{


    public Button Start;
    public Button Pause;
    public Button Stop;
    public Label CommandLabel;
    public TextArea commandField;
    public Canvas canvas;




    public ArrayList<Objects> objects = new ArrayList<>(); // to save objects created when start button is pressed


    public static final double D = 4;  // diameter.

    DoubleProperty xs  = new SimpleDoubleProperty();
    DoubleProperty ys  = new SimpleDoubleProperty();


    Duration duration = Duration.seconds(15000 / Machine.getF());


    Timeline timeline = new Timeline();
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {

        }
    };

    // Define a getter for the property's value
    public final double getxs(){return xs.get();}

    // Define a setter for the property's value
    public final void setxs(double value){xs.set(value + canvas.getWidth()/2);}

    // Define a getter for the property itself
    public DoubleProperty xsProperty() {return xs;}

    // Define a getter for the property's value
    public final double getys(){return ys.get();}

    // Define a setter for the property's value
    public final void setys(double value){ys.set(value + canvas.getHeight()/2);}

    // Define a getter for the property itself
    public DoubleProperty ysProperty() {return ys;}


    public void initialize() {

        setxs(0);
        setys(0);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);
        gc.strokeLine(canvas.getWidth()/2 -1,canvas.getHeight()/2 +2,canvas.getWidth()/2 + 5,canvas.getHeight()/2 +2);
        gc.strokeLine(canvas.getWidth()/2 +2,canvas.getHeight()/2 -1,canvas.getWidth()/2 +2,canvas.getHeight()/2 +5);

        gc.setFill(Color.RED);
        gc.fillOval(
                xs.doubleValue(),
                ys.doubleValue(),
                D,
                D
        );

    }




    public void StartClicked() {

        System.out.println("Started");
        System.out.println("Hi Im ");
        ArrayList<String> strings = new ArrayList<>();

        double X;
        double Y;
        double I;
        double J;
        double F;

        String str = commandField.getText();

        for (String strs : str.toUpperCase().split(" ")) {
            strings.add(strs);
        }/*
        for (int i = 0; i <= strings.size(); i++) {

            String string = strings.get(i);

            switch (string) {

                case "N":
                    ;
                case "G00":
                    G00 g00 = new G00();
                case "G01":
                    G01 g01 = new G01();
                    //case "G02": //G02 g02 = new G02();
                    //case "G03":
                case "X":
                    ;
                case "Y":
                    ;
                case "I":
                    ;
                case "J":
                    ;
                case "F":
                    ;
                case "M00":
                    Machine.setM00(true);
                case "M02":
                    Machine.setM02(true);
                case "M03":
                    Machine.setM03(true);
                case "M04":
                    Machine.setM04(true);
                case "M05":
                    Machine.setM05(true);
                case "M08":
                    Machine.setM08(true);
                case "M09":
                    Machine.setM09(true);
                case "M13":
                    Machine.setM13(true);
                case "M14":
                    Machine.setM14(true);
                default:
                    System.out.println("please enter valid expression");

            }

        }*/
        System.out.println(commandField.getText());

        // just for checking... should be deleted after program finished
        Machine.setF(1000);

        try {

            timeline = new Timeline(

                    new KeyFrame(Duration.seconds(15000 / Machine.getF()),
                            new KeyValue(xs, canvas.getWidth() - D),
                            new KeyValue(ys, canvas.getHeight() - D)

                    )
            );
        } catch (Exception e) {
            System.out.println("please enter Feed Rate");
        }


        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);


        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.RED);
                gc.fillOval(
                        xs.doubleValue(),
                        ys.doubleValue(),
                        D,
                        D
                );
                gc.setStroke(Color.GREEN);
                gc.strokeLine(canvas.getWidth() / 2 - 1, canvas.getHeight() / 2 + 2, canvas.getWidth() / 2 + 5, canvas.getHeight() / 2 + 2);
                gc.strokeLine(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 - 1, canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 5);

                gc.strokeText(".", getxs(),getys());
                

            }
        };
        timeline.play();
        timer.start();





    }

    public Timeline setTimeline(){

        //create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        EventHandler onFinished =  new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.RED);
                gc.fillOval(
                        xs.doubleValue(),
                        ys.doubleValue(),
                        D,
                        D
                );
                gc.setStroke(Color.GREEN);
                gc.strokeLine(canvas.getWidth() / 2 - 1, canvas.getHeight() / 2 + 2, canvas.getWidth() / 2 + 5, canvas.getHeight() / 2 + 2);
                gc.strokeLine(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 - 1, canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 5);



            }

        };





        KeyValue  keyValueX =       new KeyValue(xs, canvas.getWidth() - D);
        KeyValue  keyValueY   =  new KeyValue(ys, canvas.getHeight() - D);

        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        return timeline;
    }

    public void PauseClicked() {

        System.out.println("Paused");
        timeline.pause();



    }

    public void StopClicked() {

        System.out.println("Program Stopped");

        timeline.stop();
        setxs(0);
        setys(0);
    }

    public void commandField() {
        System.out.println("start method");
    }

    @Override
    public void run() {

    }
}

