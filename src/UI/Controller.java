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
        System.out.println("Hi Im Wiki");

        commandField.getText();
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
