package com.example.hp.cafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hp.cafe.Interface.ItemClickListener;
import com.example.hp.cafe.Model.Food;
import com.example.hp.cafe.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Foodlist extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodlist;
    String categoryId="";

    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_foodlist);

            //firebase

            database=FirebaseDatabase.getInstance();
            foodlist=database.getReference("Foods");

            recyclerView= (RecyclerView)findViewById(R.id.recycler_food);
            recyclerView.setHasFixedSize(true);


            layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if(getIntent() !=null)
            categoryId= getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId !=null)
        {
            loadListFood(categoryId);
        }


        }

    private void loadListFood(String categoryId) {

adapter =new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
        R.layout.food_items,
        FoodViewHolder.class,
foodlist.orderByChild("MenuId").equalTo(categoryId)
)
        //select * frmo food where id=
         {
    @Override
    protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {


        viewHolder.food_name.setText(model.getName());

        Picasso.with(getBaseContext()).load(model.getImage())
                .into(viewHolder.food_image);

        final Food local= model;
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //start new activity
                Intent foodDetail= new Intent(Foodlist.this,Food_Details.class);
                foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());//send food id to new activity
                startActivity(foodDetail);
            }
        });

    }
};
//set adapter
        //Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
