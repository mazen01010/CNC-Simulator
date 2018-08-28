package UI;
import javafx.scene.shape.ArcTo;
import static java.lang.Math.*;

public class G02 extends geometric {

    double R ; // radius of the arc

    /*This method should create a clockwise circular movement with interpolation after checking the status of the coolant  */
    public  G02 (double Xs, double Ys, double Xe, double Ye, double I, double J) {

        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());

        // increase the duration of path transition with the period needed to move between start and end points
        Controller.duration += (long)  15000/Machine.getF();

        double Xc = Xs + I;
        double Yc = Ys - J;

        R = abs(sqrt( (Xs - Xc )*(Xs - Xc) + (Ys - Yc)*(Ys - Yc)));

        ArcTo arcTo = new ArcTo();
        arcTo.setX(Xe);
        arcTo.setY(Ye);

        arcTo.setRadiusX(R);
        arcTo.setRadiusY(R);
        arcTo.setSweepFlag(true);
        // add the movement to the path elements
        Controller.path.getElements().add(arcTo);
    }
}
