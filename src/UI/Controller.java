package UI;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.ArrayList;
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

    double Xe;
    double Ye;

    public  double Xs;
    public  double Ys;

    static  Path path = new Path();
    static long duration;

    Animation animation ;

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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);
        gc.strokeLine(canvas.getWidth() / 2 -4, canvas.getHeight() / 2 , canvas.getWidth() / 2 +4, canvas.getHeight() / 2 );
        gc.strokeLine(canvas.getWidth() / 2 , canvas.getHeight() / 2 -4, canvas.getWidth() / 2 , canvas.getHeight() / 2 +4);
    }

    public void StartClicked() throws Exception {

        System.out.println("Started");
        System.out.println("Hi Im ");

        HandleCommandField();

        drawShapes();

        System.out.println(commandField.getText());

        // just for checking... should be deleted after program finished
        Machine.setF(1000);

        Path path = createPath();

        animation = createPathAnimation(path, Duration.seconds(duration));
        animation.play();
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

        Circle pen = new Circle(0, 0, 10, Color.RED);

        // create path transition
        PathTransition pathTransition = new PathTransition( duration, path, pen);
        pathTransition.currentTimeProperty().addListener( new ChangeListener<Duration>() {

            Location oldLocation = null;

            /**
             * Draw a line from the old location to the new location
             */
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                // skip starting at 0/0
                if( oldValue == Duration.ZERO)
                    return;

                // get current location
                double x = pen.getTranslateX();
                double y = pen.getTranslateY();

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
            }
        });

        return pathTransition;
    }


    private void drawShapes (){

        for (int i = 1; i<commandsArrayList.size(); i++) {

            Commands  command = commandsArrayList.get(i);


            if (command.getCommand().equals("G00") ) {
                setXs(command.Xe);
                setYs(command.Ye);
                G00 g00 = new G00(getXs(),getYs());

            } else if (command.getCommand().equals("G01")) {
                setXe(command.Xe);
                setYe(command.Ye);
                G01 g01 = new G01(getXe(),getYe());
                setXs(command.Xe);
                setYs(command.Ye);

            } else if (command.getCommand().equals("G02")) {
                setXe(command.Xe);
                setYe(command.Ye);
                G02 g02 = new G02(getXs(),getYs(),getXe(),getYe(),command.I,command.J);
                setXs(command.Xe);
                setYs(command.Ye);
            } else if (command.getCommand().equals("G03")) {
                setXe(command.Xe);
                setYe(command.Ye);
                G03 g03 = new G03(getXs(),getYs(),getXe(),getYe(),command.I,command.J);
                setXs(command.Xe);
                setYs(command.Ye);
            }
        }
    }

    public void PauseClicked() {

        System.out.println("Paused");
        animation.pause();

    }

    public void StopClicked() {

        System.out.println("Program Stopped");
        animation.stop();
    }

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
                                command.setM00(true);
                            } else if (field.contains("02")) {
                                command.setM02(true);
                            } else if (field.contains("03")) {
                                command.setM03(true);
                            } else if (field.contains("04")) {
                                command.setM04(true);
                            } else if (field.contains("05")) {
                                command.setM05(true);
                            } else if (field.contains("08")) {
                                command.setM08(true);
                            } else if (field.contains("09")) {
                                command.setM09(true);
                            } else if (field.contains("13")) {
                                command.setM13(true);
                            } else if (field.contains("14")) {
                                command.setM14(true);
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

