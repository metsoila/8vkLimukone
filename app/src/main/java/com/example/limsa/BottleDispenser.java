package com.example.limsa;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BottleDispenser {
    private int bottles;
    private double money;

    private BottleDispenser() {
        bottles = 5;
        money = 0;
        pullot.add(1);
        pullot.add(1);
        pullot.add(2);
        pullot.add(3);
        pullot.add(3);
        pullot.add(4);
        pullot.add(5);
        pullot.add(5);
    }
    private static BottleDispenser automaatti = new BottleDispenser();

    public static BottleDispenser getInstance(){
        return automaatti;
    }

    public double getPrice(int limukoodi){
        Bottle Pullo = Bottle.getInstance();
        double price = Pullo.getHinta(limukoodi);
        return price;
    }

    public double getSize(int limukoodi){
        Bottle Pullo = Bottle.getInstance();
        double pullokoko = Pullo.getEnergy(limukoodi);
        return pullokoko;
    }
    public String getNimi(int limukoodi){
        Bottle Pullo = Bottle.getInstance();
        String nimi = Pullo.getName(limukoodi);
        return nimi;
    }



    public ArrayList<Integer> pullot = new ArrayList<Integer>();

    public double addMoney(int addM) {
        money += addM;
        return money;
    }

    public String buyBottle(int pullokoodi) {
        Bottle tiedot = Bottle.getInstance();
        if (money < tiedot.getHinta(pullokoodi)) {
            return ("Add money first!");
        } else if (bottles == 0) {
            return ("Out of bottles!");
        } else if (pullot.contains(pullokoodi)) {
            bottles -= 1;
            money -= tiedot.getHinta(pullokoodi);
            for(int i = 0; i<pullot.size(); i++){
                if(pullot.get(i) == pullokoodi){
                    pullot.remove(i);
                }
            }
            return ("KACHUNK! " + tiedot.getName(pullokoodi) + " came out of the dispenser!");
        } else{
            return ("Dispenser is out of the product!");
        }
    }

    public double getMoney(){
        money = Math.round(money*100.0)/100.0;
        return money;
    }




   public double returnMoney() {
        if(money>0) {
            money = 0;
        }
        return money;
    }
}