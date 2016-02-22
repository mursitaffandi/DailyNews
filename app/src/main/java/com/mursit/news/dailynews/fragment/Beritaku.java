package com.mursit.news.dailynews.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.mursit.news.dailynews.DefaultHttpClient;
import com.mursit.news.dailynews.JSONParser;
import com.mursit.news.dailynews.R;
import com.mursit.news.dailynews.WebActivity;
import com.mursit.news.dailynews.helper.LazyAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ima2 on 2/15/16.
 */
public class Beritaku extends Fragment{
    ListView lvBeritaku;
    JSONParser jsonParser = new JSONParser();
    JSONArray jsonArray = null;
    LazyAdapter adapter;
    DefaultHttpClient httpClient = new DefaultHttpClient();
    ArrayList<HashMap<String, String>> DaftarBeritaku = new ArrayList<>();
    private ProgressDialog progressDialog;
    public static final String TAG_ID = "id";
    public static final String TAG_JUDUL = "title";
    public static final String TAG_LINK = "link";
    public static final String TAG_SUMBER = "source";
    public static final String TAG_DATE = "pubDate";
    public static final String TAG_KATEGORI = "category";
    public static final String TAG_GAMBAR = "image";

    public Beritaku () {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_beritaku, container, false);
        lvBeritaku = (ListView) v.findViewById(R.id.webViewBerita);
        DaftarBeritaku = new ArrayList<HashMap<String, String>>();
        new TampilBeritaku().execute();
        lvBeritaku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = DaftarBeritaku.get(position);
                Intent b = new Intent(getActivity().getApplicationContext(), WebActivity.class);
                b.putExtra(TAG_ID, map.get(TAG_ID));
                startActivity(b);
            }
        });
        return v;
    }

    private class TampilBeritaku extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> param = new ArrayList<>();
            JSONObject jsonObject = jsonParser.makeHttpRequest(DefaultHttpClient.BASE_URL, "GET", param);
            Log.i("JSON : ", " " + jsonObject);
            try {
                jsonArray = jsonObject.getJSONArray("berita");
                for (int i = 0; 1 < jsonArray.length();i++){
                    JSONObject c = jsonArray.getJSONObject(i);
                    String id = c.getString(TAG_ID);
                    String source = c.getString(TAG_SUMBER);
                    String title = c.getString(TAG_JUDUL);
                    String link = c.getString(TAG_LINK);
                    String pubDate = c.getString(TAG_DATE);
                    String category = c.getString(TAG_KATEGORI);
                    String image = c.getString(TAG_GAMBAR);
                    HashMap<String, String> map = new HashMap<>();
                    map.put(TAG_ID, id);
                    map.put(TAG_SUMBER, source);
                    map.put(TAG_JUDUL, title);
                    map.put(TAG_LINK, link);
                    map.put(TAG_DATE, pubDate);
                    map.put(TAG_KATEGORI, category);
                    map.put(TAG_GAMBAR, image);
                    DaftarBeritaku.add(map);
                }
            } catch (JSONException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            run(new Runnable() {
                @Override
                public void run() {
                    SetListAdapter(DaftarBeritaku);
                }
            });
        }
    }

    private void SetListAdapter(ArrayList<HashMap<String, String>> daftarBeritaku) {
        adapter = new LazyAdapter(getActivity(), daftarBeritaku);
        lvBeritaku.setAdapter(adapter);
    }
}

