package com.example.firebaseexample.content;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebaseexample.MainActivity;
import com.example.firebaseexample.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentActivity extends AppCompatActivity {

    @BindView(R.id.contentTV)
    TextView contentTV;

    @BindView(R.id.logoutBtn)
    Button logoutBtn;

    private FirebaseAuth firebaseAuth;

    public ContentActivity() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);

        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        contentTV.setText("Congratulations, you did it!\n" + userEmail);
    }

    /**
     * Click listener for the logout button.
     */
    @OnClick(R.id.logoutBtn)
    public void logout() {
        Intent loginActivity = new Intent(ContentActivity.this, MainActivity.class);
        startActivity(loginActivity);
        firebaseAuth.signOut();
    }
}
