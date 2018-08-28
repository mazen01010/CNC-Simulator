package UI;
import javafx.scene.shape.LineTo;

public class G01 extends geometric {

    public G01(double X , double Y){
        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());

        Controller.duration += (long)  15000/Machine.getF();

        Controller.path.getElements().add(new LineTo(X,Y));
    }
}
