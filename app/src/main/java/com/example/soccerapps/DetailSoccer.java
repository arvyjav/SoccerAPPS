package com.example.soccerapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailSoccer extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;



    Bundle extras;
    String title;
    String deskripsi;
    String path;
    String web;
    String facebook;
    String twitter;
    String instagram;
    String id;
    String yir;


    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    TextView website;
    TextView fb;
    TextView tw;
    TextView ins;
    TextView year;
    Button btnbookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail_soccer );
        extras = getIntent().getExtras();
        tvjudul = (TextView) findViewById( R.id.tvjudul );
        tvdesc = (TextView) findViewById( R.id.txtdeskripsi );
        ivposter = (ImageView) findViewById( R.id.ivposter );
        website = (TextView)  findViewById( R.id.txtwebsite );
        fb = (TextView) findViewById( R.id.txtfacebook );
        tw = (TextView) findViewById( R.id.txttwitter );
        ins = (TextView) findViewById( R.id.txtinstagram );
        year = (TextView) findViewById( R.id.txtyear );
        btnbookmark = (Button) findViewById( R.id.btnbookmark );

        if (extras != null) {
            title = extras.getString( "strTeam" );
            id = extras.getString("idTeam");
            web = extras.getString( "strWebsite" );
            yir = extras.getString( "intFormedYear" );
            deskripsi = extras.getString( "strDescriptionEN" );
            path = extras.getString( "strTeamBadge" );
            facebook = extras.getString( "strFacebook" );
            twitter = extras.getString( "strTwitter" );
            instagram = extras.getString( "strInstagram");
            tvjudul.setText( title );
            tvdesc.setText( deskripsi );
            website.setText( web );
            fb.setText( facebook );
            year.setText( yir );
            tw.setText( twitter );
            ins.setText( instagram );
            Glide.with( DetailSoccer.this )
                    .load( path )
                    .override( Target.SIZE_ORIGINAL )
                    .placeholder( R.mipmap.ic_launcher )
                    .into( ivposter );
            // and get whatever type user account id is
        }
        Realm.init( DetailSoccer.this );
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance( configuration );


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelMovieRealm();
                movieModel.setDescriptionEN(deskripsi);
                movieModel.setTeam(title);
                movieModel.setTeamBadge(path);
                movieModel.setFormedYear(yir);
                movieModel.setWebsite( web );
                movieModel.setFacebook( facebook );
                movieModel.setTwitter( twitter );
                movieModel.setInstagram( instagram );
                


                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });
    }
    }
