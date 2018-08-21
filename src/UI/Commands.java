package UI;

public class Commands {

    String Command;

    double F;
    double X;
    double Y;
    double I;
    double J;


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
    }

    public void setF(double f) {
        F = f;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setI(double i) {
        I = i;
    }

    public void setJ(double j) {
        J = j;
    }

    public void setM00(boolean m00) {
        M00 = m00;
    }

    public void setM02(boolean m02) {
        M02 = m02;
    }

    public void setM03(boolean m03) {
        M03 = m03;
    }

    public void setM04(boolean m04) {
        M04 = m04;
    }

    public void setM05(boolean m05) {
        M05 = m05;
    }

    public void setM08(boolean m08) {
        M08 = m08;
    }

    public void setM09(boolean m09) {
        M09 = m09;
    }

    public void setM13(boolean m13) {
        M13 = m13;
    }

    public void setM14(boolean m14) {
        M14 = m14;
    }
}
