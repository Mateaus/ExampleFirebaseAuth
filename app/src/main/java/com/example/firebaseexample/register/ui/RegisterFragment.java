package com.example.firebaseexample.register.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.firebaseexample.R;
import com.example.firebaseexample.login.model.AuthResponse;
import com.example.firebaseexample.register.RegisterViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterFragment extends Fragment {

    @BindView(R.id.emailET)
    EditText emailET;

    @BindView(R.id.passwordET)
    EditText passwordET;

    @BindView(R.id.errorTV)
    TextView errorTV;

    @BindView(R.id.registerBtn)
    Button registerBtn;

    @BindView(R.id.backBtn)
    Button backBtn;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private RegisterViewModel registerViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        /* Creates an instance of the ViewModels scope into our RegisterViewModel. */
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        /* Observers data containing the AuthResponse. */
        registerViewModel.getAuthResponse().observe(this, new Observer<AuthResponse>() {
            @Override
            public void onChanged(AuthResponse authResponse) {
                if (authResponse != null) {
                    if (authResponse.getAuthenticated()) {
                        getFragmentManager().popBackStack();
                    } else {
                        showError(authResponse.getResponseMessage());
                    }
                }
            }
        });

        /* Observers data containing a Boolean used for the Progress Bar. */
        registerViewModel.getProgressStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null) {
                    if (aBoolean) {
                        showProgress();
                    } else {
                        hideProgress();
                    }
                }
            }
        });

        return view;
    }

    /**
     * Click listener for the register button in which it makes a call
     * to the Firebase Api to create a user account. It also verifies
     * if the email has already been used or not.
     */

    @OnClick(R.id.registerBtn)
    public void registerButtonHandler() {
        registerViewModel.handleRegistration(
                emailET.getText().toString(), passwordET.getText().toString());
    }

    /**
     * Click listener for the back button where it will return
     * to the previous fragment.
     */

    @OnClick(R.id.backBtn)
    public void backButtonHandler() {
        getFragmentManager().popBackStack();
    }

    /**
     * When the fragment is resumed, it will clear the edit texts.
     */

    @Override
    public void onResume() {
        super.onResume();
        passwordET.setText("");
    }

    /**
     * Shows the message error in the register screen below password input.
     *
     * @param errorMessage - Error message from Firebase or exception.
     */

    private void showError(String errorMessage) {
        errorTV.setVisibility(View.VISIBLE);
        errorTV.setText(errorMessage);
    }

    /**
     * Hides the message error from the register screen.
     */

    private void hideError() {
        errorTV.setVisibility(View.GONE);
    }

    /**
     * Shows the circular progress bar in the register screen.
     */

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Hides the circular progress bar in the register screen.
     */

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
