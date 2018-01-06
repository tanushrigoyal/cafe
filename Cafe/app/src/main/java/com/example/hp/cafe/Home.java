package com.example.hp.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.cafe.Common.Common;
import com.example.hp.cafe.Interface.ItemClickListener;
import com.example.hp.cafe.Model.category;
import com.example.hp.cafe.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtFullName;
    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<com.example.hp.cafe.Model.category,MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        //firebase
        database=FirebaseDatabase.getInstance();
        category=database.getReference("category");

        FloatingActionButton fab =(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(Home.this,"hiii",Toast.LENGTH_SHORT).show();
                Intent cartIntent = new Intent(Home.this,Cart.class);
                startActivity(cartIntent);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set name for user


         View headerView= navigationView.getHeaderView(0);
        txtFullName=(TextView)headerView.findViewById(R.id.txtFullName);
         txtFullName.setText(Common.CurrentUser.getName());


        //load menu



        recycle_menu=(RecyclerView)findViewById(R.id.recycler_menu);
        recycle_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycle_menu.setLayoutManager(layoutManager);

        loadMenu();

    }

    private void loadMenu() {
        adapter =  new FirebaseRecyclerAdapter<com.example.hp.cafe.Model.category, MenuViewHolder>(category.class,R.layout.menu_items,MenuViewHolder.class, category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final category clickitem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //first Categoryid to new activity
                        Intent foodlist= new Intent(Home.this,Foodlist.class);
//category id is key
                        foodlist.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodlist);

                    }
                });
            }
        };
        recycle_menu.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu) {
            Intent i= new Intent(this,Foodlist.class);
            startActivity(i);
        } else if (id == R.id.order) {

        } else if (id == R.id.offer) {
            Intent i= new Intent(this,Happy.class);
            startActivity(i);

        } else if (id == R.id.Logout) {
            Intent intent =new Intent(Home.this,Signin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        } else if (id == R.id.Contact) {
            Intent i=new Intent(this,Contact.class);
            startActivity(i);

        } else if (id == R.id.Rate) {
            Intent i=new Intent(this,Rate_us.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
