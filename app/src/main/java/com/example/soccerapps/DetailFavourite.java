package com.example.soccerapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavourite extends AppCompatActivity {
    Realm realm;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail_favourite );
        extras = getIntent().getExtras();
        tvjudul = (TextView) findViewById( R.id.tvjudul );
        tvdesc = (TextView) findViewById( R.id.txtdeskripsi );
        ivposter = (ImageView) findViewById( R.id.ivposter );
        website = (TextView) findViewById( R.id.txtwebsite );
        fb = (TextView) findViewById( R.id.txtfacebook );
        tw = (TextView) findViewById( R.id.txttwitter );
        ins = (TextView) findViewById( R.id.txtinstagram );
        year = (TextView) findViewById( R.id.txtyear );

        if (extras != null) {
            title = extras.getString( "Team" );
            id = extras.getString( "idTeam" );
            web = extras.getString( "Website" );
            yir = extras.getString( "FormedYear" );
            deskripsi = extras.getString( "DescriptionEN" );
            path = extras.getString( "TeamBadge" );
            facebook = extras.getString( "Facebook" );
            twitter = extras.getString( "Twitter" );
            instagram = extras.getString( "Instagram" );
            tvjudul.setText( title );
            tvdesc.setText( deskripsi );
            website.setText( web );
            fb.setText( facebook );
            year.setText( yir );
            tw.setText( twitter );
            ins.setText( instagram );
            Glide.with( DetailFavourite.this )
                    .load( path )
                    .override( Target.SIZE_ORIGINAL )
                    .placeholder( R.mipmap.ic_launcher )
                    .into( ivposter );
            // and get whatever type user account id is
        }
        Realm.init( DetailFavourite.this );
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance( configuration );
    }

    }
