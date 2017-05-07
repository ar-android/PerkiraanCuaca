package com.ahmadrosid.perkiraancuaca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.perkiraancuaca.data.ModelWeather;
import com.ahmadrosid.perkiraancuaca.utils.Utility;

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

    private List<ModelWeather> data;

    public void updateData(List<ModelWeather> data) {
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
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_list_forecast, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.iconView.setImageResource(
                Utility.getIconResourceForWeatherCondition(
                        data.get(position).getWeatherId()));

        long dateInMillis = data.get(position).getDateTime();
        viewHolder.dateView.setText(Utility.getFriendlyDayString(context, dateInMillis));
        viewHolder.descriptionView.setText(data.get(position).getDescription());

        return view;
    }

    public static class ViewHolder {
        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        public final TextView highTempView;
        public final TextView lowTempView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }
    }

}
