package com.example.singh.everything4scale;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Z0NEN on 10/22/2014.
 */
public class menu1_Fragment extends Fragment {
    View rootview;
    Firebase ref = new Firebase("https://crackling-inferno-1738.firebaseio.com/");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);

        ref.child("banana").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                System.out.println("There are " + snapshot.getChildrenCount() + " dank memes");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return rootview;
    }
}
