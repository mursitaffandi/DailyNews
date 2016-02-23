package com.mursit.news.dailynews.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.mursit.news.dailynews.DefaultHttpClient;
import com.mursit.news.dailynews.JSONParser;
import com.mursit.news.dailynews.R;
import com.mursit.news.dailynews.WebActivity;
import com.mursit.news.dailynews.fragment.models.ItemBerita;
import com.mursit.news.dailynews.helper.LazyAdapter;
import com.mursit.news.dailynews.helper.RbHelpers;
import com.mursit.news.dailynews.helper.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ima2 on 2/15/16.
 */
public class Beritaku extends Fragment{
    ListView lvBeritaku;
    JSONParser jsonParser = new JSONParser();
    JSONArray jsonArray = null;
    LazyAdapter adapter;
    AQuery aq;
    ArrayList<ItemBerita> DaftarBeritaku;
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
        return inflater.inflate(R.layout.fragment_beritaku, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvBeritaku = (ListView) view.findViewById(R.id.webViewBerita);
        lvBeritaku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ItemBerita b = DaftarBeritaku.get(position);
                Intent in = new Intent(getActivity(), WebActivity.class);
                in.putExtra(Utils.LINK, b.getLinkBerita());
                in.putExtra(Utils.ID, b.getIdBerita());
                in.putExtra(Utils.KATEGORI, b.getIdBerita());
                in.putExtra(Utils.DATE, b.getIdBerita());
                startActivity(in);
            }
        });

        aq = new AQuery(getActivity());
        DaftarBeritaku = new ArrayList<>();
        if (!RbHelpers.isOnline(getActivity())) {
            RbHelpers.alertMessageNoInternet(getActivity());
        } else {
            getData();
        }
    }

    private void getData() {
        DaftarBeritaku.clear();
        String url = RbHelpers.HOME;

        try{
            RbHelpers.pre("URL : " + url);
            aq.ajax(url, String.class, new AjaxCallback<String>(){
                @Override
                public void callback(String url, String object, AjaxStatus status) {
                    if (object != null) {
                        RbHelpers.pre("Respons : " + object);
                        try {
                            JSONObject json = new JSONObject(object);
                            JSONArray berita = json.getJSONArray("berita");
                            RbHelpers.pesan(getActivity(), "Welcome");
                            if (berita.length() > 0){
                                JSONArray jArray = berita;
                                for (int i = 0 ; i < jArray.length() ; i++) {
                                    JSONObject jObj = jArray.getJSONObject(i);
                                    ItemBerita b = new ItemBerita();
                                    b.setIdBerita(jObj.getString("id"));
                                    b.setSourceBerita(jObj.getString("source"));
                                    b.setCategoryBerita(jObj.getString("category"));
                                    b.setTitleBerita(jObj.getString("title"));
                                    b.setLinkBerita(jObj.getString("link"));
                                    b.setPubDateBerita(jObj.getString("pubDate"));
                                    b.setImageBerita(jObj.getString("image"));

                                    DaftarBeritaku.add(b);
                                    RbHelpers.pre("Sedang masukan data");
                                }
                                RbHelpers.pre("sampai di adapter , tidak ada data");
                                adapter = new LazyAdapter(getActivity(), DaftarBeritaku);
                                lvBeritaku.setAdapter(adapter);
                            } else {
                                RbHelpers.pesan(getActivity(), "Data tidak ada");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            RbHelpers.pesan(getActivity(), "Error JSOn Data");
                        } catch (Exception e){
                            e.printStackTrace();
                            RbHelpers.pesan(getActivity(), "Error Parsing Data");
                        };
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

