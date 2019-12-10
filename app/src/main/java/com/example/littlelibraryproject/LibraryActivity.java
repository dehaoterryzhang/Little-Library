package com.example.littlelibraryproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LibraryActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Button button;
    Button buttonAddLibraryPhoto, buttonNavigateToLibrary, buttonTakePhoto;
    TextView textViewLibraryGenres, textViewLibraryAddress;
    ImageView imageViewLibraryPhoto;
    private FirebaseAuth mAuth;

    private BottomNavigationView mLibraryNav;
    


    ///onCreate starts here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_library );

        buttonAddLibraryPhoto = findViewById ( R.id.buttonAddLibraryPhoto );
        buttonTakePhoto = findViewById ( R.id.buttonTakePhoto );
        buttonNavigateToLibrary = findViewById ( R.id.buttonNavigateToLibrary );

        textViewLibraryAddress = findViewById ( R.id.textViewLibraryAddress );
        textViewLibraryGenres = findViewById ( R.id.textViewLibraryGenres );

        imageViewLibraryPhoto = findViewById ( R.id.imageViewLibraryPhoto );

        mLibraryNav = findViewById(R.id.library_nav);

        mLibraryNav.setOnNavigationItemSelectedListener(this);
        mLibraryNav.getMenu().findItem(R.id.navLibrary).setChecked(true);

        String name = getIntent().getStringExtra("Title");                                      //Paul help
        textViewLibraryAddress.setText(name);

        mAuth = FirebaseAuth.getInstance ();

        buttonTakePhoto.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openTakePhoto();
            }
        } );

        buttonAddLibraryPhoto.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
              openAddLibraryPhoto();

            }
        } );
    }

    public void openTakePhoto() {
        Intent intent2 = new Intent ( this,AddPhoto3Activity.class );
        startActivity ( intent2 );
    }

    public void openAddLibraryPhoto() {
        Intent intent1 = new Intent ( this , AddPhoto2activity.class );
        startActivity ( intent1 );
    }

/// Menu items start here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemLogin) {
            Intent LoginIntent = new Intent(this, LoginActivity.class);
            startActivity(LoginIntent);

        } else if (item.getItemId() == R.id.itemMap) {
            Intent MapIntent = new Intent(this, MapsActivity.class);
            startActivity(MapIntent);
        } else if (item.getItemId() == R.id.itemUsers) {
            Intent UsersIntent = new Intent(this, ProfileActivity.class);
            startActivity(UsersIntent);
        } else if (item.getItemId() == R.id.itemLibrary) {
            Intent LibraryIntent = new Intent(this, LibraryActivity.class);
            startActivity(LibraryIntent);
        } else if (item.getItemId() == R.id.itemAddLibrary) {
            Intent AddLibraryIntent = new Intent(this, AddLibraryActivity.class);
            startActivity(AddLibraryIntent);
        }
        else if (item.getItemId() == R.id.itemLogOut){

            FirebaseAuth.getInstance().signOut();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                Toast.makeText(this, "You have been logged out", Toast.LENGTH_LONG).show();

                Intent LoginIntent = new Intent(this, LoginActivity.class);
                startActivity(LoginIntent);
            } else {
                Toast.makeText(this, "Log out failed", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.navMap) {
            Intent mapIntent = new Intent(LibraryActivity.this, MapsActivity.class);
            startActivity(mapIntent);
            return true;

        } else if (menuItem.getItemId() == R.id.navLibrary) {
            Intent libraryIntent = new Intent(LibraryActivity.this, LibraryActivity.class);
            startActivity(libraryIntent);
            return true;

        } else if (menuItem.getItemId() == R.id.navProfile) {
            Intent profileIntent = new Intent(LibraryActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            return true;
        }
        return false;
    }
}
