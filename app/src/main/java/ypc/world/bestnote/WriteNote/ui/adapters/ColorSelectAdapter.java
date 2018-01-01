package ypc.world.bestnote.WriteNote.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ypc.world.bestnote.R;
import ypc.world.bestnote.WriteNote.events.ColorModifyEvent;

/**
 * Created by Administrator on 2018/1/1 0001.
 */

public class ColorSelectAdapter extends RecyclerView.Adapter<ColorSelectAdapter.ColorViewHolder>{


    private Context mContext;
    private ArrayList<Integer> colorIds;
    public class ColorViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ColorViewHolder(View v) {
            super(v);
            imageView = (ImageView)v;
            ButterKnife.bind(this, v);
        }
    }


    public ColorSelectAdapter(ArrayList<Integer> colors)
    {
        this.colorIds = colors;
    }


    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        final int color = ContextCompat.getColor(mContext, colorIds.get(position));
        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.color_circle).mutate();
        DrawableCompat.setTint(drawable, color);
        holder.imageView.setImageDrawable(drawable);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ColorModifyEvent(color));
            }
        });
    }


    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        return new ColorViewHolder(LayoutInflater.from(mContext).inflate(R.layout.select_color_squarelayout, parent, false));
    }


    @Override
    public int getItemCount() {
        return colorIds.size();
    }
}
