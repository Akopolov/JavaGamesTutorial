package DriverGame.BL;

import DriverGame.Model.Car;
import DriverGame.Resources.StaticVariable.CarVariables;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class carController {

    private Car car;

    /**Load and init all the required objects*/
    public carController(){
        car = new Car();
        car.init();
        init();
        loadContent();
    }

    private void init(){

    }

    private void loadContent(){

    }

    /**Update Car Position*/
    public void update(){

        /*Restart car object*/
        if(DriverGame.Canvas.onlyThisButtonPressed(KeyEvent.VK_R)){
            car.init();
        }

        /*if user presses W*/
        if(DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_W) &&
                !DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_S)){
            car.engineForce += CarVariables.Thrust.INCREASE;
        }

        /*if user presses S*/
        if(DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_S) &&
                !DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_W)){
            car.engineForce += CarVariables.Thrust.REVERSE;
        }

        /*if user presses A*/
        if(DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_A)){
            car.rotation(CarVariables.Rotation.LEFT);
        }

        /*if user presses D*/
        if(DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_D)){
            car.rotation(CarVariables.Rotation.RIGHT);
        }

        /*if user presses SPACE*/
        if(DriverGame.Canvas.keyboardKeyState(KeyEvent.VK_SPACE)){
            if(car.engineForce > 0.0){
                car.engineForce -= CarVariables.Thrust.STOP;
            }else if(car.engineForce < 0.0){
                car.engineForce += CarVariables.Thrust.STOP;
            }
        }

        /*engine power is decreasing*/
        car.engineDrag();

        /*update all car values*/
        car.update();

        /*rotate image*/
        imageRotation();
    }

    /**function that is used to rotate the care image*/
    private void imageRotation(){
        AffineTransform tx = AffineTransform.getTranslateInstance(car.width, car.height);
        tx.rotate(Math.toRadians(car.traction.degree), car.width /2.0, car.height /2.0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        car.newImage = op.filter(car.originalImage, null);
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(Color.white);
        graphics2D.drawString("engine force: " + car.engineForce, 10, 15);
        graphics2D.drawString(car.traction.toString(), 10, 30);
        graphics2D.drawString(car.drag.toString(), 10, 45);
        graphics2D.drawString(car.momentum.toString(), 10, 60);
        graphics2D.drawString("speedX: " + car.momentum.x + " speedY " + car.momentum.y, 10, 75);
        graphics2D.drawImage(car.newImage, car.x, car.y, null);
    }
}
