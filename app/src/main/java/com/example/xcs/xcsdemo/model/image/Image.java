package com.example.xcs.xcsdemo.model.image;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author RWX
 * @time 2019-04-12.
 */
public class Image {


    /**
     * id : LBI7cgq3pbM
     * created_at : 2016-05-03T11:00:28-04:00
     * updated_at : 2016-07-10T11:00:01-05:00
     * width : 5245
     * height : 3497
     * color : #60544D
     * likes : 12
     * liked_by_user : false
     * description : A man drinking a coffee.
     * urls : {"raw":"https://images.unsplash.com/face-springmorning.jpg","full":"https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg","regular":"https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=1080&fit=max","small":"https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=400&fit=max","thumb":"https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=200&fit=max"}
     */

    private String id;
    private String created_at;
    private String updated_at;
    private int width;
    private int height;
    private String color;
    private int likes;
    private boolean liked_by_user;
    private String description;
    private UrlsBean urls;

    public static Image objectFromData(String str) {

        return new Gson().fromJson(str, Image.class);
    }

    public static Image objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Image.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }

    public static class UrlsBean {
        /**
         * raw : https://images.unsplash.com/face-springmorning.jpg
         * full : https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg
         * regular : https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=1080&fit=max
         * small : https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=400&fit=max
         * thumb : https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=200&fit=max
         */

        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thumb;

        public static UrlsBean objectFromData(String str) {

            return new Gson().fromJson(str, UrlsBean.class);
        }

        public static UrlsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), UrlsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }
}
