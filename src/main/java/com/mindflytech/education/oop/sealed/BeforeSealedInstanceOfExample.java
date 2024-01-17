package com.mindflytech.education.oop.sealed;

public class BeforeSealedInstanceOfExample {
    public interface NonLiving {}

    public static class Plant {}

    void useInstanceof(Plant plant) {
        if (plant instanceof NonLiving) {
            System.out.println(plant.toString());
        }
    }

    public static void main(String[] args) {
        BeforeSealedInstanceOfExample before = new BeforeSealedInstanceOfExample();
        Plant plant = new PlasticPlant();
        before.useInstanceof(plant);
    }
}

class PlasticPlant extends BeforeSealedInstanceOfExample.Plant implements BeforeSealedInstanceOfExample.NonLiving {
    @Override
    public String toString() {
        return "I am a non-living plant!";
    }
}
