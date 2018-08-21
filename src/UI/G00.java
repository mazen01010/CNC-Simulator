package UI;

public class G00 extends geometric {

    public  void G00(double X, double Y){
        Machine.getMaxFeedRate();


        geometric.X = X;
        geometric.Y= Y;

        // return new LineTo(geometric.X, geometric.Y);
    }
}
