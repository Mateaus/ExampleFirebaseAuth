package com.example.firebaseexample.login.ui;


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

import com.example.firebaseexample.MainActivity;
import com.example.firebaseexample.R;
import com.example.firebaseexample.login.LoginViewModel;
import com.example.firebaseexample.login.model.AuthResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.emailET)
    EditText emailET;

    @BindView(R.id.passwordET)
    EditText passwordET;

    @BindView(R.id.errorTV)
    TextView errorTV;

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @BindView(R.id.registerBtn)
    Button registerBtn;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        /* Creates an instance of the ViewModels scope into our LoginViewModel. */
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        /* Observers data containing the AuthResponse. */
        loginViewModel.getAuthResponse().observe(this, new Observer<AuthResponse>() {
            @Override
            public void onChanged(AuthResponse authResponse) {
                if (authResponse != null) {
                    if (authResponse.getAuthenticated()) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.loadContentFragment();
                    } else {
                        showError(authResponse.getResponseMessage());
                    }
                }
            }
        });

        /* Observers data containing a Boolean used for the Progress Bar. */
        loginViewModel.getProgressStatus().observe(this, new Observer<Boolean>() {
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
     * Click listener for the login button in which it makes a call
     * to the Firebase Api to verify if the user information
     * entered exists or not.
     */

    @OnClick(R.id.loginBtn)
    public void loginButtonHandler() {
        loginViewModel.handleLogin(
                emailET.getText().toString(), passwordET.getText().toString());
    }

    /**
     * Click listener for the register button in which it will replace
     * the current login fragment for the registration fragment.
     */

    @OnClick(R.id.registerBtn)
    public void registerButtonHandler() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.loadRegisterFragment();
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
     * Shows the message error in the login screen below password input.
     *
     * @param errorMessage - Error message from Firebase or exception.
     */

    private void showError(String errorMessage) {
        errorTV.setVisibility(View.VISIBLE);
        errorTV.setText(errorMessage);
    }

    /**
     * Hides the message error from the login screen.
     */

    private void hideError() {
        errorTV.setVisibility(View.GONE);
    }

    /**
     * Shows the circular progress bar in the login screen.
     */

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Hides the circular progress bar in the login screen.
     */

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
