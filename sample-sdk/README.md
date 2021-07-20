## sample-sdk

This module takes data of ShopDefinition, so that in can be posted to lambda function

For usage with Spring Boot:

```xml
<dependencies>
  <dependency>
     <groupId>io.sant.samplesdk</groupId>
     <artifactId>sample-sdk</artifactId>
     <version>1.4.0</version>
   </dependency>
</dependencies>
```

### Usage:

To use the client, use the following snippet:

```java
import io.sant.samplesdk.model.ShopDefinition;
import io.sant.samplesdk.model.EnvironmentType;


@Autowired
HistoryUtil historyUtil;

UUID uuid = UUID.randomUUID();
ShopDefinition shopDefinition = ShopDefinition.builder()
                .id(uuid.toString()).created_at(new Date()).created_by("San")
                .build();

historyUtil.save(EnvironmentType.Int, shopDefinition);

```
