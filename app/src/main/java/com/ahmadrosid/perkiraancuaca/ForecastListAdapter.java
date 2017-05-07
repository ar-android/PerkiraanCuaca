package com.ahmadrosid.perkiraancuaca;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ocittwo on 5/1/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class ForecastListAdapter extends BaseAdapter {

    private List<String> data;

    public void updateData(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    private Context context;

    public ForecastListAdapter(Context context) {
        this.context = context;
    }

    @Override public int getCount() {
        return data.size();
    }

    @Override public Object getItem(int i) {
        return data.get(i);
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int position, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(context);
        textView.setText(data.get(position));
        return textView;
    }
}
