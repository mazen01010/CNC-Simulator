package UI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class Controller{


    public Button Start;
    public Button Pause;
    public Button Stop;
    public Label ComandLabel;
    public Canvas canvas;

    public void initialize(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.GRAY);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
