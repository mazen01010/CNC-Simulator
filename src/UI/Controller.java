package UI;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Controller implements Runnable{

    public Button Start;
    public Button Pause;
    public Button Stop;
    public Label CommandLabel;
    public TextArea commandField; // to write M- and G-Codes
    public Canvas canvas; // main display of simulator
    public Canvas status; // to display status of operations


    public ArrayList<Commands> commandsArrayList = new ArrayList<>(); // to save objects created when start button is pressed

    double Xe; // end X coordinate
    double Ye; // end Y coordinate

    public  double Xs;  // start X coordinate
    public  double Ys;  // start Y coordinate

    static  Path path = new Path();
    static long duration;
    Duration durationNew = new Duration(duration);

    Animation animation ;
    Location oldLocation = null;


    public double getXe(){return Xe;}

    public void setXe(double xe){Xe = xe + (canvas.getWidth()/2);}

    public double getYe(){return Ye;}

    public void setYe(double ye){Ye =  (canvas.getHeight()/2) - ye ;}

    public  void setXs(double xs) {
        Xs = (canvas.getWidth()  /2) + xs;
    }

    public  void setYs(double ys) {
        Ys = (canvas.getHeight()  /2) - ys;
    }

    public double getXs() {
        return Xs;
    }

    public double getYs() {
        return Ys;
    }

    public void initialize() {
        // initialize the main display
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);
        gc.strokeLine(canvas.getWidth() / 2 -4, canvas.getHeight() / 2 , canvas.getWidth() / 2 +4, canvas.getHeight() / 2 );
        gc.strokeLine(canvas.getWidth() / 2 , canvas.getHeight() / 2 -4, canvas.getWidth() / 2 , canvas.getHeight() / 2 +4);
    }

    public void StartClicked() throws Exception {

        int i =0;
        if (i == 0 ) {
            System.out.println("Started");
            HandleCommandField();
            drawShapes();
            System.out.println(commandField.getText());
            // just for checking... should be deleted after program finished
            Machine.setF(1000);
            Path path = createPath();
            animation = createPathAnimation(path, Duration.seconds(duration));
            animation.play();
            i++;
        }else
            animation.jumpTo(animation.getCurrentTime());
            animation.playFrom(durationNew);
    }

    public static class Location {
        double x;
        double y;
    }

    private Path createPath() {

        Path path = Controller.path;
        path.setStroke(Color.GRAY);
        path.setStrokeWidth(1);
        return path;
    }

    private Animation createPathAnimation(Path path, Duration duration) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Circle circle = new Circle(0, 0, 4, Color.RED);
        circle.toFront();

        // create path transition
        PathTransition pathTransition = new PathTransition( duration, path, circle);
        pathTransition.currentTimeProperty().addListener( new ChangeListener<Duration>() {

            //Draw a line from the old location to the new location
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                // skip starting at 0/0
                if( oldValue == Duration.ZERO)
                    return;

                // get current location
                double x = circle.getTranslateX();
                double y = circle.getTranslateY();

                // initialize the location
                if( oldLocation == null) {
                    oldLocation = new Location();
                    oldLocation.x = x;
                    oldLocation.y = y;
                    return;
                }
                // draw line
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1);
                gc.strokeLine(oldLocation.x, oldLocation.y, x, y);
                // update old location with current one
                oldLocation.x = x;
                oldLocation.y = y;
                String X = "1";
                String Y = "1";
                X.equals(x);
                Y.equals(y);
            }
        });
        return pathTransition;
    }

    /*   this method loops on the commandArrayList to create objects related to commands or change the Machine status
         that have been written in the CommandField.
         it also checks Machine status and stops the program if any wrong command have been written */
    private void drawShapes (){
        // settings for status canvas

        status.getGraphicsContext2D().setFill(Color.RED);
        status.getGraphicsContext2D().setFontSmoothingType(FontSmoothingType.LCD);
        status.getGraphicsContext2D().setFont(Font.font(12));
        status.getGraphicsContext2D().setTextBaseline(VPos.TOP);

        for (int i = 1; i<commandsArrayList.size(); i++) {

            Commands  command = commandsArrayList.get(i);

            if (command.getCommand().equals("G00") ) {
                    setXs(command.Xe);
                    setYs(command.Ye);
                    G00 g00 = new G00(getXs(), getYs());
            } else if (command.getCommand().equals("G01")) {
                if ( Machine.getM02() == true ){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    System.out.println("The program is already stopped");
                    status.getGraphicsContext2D().fillText("Status: The program is already stopped",0,0);
                    break;
                }else if (Machine.getM00() == true){
                    System.out.println("please resume the program");
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: please resume the program",0,0);
                    break;
                }else if ( Machine.getM05() == true ){
                    System.out.println("please turn on the spindle");
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: please turn on the spindle",0,0);
                    break;
                }else
                setXe(command.Xe);
                setYe(command.Ye);
                G01 g01 = new G01(getXe(),getYe());
                setXs(command.Xe);
                setYs(command.Ye);

            } else if (command.getCommand().equals("G02")) {
                if ( Machine.getM02() == true ){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: The program is already stopped",0,0);
                    break;
                }else if (Machine.getM00() == true){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: please resume the program",0,0);
                    break;
                }else if ( Machine.getM05() == true ){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: please turn on the spindle",0,0);
                    break;
                }else
                setXe(command.Xe);
                setYe(command.Ye);
                G02 g02 = new G02(getXs(),getYs(),getXe(),getYe(),command.I,command.J);
                setXs(command.Xe);
                setYs(command.Ye);
            } else if (command.getCommand().equals("G03")) {
                if ( Machine.getM02() == true ){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: The program is already stopped",0,0);
                    break;
                }else if (Machine.getM00() == true){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: please resume the program",0,0);
                    break;
                }else if ( Machine.getM05() == true ){
                    status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    status.getGraphicsContext2D().fillText("Status: please turn on the spindle",0,0);
                    break;
                }else
                setXe(command.Xe);
                setYe(command.Ye);
                G03 g03 = new G03(getXs(),getYs(),getXe(),getYe(),command.I,command.J);
                setXs(command.Xe);
                setYs(command.Ye);
            }else if (command.getCommand().contains("G28")){
                setXs(0);
                setYs(0);
                G28 g28 = new G28(getXs(),getYs());
            }else if (command.getCommand().contains("M00")){
                Machine.setM00(true);
            }else if (command.getCommand().contains("M02")){
                Machine.setM02(true);
            }else if (command.getCommand().contains("M03")){
                Machine.setM03(true);
            }else if (command.getCommand().contains("M04")){
                Machine.setM04(true);
            }else if (command.getCommand().contains("M05")){
                Machine.setM05(true);
            }else if (command.getCommand().contains("M08")){
                Machine.setM08(true);
            }else if (command.getCommand().contains("M09")){
                Machine.setM09(true);
            }else if (command.getCommand().contains("M13")){
                Machine.setM13(true);
            }else if (command.getCommand().contains("M14")){
                Machine.setM14(true);
            }
        }
    }
    /* handles Pause button */
    public void PauseClicked() throws Exception {

        System.out.println("Paused");
        status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        status.getGraphicsContext2D().fillText("Status: Program Paused",0,0);

        durationNew = animation.getCurrentTime();
            animation.pause();

    }
    /*handles Stop button */
    public void StopClicked() {

        System.out.println("Program Stopped");
        status.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        status.getGraphicsContext2D().fillText("Status: Program Stopped",0,0);
        animation.stop();
        commandsArrayList.clear();
        path.getElements().clear();
    }
    /*This method should loop on CommandField and handle the text have been written and
     * create respective commands then add commands to commandsArrayList.
     * line numbers are ignored therefor lines are being executed sequentially */
    public void HandleCommandField(){

        String str = commandField.getText();
        ArrayList<String> N = new ArrayList<>();

        for (String n : str.toUpperCase().split("N")) {
            N.add(n);
        }

        for (int i = 0 ; i <N.size(); i++){

            ArrayList<String> subString = new ArrayList<>();
            String string = N.get(i);

            for (String commandline : string.toUpperCase().split(" ")) {
              subString.add(commandline);
            }

            Commands command = new Commands();

            for (int j = 0; j<subString.size(); j++) {

                String field = subString.get(j);

                if (j==0){
                    field = " ";
                }

                   if (field.charAt(0) == 'G') {
                     if (field.contains("00")) {
                         command.setCommand(field);
                     } else if (field.contains("01")) {
                         command.setCommand(field);
                     } else if (field.contains("02")) {
                         command.setCommand(field);
                     } else if (field.contains("03")) {
                         command.setCommand(field);
                     } else ;
                  }

                  if (field.charAt(0) == 'M'){
                            if (field.contains("00")) {
                                command.setCommand(field);
                            } else if (field.contains("02")) {
                                command.setCommand(field);
                            } else if (field.contains("03")) {
                                command.setCommand(field);
                            } else if (field.contains("04")) {
                                command.setCommand(field);
                            } else if (field.contains("05")) {
                                command.setCommand(field);
                            } else if (field.contains("08")) {
                                command.setCommand(field);
                            } else if (field.contains("09")) {
                                command.setCommand(field);
                            } else if (field.contains("13")) {
                                command.setCommand(field);
                            } else if (field.contains("14")) {
                                command.setCommand(field);
                            }
                  }

                  if (field.charAt(0) == 'X'){
                            command.setXe(Double.parseDouble(field.substring(1))); }
                  if (field.charAt(0) == 'Y'){
                            command.setYe(Double.parseDouble(field.substring(1))); }
                  if (field.charAt(0) == 'F'){
                            command.setF(Double.parseDouble(field.substring(1)));  }
                  if (field.charAt(0) == 'I'){
                            command.setI(Double.parseDouble(field.substring(1)));  }
                  if (field.charAt(0) == 'J') {
                            command.setJ(Double.parseDouble(field.substring(1)));  }
                  }
                  commandsArrayList.add(command);
                }
          }

    @Override
    public void run() {

    }
}

