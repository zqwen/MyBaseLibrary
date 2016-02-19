package com.suixin.mybaselib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suixin.mybaselib.R;

/**
 * Created by ZQW on 2015/12/30 0030.
 * 512609950@QQ.COM
 */
public class BaseActivity extends Activity{

    private int layoutResID;
    private TextView titleTextView;
    private RelativeLayout backRelativeLayout;
    private Intent backActivityIntent;
    private LinearLayout actionBarItemLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*
     *设置actionbar的title
     */
    public void setTitle(String title){
        titleTextView.setText(title);
    }

    /*
     *设置按返回键时跳转到的页面
     * 默认返回上一页
     */
    public void setBackActivityIntent(Intent intent){
        backActivityIntent = intent;
    }

    public void setContentView(int layoutResID) {
        this.layoutResID = layoutResID;
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.setContentView(layoutResID);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        titleTextView = (TextView)getWindow().getDecorView().findViewById(R.id.title_text);
        backRelativeLayout = (RelativeLayout)getWindow().getDecorView().findViewById(R.id.back);
        backRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backActivityIntent != null) {
                    startActivity(backActivityIntent);
                    return;
                }
                finish();//默认返回上一页
            }
        });
        actionBarItemLinearLayout = (LinearLayout)getWindow().getDecorView().findViewById(R.id.action_bar_item);
    }


    public View createActionItem(int drawableResource, int padding){
        RelativeLayout actionButton = (RelativeLayout)getLayoutInflater().inflate(R.layout.view_action_item, null);
        actionBarItemLinearLayout.addView(actionButton);
        ImageView imageView = (ImageView)actionButton.findViewById(R.id.action_bar_item_imageView);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(drawableResource);
        return actionButton;
    }
    public View createActionItem(String text, int textColorResource, int textSize){
        RelativeLayout actionButton = (RelativeLayout)getLayoutInflater().inflate(R.layout.view_action_text_item, null);
        actionBarItemLinearLayout.addView(actionButton);
        TextView textView = (TextView)actionButton.findViewById(R.id.action_bar_item_TextView);
        textView.setText(text);
        textView.setTextColor(getResources().getColor(textColorResource));
        textView.setTextSize(textSize);
        return actionButton;
    }

}
