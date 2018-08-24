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
import javafx.scene.shape.*;
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

    DoubleProperty Xe  = new SimpleDoubleProperty();
    DoubleProperty Ye  = new SimpleDoubleProperty();

    public  double Xs;
    public  double Ys;





    Duration duration = Duration.seconds(15000 / Machine.getF());


    Timeline timeline = new Timeline();
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {

        }
    };


    // Define a getter for the property's value
    public final double getXe(){return Xe.get();}

    // Define a setter for the property's value
    public final void setXe(double value){Xe.set(value + canvas.getWidth()/2);}

    // Define a getter for the property itself
    public DoubleProperty XeProperty() {return Xe;}

    // Define a getter for the property's value
    public final double getYe(){return Ye.get();}

    // Define a setter for the property's value
    public final void setYe(double value){Ye.set(value + canvas.getHeight()/2);}

    // Define a getter for the property itself
    public DoubleProperty YeProperty() {return Ye;}

    public  void setXs(double xs) {
        Xs = xs;
    }

    public  void setYs(double ys) {
        Ys = ys;
    }

    public void initialize() {

        setXe(0);
        setYe(0);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);
        gc.strokeLine(canvas.getWidth()/2 -1,canvas.getHeight()/2 +2,canvas.getWidth()/2 + 5,canvas.getHeight()/2 +2);
        gc.strokeLine(canvas.getWidth()/2 +2,canvas.getHeight()/2 -1,canvas.getWidth()/2 +2,canvas.getHeight()/2 +5);

        gc.setStroke(Color.RED);
        gc.strokeOval(getXe()-5,getYe()-5,D+10,D+10);
        gc.strokeOval(getXe()+1,getYe()+1,1,1);




    }


    public void StartClicked() throws Exception {

        System.out.println("Started");
        System.out.println("Hi Im ");


        HandleCommandField();



        System.out.println(commandField.getText());

        // just for checking... should be deleted after program finished
        Machine.setF(1000);

        Thread thread = new Thread();




            try {


                timeline = new Timeline(

                        new KeyFrame(Duration.seconds(15000 / Machine.getF()),
                                new KeyValue(Xe, Xs - D    ), // Interpolator.TANGENT(Duration.seconds(15000 / Machine.getF()),Ye.doubleValue()/Xe.doubleValue())
                                new KeyValue(Ye, Ys - D)

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

                    gc.strokeOval(getXe()-5,getYe()-5,D+10,D+10);
                    gc.strokeOval(getXe()+1,getYe()+1,1,1);


                        gc.strokeLine(canvas.getWidth() / 2, canvas.getHeight() / 2, Xe.doubleValue(), Ye.doubleValue());

                        gc.strokeArc(canvas.getWidth() / 2, canvas.getHeight() / 2, Xe.doubleValue(), Ye.doubleValue(),25,150,ArcType.OPEN);

                    gc.setStroke(Color.GREEN);
                    gc.strokeLine(canvas.getWidth() / 2 - 1, canvas.getHeight() / 2 + 2, canvas.getWidth() / 2 + 5, canvas.getHeight() / 2 + 2);
                    gc.strokeLine(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 - 1, canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 5);




                }


            };

            timeline.play();
            timer.start();






      /* GraphicsContext gc = canvas.getGraphicsContext2D();
       drawShapes(gc);

       timeline = new create().createTimeLine(Xe,Ye,Xs,Ys);


       timer = new create().animationTimer(Xe,Ye,Xs,Ys);

       timeline.setAutoReverse(false);
       timeline.setCycleCount(1);

       timeline.play();
       timer.start(); */


    }

    private GraphicsContext drawShapes ( GraphicsContext gc){




        // create command just for illustration
        Commands command = new Commands();
        command.setXs(200);
        command.setYs(200);
        command.setXe(400);
        command.setYe(300);
        command.setCommand("G01");

        setXs(command.Xs);
        setYs(command.Ys);
        setXe(command.Xe);
        setYe(command.Ye);
        Machine.setF(1000);


       // timeline = new create().createTimeLine(Xe,Ye,Xs,Ys);


        //timer = new create().animationTimer(Xe,Ye,Xs,Ys);



       /* for (int i = 0 ; i <= commandsArrayList.size(); i++){
            String string =  commandsArrayList.get(i).Command;

            switch (string){

                case "G01":

                    setXs(command.Xs);
                    setYs(command.Ys);
                    setXe(command.Xe);
                    setYe(command.Ye);



                    //gc.strokeLine(command.Xs,command.Ys,command.Xe,command.Ye);

                    try {
                    timeline = new Timeline(

                            new KeyFrame(Duration.seconds(15000 / Machine.getF()),
                                    new KeyValue(Xe, Xs - D /* , Interpolator.TANGENT(Duration.seconds(15000 / Machine.getF()),Ye.doubleValue()/Xe.doubleValue())  ),
                                    new KeyValue(Ye, Ys - D)

                            )
                    );

                    } catch (Exception e) {
                             System.out.println("please enter Feed Rate"+ e );
                             }

                    timer = new AnimationTimer() {

                        @Override
                        public void handle(long now) {
                            //GraphicsContext gc = canvas.getGraphicsContext2D();

                            gc.setFill(Color.GRAY);
                            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            gc.setStroke(Color.RED);

                            gc.strokeOval(getXe()-5,getYe()-5,D+10,D+10);
                            gc.strokeOval(getXe()+1,getYe()+1,1,1);

                            //gc.strokeLine(canvas.getWidth()/2,canvas.getHeight()/2,Xe.doubleValue(),Ye.doubleValue());

                            gc.setStroke(Color.GREEN);
                            gc.strokeLine(canvas.getWidth() / 2 - 1, canvas.getHeight() / 2 + 2, canvas.getWidth() / 2 + 5, canvas.getHeight() / 2 + 2);
                            gc.strokeLine(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 - 1, canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 5);

                            gc.strokeLine(command.Xs,command.Ys,command.Xe,command.Ye);

                        }


                    };

            }
        }*/
       return gc;
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
        setXe(0);
        setYe(0);
    }

    public void commandField() {
        System.out.println("start method");
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

