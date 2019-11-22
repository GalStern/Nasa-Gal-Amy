package com.example.nasa.learning.flashcard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasa.R;

import java.util.List;

public class FlashcardCollectionAdapter extends RecyclerView.Adapter<FlashcardCollectionAdapter.FlashCardVH>{
    private List<Bodies> flashcardsToAdapt;

    public void setData(List<Bodies> flashcardsToAdapt){
        this.flashcardsToAdapt = flashcardsToAdapt;
    }

public class FlashCardVH extends RecyclerView.ViewHolder{
    public View view;
    public TextView name;

    public FlashCardVH(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.name = itemView.findViewById(R.id.flashcard_name);
    }

    public void bind(final Bodies body) {
        name.setText(body.getName());

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = view.getContext();
                Intent intent = new Intent(context, FlashcardDetailActivity.class);
                intent.putExtra("name", body.getName());
                context.startActivity(intent);
            }
        });

    }
}
    @NonNull
    @Override
    public FlashCardVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.flash_card_side1, parent, false);

        FlashCardVH flashCardVH = new FlashCardVH(view);
        return flashCardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FlashCardVH holder, int position) {
        final Bodies flashCardAtPosition = flashcardsToAdapt.get(position);
        holder.bind(flashCardAtPosition);
    }

    @Override
    public int getItemCount() {
        int a ;
        if(flashcardsToAdapt != null && !flashcardsToAdapt.isEmpty()) {
            a = flashcardsToAdapt.size();
        }
        else {
            a = 0;
        }
        return a;
    }
}
