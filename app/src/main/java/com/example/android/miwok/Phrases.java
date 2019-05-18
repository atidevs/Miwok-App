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

public class Phrases extends AppCompatActivity {

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
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this,words,getResources().getColor(R.color.category_phrases));
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
                    audioPlayer = MediaPlayer.create(Phrases.this, words.get(position).getAudioResourceId());
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
