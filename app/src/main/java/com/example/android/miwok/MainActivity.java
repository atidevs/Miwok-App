/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView numbers_textView;
    TextView family_textView;
    TextView colors_textView;
    TextView phrases_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        /**
         * Four TextView JAVA Objects created and Linked to the XML TextViews
         */
        numbers_textView = (TextView) findViewById(R.id.numbers);
        colors_textView = (TextView) findViewById(R.id.colors);
        family_textView = (TextView) findViewById(R.id.family);
        phrases_textView = (TextView) findViewById(R.id.phrases);

        /**
         * This method is Called Whenever the User Clicks on the Numbers TextView
         * The method Handles the OnClick Event and Sends the User to the Numbers Activity
         */

        numbers_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNumbers = new Intent(numbers_textView.getContext(), Numbers.class);
                startActivity(intentNumbers);
            }
        });

        /**
         * This method is Called Whenever the User Clicks on the Colors TextView
         * The method Handles the OnClick Event and Sends the User to the Colors Activity
         */

        colors_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentColors = new Intent(family_textView.getContext(), Colors.class);
                startActivity(intentColors);
            }
        });

        /**
         * This method is Called Whenever the User Clicks on the Family Members TextView
         * The method Handles the OnClick Event and Sends the User to the Family Activity
         */

        family_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFamily = new Intent(family_textView.getContext(), Family.class);
                startActivity(intentFamily);
            }
        });

        /**
         * This method is Called Whenever the User Clicks on the Family Phrases TextView
         * The method Handles the OnClick Event and Sends the User to the Phrases Activity
         */

        phrases_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPhrases = new Intent(phrases_textView.getContext(), Phrases.class);
                startActivity(intentPhrases);
            }
        });


        /**
         * This Code uses an Array and an Array is not a Class, it doesn't allow to access elements
         * and modify them through methods and is Static and the size is fixed once declared !!!
         * Checkout the Numbers Activity to see how ArrayList is more flexible in usage than an Array
         */
        /*
        String[] words = new String[10];
        words[0] = getString(R.string.eng_1);
        words[1] = getString(R.string.eng_2);
        words[2] = getString(R.string.eng_3);
        words[3] = getString(R.string.eng_4);
        words[4] = getString(R.string.eng_5);
        words[5] = getString(R.string.eng_6);
        words[6] = getString(R.string.eng_7);
        words[7] = getString(R.string.eng_8);
        words[8] = getString(R.string.eng_9);
        words[9] = getString(R.string.eng_10);
        */
    }
}

/**
 * This Commented Code was Used to configure the OnClick Event on each TextView
 * Using the XML Attribute "OnClick"
 * The Code Above uses only the Java Code to Configure the OnClick Event Listener
 */
    /*
    public void numbersCategory(View view) {
        Intent intent = new Intent(this, Numbers.class);
        startActivity(intent);
    }


    public void familyCategory(View view) {
        Intent intent = new Intent(this, Family.class);
        startActivity(intent);
    }


    public void colorsCategory(View view) {
        Intent intent = new Intent(this, Colors.class);
        startActivity(intent);
    }


    public void phrasesCategory(View view) {
        Intent intent = new Intent(this, Phrases.class);
        startActivity(intent);
    }
    */