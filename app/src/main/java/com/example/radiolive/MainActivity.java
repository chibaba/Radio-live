package com.example.radiolive;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    SimpleExoPlayer myExoPlayer;

    String radio_url = "http://voa28.akacast.akamaistream.net/7/325/437810/v1/ibb.akacast.akamaistream.net/voa28";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);

        initializePlayer();
    }

    private void initializePlayer() {
         myExoPlayer = new  SimpleExoPlayer.Builder( this)
                 .build();
         playerView.setPlayer(myExoPlayer);

        MediaItem  mediaItem = MediaItem.fromUri(Uri.parse(radio_url));
        myExoPlayer.addMediaItem(mediaItem);

        myExoPlayer.prepare();
        myExoPlayer.play();
        }
}