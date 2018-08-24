package UI;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static UI.Controller.D;


public class create {

    Canvas canvas  = new Canvas();

    public Timeline createTimeLine(DoubleProperty Xe, DoubleProperty Ye, double Xs, double Ys){

        Timeline timeline = new Timeline();

        KeyValue value = new KeyValue(Xe, Xs - D);
        KeyValue value1 = new KeyValue(Ye, Ys - D);
        KeyFrame frame = new KeyFrame(Duration.seconds(15000/Machine.getF()),value,value1);

        timeline.getKeyFrames().add(frame);
        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);


        return timeline;
    }

    public AnimationTimer animationTimer (DoubleProperty Xe, DoubleProperty Ye, double Xs, double Ys){

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                GraphicsContext gc = canvas.getGraphicsContext2D();

                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setStroke(Color.RED);

                gc.strokeOval(Xe.doubleValue() -5,Ye.doubleValue()-5,D+10,D+10);
                gc.strokeOval(Xe.doubleValue() + 1,Ye.doubleValue() + 1,1,1);

                gc.setStroke(Color.GREEN);
                gc.strokeLine(canvas.getWidth() / 2 - 1, canvas.getHeight() / 2 + 2, canvas.getWidth() / 2 + 5, canvas.getHeight() / 2 + 2);
                gc.strokeLine(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 - 1, canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 5);

                gc.setStroke(Color.BLACK);

                gc.strokeLine(Xs,Ys,Xe.doubleValue(),Ye.doubleValue());
            }
        };

        return animationTimer;
    }
}
