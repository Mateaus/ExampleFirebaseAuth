package com.example.firebaseexample.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebaseexample.login.entity.AuthResponse;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;
    private LiveData<AuthResponse> authResponse;
    private LiveData<Boolean> progressBarStatus;

    public RegisterViewModel() {
        registerRepository = new RegisterRepository();
        authResponse = registerRepository.getAuthResponse();
        progressBarStatus = registerRepository.getProgressStatus();
    }

    /**
     * Requests in the repository to verify the data being passed is correct
     * in order to create an account through the Firebase api.
     *
     * @param email    - user's email.
     * @param password - user's password.
     */

    public void requestRegistration(String email, String password) {
        registerRepository.requestRegistration(email, password);
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
