package com.genaku.remote;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.genaku.remote.jsonmodel.NewsFeedJson;
import com.genaku.remote.jsonmodel.NewsFeedResponse;
import com.genaku.remote.jsonmodel.Post;

import org.junit.Rule;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
public class ParseApiAnswerTest extends ApplicationTestCase<Application> {

    @Rule
    public final MockWebServer server = new MockWebServer();

    public ParseApiAnswerTest() {
        super(Application.class);
    }

    @SuppressWarnings("unchecked")
    public void testGetFeedRequest() {
        server.enqueue(new MockResponse().setBody(TestData.FEED));
        ANRequest request = AndroidNetworking.get(server.url("/").toString()).build();
        ANResponse<NewsFeedJson> response = request.executeForObject(NewsFeedJson.class);

        NewsFeedResponse newsFeed = response.getResult().getResponse();
        assertEquals("5/19_-23770654_303903:1848405078", newsFeed.getNext_from());
        assertEquals(5, newsFeed.getItems().size());

        Post post = newsFeed.getItems().get(0);

        assertEquals("Фильмы, меняющие взгляд на жизнь", post.getText());

        Post repost = newsFeed.getItems().get(4);

        assertEquals(1, repost.getCopyHistory().size());
    }

}
