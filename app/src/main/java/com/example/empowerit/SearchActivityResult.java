package com.example.empowerit;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.smartcanvas.CardSearchRequest;
import com.smartcanvas.Smartcanvas;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardSearchResult;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jose4j.lang.JoseException;

import adapter.WobAdapter;
import bean.Wob;
import bean.WobsResults;

public class SearchActivityResult extends Activity {

    private static final String PAGE_NUMBER = "PAGE NUMBER";
    private static final String WOB = "WOB";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private ViewPager pager;
    private WobAdapter wobAdapter;
    protected WobsResults wobsResult;
    public Smartcanvas smartcanvas;
    ArrayList<Wob> arraylist = new ArrayList<Wob>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        pager = (ViewPager) findViewById(R.id.info_pager);

        if (savedInstanceState != null) {
            pager.setCurrentItem(savedInstanceState.getInt(PAGE_NUMBER, 0));
        }


        try {


            smartcanvas = new Smartcanvas(HTTP_TRANSPORT, JSON_FACTORY, Credential.clientId, Credential.clientSecret);


        } catch (JoseException e) {
            e.printStackTrace();
//            smartcanvas = null;
        } catch (Exception e) {
            e.printStackTrace();

        }

        wobsResult = generateListWobs();
        wobAdapter = new WobAdapter(getApplicationContext(), wobsResult);

        pager.setAdapter(wobAdapter);
        pager.setOnPageChangeListener(new PageChangeListener());

        if (savedInstanceState != null) {
            pager.setCurrentItem(savedInstanceState.getInt(PAGE_NUMBER, 0));
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (wobAdapter != null) {
            outState.putSerializable(WOB, wobsResult);
            outState.putInt(PAGE_NUMBER, pager.getCurrentItem());
        }

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private WobsResults generateListWobs() {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final CardSearchRequest searchRequest = CardSearchRequest.builder()
                .providerIds("empowerit")
                .build();

        InsertCardTaks insert = new InsertCardTaks();
        insert.execute(searchRequest);

        WobsResults wobsres = new WobsResults();
        wobsres.listWobsResult = arraylist;


        return wobsres;
    }

    private class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {

        }

    }

    private class InsertCardTaks extends AsyncTask<CardSearchRequest, Smartcanvas, ArrayList<Wob>> {

        @Override
        protected ArrayList<Wob> doInBackground(CardSearchRequest... params) {

            arraylist = new ArrayList<Wob>();
            try {
                CardSearchResult cardSearchResult = smartcanvas.cards().search(params[0]);
                for (Card c : cardSearchResult.cards()) {
                    arraylist.add(new Wob(c.getTitle(), c.getAuthor().getImageURL(), c.getSummary(), "Sao Paulo, Brazil"));
                }
            } catch (IOException ioe) {
                Log.e("SeachActivityResult", ioe.getMessage());
            } catch (Exception e) {
                Log.e("SeachActivityResult", e.getMessage());
                ;
            }

            arraylist.add(new Wob("Women in Business", "http://www.macleans.ca/wp-content/uploads/2014/06/Icon2.jpg", "Company of exchange", "NYC, NY"));
            arraylist.add(new Wob("WOB in Africa", "http://www.greenmap.org/greenhouse/files/services/icons/companies.jpg", "Bank", "Maputo, Mozambique"));
            arraylist.add(new Wob("Brazil IT", "https://www.responsible-investor.com/images/uploads/articles/icon_profile.jpg", "Development of software", "Sao Paulo, SP"));


            return arraylist;
        }


    }
}
