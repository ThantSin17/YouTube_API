package com.stone.youtube_api.delegate;

import com.stone.youtube_api.dto.PlayLists;
import com.stone.youtube_api.dto.V_Data;

import java.util.ArrayList;

public interface LoadPlayLists {
    void LoadSuccess(String message);
    void LoadResult(ArrayList<PlayLists> videoList);
    void LoadFail(String message);
}
