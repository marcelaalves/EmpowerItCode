package com.example.empowerit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Marcela on 23/08/15.
 */
public class SearchActivity extends Activity {
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSectorList();

        Spinner s = (Spinner) findViewById(R.id.industry_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getSectorList());
        s.setAdapter(adapter);

        search = (Button) findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchActivityResult.class);
                startActivity(i);
            }
        });

    }


    private String[] getSectorList() {
        String[] mIndustrySector = new String[]{
                "Construction",
                "Wholesale Trade",
                "Retail",
                "Transportation & Warehousing",
                "Finance & Insurance",
                "Real Estate",
                "Professional/Scientific/ Technical Services ",
                "Administrative, Support & Waste Management Services",
                "Educational Services",
                "Health Care & Social Assistance",
                "Arts, Entertainment, Recreation",
                "Accommodation & Food Service "


        };
        return mIndustrySector;
    }

}
