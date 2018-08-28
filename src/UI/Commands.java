package UI;

public class Commands {

    String Command;

    double F;   // Feed Rate
    double Xs;  // start X
    double Ys;  // start Y
    double Xe;  // end X
    double Ye;  // end Y
    double I;   // the distance difference between Xs of the arc and X-coordinate of arc center
    double J;   // the distance difference between Ys of the arc and Y-coordinate of arc center

    boolean M00 = false; // Program pause
    boolean M02 = true; // Program stop
    boolean M03 = false; // Spindle On clockwise
    boolean M04 = false; // Spindle On counter clockwise
    boolean M05 = true; //Spindle Off
    boolean M08 = false; // coolant On
    boolean M09 = true; // coolant Off
    boolean M13 = false; // Spindle On clockwise with coolant
    boolean M14 = false; // Spindle On Counter clockwise with coolant


    public void setCommand(String command) {
        Command = command;
    } // set command name e.g.: G01

    public String getCommand() {
        return Command;
    }

    public void setF(double f) {
        F = f;
    }

    public void setXs(double xs) {
        Xs = xs;
    }

    public void setXe(double xe) {
        Xe = xe;
    }

    public void setYs(double ys) {
        Ys = ys;
    }

    public void setYe(double ye) {
        Ye = ye;
    }

    public void setI(double i) {
        I = i;
    }

    public void setJ(double j) {
        J = j;
    }

}
