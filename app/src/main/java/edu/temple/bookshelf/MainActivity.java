package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
    }

    //method to load bookshelf from string resource
    private ArrayList<Map<String, String>> loadShelf(){

        ArrayList<Map<String, String>> bookShelf = new ArrayList<Map<String, String>>();    //array list to act as shelf
        Map<String, String> book = new HashMap<>();                              //hash map to act as book
        String[] bookKeys = this.getResources().getStringArray(R.array.book_titles);                //string array of authors
        String[] bookValues = this.getResources().getStringArray(R.array.book_authors);             //string array of titles

        //loop to populate array list
        for(int i = 0; i < bookKeys.length; ++i){

            book.put(bookKeys[i], bookValues[i]);
            bookShelf.add(book);
            book.clear();
        }// end for(int i = 0; i < bookKeys.length; ++i)

        return bookShelf;
    }

}


