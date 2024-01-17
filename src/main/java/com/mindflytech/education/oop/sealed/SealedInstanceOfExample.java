package com.mindflytech.education.oop.sealed;

/**
 * Experimenting whether or not Java type check using instanceof operator actually follows sealed hierarchy
 * and shows error when instanceof cannot possibly be true.
 * If we make either OtherFinalNonLivingObject or FinalNonLivingObjectInSealed non-sealed, the code will run
 * because it is possible for an object to be both NonLiving and a Plant (we opened a runaway option).
 * Also, if we comment in FinalPlasticPlant, we directly extend Plant and implement and Non-Living, the code
 * will again compile
 */
public class SealedInstanceOfExample {
    public sealed interface NonLiving permits OtherFinalNonLivingObject, FinalNonLivingObjectInSealed
            /*, NonSealedPlant /*, FinalPlasticPlant*/{}

    public static class Plant {}

    void useInstanceof(SealedInstanceOfExample.Plant plant) {
        //Compile error
        //Inconvertible types; cannot cast 'com.mindflytech.education.oop.sealed.SealedInstanceOfExample.Plant'
        // to 'com.mindflytech.education.oop.sealed.SealedInstanceOfExample.NonLiving'
//        if (plant instanceof SealedInstanceOfExample.NonLiving) {
//            System.out.println(plant.toString());
//        } else {
//            System.err.println("Plant is not NonLiving!");
//        }
    }

    public static void main(String[] args) {
        SealedInstanceOfExample example = new SealedInstanceOfExample();
        Plant plant = new Plant();
//        Plant plant2 = new NonLivingObject();
//        Plant plant3 = new FinalNonLivingObject();
//        Plant plant4 = (Plant)new FinalNonLivingObject();
        example.useInstanceof(plant);
//        Plant plant5 = new FinalPlasticPlant();
//        example.useInstanceof(plant5);
    }
}

final class FinalNonLivingObjectInSealed implements SealedInstanceOfExample.NonLiving {}

final class OtherFinalNonLivingObject implements SealedInstanceOfExample.NonLiving {}

//non-sealed class NonSealedPlant implements SealedInstanceOfExample.NonLiving {}

//final class FinalPlasticPlant extends SealedInstanceOfExample.Plant implements SealedInstanceOfExample.NonLiving {
//    @Override
//    public String toString() {
//        return "I am a non-living plant that reside in SealedInstanceOfExample java file!";
//    }
//}
