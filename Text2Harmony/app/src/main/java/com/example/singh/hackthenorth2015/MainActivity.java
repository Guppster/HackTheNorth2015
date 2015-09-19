package com.example.singh.hackthenorth2015;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class MainActivity extends ActionBarActivity
{
    Firebase ref;
    EditText edittext = (EditText) findViewById(R.id.editText);
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
        ref.authAnonymously(new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // we've authenticated this session with your Firebase app
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public void music(View v)
    {
        fugueConverter(edittext.toString());
    }

    public void fugueConverter(String input) {

        char nextChar;
        int nextValue, keyGap = 0, oldValue = 0, note;
        String temp = "", tempo = "120", playerString = "", keyLength = "q", instrument = "Piano";
        String enteredString = input;
        for(int i = 0; i < enteredString.length(); i++) {
            nextChar = enteredString.charAt(i);
            nextValue = nextChar;
            if(oldValue != 0)
                keyGap = Math.abs(nextValue - oldValue);
            oldValue = nextValue;
            if(nextValue == 32) // skip spaces
                continue;
            note = nextValue % 8;
            nextValue = ((nextValue - 32) / 2) + 32;
            nextValue = (nextValue - note) / 10;
            switch (note) {
                case 0:
                    temp = "C";
                    break;
                case 1:
                    temp = "D";
                    break;
                case 2:
                    temp = "E";
                    break;
                case 3:
                    temp = "F";
                    break;
                case 4:
                    temp = "G";
                    break;
                case 5:
                    temp = "A";
                    break;
                case 6:
                    temp = "B";
                    break;
                case 7:
                    temp = "C";
                    ++nextValue;
                    break;
            }

            if(keyGap <= 5) {
                keyLength = "w";
                tempo = "120";
            } else if(keyGap <= 10) {
                keyLength = "h";
                tempo = "120";
            } else if(keyGap <= 40) {
                keyLength = "q";
                tempo = "240";
            }


            System.out.println(temp + nextValue);
            playerString = playerString.concat(temp + nextValue + keyLength + " ");
        }
        Pattern pattern = new Pattern();
        pattern.add(playerString);
        pattern.setTempo(240);
        pattern.setInstrument(instrument);
        Player player = new Player();
        System.out.println(playerString);
        player.play(pattern);

    }

}//End of MainActivity class
