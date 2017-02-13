package com.project.harsh.precticeall;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by harsh on 13/2/17.
 */

public class DataPage extends ListFragment {
//    int userId, id;
//    String title, body;
//    Exception exception;
    ListView listView;

    CursorAdapter customadapter;

    ArrayList<Post> postArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        View view = inflater.inflate(R.layout.data_page, container, false);
        listView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView etext = (TextView) getActivity().findViewById(R.id.login_email);
        TextView ptext = (TextView) getActivity().findViewById(R.id.login_password);

        String loginemail = getArguments().getString("key");
        String loginpass = getArguments().getString("key1");

        etext.setText(loginemail);
        ptext.setText(loginpass);

        HttpURLConnection connection;
        try

        {
            URL url = new URL("http://jsonplaceholder.typicode.com/posts");
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));


                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String bufferString = buffer.toString();


                try {
                    JSONArray rootArray = new JSONArray(bufferString);
                    for (int i = 0; i < rootArray.length(); i++) {
                        JSONObject postObject = rootArray.getJSONObject(i);
                        int userId = postObject.getInt("userId");
                        int id = postObject.getInt("id");
                        String title = postObject.getString("title");
                        String body = postObject.getString("body");

                        Post post = new Post();
                        post.setId(id);
                        post.setUserId(userId);
                        post.setTitle(title);
                        post.setBody(body);
                        postArrayList.add(post);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JsonDataAdapter adapter = new JsonDataAdapter(getActivity(),postArrayList);
        listView.setAdapter(adapter);
    }
}

//        return view;
//    }
//}
