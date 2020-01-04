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

import com.example.appbuyit.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewflipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Actionbar();
        ActionViewFlipper();

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
    }
}
