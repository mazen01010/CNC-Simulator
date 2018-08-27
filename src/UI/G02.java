package UI;


import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Path;
import static java.lang.Math.*;


public class G02 extends geometric {




    double R ;

    double theta1 ;
    double theta2 ;
    double length;

    public  G02 (double Xs, double Ys, double Xe, double Ye, double I, double J) {

        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());

        Controller.duration += (long)  15000/Machine.getF();

        double Xc = Xs - I;
        double Yc = Ys - J;

         R = abs(sqrt( (Xs - Xc )*(Xs - Xc) + (Ys - Yc)*(Ys - Yc)));

         theta1 = toDegrees( Math.acos((Xs - Xc)/R));
         theta2 = toDegrees( Math.acos((Xe - Xc)/R));

        if (theta1>=theta2){
            length = theta1 - theta2;
        }else length = 360 - theta2;

        ArcTo arcTo = new ArcTo();
        arcTo.setX(Xc);
        arcTo.setY(Yc);

        arcTo.setRadiusX(R);
        arcTo.setRadiusY(R);

        Controller.path.getElements().add(arcTo);





    }
}
