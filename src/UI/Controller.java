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


        Arc arc = new  Arc(15,36,69,36,36,8);
        Point2D point2D = new Point2D(52,25);
        gc.strokeLine(point2D.getX(),point2D.getY(),point2D.getX(),point2D.getY());




        //gc.setStroke(Color.RED);
        //gc.strokeArc(arc.getLayoutX(),arc.getLayoutY(),arc.getRadiusX(),arc.getRadiusY(),arc.getStartAngle(),arc.getLength(), ArcType.OPEN);



        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.RED);
       // gc.fillOval(canvasWidth,canvasHeight,10,10);
        gc.stroke();


        //canvas.getGraphicsContext2D().arcTo(15,175,115,151,15);
        canvas.getGraphicsContext2D().strokeArc(15,15,50,56,86,66, ArcType.OPEN);
        gc.strokeArc(115,115,550,556,836,66, ArcType.OPEN);
       // gc.beginPath();
        //gc.arcTo(15,186,572,52,557);
        //gc.stroke();

        gc.strokeOval(canvas.getWidth()/2,canvas.getHeight()/2,10,10);
        gc.setStroke(Color.GREEN);
        gc.strokeText("+",canvas.getWidth()/2 +1,canvas.getHeight()/2 + 8);
        gc.arcTo(5,55,65,78,63);


        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(250.0);
        moveTo.setY(250.0);
        ArcTo arcTo = new ArcTo();

        //setting properties of the path element arc
        arcTo.setX(300.0);
        arcTo.setY(50.0);

        arcTo.setRadiusX(50.0);
        arcTo.setRadiusY(50.0);











       /* PathTransition transition = new PathTransition();

        transition.setDuration(Duration.seconds(2));
       transition.setPath(path);

       transition.setCycleCount(Animation.INDEFINITE);
        transition.play();*/




    }

     void moveCanvas(int x, int y) {

    }
    private void drawDShape() {

    }

    public void StartClicked(){

        System.out.println("Started");
        System.out.println("Hi Im Wiki");

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
