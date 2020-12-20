package com.example.lcfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lcfinder.model.ProblemRepo;

public class MainActivity extends AppCompatActivity {

    ProblemRepo repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            repo = new ProblemRepo( getApplicationContext());
        }

        catch (Exception e){
            Toast.makeText(MainActivity.this, "Json parse failed ", Toast.LENGTH_SHORT).show();
        }

    }

    public void find(View view){
        String url = repo.findUrlGivenId(100);
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}