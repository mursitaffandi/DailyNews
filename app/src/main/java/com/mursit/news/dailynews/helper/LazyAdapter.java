package com.mursit.news.dailynews.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mursit.news.dailynews.R;
import com.mursit.news.dailynews.fragment.Beritaku;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ima2 on 2/22/16.
 */
public class LazyAdapter extends BaseAdapter
{
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.list_row, null);
        }
        String id;
        String link;
        TextView head = (TextView) vi.findViewById(R.id.tvItemJudul);
        TextView source = (TextView) vi.findViewById(R.id.tvItemSumber);
        TextView date = (TextView) vi.findViewById(R.id.tvItemDate);
        TextView category = (TextView) vi.findViewById(R.id.tvItemKategori);
        ImageView thumnail = (ImageView) vi.findViewById(R.id.ivItemPict);

        HashMap<String, String> list_rss = new HashMap<String, String>();
        list_rss = data.get(position);
        id = list_rss.get(Beritaku.TAG_ID);
        head.setText(list_rss.get(Beritaku.TAG_JUDUL));
        link = list_rss.get(Beritaku.TAG_LINK);
        source.setText(list_rss.get(Beritaku.TAG_SUMBER));
        date.setText(list_rss.get(Beritaku.TAG_DATE));
        category.setText(list_rss.get(Beritaku.TAG_KATEGORI));
        imageLoader.DisplayImage(list_rss.get(Beritaku.TAG_GAMBAR), thumnail);
        return vi;
    }
}
