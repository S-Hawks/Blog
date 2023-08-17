package dev.faiaz.blog.utils;

public class PostEndPointUtils {
    public static final String ADD_NEW_POSTS = "/user/{userId}/category/{categoryId}/posts";
    public static final String GET_POST_BY_CATEGORY = "/category/{categoryId}";
    public static final String GET_POST_BY_USER = "/user/{userId}";
    public static final String POSTS_BY_ID = "/{id}";

    public static final String POST_SEARCH_BY_KEYWORD = "/search/{keywords}";
}
