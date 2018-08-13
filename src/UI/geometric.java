package UI;

import javafx.geometry.Point2D;
import javafx.scene.shape.*;

import java.util.ArrayList;

import static java.lang.Math.*;

public class geometric {

    public static final double homeX = Controller.canvasWidth;
    public static final double homeY = Controller.canvasHeight;
    public static double X = Controller.canvasWidth;
    public static double Y = Controller.canvasHeight;


    public  void G00(double X, double Y){
        Machine.getMaxFeedRate();
        X = X + Controller.canvasWidth;
        Y = Controller.canvasHeight - Y;


        geometric.X = X;
        geometric.Y= Y;

       // return new LineTo(geometric.X, geometric.Y);
    }

    public  void G01(double X , double Y){
        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());




        ArrayList<Line> lines = new ArrayList<Line>();

        X = X + Controller.canvasWidth;
        Y = Controller.canvasHeight - Y;

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

    public  void G02(double X, double Y, double R) {
        if (Machine.getM08()) {
            Machine.setF(Machine.getMaxFeedRate1());
        } else Machine.setF(Machine.getMaxFeedRate2());

        //double R = abs( Math.sqrt( (X - I)*(X - I) + (Y-J)*(Y-J) ));
        //double startAngle = atan2(geometric.Y - J, geometric.X- I)*180/Math.PI;
        //double length = atan2(Y-J,X-J);
        ArrayList<Point2D> points = new ArrayList<>();
        //X = X + Controller.canvasWidth;
        //Y = Controller.canvasHeight - Y;
        geometric.X = geometric.X - Controller.canvasWidth;
        geometric.Y = geometric.Y - Controller.canvasHeight;

        int counter = 0;
        double incY = geometric.Y;

        if (X - geometric.X >= Y - geometric.Y && Y- geometric.Y >=0 ) {


            for (double i = geometric.X; i <= X; i++) {
                Point2D point2D = new Point2D(geometric.X + Controller.canvasWidth,Controller.canvasHeight- incY);
                points.add(counter, point2D);
                geometric.X++;
                incY = sqrt(R * R - geometric.X * geometric.X);
                geometric.Y = incY;

            }

            if (X - geometric.X >= Y - geometric.Y && Y - geometric.Y < 0) {


                double incX = geometric.X;

                for (double i = geometric.Y; i >= Y; i--) {
                    Point2D point2D = new Point2D(incX + Controller.canvasWidth, Controller.canvasHeight - geometric.Y);
                    points.add(counter, point2D);
                    geometric.Y--;
                    incX = sqrt(R * R - geometric.Y * geometric.Y);
                    geometric.X = incX;
                }
            }
            if (X- geometric.X < Y - geometric.Y){
                int counter1 =0;

                for (double i = geometric.X ; i <= 2*R; i++){
                    Point2D point2D = new Point2D(geometric.X + Controller.canvasWidth, Controller.canvasHeight - geometric.Y);
                    points.add(counter1, point2D);

                    geometric.X++;
                    geometric.Y = sqrt(R*R - geometric.X * geometric.X);
                }

                for (double i = 2*R  ; i >= X; i-- ){
                    Point2D point2D = new Point2D(geometric.X + Controller.canvasWidth , Controller.canvasHeight - geometric.Y);
                    points.add(counter1, point2D);

                    geometric.X--;
                    geometric.Y = -sqrt(R*R - geometric.X* geometric.X);
                }
            }


        }


        geometric.X = X;
        geometric.Y = Y;
       // return new Arc(geometric.X ,geometric.Y ,R ,R, startAngle , length);
    }

    public double atan(double X, double Y , double I, double J){
        double angle;
        if (Y - J >0){
            if (X - I >= Y - J){
                angle = atan2(Y-J,X-I);
            }else if (X-I <= (Y-J)*(-1) ){
                angle = atan2(Y-J,X-I) + Math.PI;
            }else angle = Math.PI/2 - atan2(Y-J,X-I);
        }else
            if (X-I >= (Y-J)*(-1) ){
                angle = atan2(Y-J,X-I);
            }else if (X-I <= Y-J){
                angle = atan2(Y-J,X-I) - PI;
            }else angle = -atan2(X-I,Y-J) - PI/2;
        return angle;
    }

}
