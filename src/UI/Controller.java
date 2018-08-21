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
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
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




    public ArrayList<Commands> commandsArrayList = new ArrayList<>(); // to save objects created when start button is pressed


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

        gc.setStroke(Color.RED);
        gc.strokeOval(getxs()-5,getys()-5,D+10,D+10);
        gc.strokeOval(getxs()+1,getys()+1,1,1);
    }




    public void StartClicked() throws Exception {

        System.out.println("Started");
        System.out.println("Hi Im ");
        ArrayList<String> strings = new ArrayList<>();



        String str = commandField.getText();

        for (String strs : str.toUpperCase().split(" ")) {
            strings.add(strs);
        }




       /* for (int i = 0 ; i <=strings.size(); i++){

            ArrayList<String> subStrings = new ArrayList<>();

            String string = strings.get(i);
            if ( string.contains("N")){
                for (int j = i+1; j <= strings.size(); j++){
                    String string1 = strings.get(j);
                    if (!string1.contains("N")){
                        subStrings.add(string1);
                    }else break;
                }
                for (int z = 0 ; z<= subStrings.size(); z++){
                    String substring = strings.get(i).substring(0,0);
                    Commands command = new Commands();
                    switch (substring){

                        case "G":
                            if (substring.contains("00")){
                                command.setCommand(substring);
                            }else if (substring.contains("01")){
                                command.setCommand(substring);
                            }else if (substring.contains("02")){
                                command.setCommand(substring);
                            }else if (substring.contains("03")){
                                command.setCommand(substring);
                            }else throw new Exception();

                        case "M":
                            if (substring.contains("00")){
                                command.setM00(true);
                            }else if (substring.contains("02")){
                                command.setM02(true);
                            }else if (substring.contains("03")){
                                command.setM03(true);
                            }else if (substring.contains("04")){
                                command.setM04(true);
                            }else if (substring.contains("05")){
                                command.setM05(true);
                            }else if (substring.contains("08")){
                                command.setM08(true);
                            }else if (substring.contains("09")){
                                command.setM09(true);
                            }else if (substring.contains("13")){
                                command.setM13(true);
                            }else if (substring.contains("14")){
                                command.setM14(true);
                            }

                        case "X":
                            command.setX(Double.parseDouble(substring));
                        case "Y":
                            command.setY(Double.valueOf(substring));
                        case "F":
                            command.setF(Double.valueOf(substring));
                        case "I":
                            command.setI(Double.valueOf(substring));
                        case "J":
                            command.setJ(Double.valueOf(substring));
                    }
                    commandsArrayList.add(command);
                }

            }
        }*/


        System.out.println(commandField.getText());

        //Arc arc = new Arc(658,658,85,85,65,45);

        // just for checking... should be deleted after program finished
        Machine.setF(1000);

        try {


           // Circle circle = new Circle(0,0,5);
            timeline = new Timeline(

                    new KeyFrame(Duration.seconds(15000 / Machine.getF()),
                            new KeyValue(xs, canvas.getWidth() - D, Interpolator.TANGENT(Duration.seconds(15000 / Machine.getF()),ys.doubleValue()/xs.doubleValue())),
                            new KeyValue(ys, canvas.getHeight() - D)

                    )
            );
        } catch (Exception e) {
            System.out.println("please enter Feed Rate"+ e );
        }


        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);



         timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setStroke(Color.RED);

                gc.strokeOval(getxs()-5,getys()-5,D+10,D+10);
                gc.strokeOval(getxs()+1,getys()+1,1,1);

                gc.setStroke(Color.GREEN);
                gc.strokeLine(canvas.getWidth() / 2 - 1, canvas.getHeight() / 2 + 2, canvas.getWidth() / 2 + 5, canvas.getHeight() / 2 + 2);
                gc.strokeLine(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 - 1, canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 5);


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
                gc.save();
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
        timer.stop();
    }

    public void StopClicked() {

        System.out.println("Program Stopped");

        timeline.stop();
        timer.stop();
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

