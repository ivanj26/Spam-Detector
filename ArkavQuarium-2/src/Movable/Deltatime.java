/*
 * ArkavQuarium
 * 
 */
package Movable;

/**
 *
 * @author asus
 */
public class Deltatime{
    //Get the system time
    long lastTime = System.nanoTime();
    //Specify how many seconds there are in a minute as a double
    //store as a double cause 60 sec in nanosec is big and store as final so it can't be changed
    final double ticks = 60D;
    //Set definition of how many ticks per 1000000000 ns or 1 sec
    double ns = 1000000000 / ticks;    
    double delta = 0;
    
    public Deltatime(){
        while (true){
            //Update the time
            long now = System.nanoTime();
            //calculate change in time since last known time
            this.delta += (now - lastTime) / ns;
            //update last known time    
            lastTime = now;
            //continue while delta is less than or equal to 1
            if(delta >= 1){
            //Go through one tick        
            //decrement delta
            delta--;
            }
        }
    }

    public double getDeltatime() {
        return this.delta;
    }
}
