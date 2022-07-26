package org.acox.challenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private ArrayList<ContentModel> contentModels = new ArrayList<>();
    private ArrayList<String> mData = new ArrayList<>();
    String myResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        RecyclerView mRecyclerView = findViewById(R.id.card_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        CardAdapter cardAdapter = new CardAdapter(contentModels);
        mRecyclerView.setAdapter(cardAdapter);


    }

    public void getData() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://smuat.megatime.com.tw/EAS/Apps/systex/hr_elearning/hr_elearning_20220602_181350.json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure( Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse( Call call,Response response) throws IOException {
                if (response.isSuccessful()) {

                    myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject reader = new JSONObject(myResponse);
                                JSONArray DataList = reader.getJSONArray("DataList");
                                //Log.d("Check", DataList.toString());

                                for (int i = 0; i < DataList.length(); i++) {
                                    JSONObject datas = DataList.getJSONObject(i);
                                    String title = datas.getString("title");
                                    String content = datas.getString("content");
                                    String msgTime = datas.getString("msgTime");
                                    String img = datas.getString("img");
                                    contentModels.add(new ContentModel(title,content,msgTime,img));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

}