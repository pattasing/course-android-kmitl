package kmitl.lab07.pattasing.mylazyinstagram.api;

import java.util.List;

import kmitl.lab07.pattasing.mylazyinstagram.model.Posts;

/**
 * Created by patta on 18/10/2560.
 */

public class UserProfile {
    private String user;
    private String urlProfile;


    private int follower;
    private int following;
    private int post;
    private String bio;

    private List<Posts> posts;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }


    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}
