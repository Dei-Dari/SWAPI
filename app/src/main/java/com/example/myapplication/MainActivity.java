//https://developer.android.com/reference/android/widget/SearchView
//https://developer.android.com/reference/android/widget/SearchView#setOnQueryTextListener(android.widget.SearchView.OnQueryTextListener)
//https://developer.android.com/reference/android/content/Intent
//https://developer.android.com/reference/androidx/appcompat/widget/SearchView.OnQueryTextListener
//https://swapi.dev/documentation#people
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private List<Character> characters = new ArrayList<>();
    private ApiService swapiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        swapiService = retrofit.create(ApiService.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CharacterAdapter(characters, this::onCharacterClick);
        recyclerView.setAdapter(adapter);

        // listner, пользователь выполняет действия в SearchView
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCharacters(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCharacters(newText);
                return true;
            }
        });

        // поиск всех персонажей
        searchCharacters("");
    }


    private void searchCharacters(String query) {
        Call<CharacterResponse> call = swapiService.getCharacters(query);
        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    characters.clear();
                    characters.addAll(response.body().getCharacters());
                    adapter.notifyDataSetChanged(); //перерисовка списка на экране
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * @param character передача данных между двумя Activity
     */
    private void onCharacterClick(Character character) {
        if (character != null) {
            Log.d("MainActivity", "Character name: " + character.getName());
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("character", character);
            startActivity(intent);
        } else {
            Log.e("MainActivity", "Character is null");
        }
    }
}