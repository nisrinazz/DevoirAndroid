package com.example.devoir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String HTTP_URL = "https://belatar.name/rest/profile.php?login=test&passwd=test&id=9998&notes=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, HTTP_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Note> notes = new ArrayList<>();
                    for (int i = 0; i < response.getJSONArray("notes").length(); i++) {
                        Note note = new Note(response.getJSONArray("notes").getJSONObject(i).getString("label"), response.getJSONArray("notes").getJSONObject(i).getDouble("score"));
                        notes.add(note);
                    }
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showNotes(notes);
                    } else {
                        hideNotes();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(MainActivity.class.getSimpleName() , error.toString());
            }
        }

        );
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
}

    private void showNotes(ArrayList<Note> notes) {
        NoteAdapter adapter = new NoteAdapter(getApplicationContext(), notes);
        ListView listView = findViewById(R.id.notes_list);
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);

    }

    private void hideNotes() {
        ListView listView = findViewById(R.id.notes_list);
        listView.setVisibility(View.GONE);
    }

}