package librarymanagement.classes;

import java.io.Serializable;
/**
 *
 * @author Joseph Lumpkin and Chad Jones
 */

/*
A class to hold the information for the books in the library.
*/
public class Book implements Serializable{
    private double priceNew;
    private double priceUsed;
    private double productCode;
    private String genre;
    private String title;
    private String author;
    
    //A constructor to initialize the class if all variable information.
    public Book(String title, String author, String genre,
            double priceNew, double priceUsed, double productCode){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.priceNew = priceNew;
        this.priceUsed = priceUsed;
        this.productCode = productCode;
    }
    
    //Basic setters and getters.
    public double getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(double priceNew) {
        this.priceNew = priceNew;
    }

    public double getPriceUsed() {
        return priceUsed;
    }

    public void setPriceUsed(double priceUsed) {
        this.priceUsed = priceUsed;
    }

    public double getProductCode() {
        return productCode;
    }

    public void setProductCode(double productCode) {
        this.productCode = productCode;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}