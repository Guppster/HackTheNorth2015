package com.example.singh.hackthenorth2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ActionBarActivity
{
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://crackling-inferno-1738.firebaseio.com/");
        setContentView(R.layout.activity_main);
    }//End of onCreate method

    public void googleClicked(View v)
    {
        ref.child("text").push().setValue("GOOGLE BUTTON TOTALLY WORKS");
    }//End of googleClicked method

    public void twitterClicked(View v)
    {
        //setup the OAuth options for Twitter
        Map<String, String> options = new HashMap<String, String>();
        options.put("oauth_token", "<OAuth token>");
        options.put("oauth_token_secret", "<OAuth token secret>");
        options.put("user_id", "<Twitter user id>");
        ref.authWithOAuthToken("twitter", options, new Firebase.AuthResultHandler()
        {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast toast = Toast.makeText(getApplicationContext(), "IT WORKED!", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }//End of twitterClicked method

    public void githubClicked(View v)
    {
        ref.child("text").push().setValue("Github BUTTON TOTALLY WORKS");
    }//End of facebookClicked method
}//End of MainActivity class
