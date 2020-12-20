package com.example.lcfinder.model;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ProblemRepo {

    public  ProblemRepo(Context context) throws IOException, JSONException {
        String jsonString = loadJSONFromAsset(context);
        JSONObject obj = new JSONObject(jsonString);
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("all-problem-set-Info-from-API.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String findUrlGivenId(int id){
        String url = "http://leetcode.com";

        return url;
    }

}
