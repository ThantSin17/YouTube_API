package com.stone.youtube_api.model;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.stone.youtube_api.delegate.GetAllVideo;
import com.stone.youtube_api.dto.V_Data;
import com.stone.youtube_api.utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayListItemLoader extends AsyncTask<String, Void, String> {
    ArrayList<V_Data> vDataList;
    Context context;
    GetAllVideo getAllVideo;

    public PlayListItemLoader(Context context, GetAllVideo getAll) {
        this.context = context;
        vDataList = new ArrayList<>();
        getAllVideo = getAll;

    }

    protected String doInBackground(String... strings) {
        return AppUtils.fetch(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        processJSON(s);
    }

    private void processJSON(String s) {

        try {
            JSONObject object = new JSONObject(s);

            JSONArray arr = object.getJSONArray("items");

            for (int i = 0; i < arr.length(); i++) {
                V_Data v_data = new V_Data();
                JSONObject items = arr.getJSONObject(i);
                JSONObject snippet = items.getJSONObject("snippet");
                JSONObject id = snippet.getJSONObject("resourceId");
                if (id.has("videoId")) {
                    String vid = id.getString("videoId");
                    v_data.setVideoId(vid);
                }


                v_data.setTitle(snippet.getString("title"));
                v_data.setDescription(snippet.getString("description"));
                v_data.setPublishTime(snippet.getString("publishedAt"));
                if (snippet.getJSONObject("thumbnails").has("medium")) {
                    String img = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                    v_data.setImg(img);

                }else {

                    v_data.setImg("");
                    continue;
                }

                if (object.has("nextPageToken")) {
                    v_data.setNextPageToken(object.getString("nextPageToken"));
                }else {
                    v_data.setNextPageToken("noData");
                }
                vDataList.add(v_data);
            }
            getAllVideo.Success("success");
            getAllVideo.SuccessResult(vDataList);

        } catch (JSONException e) {
            getAllVideo.Fail(e.toString());
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
