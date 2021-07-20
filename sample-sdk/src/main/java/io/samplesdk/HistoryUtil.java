package io.sant.samplesdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sant.samplesdk.model.PublishedProduct;
import io.sant.samplesdk.model.ShopDefinition;
import io.sant.samplesdk.model.EnvironmentType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

@Component
public class HistoryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryUtil.class);
    private final AsyncHttpClient httpClient;
    private final Environment environment;
    private EnvironmentType environmentType;

    @Autowired
    public HistoryUtil(AsyncHttpClient httpClient, Environment environment) {
        this.httpClient = httpClient;
        this.environment = environment;
    }

    public void save(EnvironmentType envType, PublishedProduct publishedProduct) {
        this.environmentType = envType;
        prepareData(publishedProduct);
    }

    public void save(EnvironmentType envType, ShopDefinition shopDefinition) {
        this.environmentType = envType;
        prepareData(shopDefinition);
    }

    private void prepareData(Object dataObject) {
        try {
            String apiUrl = this.environment
                    .getProperty(dataObject.getClass().getSimpleName() + "ApiUrl" + "." + this.environmentType);
            ObjectMapper mapper = new ObjectMapper();
            String postData = mapper.writeValueAsString(dataObject);
            postDataToAPI(apiUrl, postData);

            CompletableFuture<Void> testCF = CompletableFuture.supplyAsync(() -> {
                try {
                    JsonNode test;
                } catch (Exception ex) {
                    throw new CompletionException(ex);
                }
                return null;
            });

            try {
                testCF.join();
            } catch (CompletionException ex) {
                LOGGER.error(e.getCause());
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void postDataToAPI(String url, String body) {
        org.asynchttpclient.Response response = null;
        try {
            BoundRequestBuilder boundRequestBuilder = httpClient.preparePost(url).setBody(body);
            response = boundRequestBuilder.execute().get();
        } catch (Exception e) {
            LOGGER.error("postDataToAPI - response:" + response.getStatusCode() + " url:" + url + " error:"
                    + e.getMessage());
        }
    }
}