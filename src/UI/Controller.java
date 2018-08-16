package UI;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light;
import javafx.scene.layout.Background;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

import static javafx.geometry.VPos.CENTER;
import static javafx.scene.input.KeyCode.R;

public class Controller  {


    public Button Start;
    public Button Pause;
    public Button Stop;
    public Label CommandLabel;
    public Canvas canvas;
    public TextArea commandField;

    public ArrayList<Objects> objects = new ArrayList<>(); // to save objects created when start button is pressed



    public void initialize(){





        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setLayoutX(canvas.getWidth()/2);
        canvas.setLayoutY(canvas.getHeight()/2);

        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.stroke();

        gc.setStroke(Color.RED);
        gc.strokeOval(canvas.getWidth()/2,canvas.getHeight()/2,10,10);
        gc.setStroke(Color.GREEN);
        gc.strokeText("+",canvas.getWidth()/2 +1,canvas.getHeight()/2 + 8);
       // Arc arc = new  Arc(15,36,69,36,36,8);
        //Point2D point2D = new Point2D(52,25);
        //gc.strokeLine(point2D.getX(),point2D.getY(),point2D.getX(),point2D.getY());




        gc.setStroke(Color.BLACK);
        gc.strokeArc(115,115,550,556,836,66, ArcType.OPEN);


        G02 ge = new G02(50,50,0,0.5);


        for (int i =0 ; i< ge.arcs.size(); i++){

            gc.setStroke(Color.RED);
            gc.strokeArc(ge.arcs.get(i).getCenterX(),ge.arcs.get(i).getCenterY(),ge.arcs.get(i).getRadiusX(),ge.arcs.get(i).getRadiusY(),ge.arcs.get(i).getStartAngle(),ge.arcs.get(i).getLength(),ge.arcs.get(i).getType());
            i++;

        }
        gc.strokeArc(ge.arcs.get(1).getCenterX(),ge.arcs.get(1).getCenterY(),ge.arcs.get(1).getRadiusX(),ge.arcs.get(1).getRadiusY(),ge.arcs.get(1).getStartAngle(),ge.arcs.get(1).getLength(),ge.arcs.get(1).getType());

    }


    public void StartClicked(){

        System.out.println("Started");
        System.out.println("Hi Im ");
        ArrayList<String> strings = new ArrayList<>();

        double X;
        double Y;
        double I;
        double J;
        double F;

        String str = commandField.getText();

        for (String strs: str.toUpperCase().split(" ")) {
            strings.add(strs);
        }
        for (int i = 0 ; i <= strings.size(); i++){

            String string = strings.get(i);

            switch (string){

                case "N":;
                case "G00": G00 g00 = new G00();
                case "G01": G01 g01 = new G01();
                //case "G02": //G02 g02 = new G02();
                case "X":;
                case "Y":;
                case "I":;
                case "J":;
                case "F":;
                case "M00": Machine.setM00(true);
                case "M02": Machine.setM02(true);
                case "M03": Machine.setM03(true);
                case "M04": Machine.setM04(true);
                case "M05": Machine.setM05(true);
                case "M08": Machine.setM08(true);
                case "M09": Machine.setM09(true);
                case "M13": Machine.setM13(true);
                case "M14": Machine.setM14(true);
                default: System.out.println("please enter valid expression");

            }

        }



        System.out.println(commandField.getText());

    }

    public void PauseClicked(){

        System.out.println("Paused");
    }

    public void StopClicked(){

        System.out.println("Program Stopped");
    }

    public  void commandField(){
        System.out.println("start method");
    }

}
