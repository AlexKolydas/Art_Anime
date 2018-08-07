package com.kolydasartanime.artanime.activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.kolydasartanime.artanime.activities.MainChatActivity;
import com.kolydasartanime.artanime.R;

public class AppsMainScreen extends BaseActivity {

    ImageButton facebookBtn;
    ImageButton twitchBtn;
    ImageButton chatRoomBtn;
    ImageButton membersUpBtn;
    ImageButton acsBtn;
    ImageButton discordbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        facebookBtn= (ImageButton) findViewById(R.id.artAnimeFacebook);
        twitchBtn = (ImageButton) findViewById(R.id.twitchBtn);
        chatRoomBtn=(ImageButton) findViewById(R.id.chatRoom);
        membersUpBtn =(ImageButton) findViewById(R.id.usersArts);
        acsBtn =(ImageButton) findViewById(R.id.acsTrack2);
        discordbtn =(ImageButton)findViewById(R.id.discordbtn);


        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com//groups/138509213384263"));
                startActivity(intent);
                }
        });

        twitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitch.tv/art_anime"));
                startActivity(intent);
            }
        });

        chatRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AppsMainScreen.this,MainChatActivity.class);
                startActivity(intent);
            }
        });

        membersUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AppsMainScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

        acsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.acscourier.net/el/web/mobile"));
                startActivity(intent);
            }
        });

        discordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/T4YeYuk"));
                startActivity(intent);
            }
        });


    }

    }
