package com.tianbin.theoldreaderapp.data.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * blog list
 * Created by tianbin on 16/11/4.
 */
public class BlogList {

    @SerializedName("direction")
    private String mDirection;
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("self")
    private SelfEntity mSelf;
    @SerializedName("updated")
    private int mUpdated;
    @SerializedName("continuation")
    private long mContinuation;
    @SerializedName("items")
    private List<Blog> mItems;

    public String getDirection() {
        return mDirection;
    }

    public void setDirection(String direction) {
        mDirection = direction;
    }

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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public SelfEntity getSelf() {
        return mSelf;
    }

    public void setSelf(SelfEntity self) {
        mSelf = self;
    }

    public int getUpdated() {
        return mUpdated;
    }

    public void setUpdated(int updated) {
        mUpdated = updated;
    }

    public long getContinuation() {
        return mContinuation;
    }

    public void setContinuation(long continuation) {
        mContinuation = continuation;
    }

    public List<Blog> getItems() {
        return mItems;
    }

    public void setItems(List<Blog> items) {
        mItems = items;
    }

    public static class SelfEntity {
        @SerializedName("href")
        private String mHref;

        public String getHref() {
            return mHref;
        }

        public void setHref(String href) {
            mHref = href;
        }
    }

    public static class Blog implements Serializable{
        @SerializedName("crawlTimeMsec")
        private String mCrawlTimeMsec;
        @SerializedName("timestampUsec")
        private String mTimestampUsec;
        @SerializedName("id")
        private String mId;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("published")
        private int mPublished;
        @SerializedName("updated")
        private int mUpdated;
        @SerializedName("summary")
        private SummaryEntity mSummary;
        @SerializedName("author")
        private String mAuthor;
        @SerializedName("likingUsersCount")
        private int mLikingUsersCount;
        @SerializedName("origin")
        private OriginEntity mOrigin;
        @SerializedName("categories")
        private List<String> mCategories;
        @SerializedName("canonical")
        private List<CanonicalEntity> mCanonical;
        @SerializedName("alternate")
        private List<AlternateEntity> mAlternate;
        @SerializedName("annotations")
        private List<?> mAnnotations;
        @SerializedName("likingUsers")
        private List<?> mLikingUsers;
        @SerializedName("comments")
        private List<?> mComments;

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj instanceof Blog) {
                return this.mId.equals(((Blog) obj).getId());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return mId.hashCode();
        }

        public String getCrawlTimeMsec() {
            return mCrawlTimeMsec;
        }

        public void setCrawlTimeMsec(String crawlTimeMsec) {
            mCrawlTimeMsec = crawlTimeMsec;
        }

        public String getTimestampUsec() {
            return mTimestampUsec;
        }

        public void setTimestampUsec(String timestampUsec) {
            mTimestampUsec = timestampUsec;
        }

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

        public int getPublished() {
            return mPublished;
        }

        public void setPublished(int published) {
            mPublished = published;
        }

        public int getUpdated() {
            return mUpdated;
        }

        public void setUpdated(int updated) {
            mUpdated = updated;
        }

        public SummaryEntity getSummary() {
            return mSummary;
        }

        public void setSummary(SummaryEntity summary) {
            mSummary = summary;
        }

        public String getAuthor() {
            return mAuthor;
        }

        public void setAuthor(String author) {
            mAuthor = author;
        }

        public int getLikingUsersCount() {
            return mLikingUsersCount;
        }

        public void setLikingUsersCount(int likingUsersCount) {
            mLikingUsersCount = likingUsersCount;
        }

        public OriginEntity getOrigin() {
            return mOrigin;
        }

        public void setOrigin(OriginEntity origin) {
            mOrigin = origin;
        }

        public List<String> getCategories() {
            return mCategories;
        }

        public void setCategories(List<String> categories) {
            mCategories = categories;
        }

        public List<CanonicalEntity> getCanonical() {
            return mCanonical;
        }

        public void setCanonical(List<CanonicalEntity> canonical) {
            mCanonical = canonical;
        }

        public List<AlternateEntity> getAlternate() {
            return mAlternate;
        }

        public void setAlternate(List<AlternateEntity> alternate) {
            mAlternate = alternate;
        }

        public List<?> getAnnotations() {
            return mAnnotations;
        }

        public void setAnnotations(List<?> annotations) {
            mAnnotations = annotations;
        }

        public List<?> getLikingUsers() {
            return mLikingUsers;
        }

        public void setLikingUsers(List<?> likingUsers) {
            mLikingUsers = likingUsers;
        }

        public List<?> getComments() {
            return mComments;
        }

        public void setComments(List<?> comments) {
            mComments = comments;
        }

        public static class SummaryEntity implements Serializable{
            @SerializedName("direction")
            private String mDirection;
            @SerializedName("content")
            private String mContent;

            public String getDirection() {
                return mDirection;
            }

            public void setDirection(String direction) {
                mDirection = direction;
            }

            public String getContent() {
                return mContent;
            }

            public void setContent(String content) {
                mContent = content;
            }
        }

        public static class OriginEntity implements Serializable{
            @SerializedName("streamId")
            private String mStreamId;
            @SerializedName("title")
            private String mTitle;
            @SerializedName("htmlUrl")
            private String mHtmlUrl;

            public String getStreamId() {
                return mStreamId;
            }

            public void setStreamId(String streamId) {
                mStreamId = streamId;
            }

            public String getTitle() {
                return mTitle;
            }

            public void setTitle(String title) {
                mTitle = title;
            }

            public String getHtmlUrl() {
                return mHtmlUrl;
            }

            public void setHtmlUrl(String htmlUrl) {
                mHtmlUrl = htmlUrl;
            }
        }

        public static class CanonicalEntity implements Serializable{
            @SerializedName("href")
            private String mHref;

            public String getHref() {
                return mHref;
            }

            public void setHref(String href) {
                mHref = href;
            }
        }

        public static class AlternateEntity implements Serializable{
            @SerializedName("href")
            private String mHref;
            @SerializedName("type")
            private String mType;

            public String getHref() {
                return mHref;
            }

            public void setHref(String href) {
                mHref = href;
            }

            public String getType() {
                return mType;
            }

            public void setType(String type) {
                mType = type;
            }
        }
    }
}
