/*
 * ArkavQuarium
 * 
 */
package Aquarium;

import java.util.*;
import Animal.*;
import Coin.*;
import Food.*;

/**
 *
 * @author regipurba
 */
public class Aquarium {
    private List<Fish> Fishes;
    private List<Coin> Coins;
    private List<Food> Foods;
    Snail snail;

    public Aquarium() {
        
    }
    
    public Snail getSnail(){
        return this.snail;
    }
    
    public List<Fish> getFishes(){
        return this.Fishes;
    }
    
    public List<Coin> getCoins(){
        return this.Coins;
    }
    
    public List<Food> getFood(){
        return this.Foods;
    }
}
