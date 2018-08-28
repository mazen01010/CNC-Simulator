package UI;
import javafx.scene.shape.ArcTo;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class G03 extends geometric {

    double R ;

    public G03 (double Xs, double Ys, double Xe, double Ye, double I, double J) {

        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());

        Controller.duration += (long)  15000/Machine.getF();

        double Xc = Xs - I;
        double Yc = Ys - J;

        R = abs(sqrt( (Xs - Xc )*(Xs - Xc) + (Ys - Yc)*(Ys - Yc)));

        ArcTo arcTo = new ArcTo();
        arcTo.setX(Xe);
        arcTo.setY(Ye);

        arcTo.setRadiusX(R);
        arcTo.setRadiusY(R);

        Controller.path.getElements().add(arcTo);
    }
}
