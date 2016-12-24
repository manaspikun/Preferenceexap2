package com.techpalle.preferenceexp2;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //take a preference file-with int variable-counter
        //if counter =0,display fragment one,and make counter=1
        //if counter=1,display fragment two
        SharedPreferences sp=getSharedPreferences("firsttime",0);
        int counter=sp.getInt("counter",0);
        if (counter==1){
            //user is returning user,so open fragment
            FragmentTwo f2=new FragmentTwo();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container1,f2);
            fragmentTransaction.commit();
            return;
        }
        //load fragment one
        FragmentOne f1=new FragmentOne();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container1,f1);
        fragmentTransaction.commit();

        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("counter",1);
        editor.commit();
        //after 5seconds load fragment two
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //android os will come
                FragmentTwo f2=new FragmentTwo();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container1,f2);
                fragmentTransaction.commit();
            }
        }, 5000);

    }
}
