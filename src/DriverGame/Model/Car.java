package DriverGame.Model;

import DriverGame.Framework;
import DriverGame.Resources.StaticVariable.CarVariables;
import DriverGame.Resources.Vector2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class Car {

    /**Car X and Y position in Frame*/
    public int x;
    public int y;

    /**Did Car died?*/
    public boolean died;

    /**User force*/
    public double engineForce;

    /**Vector that holds direction and force*/
    public Vector2D traction;

    /**Vector that will slow the car*/
    public Vector2D drag;

    public Vector2D momentum;

    /**Size of Car*/
    public int width;
    public int height;

    /**Image that will be used to display Car*/
    public BufferedImage originalImage;
    public BufferedImage newImage;

    private DecimalFormat dFormat;

    public Car(){
        try {
            URL pacManImageUrl = this.getClass().getResource("/DriverGame/Resources/Images/Car00.png");
            originalImage = ImageIO.read(pacManImageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = originalImage.getWidth();
        height = originalImage.getHeight();
    }

    public void init(){
        died = false;

        x = new Random().nextInt(Framework.frameWidth - width);
        y = new Random().nextInt(Framework.frameHeight - height);

        traction = new Vector2D(10.0,0.0);

        drag = traction.getReversVector(0.1);

        engineForce = 0.0;
    }

    /**Update car object values*/
    public void update(){
        momentum = new Vector2D();
        momentum.add(traction);
        momentum.scale(engineForce);

        drag = traction.getReversVector(0.05);

        if(momentum.radial > drag.radial){
            momentum.add(drag);
        }else{
            momentum.radial = 0;
            momentum.updateCartesian();
        }

        if(momentum.radial <= 1.0){
            momentum.radial = 0;
            momentum.x = 0;
            momentum.y = 0;
        }

        x += momentum.x;
        y += momentum.y;
    }

    /**Slow the car engine that pushes the car*/
    public void engineDrag() {
        if(engineForce > 0.0){
            engineForce -= CarVariables.Thrust.ENGINE_DRAG;
        }else if(engineForce < 0.0){
            engineForce += CarVariables.Thrust.ENGINE_DRAG;
        }

        if(Math.abs(engineForce) < CarVariables.Thrust.ENGINE_STOP){
            engineForce = 0.0;
        }

        dFormat = new DecimalFormat("#.####");
        engineForce = Double.parseDouble(dFormat.format(engineForce));
    }

    /**Car rotation*/
    public void rotation(double direction) {
        if(momentum.radial > 1.0){
            traction.degree += direction;

            if(traction.degree < 0){
                traction.degree += 360;
            }else if(Math.abs(traction.degree) > 360){
                traction.degree -= 360;
            }

            traction.updateCartesian();
        }
    }
}
