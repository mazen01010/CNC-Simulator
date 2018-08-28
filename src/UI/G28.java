package UI;
import javafx.scene.shape.MoveTo;

public class G28 extends geometric {

    /*This should create a linear movement to home position without interpolation  */
    public  G28(double X, double Y){

        Machine.getMaxFeedRate();

        // increase the duration of path transition with the period needed to move between start and end points
        Controller.duration += (long)  15000/Machine.getMaxFeedRate();

        // add the movement to the path elements
        Controller.path.getElements().add(new MoveTo(X,Y));
    }
}
