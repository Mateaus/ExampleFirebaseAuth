package com.example.firebaseexample.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebaseexample.login.entity.AuthResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterRepository {

    private FirebaseAuth firebaseAuth;
    private MutableLiveData<AuthResponse> authResponse;
    private MutableLiveData<Boolean> progressBarStatus;

    public RegisterRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        authResponse = new MutableLiveData<>();
        progressBarStatus = new MutableLiveData<>();
    }

    /**
     * Handles the Registration verification and creation of an account through the
     * Firebase api. It also sets two different
     * mutable livedatas. The AuthReponse Object type containing the response from Firebase
     * or us and A Boolean Object time containing whether if the progress bar should be
     * showing (true) or not (false).
     *
     * @param email - user's email coming from the Email Edit Text in the View.
     * @param password - user's password coming from Password the Edit Text in the View.
     */

    public void requestRegistration(String email, String password) {
        try {
            progressBarStatus.setValue(true);
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            authResponse.setValue(new AuthResponse("Created Account Successfully",
                                    true));
                            progressBarStatus.setValue(false);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            authResponse.setValue(new AuthResponse(e.getMessage(),
                                    false));
                            progressBarStatus.setValue(false);
                        }
                    });
        } catch (Exception e) {
            authResponse.setValue(new AuthResponse("Please fill all the blank spaces",
                    false));
            progressBarStatus.setValue(false);
        }
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
