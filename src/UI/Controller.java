package UI;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.text.TextAlignment;

import static javafx.geometry.VPos.CENTER;

public class Controller{


    public Button Start;
    public Button Pause;
    public Button Stop;
    public Label CommandLabel;
    public Canvas canvas;
    private double x = 5;

    public void initialize(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //gc.setTextAlign(TextAlignment.CENTER);
        //gc.setTextBaseline(VPos.CENTER);
        gc.setStroke(Color.GREEN);
        gc.strokeText("+",canvas.getWidth()/2 -4,canvas.getHeight()/2 + 5);

    }

    public void StartClicked(){

        System.out.println("Started");

    }

    public void PauseClicked(){

        System.out.println("Paused");
    }

    public void StopClicked(){

        System.out.println("Program Stopped");
    }

    public  void commandfield(){
        System.out.println("start method");
    }



}
