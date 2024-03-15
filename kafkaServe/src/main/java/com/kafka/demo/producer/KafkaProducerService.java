package com.kafka.demo.producer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Configuration
@EnableScheduling
public class KafkaProducerService implements SchedulingConfigurer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addCronTask(this::collectDataFromCovidAPI, "0 0/30 * * * *");
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(1);
    }

    private void collectDataFromCovidAPI() {

        try {


            OkHttpClient client = new OkHttpClient().newBuilder().build();

            Request request = new Request.Builder()
                    .url("https://api.covid19api.com/summary")
                    .method("GET", null)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String data = response.body() != null ? response.body().string() : "";
                sendMessage(data);
            } catch (Exception e) { e.printStackTrace(); }

        } catch (Exception e) { System.err.println("Error while fetching API"); }


    }

    public void sendMessage(String message) {
        kafkaTemplate.send("topic1", message);
    }

}
