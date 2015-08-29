package com.example.empowerit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Marcela on 23/08/15.
 */
public class ListCardsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ListView l = (ListView)findViewById(R.id.listCards);
        ArrayList<String> test = new ArrayList<String>();
        test.add("Wob01");
        test.add("Wob02");
        test.add("Wob03");
        ArrayAdapter arrayAdapter = new  ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, test);
        l.setAdapter(arrayAdapter);
        l.notify();
    }

}
