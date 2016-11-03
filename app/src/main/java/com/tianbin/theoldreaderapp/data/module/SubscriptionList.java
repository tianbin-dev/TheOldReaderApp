package com.tianbin.theoldreaderapp.data.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * subscription list
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionList {

    /**
     * id : feed/581442bbc70bc24d130005ca
     * title : #androiduiux
     * categories : [{"id":"user/-/label/android","label":"android"}]
     * sortid : 581442bbc70bc24d130005ca
     * firstitemmsec : 1460302439000
     * url : https://androiduiux.com/feed/
     * htmlUrl : https://androiduiux.com
     * iconUrl : //s.theoldreader.com/system/uploads/feed/picture/0066/6225/2a3f/49ff/b825/icon_1ee2.ico
     */

    @SerializedName("subscriptions")
    private List<SubscriptionsEntity> mSubscriptions;

    public List<SubscriptionsEntity> getSubscriptions() {
        return mSubscriptions;
    }

    public void setSubscriptions(List<SubscriptionsEntity> subscriptions) {
        mSubscriptions = subscriptions;
    }

    public static class SubscriptionsEntity {
        @SerializedName("id")
        private String mId;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("sortid")
        private String mSortid;
        @SerializedName("firstitemmsec")
        private String mFirstitemmsec;
        @SerializedName("url")
        private String mUrl;
        @SerializedName("htmlUrl")
        private String mHtmlUrl;
        @SerializedName("iconUrl")
        private String mIconUrl;
        /**
         * id : user/-/label/android
         * label : android
         */

        @SerializedName("categories")
        private List<CategoriesEntity> mCategories;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getSortid() {
            return mSortid;
        }

        public void setSortid(String sortid) {
            mSortid = sortid;
        }

        public String getFirstitemmsec() {
            return mFirstitemmsec;
        }

        public void setFirstitemmsec(String firstitemmsec) {
            mFirstitemmsec = firstitemmsec;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

        public String getHtmlUrl() {
            return mHtmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            mHtmlUrl = htmlUrl;
        }

        public String getIconUrl() {
            return mIconUrl;
        }

        public void setIconUrl(String iconUrl) {
            mIconUrl = iconUrl;
        }

        public List<CategoriesEntity> getCategories() {
            return mCategories;
        }

        public void setCategories(List<CategoriesEntity> categories) {
            mCategories = categories;
        }

        public static class CategoriesEntity {
            @SerializedName("id")
            private String mId;
            @SerializedName("label")
            private String mLabel;

            public String getId() {
                return mId;
            }

            public void setId(String id) {
                mId = id;
            }

            public String getLabel() {
                return mLabel;
            }

            public void setLabel(String label) {
                mLabel = label;
            }
        }
    }
}
