package com.example.firebaseexample.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebaseexample.login.entity.AuthResponse;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;
    private LiveData<AuthResponse> authResponse;
    private LiveData<Boolean> progressBarStatus;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
        authResponse = loginRepository.getAuthResponse();
        progressBarStatus = loginRepository.getProgressStatus();
    }

    /**
     * Requests in the repository to verify the data being passed is correct
     * by the Firebase api in order to access the application.
     * @param email - user's email.
     * @param password - user's password.
     */

    public void requestAuthentication(String email, String password) {
        loginRepository.requestAuthentication(email, password);
    }

    /**
     * Returns the Response coming from either Firebase or the value set by us.
     *
     * @return - returns the message caused inside the Firebase listeners.
     */

    public LiveData<AuthResponse> getAuthResponse() {
        return authResponse;
    }

    /**
     * Returns the progress bar boolean status based on the Firebase authentication listeners.
     *
     * @return - returns the boolean status for the progress bar.
     */

    public LiveData<Boolean> getProgressStatus() {
        return progressBarStatus;
    }
}
