package com.example.firebaseexample.content;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.firebaseexample.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContentFragment extends Fragment {

    @BindView(R.id.contentTV)
    TextView contentTV;

    @BindView(R.id.logoutBtn)
    Button logoutBtn;

    private FirebaseAuth mAuth;

    public ContentFragment() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        String userEmail = mAuth.getCurrentUser().getEmail();
        contentTV.setText("Congratulations, you did it!\n" + userEmail);


        return view;
    }

    /**
     * Click listener for the logout button.
     */
    @OnClick(R.id.logoutBtn)
    public void logout() {
        /* Pops the current fragment and goes to the previous one */
        getFragmentManager().popBackStack();
        mAuth.signOut(); // We sign out from Firebase.
    }

}
