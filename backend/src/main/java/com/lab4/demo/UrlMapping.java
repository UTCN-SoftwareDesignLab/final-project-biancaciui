package com.lab4.demo;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String BOOKS = API_PATH + "/books";
    public static final String EXPORT_REPORT = "/export/{type}";
    public static final String MOVIES = API_PATH + "/movies";
    public static final String GENRES = "/genres";

    public static final String ENTITY = "/{id}";
    public static final String ENTITY_MOVIE = "/{movie_id}";
    public static final String ENTITY_USER = "/{user_id}";
    public static final String QUANTITY = "/{amount}";
    public static final String NAME = "/{name}";
    public static final String DESCRIPTION = "/{description}";

    public static final String SELL_BOOK = "/bookstore";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";

    public static final String SELL_MOVIE = "/moviestore";

    public static final String ORDER = "/order";


}
