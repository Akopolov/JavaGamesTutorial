package DriverGame.Resources.StaticVariable;

public class CarVariables {

    public static class Thrust{
        public static final double INCREASE = 0.01;
        public static final double REVERSE = -0.01;
        public static final double ENGINE_DRAG = 0.001;
        public static final double ENGINE_STOP = 0.005;
        public static final double STOP = 0.05;
    }

    public static class Rotation{
        public static final double DEFAULT = 0.0;
        public static final double RIGHT = 1.0;
        public static final double LEFT = -1.0;
    }
}
