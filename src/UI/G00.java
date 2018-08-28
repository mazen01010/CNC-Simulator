package UI;
import javafx.scene.shape.MoveTo;

public class G00 extends geometric {

    /*This method should create a move without interpolation with max feed rate of 4000 */
    public  G00(double X, double Y){
        Machine.getMaxFeedRate();

        // increase the duration of path transition with the period needed to move between start and end points
        Controller.duration += (long)  15000/Machine.getMaxFeedRate();

        // add the movement to the path elements
        Controller.path.getElements().add(new MoveTo(X, Y));
    }
}
