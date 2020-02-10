package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.movie_app.R;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    final ArrayList<Word> words = new ArrayList<>();
    ListView listView;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                           mediaPlayer.pause();
                           mediaPlayer.seekTo(0);
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                        mediaPlayer.start();
                        //resume playback
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
                    {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.

                        releaseMediaPlayer();
                    }
                }
            };
    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    // ArrayAdapter<Word>itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        //listView=new ListView(this);
        //create and setup {@link AudioManager} to request audioFocus
        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        words.add(new Word(R.drawable.color_red, "Red", "weṭeṭṭi", R.raw.color_red));
        words.add(new Word(R.drawable.color_white, "White", "kelelli", R.raw.color_white));
        words.add(new Word(R.drawable.color_black, "Black", "kululli", R.raw.color_black));
        words.add(new Word(R.drawable.color_brown, "Brown", "ṭakaakki", R.raw.color_brown));
        words.add(new Word(R.drawable.color_dusty_yellow, "Dusty Yellow", "ṭopiisә", R.raw.color_dusty_yellow));
        words.add(new Word(R.drawable.color_gray, "Gray", "ṭopoppi", R.raw.color_gray));
        words.add(new Word(R.drawable.color_green, "Green", "chokokki", R.raw.color_green));
        words.add(new Word(R.drawable.color_mustard_yellow, "Mustard Yellow", "chiwiiṭә", R.raw.color_mustard_yellow));
        // colors.add(new Word(R.drawable.number_nine,"Nine","wo' e"));
        // colors.add(new Word(R.drawable.number_ten,"Ten","na'aacha"))
        WordAdapter colorsAdapter = new WordAdapter(this, words, R.color.category_colors);
        listView = (ListView) findViewById(R.id.list_colors);
        listView.setAdapter(colorsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                int result=audioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                   // audioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    //we have a audio focus now

                mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getPositionForAudio());
                mediaPlayer.start();
                //عندما يكون المستخدم يضغطعلى بوز بوتن
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getPositionForAudio());
                mediaPlayer.start();
                //عندما ينتهى الريكورد المسجل للحفاظ على مساحه التخزين
                mediaPlayer.setOnCompletionListener(mcompletionListener);}
            }
        });
    }
    @Override
    protected void onStop()
    {
        //to avoid the app from cruch when stopping it
        super.onStop();
        releaseMediaPlayer();
    }
    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}