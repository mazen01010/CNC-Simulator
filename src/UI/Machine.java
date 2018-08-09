package UI;

public class Machine {
    // Created by Mazen

        private static boolean interpolation = false;
        private static boolean M00 = false; // Program pause
        private static boolean M02 = true; // Program stop
        private static boolean M03 = false; // Spindle On clockwise
        private static boolean M04 = false; // Spindle On counter clockwise
        private static boolean M05 = true; //Spindle Off
        private static boolean M08 = false; // coolant On
        private static boolean M09 = true; // coolant Off
        private static boolean M13 = false; // Spindle On clockwise with coolant
        private static boolean M14 = false; // Spindle On Counter clockwise with coolant
        private static double F = 0; // Feed Rate
        private static final double MaxFeedRate = 4000; // Max Feed Rate G00
        private static final double MaxFeedRate1 = 3000; // Max Feed Rate, interpolating with cooling
        private static final double MaxFeedRate2 = 2000; // Max Feed Rate, interpolating without cooling


        static void setM00(boolean ON){
            boolean M03N = M03;
            boolean M04N = M04;
            double FN = F;
            boolean M13N= M13;
            boolean M14N = M14;
            if(ON){
                setM05(true);
                setM09(true);
                setF(0);
                M00 = true;
            }else if ( !ON && !M02 ){
                setM03(M03N);
                setM04(M04N);
                setM13(M13N);
                setM14(M14N);
                setF(FN);
                M00 = false;}
            else M00 = false;

        }
        static void setM02(boolean ON){
            if(ON){
                M02 = true;
                setM00(false);
                setM05(true);
            }else M02 = false;
        }
        // Spindle On clockwise
        static void setM03(boolean ON){
            if(ON){
                M03 = true;
                setM04(false);
                setM05(false);
            }else M03 = false;
        }
        // Spindle On counter clockwise
        static void setM04(boolean ON){
            if(ON){
                M04 = true;
                setM03(false);
                setM05(false);
            }else M04 = false;
        }
        //Spindle Off
        static void setM05(boolean ON){
            if(ON){
                M05 = true;
                setM03(false);
                setM04(false);
                setM13(false);
                setM14(false);
            }else M05 = false;
        }
        // coolant On
        static void setM08(boolean ON){
            if(ON){
                M08 = true;
                setM09(false);
            }else M08 = false;
        }
        // coolant Off
        static void setM09(boolean ON){
            if(ON){
                M09 = true;
                setM08(false);
            }else M09 = false;

        }
        // Spindle On clockwise with coolant
        static void setM13(boolean ON){
            if(ON){
                M13 = true;
                setM14(false);
                setM04(false);
                setM08(true);
            }else M13 = false; setM09(true);
        }
        // Spindle On Counter clockwise with coolant
        static void setM14(boolean ON){
            if(ON){
                M14 = true;
                setM13(false);
                setM03(false);
                setM08(true);
            }else M14 = false; setM09(true);
        }
        static void setF(double FN){
            if(FN > MaxFeedRate ){
                System.out.println("Feed Rate should not be greater than "+ MaxFeedRate);
            }else if(FN > MaxFeedRate2 && !M08) {
                System.out.println("Feed Rate without cooling should not be greater than " + MaxFeedRate2);
            }else if (FN > MaxFeedRate1 && interpolation ){
                System.out.println("Feed Rate with cooling should not be greater than " + MaxFeedRate1);
            }else F = FN;
        }



        
}

