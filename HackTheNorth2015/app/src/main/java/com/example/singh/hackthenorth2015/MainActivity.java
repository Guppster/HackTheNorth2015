package com.example.singh.hackthenorth2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
        ref.child("text").push().setValue("TWITTER BUTTON TOTALLY WORKS");
    }//End of twitterClicked method

    public void facebookClicked(View v)
    {
        ref.child("text").push().setValue("FACEBOOK BUTTON TOTALLY WORKS");
        ref.child("message").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                System.out.println("WOAHHHHH");  //prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(FirebaseError error) { }
    });
    }//End of facebookClicked method
}//End of MainActivity class
