package UI;

import javafx.geometry.Point2D;
import javafx.scene.shape.Arc;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class G02 extends geometric {


    ArrayList<Arc> arcs = new ArrayList<>();

    static double I;
    static double J;

    public G02 (double Xe, double Ye, double I, double J) {

        G02.I = I ;
        G02.J = J ;


        double Xc = geometric.X - I;
        double Yc = geometric.Y - J;

        double R = abs(sqrt( (Xe - Xc )*(Xe - Xc) + (Ye - Yc)*(Ye - Yc)));

        double theta1 = toDegrees(atan2(geometric.Y, geometric.X));
        double theta2 = toDegrees(atan2(Ye,Xe));
        double theta;
        int counter= 0;


        if (theta1 < theta2){
            for (double i = theta1; i <= theta2; i++) {
                theta = i;
                geometric.X = R * cos(theta) + Xc;
                geometric.Y = R * sin(theta) + Yc;
                Arc arc = new Arc(Xc,Yc,R,R,toRadians(theta),1);
                arcs.add(counter, arc);
            }
        }else {
            for (double i = theta1; i >= theta2; i++) {
                if (i == 360) {
                    i = 0;
                    for (i = 0; i <= theta2; i++) {
                        theta = i;
                        geometric.X = R * cos(theta) + Xc;
                        geometric.Y = R * sin(theta) + Yc;
                        Arc arc = new Arc(Xc,Yc,R,R,toRadians(theta),1);
                        arcs.add(counter, arc);
                    }
                }
                theta = i;
                geometric.X = R * cos(theta) + Xc;
                geometric.Y = R * sin(theta) + Yc;
                Arc arc = new Arc(Xc,Yc,R,R,toRadians(theta),1);
                arcs.add(counter, arc);
            }
        }
    }
}
