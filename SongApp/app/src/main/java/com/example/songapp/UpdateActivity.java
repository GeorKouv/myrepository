package com.example.songapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.strictmode.CleartextNetworkViolation;
import android.os.strictmode.CredentialProtectedWhileLockedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    TextView updateLabel;
    EditText Name;
    EditText Category;
    EditText Artist;
    EditText Rating;
    EditText Critic;
    EditText Duration;
    EditText Link;

    Button updateButton, clearButton, deleteButton;

    String id, name, category, artist, rating, critic, duration, link;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateLabel = findViewById(R.id.updateLabel);
        Name = findViewById(R.id.NameId_update);
        Category = findViewById(R.id.CategoryId_update);
        Artist = findViewById(R.id.ArtistId_update);
        Rating = findViewById(R.id.RatingId_update);
        Critic = findViewById(R.id.CriticId_update);
        Duration = findViewById(R.id.DurationId_update);
        Link = findViewById(R.id.LinkId_update);

        updateButton = findViewById(R.id.updateButton);
        clearButton = findViewById(R.id.clearButtonId_update);
        deleteButton = findViewById(R.id.deleteButton);

        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                name = Name.getText().toString().trim();
                category = Category.getText().toString().trim();
                artist = Artist.getText().toString().trim();
                rating = Rating.getText().toString().trim();
                critic = Critic.getText().toString().trim();
                duration = Duration.getText().toString().trim();
                link = Link.getText().toString().trim();
                myDB.updateData(id, name, category, artist, rating, critic, duration, link);

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.getText().clear();
                Category.getText().clear();
                Artist.getText().clear();
                Rating.getText().clear();
                Critic.getText().clear();
                Duration.getText().clear();
                Link.getText().clear();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog();

            }
        });

    }


    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")
        && getIntent().hasExtra("category") && getIntent().hasExtra("artist") && getIntent().hasExtra("rating") &&  getIntent().hasExtra("critic")
        &&  getIntent().hasExtra("duration") &&  getIntent().hasExtra("link"))
        {


            id = getIntent().getStringExtra( "id");
            name = getIntent().getStringExtra("name");
            category = getIntent().getStringExtra("category");
            artist = getIntent().getStringExtra("artist");
            rating = getIntent().getStringExtra("rating");
            critic = getIntent().getStringExtra("critic");
            duration = getIntent().getStringExtra("duration");
            link = getIntent().getStringExtra("link");



            Name.setText(name);
            Category.setText(category);
            Artist.setText(artist);
            Rating.setText(rating);
            Critic.setText(critic);
            Duration.setText(duration);
            Link.setText(link);


        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
