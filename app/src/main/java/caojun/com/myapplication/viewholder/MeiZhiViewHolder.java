package caojun.com.myapplication.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import caojun.com.myapplication.R;
import caojun.com.myapplication.model.GanHuo;

/**
 * Created by tiger on 2017/3/6.
 */
public class MeiZhiViewHolder extends BaseViewHolder<GanHuo.ResultsBean> {

    private ImageView mImage;

    public MeiZhiViewHolder(ViewGroup parent) {
        super(parent, R.layout.img_meizhi);
        mImage = $(R.id.iv_meizhi);
    }

    @Override
    public void setData(GanHuo.ResultsBean data) {
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImage);
    }
}
