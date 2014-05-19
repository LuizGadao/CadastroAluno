package com.luizgadao.cadastroaluno.app.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by luizcarlos on 19/05/14.
 */
public class WebClient
{

    private final String urlPost;

    public WebClient( String urlPost )
    {
        this.urlPost = urlPost;
    }

    public String post( String jsonDadaStudents ) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urlPost);

            post.setEntity(new StringEntity(jsonDadaStudents));
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");

            HttpResponse httpResponse = client.execute(post);
            HttpEntity resp = httpResponse.getEntity();

            String respJson = EntityUtils.toString(resp);

            return respJson;
        } catch (Exception e)
        {
            throw new RuntimeException( e );
        }
    }

}
