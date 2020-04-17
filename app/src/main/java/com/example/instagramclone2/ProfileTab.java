package com.example.instagramclone2;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobby,edtProfileSport;
    private Button btnUpdateInfo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileTab newInstance(String param1, String param2) {
        ProfileTab fragment = new ProfileTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobby = view.findViewById(R.id.edtProfileHobby);
        edtProfileSport = view.findViewById(R.id.edtProfileSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("profileName") == null){
            edtProfileName.setText("");
        }else{
            edtProfileName.setText(parseUser.get("profileName").toString());
        }
        if (parseUser.get("profileBio") == null){
            edtProfileBio.setText("");
        }else{
            edtProfileBio.setText(parseUser.get("profileBio").toString());
        }
        if ( parseUser.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }else{
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
        }
        if (parseUser.get("profileHobby") == null){
            edtProfileHobby.setText("");
        }else{
            edtProfileHobby.setText(parseUser.get("profileHobby").toString());
        }
        if(parseUser.get("profileSport") == null){
            edtProfileSport.setText("");
        }else {
            edtProfileSport.setText(parseUser.get("profileSport").toString());
        }



        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                parseUser.put("profileHobby", edtProfileHobby.getText().toString());
                parseUser.put("profileSport",edtProfileSport.getText().toString());

                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating Info...");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(getContext(), "Info Updated.", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                        }else {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                        }
                    }
                });
                progressDialog.dismiss();
            }
        });

        return view;
    }
}
