package caojun.com.myapplication.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import caojun.com.myapplication.R;
import caojun.com.myapplication.model.GanHuo;

/**
 * Created by tiger on 2017/3/6.
 */

public class GanHupViewHolder extends BaseViewHolder<GanHuo.ResultsBean> {
    TextView desc ;
    TextView type ;
    TextView author ;
    TextView time ;


    public GanHupViewHolder(ViewGroup parent) {
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


    }
}
