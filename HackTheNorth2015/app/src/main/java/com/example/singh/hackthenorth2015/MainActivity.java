package com.example.singh.hackthenorth2015;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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
        Intent intent = new Intent(this, SmsListener.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
    }//End of onCreate method

    public void loginAnon()
    {
        ref.authAnonymously(new Firebase.AuthResultHandler()
        {
            @Override
            public void onAuthenticated(AuthData authData)
            {
                // we've authenticated this session with your Firebase app
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }
}//End of MainActivity class
