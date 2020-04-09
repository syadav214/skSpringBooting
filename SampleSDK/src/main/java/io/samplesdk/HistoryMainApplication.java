package io.sant.samplesdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sant.samplesdk.model.ShopDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.sant.samplesdk.model.PublishedProduct;
import io.sant.samplesdk.model.EnvironmentType;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
class HistoryMainApplication implements CommandLineRunner {
    @Autowired
    HistoryUtil historyUtil;

    public static void main(String[] args) {
        SpringApplication.run(HistoryMainApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running Spring Boot Application");
        UUID uuid = UUID.randomUUID();
        ShopDefinition shopDefinition = ShopDefinition.builder()
                .id(uuid.toString()).created_at(new Date()).created_by("San")
                .build();
        /*ObjectMapper mapper = new ObjectMapper();
        JsonNode testJson = mapper.readValue("{\"data\":\"test\"}", JsonNode.class);
        PublishedProduct publishedProduct = PublishedProduct.builder()
                .id(uuid.toString()).created_at(new Date()).created_by("San").my_rules(testJson).build();*/
        historyUtil.save(EnvironmentType.Dev, shopDefinition);
    }
}