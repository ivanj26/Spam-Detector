/*
 * ArkavQuarium
 * 
 */
package Coin;

import Location.*;
import Movable.*;

/**
 *
 * @author Uslaconi
 */
public class Coin{    
    private int statePic;
    private long value;
    private Location location;
    
    //void Move(double deltatime); //Selalu move ke bawah
    
    public long getValue(){
        return this.value;
    }
    public void setValue(long v) {
        this.value = v;
    }
    public void printCoin(String[] s){
        
    }
    public int getStatePic(){
        return this.statePic;
    }
    public void setStatePic(int sp){
        this.statePic = sp;
    }
}
