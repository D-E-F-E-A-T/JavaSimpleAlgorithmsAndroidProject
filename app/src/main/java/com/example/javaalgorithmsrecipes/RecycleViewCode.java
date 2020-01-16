package com.example.javaalgorithmsrecipes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecycleViewCode extends RecyclerView.Adapter<RecycleViewCode.CodeViewHolder>
implements Filterable {

    private ItemClickListener mClickListener;
    private final static int FADE_DURATION = 1000;
    private Context context;
    private List<CodeObject> codeObjectList;
    private List<CodeObject> codeObjectListFiltered;
    private ContactsAdapterListener listener;
    RecycleViewCode(Context context, List<CodeObject> data, ContactsAdapterListener listener){
        this.context = context;
        this.listener = listener;
        this.codeObjectList = data;
        this.codeObjectListFiltered = data;
    }

    @NonNull
    @Override
    public CodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new CodeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CodeViewHolder holder, int position) {
        holder.myTextView.setText(codeObjectListFiltered.get(position).getTitle());
        holder.author.setText(codeObjectListFiltered.get(position).getAuthor());
        holder.img.setImageResource(codeObjectListFiltered.get(position).getImage());
//        setScaleAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return codeObjectListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        List<CodeObject> filteredList = new ArrayList<>();
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    codeObjectListFiltered = codeObjectList;
                } else {
                    for (CodeObject row : codeObjectList) {
                        if (BuildConfig.DEBUG) {
//                            Log.i("JAVATAO", row.getTitle());
                        }
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                            if (BuildConfig.DEBUG) {
                                Log.i("JAVATAO", "row matching is: " + row.getTitle());
                            }
                        }
                    }
                    codeObjectListFiltered = filteredList;
                    if (BuildConfig.DEBUG) {
                        Log.i("JAVATAO", "codeObjectListFiltered row matching is: " + codeObjectListFiltered.get(0).getTitle());
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = codeObjectListFiltered;
                if (BuildConfig.DEBUG) {
                    Log.i("JAVATAO", "filterResults are: " + codeObjectListFiltered.get(0).getTitle());
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                if (results.count == 0) {
                    //do something
                } else {
                    @SuppressWarnings("unchecked")
                    ArrayList<CodeObject> lst = (ArrayList<CodeObject>)results.values;
                    ArrayList<CodeObject> itemsList = new ArrayList<CodeObject>(lst);
                    codeObjectListFiltered = itemsList;
                    for (CodeObject item : codeObjectListFiltered) {
                        if (BuildConfig.DEBUG) {
                            Log.i("JAVATAOPUBLISH", "publishResults are: " + item.getTitle());
                        }
                    }
                }
                notifyDataSetChanged();
            }
        };
    }


    public class CodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView,author;
        ImageView img;
        CodeViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvCodeName);
            author = itemView.findViewById(R.id.tvAuthor);
            img = itemView.findViewById(R.id.tvImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactSelected(codeObjectListFiltered.get(getAdapterPosition()));
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    String getItem(int id) {
        return String.valueOf(codeObjectListFiltered.get(id));
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ContactsAdapterListener {
        void onContactSelected(CodeObject codeObject);
    }


}