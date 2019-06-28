package librarymanagement.classes;

import java.io.Serializable;

/**
 *
 * @author Chad Jones and Joseph Lumpkin
 */

/*
This is a class for a basic way to keep track preferred genres.
It does this by holding a score for each genre that increments when a person
checks out a book. This score should not exceed 10.
This will help keep a diverse recommendation portfolio, and won't allow one
genre to overpower the others.
*/
public class GenreAlgorithm implements Serializable{
    public int sciFi = 0;
    public int thriller = 0;
    public int mystery = 0;
    public int fantasy = 0;
    
    private int nuetralCounter = 0;
    
    public GenreAlgorithm(){
    }

    
    //Control methods for the algorithm.
    
    //Incrementers for the genre scores.
    public void incSciFi(){this.sciFi++;}
    public void incThriller(){this.thriller++;}
    public void incMystery(){this.mystery++;}
    public void incFantasy(){this.fantasy++;}
    
    //Decrementers for the genre scores.
    public void decSciFi(){this.sciFi--;}
    public void decThriller(){this.thriller--;}
    public void decMystery(){this.mystery--;}
    public void decFantasy(){this.fantasy--;}
    
    
    
    //Setters and getters.
    public int getScienceFiction() {
        return sciFi;
    }

    public void setScienceFiction(int scienceFiction) {
        this.sciFi = scienceFiction;
    }

    public int getThriller() {
        return thriller;
    }

    public void setThriller(int thriller) {
        this.thriller = thriller;
    }

    public int getMystery() {
        return mystery;
    }

    public void setMystery(int mystery) {
        this.mystery = mystery;
    }

    public int getFantasy() {
        return fantasy;
    }

    public void setFantasy(int fantasy) {
        this.fantasy = fantasy;
    }
}