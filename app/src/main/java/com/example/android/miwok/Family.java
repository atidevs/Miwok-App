package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {

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

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter wordAdapter = new WordAdapter(this,words,getResources().getColor(R.color.category_family));
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
                    audioPlayer = MediaPlayer.create(Family.this, words.get(position).getAudioResourceId());
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
}
