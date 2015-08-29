package com.example.empowerit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.json.MockJsonFactory;
import com.google.api.client.util.DateTime;
import com.smartcanvas.Smartcanvas;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardId;

import org.jose4j.lang.JoseException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Marcela on 26/08/15.
 */
public class RegistrationActivity extends Activity {

    EditText company_name;
    EditText user_name;
    EditText password;
    EditText password2;
    Smartcanvas smartcanvas;

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    LinearLayout newRegister;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        finds();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractFieldsAndIntent();
            }
        });

    }

    private void finds() {
        newRegister = (LinearLayout) findViewById(R.id.btn_register);
        company_name = (EditText) findViewById(R.id.company_name);
        user_name = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);
    }

    private void extractFieldsAndIntent() {

        try {

            Date date = new Date();
            DateTime dateTime = new DateTime(date);

            Card.Author author = new Card.Author();
            author.setDisplayName(company_name.getText().toString());
            author.setImageURL("http://bunnytrade.com/sites/default/files/imagecache/breeder_directory_logo/imagefield_default_images/no_logo_available__250x250_default_photo.png");
            String nameCompany = company_name.getText().toString();
            Card.ContentProvider contentProvider = new Card.ContentProvider("empowerit", String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)), "empowerit");
            Smartcanvas smartcanvas = new Smartcanvas(HTTP_TRANSPORT, JSON_FACTORY, Credential.clientId, Credential.clientSecret);
            Card buildCard = new Card();


            buildCard.setContent("Company registered  by EmpowerIt");
            buildCard.setAuthor(author);
            buildCard.setTitle(nameCompany);
            buildCard.setCreateDate(dateTime);
            buildCard.setId(Long.valueOf(String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND))));
            buildCard.setContentProvider(contentProvider);
            CardId id =  smartcanvas.cards().insert(buildCard);

            Toast.makeText(this, R.string.success_card_insert, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegistrationActivity.this, Login.class);
            startActivity(i);
            finish();
        } catch (JoseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            Toast.makeText(this, R.string.erro_card_insert, Toast.LENGTH_SHORT).show();
        }


    }

}
