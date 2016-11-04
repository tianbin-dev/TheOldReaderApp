package com.tianbin.theoldreaderapp.data.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * subscription list
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionList {

    @SerializedName("subscriptions")
    private List<Entity> mSubscriptions;

    public List<Entity> getSubscriptions() {
        return mSubscriptions;
    }

    public void setSubscriptions(List<Entity> subscriptions) {
        mSubscriptions = subscriptions;
    }

    public static class Entity {
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
