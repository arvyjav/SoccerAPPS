package com.example.soccerapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmConfiguration;
public class ListDataFavourite extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    TextView tvnodata;
    RecyclerView recyclerView;
    DataAdapterFavourite adapter;
    List<ModelMovieRealm> DataArrayList; //kit add kan ke adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_data );
        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById( R.id.rvdata );
        DataArrayList = new ArrayList<>();
        // Setup Realm
        tvnodata = (TextView) findViewById( R.id.tvnodata );
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance( configuration );
        realmHelper = new RealmHelper( realm );
        DataArrayList = realmHelper.getAllSoccer();
        if (DataArrayList.size() == 0) {
            tvnodata.setVisibility( View.VISIBLE );
            recyclerView.setVisibility( View.GONE );

        } else {
            tvnodata.setVisibility( View.GONE );
            recyclerView.setVisibility( View.VISIBLE );

            adapter = new DataAdapterFavourite( DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {
                    Intent move = new Intent( getApplicationContext(), DetailFavourite.class );
                    move.putExtra( "Team", DataArrayList.get( position ).getTeam() );
                    move.putExtra( "DescriptionEN", DataArrayList.get( position ).getDescriptionEN() );
                    move.putExtra( "TeamBadge", DataArrayList.get( position ).getTeamBadge() );
                    move.putExtra( "Sport", DataArrayList.get( position ).getSport() );
                    move.putExtra( "Country", DataArrayList.get( position ).getCountry() );
                    move.putExtra( "Website", DataArrayList.get( position ).getWebsite() );
                    move.putExtra( "Facebook", DataArrayList.get( position ).getFacebook() );
                    move.putExtra( "Twitter", DataArrayList.get( position ).getTwitter() );
                    move.putExtra( "Instagram", DataArrayList.get( position ).getInstagram() );
                    move.putExtra( "FormedYear", DataArrayList.get( position ).getFormedYear() );
                    // di putextra yang lain
                    startActivity( move );
                }

                @Override
                public void test() {

                }
            } );
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( ListDataFavourite.this );
            recyclerView.setLayoutManager( layoutManager );
            recyclerView.setAdapter( adapter );
        }


    }
}