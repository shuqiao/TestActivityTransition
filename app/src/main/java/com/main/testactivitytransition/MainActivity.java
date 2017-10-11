package com.main.testactivitytransition;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivGirl;
    private ImageView ivBoy;
    private Button btnStart;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(slide);
        getWindow().setEnterTransition(slide);
        getWindow().setReenterTransition(slide);

        setContentView(R.layout.activity_main);

        ivGirl = findViewById(R.id.iv_girl);
        ivBoy = findViewById(R.id.iv_boy);
        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowImageDetailActivity.class);

                Pair girl = new Pair(ivGirl, ViewCompat.getTransitionName(ivGirl));
                Pair boy = new Pair(ivBoy, ViewCompat.getTransitionName(ivBoy));
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, girl, boy);
                startActivity(intent, compat.toBundle());
            }
        });
    }
}
