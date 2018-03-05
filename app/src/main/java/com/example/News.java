package com.example.aphomework;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Jane_Chiang on 2018/2/8.
 */

//public class News {
//}
//implements Serialzable是为了在Intent中能够直接传递News对象
public class News implements Serializable {
    //新闻标题，内容，图片
    private String title;
    private String desc;
    private int photoId;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public News(String name, String age, int photoId) {
        this.title=name;
        this.desc=age;
        this.photoId=photoId;
    }

    public void setDesc(String desc) {
        Log.d("[#####]","setDesc");
        this.desc = desc;
    }

    public void setTitle(String title) {
        Log.d("[#####]","setTitle");
        this.title = title;
    }

    public void setPhotoId(int photoId) {
        Log.d("[#####]","setPhotoId");
        this.photoId = photoId;
    }

    public String getDesc() {
        Log.d("[#####]","getDesc");
        return desc;
    }

    public int getPhotoId() {
        Log.d("[#####]","getPhotoId");
        return photoId;
    }

    public String getTitle() {
        Log.d("[#####]","getTitle");
        return title;
    }
}


/*
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"

    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="5dp">

    <android.support.v7.widget.CardView
        android:id="@+id/card_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:foreground="?android:attr/selectableItemBackground"
        android:clickable="true"
        app:cardCornerRadius="3dp"
        app:cardElevation="8dp">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <RelativeLayout
                android:id="@+id/news_header"
                android:layout_width="match_parent"
                android:layout_height="wrap_content">
                <ImageView
                    android:id="@+id/news_photo"
                    android:scaleType="centerCrop"
                    android:layout_alignParentTop="true"
                    android:layout_width="match_parent"
                    android:layout_height="150dp"/>
                <TextView
                    android:id="@+id/news_title"
                    android:layout_alignParentBottom="true"
                    android:layout_alignParentLeft="true"
                    android:maxLines="1"
                    android:textSize="20sp"
                    android:padding="5dp"
                    android:textColor="#ffffff"
                    android:gravity="center"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"/>
            </RelativeLayout>

            <TextView
                android:id="@+id/news_desc"
                android:maxLines="2"
                android:layout_below="@+id/news_header"
                android:layout_margin="15dp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
            <LinearLayout
                android:orientation="horizontal"
                android:layout_below="@+id/news_desc"
                android:layout_width="match_parent"
                android:layout_height="wrap_content">
                <Button
                    android:id="@+id/btn_share"
                    android:text="SHARE"
                    android:background="#00000000"
                    android:layout_marginLeft="10dp"
                    android:layout_marginRight="20dp"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />
                <Button
                    android:id="@+id/btn_more"
                    android:background="#00000000"
                    android:textColor="#7AD3E0"
                    android:text="READ MORE"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />
            </LinearLayout>

        </RelativeLayout>

    </android.support.v7.widget.CardView>


</RelativeLayout>
 */