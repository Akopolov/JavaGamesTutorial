package Boston;

import java.awt.*;

public class Sprite {

    private Animation _animation;
    private float positionX;
    private float positionY;
    private float velosityX;
    private float velosityY;

    public Sprite(Animation animation){
        this._animation = animation;
    }

    //change position
    public void update(long timePassed){
        positionX += velosityX * timePassed;
        positionY += velosityY * timePassed;
        _animation.update(timePassed);
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getVelosityX() {
        return velosityX;
    }

    public void setVelosityX(float velosityX) {
        this.velosityX = velosityX;
    }

    public float getVelosityY() {
        return velosityY;
    }

    public void setVelosityY(float velosityY) {
        this.velosityY = velosityY;
    }

    //get width of the sprite
    public int getWidth(){
        return _animation.getImage().getWidth(null);
    }

    //get height of the sprite
    public int getHeight(){
        return _animation.getImage().getHeight(null);
    }

    //get sprites/image
    public Image getImage(){
        return _animation.getImage();
    }
}
