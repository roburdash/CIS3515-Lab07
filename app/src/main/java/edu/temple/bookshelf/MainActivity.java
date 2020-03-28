package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements BookListFragment.GetBookInterface{


    boolean singleVis;          //boolean for both fragments visible
    BookDetailsFragment bdf;    //book details fragment
    FragmentManager fm;         //fragment manager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //determine if all panes are visible and store to boolean
        singleVis = (findViewById(R.id.container_details)) != null;
        bdf = new BookDetailsFragment();

        //load fragment manager and place book list into container list
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container_list, new BookListFragment()).commit();

        //if both fragments should be visible load details into details container
        if(!singleVis){
            fm.beginTransaction().replace(R.id.container_details, bdf);
        }// end if(!singleVis)
    }

    @Override
    public void bookSelected(HashMap book){

        if(singleVis) {

            BookDetailsFragment newFrag = BookDetailsFragment.newInstance(book);
            fm.beginTransaction().replace(R.id.container_list, newFrag).addToBackStack(null).commit();

        } else {

            bdf.displayBook(book);
        }
    }
}


