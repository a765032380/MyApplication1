package com.example.gll.myapplication.view.card_view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.bean.ShareCardItem;
import com.example.gll.myapplication.view.RoundByXfermode;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



/**
 * @author SoBan
 * @create 2017/5/19 15:33.
 */
public class ShareCardView extends FrameLayout implements ViewPager.OnPageChangeListener{
    private static final int MSG_NEXT = 1;
    private Context mContext;
    private ViewPager mViewPager; //自定义的无限循环ViewPager
    private ViewGroup mViewGroup;
    private CardAdapter mAdapter;
    private int mFocusImageId;
    private int mUnfocusImageId;
    private Handler mHandler;
    private TimerTask mTimerTask;
    private Timer mTimer;
    private int centerPos; //中间卡片位置
    private int pageCount; //所有卡片的个数
    private OnClickListener1 onClickListener;
    private ShareCardItem shareCardItem;

    public interface OnClickListener1{
        void onClickListener(String url);
    }
    public void setOnClickListener(OnClickListener1 onClickListener){
        this.onClickListener=onClickListener;
    }
    public ShareCardView(Context context) {
        this(context, null);
    }

    public ShareCardView(Context context, AttributeSet set) {
        super(context, set);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View container = LayoutInflater.from(mContext).inflate(R.layout.layout_share_cardview, null);
        addView(container);
        mViewPager = (ViewPager) (container.findViewById(R.id.slide_viewPager));
        mViewGroup = (ViewGroup) (container.findViewById(R.id.slide_viewGroup));
//        mFocusImageId = R.drawable.a_icon_lvdian;
//        mUnfocusImageId = R.drawable.a_icon_huidian;

        mViewPager.setPageTransformer(false, new ScaleTransformer());//设置卡片切换动画
        mViewPager.setPageMargin(40);//卡片与卡片间的距离
        mViewPager.setOnPageChangeListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_NEXT:
                        next();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        stopTimer();
                        break;
                    case MotionEvent.ACTION_UP:
                        startTimer();
                        break;
                }
                return false;
            }
        });
    }

    //设置数据
    public void setCardData(ShareCardItem cardItem) {
        this.shareCardItem=cardItem;
        pageCount = cardItem.getDataList().size();
        centerPos = (pageCount+1) / 2;//中间卡片的位置
        mAdapter = new CardAdapter(cardItem);
        mViewPager.setAdapter(mAdapter);
        mAdapter.select(centerPos);//默认从中间卡片开始
        mViewPager.setCurrentItem(centerPos);
        mViewPager.setOffscreenPageLimit(pageCount);//预加载所有卡片

        startTimer();
    }

    //启动动画
    public void startTimer() {
        stopTimer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_NEXT);
            }
        };
        mTimer = new Timer(true);
        mTimer.schedule(mTimerTask, 3000, 3000);
    }

    //停止动画
    public void stopTimer() {
        mHandler.removeMessages(MSG_NEXT);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    //选择下一个卡片
    public void next() {
        int pos = mViewPager.getCurrentItem();
        pos += 1;
        mViewPager.setCurrentItem(pos);
    }

    //判断是否显隐控件
    public void refresh() {
        if (getCount() <= 0) {
            this.setVisibility(View.GONE);
        } else {
            this.setVisibility(View.VISIBLE);
        }
    }

    public int getCount() {
        if (mAdapter != null) {
            return mAdapter.getCount();
        }
        return 0;
    }

    public class CardAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private ArrayList<ImageView> mPoints;
        private ShareCardItem.ZSCardItem zsCardItem = new ShareCardItem.ZSCardItem();
        private List<ShareCardItem.LRCardItem> lrCardItems = new ArrayList<>();
        private List<Integer> iconLists = Arrays.asList(R.mipmap.share_card1, R.mipmap.share_card2,
                R.mipmap.share_card3, R.mipmap.share_card4);

        public CardAdapter(ShareCardItem cardItem) {
            inflater = LayoutInflater.from(mContext);
            mPoints = new ArrayList<>();
            zsCardItem = cardItem.getData();
            lrCardItems = cardItem.getDataList();
            setItems();
        }

        @Override
        public int getCount() {
            return pageCount;
        }

        private ImageView newPoint() {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 15;
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(mUnfocusImageId);
            return imageView;
        }

        //中间卡片
        public void setZSCard(View view, ShareCardItem.LRCardItem item) {
            RoundByXfermode imageView=  view.findViewById(R.id.image);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.loadImage(item.getContent(),R.drawable.ic_launcher_background);
            Glide.with(view.getContext())
                    .load(item.getContent())
                    .into(imageView);
//            ImageLoaderManager.getInstance(getContext())
//                    .displayImage(imageView,item.getContent());

//            imageView.setBackgroundResource(R.color.white);

//            TextView zsTv = (TextView) view.findViewById(R.id.zs_tv);
//            TextView jyeTv = (TextView) view.findViewById(R.id.jye_tv);
//            TextView lmTv = (TextView) view.findViewById(R.id.lm_tv);
//            TextView sbTv = (TextView) view.findViewById(R.id.sb_tv);
//            zsTv.setText(String.valueOf(item.getTodayIndex()));
//            jyeTv.setText(String.format("交易额：%s元", item.getAmount()));
//            lmTv.setText(String.format("%s家", item.getShopNum()));
//            sbTv.setText(String.format("%s个", item.getMemberNum()));
        }

        //其他卡片
        public void setLRCard(View view, int lrCardItemPos) {
            ShareCardItem.LRCardItem item = lrCardItems.size() > lrCardItemPos ?
                    lrCardItems.get(lrCardItemPos) : new ShareCardItem.LRCardItem();
//            ImageView iconIv = (ImageView) view.findViewById(R.id.icon_iv);
//            TextView titleTv = (TextView) view.findViewById(R.id.title_tv);
//            TextView contentTv = (TextView) view.findViewById(R.id.content_tv);
//            if (lrCardItemPos < iconLists.size()) {
//                iconIv.setImageResource(iconLists.get(lrCardItemPos));
//            }
//            titleTv.setText(item.getTitle());
//            contentTv.setText(item.getContent());
//            contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        }

        public void setItems() {
            while (mPoints.size() < pageCount) mPoints.add(newPoint());
            while (mPoints.size() > pageCount) mPoints.remove(0);
            mViewGroup.removeAllViews();
            for (ImageView view : mPoints) {
                mViewGroup.addView(view);
            }
            mViewPager.setCurrentItem(0);
            select(0);
        }

        public void select(int pos) {
            if (mPoints.size() > 0) {
                pos = pos % mPoints.size();
                for (int i = 0; i < mPoints.size(); i++) {
                    if (i == pos) {
                        mPoints.get(i).setBackgroundResource(mFocusImageId);
                    } else {
                        mPoints.get(i).setBackgroundResource(mUnfocusImageId);
                    }
                }
            }
            refresh();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
                view = inflater.inflate(R.layout.layout_share_zscard, container, false);
//            if (position == centerPos) {
//                view = inflater.inflate(R.layout.layout_share_zscard, container, false);
                setZSCard(view, lrCardItems.get(position));
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener!=null){
                        onClickListener.onClickListener(
                                shareCardItem.getDataList().get(mViewPager.getCurrentItem()).getTitle());
                    }
                }
            });
//            } else if (position < centerPos) {
//                view = inflater.inflate(R.layout.layout_share_lrcard, container, false);
//                setLRCard(view, position);
//            } else {
//                view = inflater.inflate(R.layout.layout_share_lrcard, container, false);
//                setLRCard(view, position - 1);
//            }
                container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mAdapter.select(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
