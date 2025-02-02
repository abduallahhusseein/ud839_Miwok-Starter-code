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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.movie_app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     TextView Numbers,Family,Colors,Phrases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //  view ListItemView=convertView
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        Numbers= (TextView) findViewById(R.id.numbers);
        Family = (TextView) findViewById(R.id.family);
        Colors =(TextView) findViewById(R.id.colors);
        Phrases= (TextView) findViewById(R.id.phrases);
        Numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbers=new Intent(MainActivity.this,NumbersActivity.class);
                if(numbers.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(numbers);
                }
            }
        });
        Family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent family=new Intent(MainActivity.this,FamilyActivity.class);
                if(family.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(family);
                }
            }
        });
        Colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colors=new Intent(MainActivity.this,ColorsActivity.class);
                if(colors.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(colors);
                }
            }
        });
        Phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrases=new Intent(MainActivity.this,PhrasesActivity.class);
                if(phrases.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(phrases);
                }
            }
        });
    }
}
