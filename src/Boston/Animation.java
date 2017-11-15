package Boston;

import java.awt.*;
import java.util.ArrayList;

public class Animation {

    private ArrayList _scenes;
    private int _sceneIndex;
    private long _movieTime;
    private long _totalTime;

    public Animation(){
        _scenes = new ArrayList();
        _totalTime = 0;
        start();
    }

    //add scene to arrayList and set time for each scene
    public synchronized void addScene(Image image, long time){
        _totalTime += time;
        _scenes.add(new OneScene(image, _totalTime));
    }

    //start animation
    public synchronized void start(){
        _movieTime = 0;
        _sceneIndex = 0;
    }

    //change scenes
    public synchronized void update(long timePassed){
        if(_scenes.size()>1){
            _movieTime += timePassed;
            if(_movieTime >= _totalTime){
                _movieTime = 0;
                _sceneIndex = 0;
            }
            while (_movieTime > getScene(_sceneIndex).endTime){
                _sceneIndex ++;
            }
        }
    }

    //get animations current scene
    public synchronized Image getImage(){
        if(_scenes.size() == 0){
            return null;
        }else{
            return getScene(_sceneIndex).pic;
        }
    }

    //get scene
    private OneScene getScene(int sceneIndex){
        return (OneScene) _scenes.get(sceneIndex);
    }
}
