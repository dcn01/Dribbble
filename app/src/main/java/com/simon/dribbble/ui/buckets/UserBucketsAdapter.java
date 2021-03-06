package com.simon.dribbble.ui.buckets;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.simon.agiledevelop.recycler.RecycledViewHolder;
import com.simon.agiledevelop.recycler.adapter.RecycledAdapter;
import com.simon.agiledevelop.utils.ImgLoadHelper;
import com.simon.dribbble.R;
import com.simon.dribbble.data.model.BucketEntity;
import com.simon.dribbble.data.model.User;
import com.simon.dribbble.util.ColorPhrase;
import com.simon.dribbble.util.DateTimeUtil;
import com.simon.dribbble.util.StringUtil;

/**
 * Created by: Simon
 * Email: simon.han0220@gmail.com
 * Created on: 2016/9/2 15:57
 */

public class UserBucketsAdapter extends RecycledAdapter<BucketEntity,RecycledViewHolder> {

    private RecyclerView.RecycledViewPool mPool = new RecyclerView.RecycledViewPool();

    public UserBucketsAdapter() {
        super(R.layout.item_user_buckets);
    }

    @Override
    protected void convert(RecycledViewHolder helper, BucketEntity item) {
        if (null != item) {
            User user = item.getUser();
            if (null != user) {
                helper.setVisible(R.id.imv_avatar, true);
                ImgLoadHelper.loadAvatar(user.avatar_url, (ImageView) helper.getView(R.id
                        .imv_avatar));
            } else {
                helper.setVisible(R.id.imv_avatar, false);
            }


            String name = item.getName();
            String created_at = item.getCreated_at();
            String description = item.getDescription();
            int shots_count = item.getShots_count();
            String s = DateTimeUtil.formatUTC(created_at);
            String time = DateTimeUtil.friendly_time(s);


            helper.setText(R.id.tv_buckets_title, name);
            helper.setText(R.id.tv_buckets_create_time, time);

            CharSequence format = ColorPhrase.from(shots_count + "  <作品>")
                    .withSeparator("<>")
                    .innerColor(0xFF808080)
                    .outerColor(0xFF333333)
                    .format();

            helper.setText(R.id.tv_shots_count, format);

            if (StringUtil.isEmpty(description)) {
                helper.setVisible(R.id.tv_buckets_desc, false);
            } else {
                helper.setVisible(R.id.tv_buckets_desc, true);
                helper.setText(R.id.tv_buckets_desc, description);
            }
        }
    }

    public RecyclerView.RecycledViewPool getPool() {
        return mPool;
    }
}
