package com.example.ge63vr.presenter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.ge63vr.R
import com.example.ge63vr.model.Data
import com.example.ge63vr.model.Story
import com.example.ge63vr.model.TopStory
import com.example.ge63vr.view.StoryDetailActivity
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class RecyclerviewAdapter(private val context: Context, private val mData: Data) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mStoriesBean: ArrayList<Story>
    private val mTopStoriesBean: ArrayList<TopStory>
    private lateinit var topViewHolder: TopViewHolder
    private lateinit var textViewHolder: TextViewHolder
    private lateinit var dateTextViewHolder: DateTextViewHolder
    private var currentItem = 0
    private lateinit var views: MutableList<View>
    private val handler = Handler()
    private lateinit var more: ArrayList<Story>
    private val positions = ArrayList<Int>()
    private val TOP_ITEM = 0
    private val DATE_ITEM = 1
    private val NORMAL_ITEM = 2
    private val dateMap = HashMap<Int, String?>()

    fun addMoreStories(beforeStories: ArrayList<Story>, datebefore: String?) {
        more = beforeStories
        positions.add(mStoriesBean.size + 1)
        dateMap[mStoriesBean.size + 1] = datebefore
        mStoriesBean.addAll(beforeStories)
        notifyDataSetChanged()
    }

    init {
        dateMap[1] = "今日新闻"
        positions.add(1)
        mStoriesBean = mData.stories as ArrayList<Story>
        mTopStoriesBean = mData.top_stories as ArrayList<TopStory>
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            TOP_ITEM -> {
                view = LayoutInflater.from(context).inflate(R.layout.top, viewGroup, false)
                viewHolder = TopViewHolder(view)
            }
            NORMAL_ITEM -> {
                view = LayoutInflater.from(context).inflate(R.layout.fragment_base_swipe_list, viewGroup, false)
                viewHolder = TextViewHolder(view)
            }
            DATE_ITEM -> {
                view = LayoutInflater.from(context).inflate(R.layout.recycler_item_withdate, viewGroup, false)
                viewHolder = DateTextViewHolder(view)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is TextViewHolder) {
            val storiesBean = mStoriesBean[viewHolder.getAdapterPosition() - 1]
            textViewHolder = viewHolder
            textViewHolder.textView.text = storiesBean.title
            textViewHolder.itemView.setOnClickListener {
                val intent = Intent(context, StoryDetailActivity::class.java)
                intent.putExtra("id", mStoriesBean[position - 1].id)
                context.startActivity(intent)
            }
            Glide.with(context).load(storiesBean.images.get(0)).into(textViewHolder.imageView)
        }

        if (viewHolder is TopViewHolder) {
            topViewHolder = viewHolder
            val topStoriesBean = mTopStoriesBean[viewHolder.getAdapterPosition()]
            topViewHolder.textview_Viewpager.text = topStoriesBean.title
            val adapter = viewpagerecommendAdapter(context)
            topViewHolder.myViewPager.adapter = adapter
            topViewHolder.myViewPager.setOnPageChangeListener(viewpagerRecommendPageChangeListener())
            val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
            scheduledExecutorService.scheduleAtFixedRate(DotRun(), 3, 6,
                    TimeUnit.SECONDS)
        }
        if (viewHolder is DateTextViewHolder) {
            val storiesBean = mStoriesBean[viewHolder.getAdapterPosition() - 1]
            dateTextViewHolder = viewHolder
                dateTextViewHolder.dateView.text = turnSringToDate(Objects.requireNonNull<String>(dateMap[viewHolder.getAdapterPosition()]))

            dateTextViewHolder.itemView.setOnClickListener { v ->
                val intent = Intent(context, StoryDetailActivity::class.java)
                intent.putExtra("id", mStoriesBean[position - 1].id)
                context.startActivity(intent)
            }
            dateTextViewHolder.textView.text = storiesBean.title
            Glide.with(context).load(storiesBean.images[0]).into(dateTextViewHolder.imageView)
        }
    }


    override fun getItemCount(): Int {
        return mStoriesBean.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TOP_ITEM//MagicNumber
        }
        return if (positions.contains(position)) {
            DATE_ITEM
        } else {
            NORMAL_ITEM
        }
    }


    internal inner class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myViewPager: MyViewPager
        val textview_Viewpager: TextView
        val textview_tuijian_tou1: View
        val textview_tuijian_tou2: View
        val textview_tuijian_tou3: View
        val textview_tuijian_tou4: View
        val textview_tuijian_tou5: View

        init {
            myViewPager = itemView.findViewById(R.id.myViewPage_tuijian_tou)
            textview_Viewpager = itemView.findViewById(R.id.textview_Viewpager)
            textview_tuijian_tou1 = itemView.findViewById(R.id.textview_tuijian_tou1)
            textview_tuijian_tou2 = itemView.findViewById(R.id.textview_tuijian_tou2)
            textview_tuijian_tou3 = itemView.findViewById(R.id.textview_tuijian_tou3)
            textview_tuijian_tou4 = itemView.findViewById(R.id.textview_tuijian_tou4)
            textview_tuijian_tou5 = itemView.findViewById(R.id.textview_tuijian_tou5)
            //初始化原点
            views = ArrayList()
            views.add(textview_tuijian_tou1)
            views.add(textview_tuijian_tou2)
            views.add(textview_tuijian_tou3)
            views.add(textview_tuijian_tou4)
            views.add(textview_tuijian_tou5)
        }
    }

    internal inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.base_swipe_item_icon)
            textView = itemView.findViewById(R.id.base_swipe_item_title)
        }
    }

    internal inner class DateTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val imageView: ImageView
        val dateView: TextView

        init {
            dateView = itemView.findViewById(R.id.base_swipe_group_item_date)
            imageView = itemView.findViewById(R.id.base_swipe_item_icon)
            textView = itemView.findViewById(R.id.base_swipe_item_title)
        }
    }

    private inner class viewpagerecommendAdapter(private val mContext: Context) : PagerAdapter() {

        override fun getCount(): Int {
            return mTopStoriesBean.size
        }

        override fun instantiateItem(container: View, position: Int): Any {
            val imageview = ImageView(context)
            Glide.with(mContext).load(mTopStoriesBean[position].image).centerCrop().into(imageview)
            (container as ViewPager).addView(imageview)
            imageview.setOnClickListener {
                //头条点击
                //Toast.makeText(context, "点击了第" + position + "项,ID是" + mTopStoriesBean.get(position).getId(), Toast.LENGTH_LONG).show();
                //嗯ID在这里是没有错了
                val intent = Intent(mContext, StoryDetailActivity::class.java)
                intent.putExtra("id", mTopStoriesBean[position].id)
                mContext.startActivity(intent)
            }
            return imageview
        }

        override fun destroyItem(container: View, position: Int, `object`: Any) {
            (container as ViewPager).removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun restoreState(arg0: Parcelable?, arg1: ClassLoader?) {

        }

        override fun saveState(): Parcelable? {
            return null
        }

        override fun startUpdate(arg0: View) {

        }

        override fun finishUpdate(arg0: View) {

        }

    }

    //viewpagger的PageChangeListener
    private inner class viewpagerRecommendPageChangeListener : ViewPager.OnPageChangeListener {

        private var elderposition = 0
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            currentItem = position
            topViewHolder.textview_Viewpager.text = mTopStoriesBean[position].title
            views[elderposition].setBackgroundResource(R.drawable.dot_normal)
            views!![position].setBackgroundResource(R.drawable.dot_focused)
            elderposition = position
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    private inner class DotRun : Runnable {
        override fun run() {
            synchronized(topViewHolder.myViewPager) {
                currentItem = (currentItem + 1) % 5
                // handler切换图片
                handler.obtainMessage().sendToTarget()
            }
        }
    }

    private fun turnSringToDate(date: String): String {
        return if (date.length > 4) {
            date.substring(0, 4) + "年" + date.substring(4, 6) + "月" + date.substring(6) + "日"
        } else {
            date
        }
    }
}
