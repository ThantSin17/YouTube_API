package com.stone.youtube_api.delegate;

import com.stone.youtube_api.dto.V_Data;

import java.util.ArrayList;

public interface GetAllVideo {
    void Success(String message);
    void SuccessResult(ArrayList<V_Data> videoList);
    void Fail(String message);
}
