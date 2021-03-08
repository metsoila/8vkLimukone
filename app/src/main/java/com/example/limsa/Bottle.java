package com.example.limsa;

public class Bottle {
    public String name;
    private String manufacturer;
    private double hinta;
    private double total_energy;

    private static Bottle Pullo = new Bottle();

    public static Bottle getInstance(){
        return Pullo;
    }

    public Bottle() {

    }

    public double getHinta(int e) {

        if (e == 1)
            hinta = 1.8;
        else if (e == 2)
            hinta = 2.2;
        else if (e == 3)
            hinta = 2.0;
        else if (e == 4)
            hinta = 2.5;
        else if (e == 5)
            hinta = 1.95;
        return hinta;
    }

    public String getName(int n) {
        if (n == 1)
            name = "Pepsi Max";
        else if (n == 2)
            name = "Pepsi Max";
        else if (n == 3)
            name = "Coca-Cola Zero";
        else if (n == 4)
            name = "Coca-Cola Zero";
        else if (n == 5)
            name = "Fanta Zero";
        return name;
    }


    public double getEnergy(int w) {
        if (w == 1)
            total_energy = 0.5;
        else if (w == 2)
            total_energy = 1.5;
        else if (w == 3)
            total_energy = 0.5;
        else if (w == 4)
            total_energy = 1.5;
        else if (w == 5)
            total_energy = 0.5;
        return total_energy;

    }
}