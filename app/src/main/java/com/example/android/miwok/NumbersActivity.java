package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.UserDictionary;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.movie_app.R;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    final ArrayList<Word> words = new ArrayList<Word>();
    // GridView gridView;
    ListView listview;
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
    private MediaPlayer.OnCompletionListener mcompletionListener = new  MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion (MediaPlayer mediaPlayer){
        releaseMediaPlayer();
    }
    };

    //ArrayAdapter<Word>itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
       // listview=new ListView(this);
        //we can do the following Word w=new Word("String 1","String 2")
        //
        words.add(new Word(R.drawable.number_one, "One", "lutti", R.raw.number_one));
        words.add(new Word(R.drawable.number_two, "Two", "otiiko", R.raw.number_two));
        words.add(new Word(R.drawable.number_three, "Three", "tolookosa", R.raw.number_three));
        words.add(new Word(R.drawable.number_four, "Four", "oyyisa", R.raw.number_four));
        words.add(new Word(R.drawable.number_five, "Five", "massokka", R.raw.number_five));
        words.add(new Word(R.drawable.number_six, "Six", "temmoka", R.raw.number_six));
        words.add(new Word(R.drawable.number_seven, "Seven", "kenekaku", R.raw.number_seven));
        words.add(new Word(R.drawable.number_eight, "Eight", "kawinta", R.raw.number_eight));
        words.add(new Word(R.drawable.number_nine, "Nine", "wo' e", R.raw.number_nine));
        words.add(new Word(R.drawable.number_ten, "Ten", "na'aacha", R.raw.number_ten));
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        listview = (ListView) findViewById(R.id.list_numbers);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(NumbersActivity.this,"list item is clicked",Toast.LENGTH_LONG).show();
                Word word = words.get(i);
                //عندما يكون المستخدم يضغطعلى بوز بوتن
                int result=audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getPositionForAudio());
                    mediaPlayer.start();
                    //عندما ينتهى الريكورد المسجل للحفاظ على مساحه التخزين
                    mediaPlayer.setOnCompletionListener(mcompletionListener);
                }
            }
        });

        //itemsAdapter=new ArrayAdapter<Word>(this,R.layout.list_item,words);
        //        listview.setAdapter(itemsAdapter);
        //  LinearLayout linear=(LinearLayout)findViewById(R.id.root_view);
        // itemsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        //itemsAdapter=new ArrayAdapter<String>(this,R.layout.list_item,words);
        // gridView = (GridView) findViewById(R.id.grid);
        // gridView.setAdapter(itemsAdapter);
        // linear.addView(listview);

//        int index=0;
//       while(index<words.size())
//       {
//           TextView textview=new TextView(this);
//           textview.setText(words.get(index));
//           linear.addView(textview);
//           index++;
////       }
//         for(int i=0;i<words.size();i++)
//         {
//             TextView textview=new TextView(this);
//                textview.setText(words.get(i));
//              linear.addView(textview);
//
//         }

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
