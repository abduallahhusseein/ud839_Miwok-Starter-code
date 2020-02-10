package com.example.android.miwok;

import android.widget.BaseAdapter;

/**
 * Created by MY on 3/19/2018.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private   int positionForImageView=noImageProvided;
    private static final int noImageProvided=-1;
    private int positoinOfResourceOfaudio;
    public Word(String defaultTranslation,String miwokTranselation,int positoinAudio)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranselation;
        positoinOfResourceOfaudio=positoinAudio;
    }
    public Word(int position, String defaultTranslation, String miwokTranselation,int positionForAudio)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranselation;
        positionForImageView=position;
        positoinOfResourceOfaudio=positionForAudio;
    }
//    public Word(int position, String defaultTranslation, String miwokTranselation,int positionaudio)
//    {
//        mDefaultTranslation=defaultTranslation;
//        mMiwokTranslation=miwokTranselation;
//        positionForImageView=position;
//        positoinOfResourceOfaudio=positionaudio;
//    }
    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }
    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }
    public int    getPositionForImageView(){return positionForImageView; }
    public int    getPositionForAudio(){return positoinOfResourceOfaudio; }
   public boolean hasImage(){
      return positionForImageView!=noImageProvided;
   }
}
