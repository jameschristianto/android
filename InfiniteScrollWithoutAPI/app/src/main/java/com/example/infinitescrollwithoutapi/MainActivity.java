package com.example.infinitescrollwithoutapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.infinitescrollwithoutapi.adapter.ContactAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean isScroll = true;
    private ContactAdapter contactAdapter = new ContactAdapter();
    private ArrayList<String> dataContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData(0);
        RecyclerView rvContact = findViewById(R.id.rvContact);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvContact.setLayoutManager(layoutManager);
        rvContact.setHasFixedSize(true);
        rvContact.setAdapter(contactAdapter);

        rvContact.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                final int countItems = layoutManager.getItemCount();
                int currentItem = layoutManager.getChildCount();
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int totalScrollItem =  currentItem + firstVisiblePosition;

                if(isScroll && (totalScrollItem >= countItems)){
                    isScroll = false;
                    contactAdapter.addDataLoading();

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            contactAdapter.remodeDataLoading();
                            fetchData(countItems);
                            isScroll = true;
                        }
                    }, 1000);
                }
            }
        });
    }

    private void fetchData(int countItems) {
        if(dataContacts.size() > 0){
            dataContacts.clear();
        }

        for(int i = countItems; i < countItems + 15; i++){
            dataContacts.add(i + 1 + ". James");
        }
        contactAdapter.addDataContact(dataContacts);

    }
}