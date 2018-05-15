package com.example.gll.myapplication.view.card_view;

import android.support.v4.view.ViewPager;
import android.view.View;


/**
 * @author SoBan
 * @create 2017/5/19 15:33.
 */
public class ScaleTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.7f;//缩放的比例
    private static final float MIN_ALPHA = 0.5f;//透明度的比例
    private static final float MOVE_Y = 40;//设置y轴移动的基数

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
//            page.setAlpha(MIN_ALPHA);
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
            page.setTranslationY(0);
        } else if (position <= 1) { // [-1,1]
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position <= 0) {
//                float scaleX = 1 + 0.3f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
                page.setTranslationY(0);
            } else {
                page.setTranslationY(-MOVE_Y * (1 - Math.abs(position)));
//                float scaleX = 1 - 0.3f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
            }
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
