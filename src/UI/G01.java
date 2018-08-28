package UI;
import javafx.scene.shape.LineTo;

public class G01 extends geometric {

    /*This method should create a linear movement with interpolation after checking the status of the coolant*/
    public G01(double X , double Y){
        if (Machine.getM08()){
            Machine.setF(Machine.getMaxFeedRate1());
        }else Machine.setF(Machine.getMaxFeedRate2());

        // increase the duration of path transition with the period needed to move between start and end points
        Controller.duration += (long)  15000/Machine.getF();

        // add the movement to the path elements
        Controller.path.getElements().add(new LineTo(X,Y));
    }
}
