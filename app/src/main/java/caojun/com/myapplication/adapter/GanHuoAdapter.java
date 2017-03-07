package caojun.com.myapplication.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import caojun.com.myapplication.model.GanHuo;
import caojun.com.myapplication.viewholder.GanHuoViewHolder;

/**
 * Created by tiger on 2017/3/6.
 */

public class GanHuoAdapter extends RecyclerArrayAdapter<GanHuo.ResultsBean> {
    public GanHuoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GanHuoViewHolder(parent);
    }


}
