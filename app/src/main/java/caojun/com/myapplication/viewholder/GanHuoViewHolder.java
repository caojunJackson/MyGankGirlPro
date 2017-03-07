package caojun.com.myapplication.viewholder;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import caojun.com.myapplication.R;
import caojun.com.myapplication.model.GanHuo;
import caojun.com.myapplication.util.TimeUtil;

import static caojun.com.myapplication.gobal.App.names;

/**
 * Created by tiger on 2017/3/6.
 */

public class GanHuoViewHolder extends BaseViewHolder<GanHuo.ResultsBean> {
    TextView desc ;
    TextView type ;
    TextView author ;
    TextView time ;


    public GanHuoViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_ganhuo);
        desc = $(R.id.item_title);
        type = $(R.id.item_type);
        author = $(R.id.item_author);
        time = $(R.id.item_time);
    }

    @Override
    public void setData(GanHuo.ResultsBean data) {
        desc.setText(data.getDesc());
        type.setText(data.getType());
        if(names[1].equals(data.getType())){
            setDrawableLeft(R.drawable.ic_android_black_24dp);
        }else if(names[2].equals(data.getType())){
            setDrawableLeft(R.drawable.ic_whatshot_black_24dp);
        }else if(names[3].equals(data.getType())){
            setDrawableLeft(R.drawable.ic_play_circle_filled_black_24dp);

        }


        author.setText(data.getWho());
        time.setText(TimeUtil.getFormatTime(data.getPublishedAt()));

    }

    private void setDrawableLeft(int res){
        Drawable drawable = getContext().getResources().getDrawable(res);
        drawable.setBounds(0,0,drawable.getMinimumWidth() , drawable.getMinimumHeight());
        type.setCompoundDrawables(drawable , null ,null , null);

    }
}
