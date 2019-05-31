package com.example.firebaseexample.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.firebaseexample.MainActivity;
import com.example.firebaseexample.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.emailET)
    EditText emailET;

    @BindView(R.id.passwordET)
    EditText passwordET;

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @BindView(R.id.registerBtn)
    Button registerBtn;

    private FirebaseAuth mAuth;

    public LoginFragment() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        /*
         * You can add any content in this space.
         */

        return view;
    }

    /**
     * When the fragment is resumed, it will clear the edit texts.
     */
    @Override
    public void onResume() {
        super.onResume();
        emailET.setText("");
        passwordET.setText("");
    }

    /**
     * Click listener for the login button.
     */
    @OnClick(R.id.loginBtn)
    public void login() {
        try {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            /* Successfully Authenticated, switch fragments. */
                            MainActivity mainActivity = (MainActivity)getActivity();
                            mainActivity.loadContentFragment();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Click listener for the register button.
     */
    @OnClick(R.id.registerBtn)
    public void loadRegisterScreen() {
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.loadRegisterFragment();
    }

}
