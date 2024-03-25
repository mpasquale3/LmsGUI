/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
Book class - creation of what a book is, the variables that belong to it so when listing or adding to the collection, you know what that entails.
 */

package pasquale3.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Book implements Serializable
{
    private int isbn;
    private String title, author;
    private double price;

    private List<VIM> vims;



    public Book(){
        isbn = 0;
        title = null;
        author = null;
        price = 0;
        vims = new ArrayList<VIM>(); //instantiate list of books into an array list

    }


    /*
    linking parameter to private variable
     */
    public Book(int isbn, String title, String author, double price){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        vims = new ArrayList<VIM>();


    }

    public void addVIM(VIM v){
        vims.add(v);
    }



    public VIM getVIMByName(String name){
        VIM v = null;
        Iterator<VIM> i = vims.iterator();
        while(i.hasNext()){
            v = i.next();
            //comparing two strings can't do this: "string" == "string". must use contentEquals.
            if(v.getName().toLowerCase().contentEquals(name.toLowerCase())){
                return v;
            }
        }

        return null;
    }


    /*
    returns string representation of the String object
     */
    @Override
    public String toString(){
        String vimNames = "";
        String vimAmount = "(" + String.valueOf(vims.size()) + ")"; //converting integer to a string
        Iterator<VIM> i = vims.iterator();
        while(i.hasNext()){
            vimNames += i.next().getName() + ", ";
        }
        return "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nISBN: " + isbn +
                "\nPrice: " + price +
                "\nVIM Files" + vimAmount + ": " + vimNames +
                "\n";
    }


    public int getIsbn(){
        return isbn;
    }

    public String getTitle(){
        return title;
    }
    public String getISBN(){
        return String.valueOf(isbn);
    }




    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return String.valueOf(price);
    }


    public String[][] toStringVectorFiles() {
        String total [][] = new String[vims.size()][3];
        VIM v;

        for(int i=0; i<vims.size(); i++){
            v = vims.get(i);
            if (v.getName().endsWith("wav") || v.getName().endsWith("mp3")) total[i][0] = v.getName(); //music column
            else if(v.getName().endsWith("png")|| v.getName().endsWith("jpeg")) total[i][1] = v.getName(); //image column
            else total[i][2] = v.getName(); //video column
        }
        return total;
    }
}
