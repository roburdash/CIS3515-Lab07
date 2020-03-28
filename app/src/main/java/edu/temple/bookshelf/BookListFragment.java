package edu.temple.bookshelf;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment {

    ListView lv;
    Context parent;
    ArrayList al;

    public static final String BOOK_LIST_KEY = "list of books";

    public BookListFragment() {
        // Required empty public constructor
    }

    public static BookListFragment newInstance(ArrayList<HashMap> books){

        BookListFragment blf = new BookListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_LIST_KEY, books);
        blf.setArguments(bundle);
        return blf;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        if(savedInstanceState.getSerializable(BOOK_LIST_KEY) != null) {
            al = (ArrayList) savedInstanceState.getSerializable(BOOK_LIST_KEY);
        }
    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        this.parent = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);
        lv = v.findViewById(R.id.bookList);
        al = loadShelf();

        lv.setAdapter(new ArrayAdapter<HashMap<String, String>>(parent, android.R.layout.simple_list_item_1,
                al));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap book = (HashMap<String, String>) parent.getItemAtPosition(position);
                ((GetBookInterface) parent).bookSelected(book);
            }
        });

        return v;
    }

    interface GetBookInterface{

        void bookSelected(HashMap book);
    }

    //method to load bookshelf from string resource
    public ArrayList<HashMap<String, String>> loadShelf(){

        ArrayList<HashMap<String, String>> bookShelf = new ArrayList<HashMap<String, String>>();    //array list to act as shelf
        HashMap<String, String> book = new HashMap<>();                              //hash map to act as book
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
