package com.example.contactslistapplication;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewManager {
    private static RecyclerViewManager instance;
    private static RecyclerView recyclerView;
    private ContactsAdapter adapter;

    private RecyclerViewManager() { }

    public static synchronized RecyclerViewManager getInstance() {
        if (instance == null) {
            instance = new RecyclerViewManager();
        }
        return instance;
    }

    public void setRecyclerView(RecyclerView recyclerView, ContactsAdapter adapter) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }

    public static RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ContactsAdapter getAdapter() {
        return adapter;
    }

    public void refreshRecyclerView() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
