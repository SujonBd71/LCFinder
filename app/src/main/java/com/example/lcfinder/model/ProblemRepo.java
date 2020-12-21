package com.example.lcfinder.model;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ProblemRepo {
    HashMap<String, String> idToUrl;

    public  ProblemRepo(Context context) throws IOException, JSONException {
        idToUrl = new HashMap<String, String>();
        String jsonString = loadJSONFromAsset(context);
        JSONObject obj = new JSONObject(jsonString);
        parseJsonObject(obj);
    }

    private void parseJsonObject(JSONObject jsonObj) throws JSONException {
        List<String> list = new ArrayList<String>();
        JSONArray array = jsonObj.getJSONArray("stat_status_pairs");
        for(int i = 0 ; i < array.length() ; i++){
            JSONObject obj = array.getJSONObject(i);
            JSONObject objDetails = obj.getJSONObject("stat");
            String id = objDetails.getString("frontend_question_id");
            String url = objDetails.getString("question__title_slug");
            url = "https://leetcode.com/problems/"+url;
            idToUrl.put(id, url);
        }
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String findUrlGivenId(String id){
        String url = idToUrl.getOrDefault(id, "http://google.com");

        return url;
    }

}
