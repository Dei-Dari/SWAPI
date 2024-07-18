package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView heightTextView;
    private TextView massTextView;
    private TextView hairColorTextView;
    private TextView skinColorTextView;
    private TextView eyeColorTextView;
    private TextView birthYearTextView;
    private TextView genderTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Character character = (Character) getIntent().getSerializableExtra("character");

        nameTextView = findViewById(R.id.nameTextView);
        heightTextView = findViewById(R.id.heightTextView);
        massTextView = findViewById(R.id.massTextView);
        hairColorTextView = findViewById(R.id.hairColorTextView);
        skinColorTextView = findViewById(R.id.skinColorTextView);
        eyeColorTextView = findViewById(R.id.eyeColorTextView);
        birthYearTextView = findViewById(R.id.birthYearTextView);
        genderTextView = findViewById(R.id.genderTextView);


        nameTextView.setText(character.getName());
        heightTextView.setText("Height: " + character.getHeight());
        massTextView.setText("Mass: " + character.getMass());
        hairColorTextView.setText("Hair Color: " + character.getHairColor());
        skinColorTextView.setText("Skin Color: " + character.getSkinColor());
        eyeColorTextView.setText("Eye Color: " + character.getEyeColor());
        birthYearTextView.setText("Birth Year: " + character.getBirthYear());
        genderTextView.setText("Gender: " + character.getGender());

    }
}
