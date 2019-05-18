package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    private MediaPlayer audioPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i) {
                case 1:
                    audioPlayer.start();
                    break;
                case 2:
                    audioPlayer.stop();
                    break;
                case 3:
                    audioPlayer.pause();
                    break;
                case 4:
                    audioPlayer.pause();
                    break;
                default:
                    audioPlayer.release();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        /**
         * This code uses an ArrayList which is more flexible and more dynamic and is a Class
         * And only holds Objects as elements of the ArrayList and doesn't accept primitive data types as elements !!!
         */

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        final WordAdapter wordAdapter = new WordAdapter(this, words, getResources().getColor(R.color.category_numbers));
        ListView listView = (ListView) findViewById(R.id.list);
        //GridView gridView = (GridView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        //gridView.setAdapter(itemsAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                int audioFocusResult = audioManager.requestAudioFocus(audioFocusChangeListener, audioManager.STREAM_MUSIC, audioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (audioFocusResult == audioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    audioPlayer = MediaPlayer.create(Numbers.this, words.get(position).getAudioResourceId());
                    audioPlayer.start();
                    audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                        }
                    });
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        if (audioPlayer != null) {
            audioPlayer.release();
            audioPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public String toString() {
        return "Numbers{" +
                "audioPlayer=" + audioPlayer +
                '}';
    }
}


/**
 * Commented below is :
 * Writing to the Log to check if the ArrayList is properly created !!!
 * <p>
 * This is a While Loop !!
 * <p>
 * This is a For Loop that does the same thing as the While Loop !
 * <p>
 * This is the New Code that Uses the ListView and Recycling Views Concept !!
 */
        /*
        for(int i =0; i<10 ; i++) {
            Log.v("Numbers Activity","The ArrayList element N° " + i + " is " + engWords.get(i));
        }
        */

/**
 * This is a While Loop !!
 */
        /*
        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        int counter =0;
        while ( counter<engWords.size() ) {
            TextView word = new TextView(this);
            word.setText(engWords.get(counter));
            rootView.addView(word);
            counter++;
        }
        */

/**
 * This is a For Loop that does the same thing as the While Loop !
 */
        /*
        for(int counter=0; counter<engWords.size();counter++){
            TextView word = new TextView(this);
            word.setText(engWords.get(counter));
            rootView.addView(word);
        }
        */

/**
 * This is the New Code that Uses the ListView and Recycling Views Concept !!
 */



