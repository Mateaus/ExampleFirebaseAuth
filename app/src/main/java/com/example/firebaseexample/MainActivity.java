package com.example.firebaseexample;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.firebaseexample.content.ContentActivity;
import com.example.firebaseexample.login.ui.LoginFragment;
import com.example.firebaseexample.register.ui.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* We need a fragment manager in order to keep track of all the fragments and add them
         * into the back stack or replace them. */
        fragmentManager = getSupportFragmentManager();

        /* Gets an id reference to the fragment container since we will be replacing
         * that container with all the fragments. */
        Fragment mainFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        /* We will only create one instance of the main fragment to avoid memory leaks. */
        if (mainFragment == null) {
            /**
             * The initial fragment that will be loaded is login fragment.
             */
            LoginFragment loginFragment = new LoginFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, loginFragment)
                    .commit();
        }
    }

    /**
     * Loads the content activity.
     */
    public void loadContentActivity() {
        Intent contentActivity = new Intent(this, ContentActivity.class);
        startActivity(contentActivity);
    }

    /**
     * Loads the register fragment.
     */
    public void loadRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, registerFragment)
                .addToBackStack(null)
                .commit();
    }
}
