package io.sant.samplesdk.config;

import io.netty.util.HashedWheelTimer;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.netty.channel.DefaultChannelPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

@Configuration
public class AsyncHttpClientConfig {
    @Bean
    @Primary
    public AsyncHttpClient defaultAsyncHttpClient() {
        HashedWheelTimer timer = new HashedWheelTimer();
        timer.start();
        ChannelPool pool =
                new DefaultChannelPool(60000, -1, DefaultChannelPool.PoolLeaseStrategy.FIFO, timer, -1);
        return asyncHttpClient(config()
                .setNettyTimer(timer)
                .setChannelPool(pool)
                .setConnectTimeout(60000));
    }
}
