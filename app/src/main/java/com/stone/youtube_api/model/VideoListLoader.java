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

public class VideoListLoader extends AsyncTask<String, Void, String> {
    ArrayList<V_Data> vDataList;
    Context context;
    GetAllVideo getAllVideo;

    public VideoListLoader(Context context, GetAllVideo getAll) {
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
                JSONObject id=items.getJSONObject("id");
                if (id.has("videoId")){
                    String vid=id.getString("videoId");
                    v_data.setVideoId(vid);
                }

                JSONObject snippet = items.getJSONObject("snippet");
                v_data.setTitle(snippet.getString("title"));
                v_data.setDescription(snippet.getString("description"));
                v_data.setPublishTime(snippet.getString("publishTime"));
                String img=snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                v_data.setImg(img);
                v_data.setNextPageToken(object.getString("nextPageToken"));
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

    public ArrayList<V_Data> getData() {
        return vDataList;
    }
}
