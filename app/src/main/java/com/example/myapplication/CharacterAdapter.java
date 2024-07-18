//https://developer.android.com/develop/ui/views/layout/recyclerview#java
//https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder
//https://swapi.dev/
//https://swapi.dev/documentation
package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * адаптер для RecyclerView
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characters;
    private OnCharacterClickListener listener;

    public CharacterAdapter(List<Character> characters, OnCharacterClickListener listener) {
        this.characters = characters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.bind(character, listener);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        CharacterViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(android.R.id.text1);
        }

        void bind(final Character character, final OnCharacterClickListener listener) {
            nameTextView.setText(character.getName());
            itemView.setOnClickListener(v -> listener.onCharacterClick(character));
        }
    }

    public interface OnCharacterClickListener {
        void onCharacterClick(Character character);
    }
}
