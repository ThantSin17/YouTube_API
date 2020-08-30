package com.stone.youtube_api.model;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.stone.youtube_api.delegate.LoadPlayLists;
import com.stone.youtube_api.dto.PlayLists;
import com.stone.youtube_api.dto.V_Data;
import com.stone.youtube_api.utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayListLoader extends AsyncTask<String,Void,String> {
    ArrayList<PlayLists> playLists;
    Context context;
    LoadPlayLists loadPlayLists;

    public PlayListLoader(Context context, LoadPlayLists loadPlayLists) {
        this.context = context;
        this.loadPlayLists = loadPlayLists;
        playLists=new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... strings) {
        return AppUtils.fetch(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        processJson(s);
    }

    private void processJson(String s) {
        try {
            JSONObject object = new JSONObject(s);

            JSONArray arr = object.getJSONArray("items");

            for (int i = 0; i < arr.length(); i++) {
                PlayLists playList=new PlayLists();
                JSONObject items = arr.getJSONObject(i);
                JSONObject snippet = items.getJSONObject("snippet");
                playList.setNextPageToken(object.getString("nextPageToken"));
                playList.setListId(items.getString("id"));
                playList.setListTitle(snippet.getString("title"));
                playList.setListDescription(snippet.getString("description"));
                playList.setListImgUrl(snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url"));
                playList.setListItemCount(items.getJSONObject("contentDetails").getInt("itemCount"));


                playLists.add(playList);


            }
            loadPlayLists.LoadSuccess("success");
            loadPlayLists.LoadResult(playLists);

        } catch (JSONException e) {
            loadPlayLists.LoadFail(e.toString());
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}
