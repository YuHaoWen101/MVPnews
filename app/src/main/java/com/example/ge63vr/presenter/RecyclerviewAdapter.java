package com.example.ge63vr.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ge63vr.R;
import com.example.ge63vr.model.Data;
import com.example.ge63vr.view.StoryDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Data.StoriesBean> mStoriesBean;
    private ArrayList<Data.TopStoriesBean> mTopStoriesBean;
    private Data mData;
    private TopViewHolder topViewHolder;
    private TextViewHolder textViewHolder;
    private DateTextViewHolder dateTextViewHolder;
    private Context context;
    private int currentItem = 0;
    private List<View> views;
    private Handler handler = new Handler();
    private ArrayList<Data.StoriesBean> more;
    private ArrayList<Integer> positions = new ArrayList<>();
    private final int TOP_ITEM=0;
    private final int DATE_ITEM=1;
    private final int NORMAL_ITEM =2;
    private int datemode = 0;
    private Map<Integer,String> dateMap = new HashMap<>();

    public void addMoreStories(ArrayList<Data.StoriesBean> beforeStories,String datebefore) {
        more = beforeStories;
        positions.add(mStoriesBean.size() + 1);
        dateMap.put(mStoriesBean.size()+1,datebefore);
        mStoriesBean.addAll(beforeStories);
        notifyDataSetChanged();
    }

    public RecyclerviewAdapter(Context context,@NonNull Data data) {
        super();
        dateMap.put(1,"今日新闻");

        positions.add(1);
        this.context = context;
        mData = data;
        mStoriesBean = (ArrayList<Data.StoriesBean>) mData.getStories();
        mTopStoriesBean = (ArrayList<Data.TopStoriesBean>) mData.getTop_stories();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TOP_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.top,viewGroup,false);
                viewHolder = new TopViewHolder(view);
                break;
            case NORMAL_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.fragment_base_swipe_list, viewGroup, false);
                viewHolder = new TextViewHolder(view);
                break;
            case DATE_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.recycler_item_withdate, viewGroup, false);
                viewHolder = new DateTextViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof TextViewHolder) {
            Data.StoriesBean storiesBean = mStoriesBean.get(viewHolder.getAdapterPosition() - 1);
            textViewHolder = (TextViewHolder) viewHolder;
            textViewHolder.textView.setText(storiesBean.getTitle());
            textViewHolder.itemView.setOnClickListener(v ->{
                Intent intent = new Intent(context,StoryDetailActivity.class);
                intent.putExtra("id",mStoriesBean.get(position-1).getId());
                context.startActivity(intent);});
            Glide.with(context).load(storiesBean.getImages().get(0)).into(textViewHolder.imageView);
        }

        if (viewHolder instanceof TopViewHolder) {
            Data.TopStoriesBean topStoriesBean = mTopStoriesBean.get(viewHolder.getAdapterPosition());
            topViewHolder = (TopViewHolder) viewHolder;
            topViewHolder.textview_Viewpager.setText(topStoriesBean.getTitle());
            viewpagerecommendAdapter adapter = new viewpagerecommendAdapter(context);
            topViewHolder.myViewPager.setAdapter(adapter);
            topViewHolder.myViewPager.setOnPageChangeListener(new viewpagerRecommendPageChangeListener());
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(new DotRun(), 3, 6,
                    TimeUnit.SECONDS);
        }
        if (viewHolder instanceof DateTextViewHolder) {
            Data.StoriesBean storiesBean = mStoriesBean.get(viewHolder.getAdapterPosition()-1);
            dateTextViewHolder = (DateTextViewHolder) viewHolder;
            if (more != null) {
                dateTextViewHolder.dateView.setText(turnSringToDate(Objects.requireNonNull(dateMap.get(viewHolder.getAdapterPosition()))));
            }
            dateTextViewHolder.itemView.setOnClickListener(v ->{
                Intent intent = new Intent(context,StoryDetailActivity.class);
                intent.putExtra("id",mStoriesBean.get(position-1).getId());
                context.startActivity(intent);
            });
            dateTextViewHolder.textView.setText(storiesBean.getTitle());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(dateTextViewHolder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return mStoriesBean.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP_ITEM;//MagicNumber
        }
        if (positions.contains(position)) {
            return DATE_ITEM;
        } else {
            return NORMAL_ITEM;
        }
    }


    class TopViewHolder extends RecyclerView.ViewHolder {
        private final MyViewPager myViewPager;
        private TextView textview_Viewpager;
        private View textview_tuijian_tou1;
        private View textview_tuijian_tou2;
        private View textview_tuijian_tou3;
        private View textview_tuijian_tou4;
        private View textview_tuijian_tou5;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            myViewPager = itemView.findViewById(R.id.myViewPage_tuijian_tou);
            textview_Viewpager = itemView.findViewById(R.id.textview_Viewpager);
            textview_tuijian_tou1 = itemView.findViewById(R.id.textview_tuijian_tou1);
            textview_tuijian_tou2 = itemView.findViewById(R.id.textview_tuijian_tou2);
            textview_tuijian_tou3 = itemView.findViewById(R.id.textview_tuijian_tou3);
            textview_tuijian_tou4 = itemView.findViewById(R.id.textview_tuijian_tou4);
            textview_tuijian_tou5 = itemView.findViewById(R.id.textview_tuijian_tou5);
            //初始化原点
            views = new ArrayList<>();
            views.add(textview_tuijian_tou1);
            views.add(textview_tuijian_tou2);
            views.add(textview_tuijian_tou3);
            views.add(textview_tuijian_tou4);
            views.add(textview_tuijian_tou5);
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final ImageView imageView;

        TextViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.base_swipe_item_icon);
            textView = itemView.findViewById(R.id.base_swipe_item_title);
        }
    }

    class DateTextViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final ImageView imageView;
        final TextView dateView;

        DateTextViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.base_swipe_group_item_date);
            imageView = itemView.findViewById(R.id.base_swipe_item_icon);
            textView = itemView.findViewById(R.id.base_swipe_item_title);
        }
    }

    private class viewpagerecommendAdapter extends PagerAdapter {

        private Context mContext;

        public viewpagerecommendAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mTopStoriesBean.size();
        }

        @Override
        public Object instantiateItem(@NonNull View container, int position) {
            ImageView imageview = new ImageView(context);
            Glide.with(mContext).load(mTopStoriesBean.get(position).getImage()).centerCrop().into(imageview);
            ((ViewPager) container).addView(imageview);
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //头条点击
                    //Toast.makeText(context, "点击了第" + position + "项,ID是" + mTopStoriesBean.get(position).getId(), Toast.LENGTH_LONG).show();
                    //嗯ID在这里是没有错了
                    Intent intent = new Intent(mContext,StoryDetailActivity.class);
                    intent.putExtra("id",mTopStoriesBean.get(position).getId());
                    mContext.startActivity(intent);
                }
            });
            return imageview;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    //viewpagger的PageChangeListener
    private class viewpagerRecommendPageChangeListener implements ViewPager.OnPageChangeListener {

        private int elderposition = 0;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            topViewHolder.textview_Viewpager.setText(mTopStoriesBean.get(position).getTitle());
            views.get(elderposition).setBackgroundResource(R.drawable.dot_normal);
            views.get(position).setBackgroundResource(R.drawable.dot_focused);
            elderposition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class DotRun implements Runnable {
        @Override
        public void run() {
            synchronized (topViewHolder.myViewPager) {
                currentItem = (currentItem + 1) % 5;
                // handler切换图片
                handler.obtainMessage().sendToTarget();
            }
        }
    }
    private String turnSringToDate(String date){
        if (date.length()>4) {
            return date.substring(0, 4) + "年" + date.substring(4, 6) + "月" + date.substring(6) + "日";
        }else {return date;}
    }
}
