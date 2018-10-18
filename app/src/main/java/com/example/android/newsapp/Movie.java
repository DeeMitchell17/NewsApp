package com.example.android.newsapp;

public class Movie {

    private String mWebTitle;

    private String mSectionName;

    private String mPublicationDate;

    private String mAuthorName;

    private int mImageResourceId;

    private String mUrl;

    public Movie(String webTitle, String sectionName, String publicationDate, String authorName, String url) {
        mWebTitle = webTitle;
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mAuthorName = authorName;
        mUrl = url;
    }


    public Movie(String webTitle, String sectionName, String publicationDate, int imageResourceId, String authorName, String url) {
        mWebTitle = webTitle;
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mAuthorName = authorName;
        mImageResourceId = imageResourceId;
        mUrl = url;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getAuthorName(){
        return mAuthorName;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}

