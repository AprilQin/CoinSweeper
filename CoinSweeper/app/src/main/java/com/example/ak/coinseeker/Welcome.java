package com.example.ak.coinseeker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        final ImageView image = (ImageView) findViewById(R.id.imageView);
        final TextView coin_seeker = (TextView) findViewById(R.id.Name_of_application);
        final TextView hint_msg = (TextView) findViewById(R.id.Hint_message);

        RelativeLayout click_on_screen = (RelativeLayout) findViewById(R.id.activity_welcome);
        click_on_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Welcome.this, Main_menu.class));
            }
        });


        Animation title_ani = AnimationUtils.loadAnimation(Welcome.this, R.anim.slide_top_to_bottom);
        coin_seeker.startAnimation(title_ani);

        Animation background_animation = AnimationUtils.loadAnimation(Welcome.this, R.anim.slide_left_to_right);
        image.setAnimation(background_animation);

        Animation hint_ani = AnimationUtils.loadAnimation(Welcome.this, R.anim.blink);
        hint_msg.setAnimation(hint_ani);

    }


}

