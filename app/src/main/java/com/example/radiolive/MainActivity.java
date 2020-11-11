package com.example.radiolive;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    SimpleExoPlayer myExoPlayer;
    ProgressBar progressBar;

    Player.EventListener listener;

    String radio_url = "http://voa28.akacast.akamaistream.net/7/325/437810/v1/ibb.akacast.akamaistream.net/voa28";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);

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
        Toast.makeText(this, "Radio Streaming live",Toast.LENGTH_SHORT).show();

        listener = new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
               if(state == Player.STATE_BUFFERING) {
                   progressBar.setVisibility(View.VISIBLE);
               }

               if(state == Player.STATE_READY) {
                   progressBar.setVisibility(View.GONE);
               }
            }
        };
        myExoPlayer.addListener(listener);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myExoPlayer != null) {
            myExoPlayer.removeListener(listener);
            myExoPlayer.release();
            myExoPlayer = null;
        }
    }
}