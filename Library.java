/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
Library Class- creation of the list of books in the library, includes iterators to be able to display the list of books and add the books to the library.
 */

package pasquale3.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library implements Serializable {
    private List<Book> collection;



    /*
    setting a list to an array list b/c an array list is more flexible.
     */
    public Library(){
        collection = new ArrayList<Book>();
    }


    /*
    add books to collection from our list of books
     */
    public void addBook(Book book){
        collection.add(book);
    }

    //passing in a title of the book, comparing for each books in the list of books has the same title name and returning that title, if it doesn't match, return null.
    public Book getBookByName(String name){
        Book v = null;
        Iterator<Book> i = collection.iterator();
        while(i.hasNext()){
            v = i.next();
            //comparing two strings can't do this: "string" == "string". must use contentEquals.
            if(v.getTitle().toLowerCase().contentEquals(name.toLowerCase())){
                return v;
            }
        }

        return null;
    }



    /*
    returns a string
     */
    @Override
    public String toString(){
        String total = "\n";
        for (int i =0; i<collection.size(); i++){
            Book b = collection.get(i); //getting book at position i from library collection
            total = total + b.toString();

            /*
            can also write using iterator-some think its more efficient and looks more professional, add extends Object after public class library
            extract an iterator from array list
            Iterator<Book> i = collection.iterator();
            while(i.hasNext()){ //while i has another line
                Book b = (Book)i.next(); //returns an object (google cast for more info on whhy added (Book))
                total = total + b.toString();
             */

        }


        return total;
    }


    public boolean doesIsbnAlreadyExist(int isbn) {

        Iterator<Book> i = collection.iterator();
        while(i.hasNext()){
            if(i.next().getIsbn() == isbn){
                return true;
            }
        }


        return false;
    }


    public String[][] toStringVector(){
        String[][] total = new String[collection.size()][5]; //rows-columns

        for (int i=0; i<collection.size(); i++){
            total[i][0] = collection.get(i).getTitle(); //assigning row to column
            total[i][1] = collection.get(i).getAuthor();
            total[i][2] = collection.get(i).getPrice();
            total[i][3] = collection.get(i).getISBN();

        }
        return total;
    }

    public Book getBookByISBN(String isbn) {
        for (int i=0; i<collection.size(); i++){
            if(collection.get(i).getISBN().contentEquals(isbn)){
                return collection.get(i);
            }
        }
        return null;
    }
}
