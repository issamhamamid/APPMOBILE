package com.example.PROJETTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.PROJETTP.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    ActivityMainBinding binding;


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<StoryModel> storieslist;
    Adapter adapter;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView2.setSelectedItemId(R.id.home);

        binding.bottomNavigationView2.setOnItemSelectedListener(item ->{
            if(item.getItemId() == R.id.favoritesbtnn){
                Intent i = new Intent(this , FavoritesActivity.class );
                startActivity(i);
                adapter.notifyDataSetChanged();

            }
            return true;
        });




        initData();
        initRecyclerView();



    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(storieslist , this );
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        storieslist = Data.storieslist;


        
    }


    @Override
    public void onStoryClicked(int position) {
        Intent i = new Intent(this , NavigationActivity.class );


        i.putExtra("scenes", storieslist.get(position).getScenes());
        i.putExtra("numscenes", storieslist.get(position).getScenes().length);
        startActivity(i);
    }

    @Override
    public void onFavoritebtnClicked(int position) {
        storieslist.get(position).favoriteStatus=!storieslist.get(position).favoriteStatus;
        adapter.notifyItemChanged(position);

    }
}