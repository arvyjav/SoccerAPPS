package com.example.soccerapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {
     RecyclerView recyclerView;
     DataAdapter adapter;
     ArrayList<Model> DataArrayList; //kit add kan ke adapter
    ProgressDialog dialog;
    ImageView tambah_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(ListData.this);
        //addData();
        addDataOnline();
    }

    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setStrTeam("strTeam");
        data1.setStrTeamBadge("https://www.thesportsdb.com/images/media/team/logo/q2mxlz1512644512.png");
        data1.setStrSport("caborOlahraga");
        data1.setStrCountry("strCountry");
        data1.setIntFormedYear("01-01-2020");
        data1.setStrWebsite("strWebsite");
        data1.setStrFacebook( "strFacebook" );
        data1.setStrTwitter( "strTwitter" );
        data1.setStrInstagram( "strInstagram" );

        DataArrayList.add(data1);
        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {
            }
            @Override
            public void test() {
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //get data online


    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang memproses data");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setIdTeam(jsonObject.getInt("idTeam"));
                                modelku.setStrTeam(jsonObject.getString("strTeam"));
                                modelku.setStrSport(jsonObject.getString("strSport"));
                                modelku.setStrDescriptionEN( jsonObject.getString( "strDescriptionEN" ) );
                                modelku.setIntFormedYear(jsonObject.getString("intFormedYear"));
                                modelku.setStrTeamBadge(jsonObject.getString("strTeamBadge"));
                                modelku.setStrCountry(jsonObject.getString("strCountry"));
                                modelku.setStrWebsite( jsonObject.getString( "strWebsite" ) );
                                modelku.setStrFacebook( jsonObject.getString( "strFacebook" ));
                                modelku.setStrTwitter( jsonObject.getString( "strTwitter" ));
                                modelku.setStrInstagram( jsonObject.getString( "strInstagram" ));

                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model soccer = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailSoccer.class);
                                    intent.putExtra("idTeam",soccer.idTeam);
                                    intent.putExtra( "intFormedYear",soccer.intFormedYear );
                                    intent.putExtra("strTeam",soccer.strTeam);
                                    intent.putExtra("strCountry",soccer.strCountry);
                                    intent.putExtra("strDescriptionEN",soccer.strDescriptionEN);
                                    intent.putExtra("strTeamBadge",soccer.strTeamBadge);
                                    intent.putExtra( "strWebsite", soccer.strWebsite );
                                    intent.putExtra( "strFacebook" , soccer.strFacebook);
                                    intent.putExtra( "strTwitter" , soccer.strTwitter);
                                    intent.putExtra( "strInstagram" , soccer.strInstagram);

                                    startActivity(intent);
                                    Toast.makeText(ListData.this, ""+position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}
