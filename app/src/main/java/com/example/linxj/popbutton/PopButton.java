package com.example.linxj.popbutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by linxj on 2015/11/13.
 */
public class PopButton extends FrameLayout {
    private ImageView[] images;
    private Context mContext;
    private boolean mFlag = true;
    private  ImageView image1,image2,image3;
    public PopButton(Context context) {
        super(context, null);
    }

    public PopButton(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        this.mContext = context;
       View view = LayoutInflater.from(mContext).inflate(R.layout.image1,this);
        image1 = (ImageView)view.findViewById(R.id.image1);
        image2 = (ImageView)view.findViewById(R.id.image2);
        image3 = (ImageView)view.findViewById(R.id.image3);
        setListener(view);

    }

    public PopButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //this.images = img;
        this.mContext = context;
    }
    public void setListener(final View view){
        image1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFlag) {
                    setAnimation(view);
                }else{
                    closeAnimation(view);
                }
            }
        });
    }
   public void setAnimation(View view){

       image2.setVisibility(View.VISIBLE);
       image3.setVisibility(View.VISIBLE);
       //float value1 = image1.getTranslationY();
       ObjectAnimator animator1 = ObjectAnimator.ofFloat(image2,"alpha",0,1f);
       ObjectAnimator animator2 = ObjectAnimator.ofFloat(image2,"translationY",-200F);
       ObjectAnimator animator3 = ObjectAnimator.ofFloat(image3, "translationY", -400F);
       ObjectAnimator animator4 = ObjectAnimator.ofFloat(image3, "alpha", 0,1f);
       ObjectAnimator animator5 = ObjectAnimator.ofFloat(image1, "rotation", 0,90);
       AnimatorSet set = new AnimatorSet();
       set.setDuration(500);
       set.setInterpolator(new BounceInterpolator());
       set.playTogether(animator1, animator2, animator3, animator4, animator5);
       set.start();
       mFlag = false;
   }
    public void closeAnimation(View view){
       // ObjectAnimator animator5 = ObjectAnimator.ofFloat(image1, "translationY", -200);
        float value1 = image1.getTranslationY();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(image2,"alpha",1f,0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(image2,"translationY",-200F,value1);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(image3, "translationY", -400F,value1);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(image3, "alpha", 1f,0);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(image1, "rotation", 90,0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator1, animator2, animator3, animator4, animator5);
        set.start();

        mFlag = true;
    }
}
