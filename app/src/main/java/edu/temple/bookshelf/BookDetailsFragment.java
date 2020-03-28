package edu.temple.bookshelf;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {

    HashMap book;
    String author;
    String title;
    TextView authorView;
    TextView titleView;

    public static final String BOOK_KEY = "book";

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(HashMap book){

        BookDetailsFragment bdf = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_KEY, book);
        bdf.setArguments(bundle);

        return bdf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        if(savedInstanceState.getSerializable(BOOK_KEY) != null){

            book = (HashMap) savedInstanceState.getSerializable(BOOK_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_details, container, false);
        titleView = v.findViewById(R.id.titleView);
        authorView = v.findViewById(R.id.authorView);
        displayBook(book);

        return v;
    }

    public void displayBook(HashMap book){

        Set<String> keys = book.keySet();
        for(String title : keys){

            titleView.setText(title);
            authorView.setText((String)book.get(title));
        }
    }

}
