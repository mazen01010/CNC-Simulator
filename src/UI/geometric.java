package UI;

import javafx.geometry.Point2D;
import javafx.scene.shape.*;

import java.util.ArrayList;

import static java.lang.Math.*;

public class geometric {

    public static final double homeX = 0;
    public static final double homeY = 0;
    public static double X =0;
    public static double Y = 0;


    public  void G00(double X, double Y){
        Machine.getMaxFeedRate();



        geometric.X = X;
        geometric.Y= Y;

       // return new LineTo(geometric.X, geometric.Y);
    }

    public  void G01(double X , double Y){
        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());




        ArrayList<Line> lines = new ArrayList<Line>();


        if (X > geometric.X) {
            double incY = (Y - geometric.Y) / (X - geometric.X);
            double iLoop = geometric.X;

            for (double i = iLoop; i <= X; i++) {
                double XNext = geometric.X + 1;
                double YNext = geometric.Y + incY;
                int counter = 0;
                Line line = new Line();
                line.setStartX(geometric.X);
                line.setStartY(geometric.Y);
                line.setEndX(XNext);
                line.setEndY(YNext);
                lines.add(counter, line);
                counter++;
                geometric.X++;
                geometric.Y += incY;
            }
        }else if (X < geometric.X){
            double iLoop = geometric.X;
            double incY = (geometric.Y - Y) / (X - geometric.X);
            int counter = 0;
            for (double i = iLoop; i >= X; i++) {
                double XNext = geometric.X - 1;
                double YNext = geometric.Y + incY;

                Line line = new Line();
                line.setStartX(geometric.X);
                line.setStartY(geometric.Y);
                line.setEndX(XNext);
                line.setEndY(YNext);
                lines.add(counter, line);
                counter++;
                geometric.X--;
                geometric.Y += incY;
            }
        }else {
            double incY = geometric.Y;
            double iLoop = geometric.Y;
            int counter = 0;
            if (Y > geometric.Y){
                for (double i = iLoop; i <= Y ; i++){
                    incY++;
                    Line line = new Line();
                    line.setStartX(geometric.X);
                    line.setEndX(X);
                    line.setStartY(geometric.Y);
                    line.setEndY(incY);
                    lines.add(counter, line);
                    counter++;
                    geometric.Y++;
                }
            }else if (Y < geometric.Y){
                for (double i = iLoop; i >= Y ; i--){
                    incY--;
                    Line line = new Line();
                    line.setStartX(geometric.X);
                    line.setEndX(X);
                    line.setStartY(geometric.Y);
                    line.setEndY(incY);
                    lines.add(counter, line);
                    counter++;
                    geometric.Y--;
                }

            }
        }

        geometric.X = X;
        geometric.Y= Y;

     //return new LineTo(geometric.X, geometric.Y);
    }



}
