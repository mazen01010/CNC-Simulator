package UI;
import javafx.scene.shape.MoveTo;

public class G28 extends geometric {

    public  G28(double X, double Y){

        Machine.getMaxFeedRate();

        Controller.duration += (long)  15000/Machine.getMaxFeedRate();

        Controller.path.getElements().add(new MoveTo(X,Y));
    }
}
