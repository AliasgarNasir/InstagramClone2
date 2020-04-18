package com.example.instagramclone2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment {

    private ListView listView ;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;

    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_tab, container, false);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,arrayList);
        final TextView txtLoadingData = view.findViewById(R.id.txtLoadingUsers);

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Users...");
        progressDialog.show();

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) { //objects are users
                if (e == null){
                    if (objects.size() > 0){
                        for (ParseUser user : objects){
                            arrayList.add(user.getUsername());
                        }
                        listView.setAdapter(arrayAdapter);
//                        txtLoadingData.animate()
//                                .alpha(0)
//                                .setDuration(500);
//                        listView.setVisibility(View.VISIBLE);
                    }
                }
                progressDialog.dismiss();
            }
        });

        return view;
    }
}
