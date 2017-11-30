package DriverGame.Resources;

import java.text.DecimalFormat;

public class Vector2D {
    public double x;
    public double y;
    public double radial;
    public double degree;

    private static final DecimalFormat dFormat = new DecimalFormat("#.###");

    public Vector2D(){
        x = y = 0.0;
    }

    public Vector2D(double radial, double degree){
        this.radial = radial;
        this.degree = degree;
        updateCartesian();
    }

    public String toString(){
        return "X: " + x + " Y: " + y + " Radial: " + radial + " Degree: " + degree;
    }

    public void formatValues(){
        radial = Double.parseDouble(dFormat.format(radial));
        degree = Double.parseDouble(dFormat.format(degree));
        x = Double.parseDouble(dFormat.format(x));
        y = Double.parseDouble(dFormat.format(y));
    }

    /**Update polar system values*/
    public void updatePolar(){
        if(Math.abs(x) == 0.0){
            if(y>=0){
                degree = 90.0;
            }else{
                degree = -90.0;
            }
        }else if(Math.abs(y) == 0.0){
            if(x >= 0){
                degree = 0.0;
            }else{
                degree = 180.0;
            }
        }else {
            degree = 1/Math.tan(y/x);
        }
        radial = Math.sqrt(x * x + y * y);
        formatValues();
    }

    /**Update cartesian system values*/
    public void updateCartesian(){
        x = radial * Math.cos(Math.toRadians(degree));
        y = radial * Math.sin(Math.toRadians(degree));
        formatValues();
    }

    /**Add vector*/
    public void add(Vector2D v2){
        x += v2.x;
        y += v2.y;
        updatePolar();
    }

    /**Subtract vector*/
    public void sub(Vector2D v2){
        x -= v2.x;
        y -= v2.y;
        updatePolar();
    }

    /**Scale vector by a constant*/
    public void scale(double scaleFactor){
        x *= scaleFactor;
        y *= scaleFactor;
        updatePolar();
    }

    /**Return unit vector of the current vector  */
    public Vector2D getUnitVector(){
        Vector2D v2 = new Vector2D();
        double length = Math.sqrt(x * x + y * y);
        if(length != 0){
            v2.x = x/length;
            v2.y = y/length;
        }
        return v2;
    }

    /**Return a new vector that is oposed to this*/
    public Vector2D getReversVector(){
        return getReversVector(1);
    }

    public Vector2D getReversVector(double scale){
        Vector2D v2 = new Vector2D();

        v2.radial = radial * scale;
        v2.degree = degree + 180;

        v2.updateCartesian();
        return v2;
    }
}
