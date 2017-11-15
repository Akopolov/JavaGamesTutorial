package Boston;

import java.util.Random;

public class Apple implements Runnable{

    String _name;
    int _time;
    Random random = new Random();

    public Apple(String name){
        _name = name;
        _time = random.nextInt(999);
    }

    @Override
    public void run() {
        try{
            System.out.printf("%s is sleeping for %d\n", _name, _time);
            Thread.sleep(_time);
            System.out.printf("%s is done\n", _name);
        }catch (Exception e){

        }
    }
}
