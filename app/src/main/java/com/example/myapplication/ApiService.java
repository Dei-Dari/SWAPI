package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * @param search name/All
     * @return /people/-- получить все человеческие ресурсы
     */
    @GET("people")
    Call<CharacterResponse> getCharacters(@Query("search") String search);

    @GET("people/{id}")
    Call<Character> getCharacter(@Path("id") int id);
}
