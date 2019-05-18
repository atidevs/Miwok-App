package com.example.android.miwok;

/**
 * Created by gamer on 13/03/2018.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mAudioResourceId;
    public static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;



    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId= audioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId=imageResourceId;
        mAudioResourceId=audioResourceId;
    }


    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId () { return mImageResourceId; }

    public boolean hasImage(){ return mImageResourceId!= NO_IMAGE_PROVIDED; }

    public int getAudioResourceId() { return mAudioResourceId; }


}
