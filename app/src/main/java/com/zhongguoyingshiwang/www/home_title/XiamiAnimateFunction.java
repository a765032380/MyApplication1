package com.zhongguoyingshiwang.www.home_title;

import android.animation.Animator;
import android.animation.ValueAnimator;

public interface XiamiAnimateFunction {

    void onAnimationUpdate(ValueAnimator animation);

    void onAnimationEnd(Animator animation);

}
