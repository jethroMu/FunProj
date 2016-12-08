package com.lin.mu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.lin.mu.R;
import com.lin.mu.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/8/2.
 */
public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<PhotoModel.PictureBody> mList;
    private OnItemActionListener mOnItemActionListener;

    public PhotoAdapter(Context ctx) {
        mContext = ctx;
        mList = new ArrayList<>();
    }

    public void setDatas(List<PhotoModel.PictureBody> datas) {
        if (datas != null && datas.size() > 0) {
            this.mList.clear();
            this.mList.addAll(datas);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_photo, parent, false);
            return new ViewHolderPhoto(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.customer_item_foot, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolderPhoto) {
            final ViewHolderPhoto holder = (ViewHolderPhoto) viewHolder;
            onBindViewHolderPhoto(holder, position);
        } else if (viewHolder instanceof FootViewHolder) {
            onBindFootViewHolder(viewHolder, position);
        }
    }

    private void onBindViewHolderPhoto(final ViewHolderPhoto holderPhoto, final int position) {
        PhotoModel.PictureBody pictureBody = mList.get(position);
        holderPhoto.iv_picture.setImageResource(R.mipmap.ic_navigation);
        if (!TextUtils.isEmpty(pictureBody.list.get(0).middle)) {
            Glide.with(mContext).
                    load(pictureBody.list.get(0).middle).
                    centerCrop().
                    dontAnimate().
                    placeholder(R.mipmap.ic_loading).
                    error(R.mipmap.ic_loading).
                    into(holderPhoto.iv_picture);
        }
        holderPhoto.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemActionListener != null) {
                    mOnItemActionListener.onItemClickListener(holderPhoto.itemView, position);
                }
            }
        });
    }

    private void onBindFootViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final FootViewHolder holder = (FootViewHolder) viewHolder;
        if (canLoadMore) {
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.tvFoot.setText("正在加载");
        } else {
            holder.progressBar.setVisibility(View.GONE);
            holder.tvFoot.setText("没有更多数据");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 0 : mList.size() - 1;
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView tvFoot;

        public FootViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            tvFoot = (TextView) view.findViewById(R.id.tv_foot);
        }
    }


    private class ViewHolderPhoto extends RecyclerView.ViewHolder {
        public ImageView iv_picture;

        public ViewHolderPhoto(final View itemView) {
            super(itemView);
            iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
        }
    }


    /**********
     * 定义点击事件
     **********/
    public interface OnItemActionListener {
        public void onItemClickListener(View v, int pos);

        public boolean onItemLongClickListener(View v, int pos);
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.mOnItemActionListener = onItemActionListener;
    }


    private boolean canLoadMore;

    public void canLoad(boolean b) {
        canLoadMore = b;
    }

}
