package com.example.nasa.learning.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nasa.R;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class YouTubeActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Button btnPlayVideo = (Button) findViewById(R.id.buttonVideo);
        Button btnPlaylist = (Button) findViewById(R.id.buttonPlaylist);

        //Passing to the setOnClickListener method and object that passes the OnClickListener
        //Interface that's called when the button is tapped

        btnPlayVideo.setOnClickListener(this);
        btnPlaylist.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        //Utilising YoutubeStandalonePlayer
        switch (view.getId()) {
            case R.id.buttonVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubePlayerActivity.GOOGLE_API_KEY, YoutubePlayerActivity.YOUTUBE_VIDEO_ID, 0, true, false);
                break;

            case R.id.buttonPlaylist:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubePlayerActivity.GOOGLE_API_KEY, YoutubePlayerActivity.YOUTUBE_PLAYLIST, 0, 0, true, true);
                break;

            default:

        }

        if (intent != null) {
            startActivity(intent);
        }


    }
}