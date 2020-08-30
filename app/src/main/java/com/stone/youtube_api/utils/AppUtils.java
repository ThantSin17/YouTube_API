package com.stone.youtube_api.utils;

import com.stone.youtube_api.dto.V_Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class AppUtils {
    public static final String baseUrl="https://www.googleapis.com/youtube/v3/search?key=";
    public static final String channelTitle="&channelId=";
    public static final String urlData="&part=snippet,id&order=date&maxResults=50";
    public static final String playlistItemUrl="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&key=AIzaSyDLBthvINcog5Ka0d9lLkK6URNZfzWWtIw&maxResults=10&playlistId=";
    public static final String playlistUrl="https://www.googleapis.com/youtube/v3/playlists?part=snippet,contentDetails&channelId=UCJFGNmtLtwmX752P7OhcdvQ&maxResults=30&key=AIzaSyDLBthvINcog5Ka0d9lLkK6URNZfzWWtIw";
    public static final String testUrl="https://www.googleapis.com/youtube/v3/search?key=AIzaSyDLBthvINcog5Ka0d9lLkK6URNZfzWWtIw&channelId=UCJFGNmtLtwmX752P7OhcdvQ&part=snippet,id&order=date&maxResults=30";
    public static String fetch(String s){
        StringBuffer result=new StringBuffer();
        try {
            URL url=new URL(testUrl);
            HttpURLConnection conn= (HttpURLConnection) new URL(s).openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String data="";
            while ((data=br.readLine())!=null){
                    result.append(data).append("\n");

            }
            br.close();
            conn.disconnect();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

}
