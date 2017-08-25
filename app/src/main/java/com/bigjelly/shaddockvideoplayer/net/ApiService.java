package com.bigjelly.shaddockvideoplayer.net;

/**
 * Created by mby on 17-8-5.
 */

public interface ApiService {

    String HOST = "https://";
    String API_SERVER_URL = HOST + "cnodejs.org/api/v1/";

    String URL_ARTICLE_FEED = "/api/article/recent/";

    String GET_ARTICLE_LIST = "api/news/feed/v54/?refer=1&count=20&loc_mode=4&device_id=34960436458";

}
