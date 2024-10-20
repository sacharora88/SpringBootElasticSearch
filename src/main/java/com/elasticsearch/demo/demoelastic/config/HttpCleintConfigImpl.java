package com.elasticsearch.demo.demoelastic.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.File;

import java.io.File;

@Configuration
public class HttpCleintConfigImpl implements RestClientBuilder.HttpClientConfigCallback{

    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("elastic", "LQxu*-rV7cevwlOaqoI6");
            credentialsProvider.setCredentials(AuthScope.ANY,usernamePasswordCredentials);
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);


            String trustStoreLocation = "C:\\Users\\SACHIN\\Downloads\\elasticsearch-8.15.3-windows-x86_64\\elasticsearch-8.15.3\\config\\certs\\truststore.p12";
            File truestStoreLocationFile = new File(trustStoreLocation);

            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(truestStoreLocationFile, "password".toCharArray());
            SSLContext sslContext = sslContextBuilder.build();

            httpAsyncClientBuilder.setSSLContext(sslContext);

        }catch (Exception e){
            e.printStackTrace();
        }

        return httpAsyncClientBuilder;
    }
}
