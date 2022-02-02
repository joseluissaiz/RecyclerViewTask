package com.overshade.recyclerviewtask;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.overshade.recyclerviewtask.databinding.FragmentFirstBinding;

import java.util.LinkedList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private LinkedList<String> wordList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wordList = new LinkedList<>();
        for (int i = 1; i <= 2000; i++) {
            wordList.add("Word"+i);
        }
        binding.recyclerview.setAdapter(new WordListAdapter(requireContext(), wordList));
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /** Words Recycler View Adapter*/
    public static class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
        private LayoutInflater mInflater;
        private LinkedList<String> mWordList;

        public WordListAdapter(Context context, LinkedList<String> wordList) {
            mInflater = LayoutInflater.from(context);
            this.mWordList = wordList;
        }

        @NonNull
        @Override
        public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mItemView = mInflater.inflate(R.layout.item_row, parent, false);
            return new WordViewHolder(mItemView, this);
        }

        @Override
        public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
            String mCurrent = mWordList.get(position);
            holder.wordItemView.setText(mCurrent);
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }

        public static class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView wordItemView;
            private WordListAdapter mAdapter;

            public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
                super(itemView);
                wordItemView = itemView.findViewById(R.id.word);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                wordItemView.setText(String.format("Clicked! %s", wordItemView.getText()));
            }
        }
    }
}