package com.example.appbuyit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbuyit.R;
import com.example.appbuyit.adapter.Loaispadapter;
import com.example.appbuyit.model.Loaisp;
import com.example.appbuyit.ultil.Checkconnection;
import com.example.appbuyit.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewflipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    Loaispadapter loaispadapter;
    int id;
    String tenloaisp="";
    String hinhanhloaisp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (Checkconnection.haveNetworkConnection(getApplicationContext()))
        {
            Actionbar();
            ActionViewFlipper();
            Getdulieuloaisp();
        }
        else
        {
            Checkconnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi");
            finish();
        }


    }

    private void Getdulieuloaisp() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest  jsonArrayRequest= new JsonArrayRequest(Server.DươngdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                  if (response!=null)
                  {
                      for(int i=0;i<response.length();i++)
                      {
                          try {
                              JSONObject jsonObject= response.getJSONObject(i);
                              id= jsonObject.getInt("id");
                              tenloaisp= jsonObject.getString("tenloaisp");
                              hinhanhloaisp= jsonObject.getString("hinhanhloaisp");
                              mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                              loaispadapter.notifyDataSetChanged();
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }
                  }
                  else
                  {
                      Checkconnection.ShowToast_Short(getApplicationContext(),"Respon JSON is null");
                  }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://salt.tikicdn.com/cache/w584/ts/banner/65/8b/c1/23de225e3ffc612a23b052d034ca23e5.jpg");
        mangquangcao.add("https://salt.tikicdn.com/cache/w584/ts/banner/32/d8/55/171bf220034f12c6e9fe01cb0f48f226.png");
        mangquangcao.add("https://salt.tikicdn.com/cache/w584/ts/banner/31/1d/12/61328ed39ae03ef2da86aab7cd5ca4d5.png");
        mangquangcao.add("https://salt.tikicdn.com/cache/w584/ts/banner/a8/96/9c/37cc93532887ffc553c13942feb79fb8.png");
        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView= new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflipper.addView(imageView);
        }
        viewflipper.setFlipInterval(5000);
        viewflipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sight_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);
        viewflipper.setInAnimation(animation_slide_in);
        viewflipper.setOutAnimation(animation_slide_out);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar= findViewById(R.id.toolbarmanhinhchinh);
        viewflipper= findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh= findViewById(R.id.recyclerview);
        navigationView= findViewById(R.id.navigationview);
        listViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout= findViewById(R.id.drawerlayout);
        mangloaisp=new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang chinh","http://icons.iconarchive.com/icons/graphicloads/colorful-long-shadow/128/Home-icon.png"));
        loaispadapter= new Loaispadapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispadapter);
    }
}
