package com.mursit.news.dailynews.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mursit.news.dailynews.R;
import com.mursit.news.dailynews.fragment.models.ItemBerita;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ima2 on 2/22/16.
 */
public class LazyAdapter extends BaseAdapter
{
    private Context c;
    private ArrayList<ItemBerita> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public LazyAdapter(Context c, ArrayList<ItemBerita> dataBerita) {
        this.c = c;
        data = new ArrayList<>();
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(c.getApplicationContext());
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

    private class ViewHolder {
        ImageView thumnail;
        TextView id, head, source, date, category;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View vi = view;
        ViewHolder holder = null;
        if (view == null) {
            inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.id = (TextView) vi.findViewById(R.id.tvItemID);
            holder.head = (TextView) vi.findViewById(R.id.tvItemJudul);
            holder.source = (TextView) vi.findViewById(R.id.tvItemSumber);
            holder.date = (TextView) vi.findViewById(R.id.tvItemDate);
            holder.category = (TextView) vi.findViewById(R.id.tvItemKategori);
            holder.thumnail = (ImageView) vi.findViewById(R.id.ivItemPict);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        ItemBerita b = data.get(position);
        return vi;
    }
}
