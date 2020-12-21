package com.example.lcfinder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void find(View view){
        EditText idText = (EditText) (findViewById(R.id.probID));
        String id = idText.getText().toString();
        String url = repo.findUrlGivenId(id);
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}