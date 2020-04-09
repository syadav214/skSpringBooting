## product-change-history-sdk

This module takes data of Attributes, Datasheet, ExclusionRules and TenantDefinition, so that in can be posted to lambda function (which insert change history in respective tables)

For usage with Spring Boot:

```xml
<dependencies>
  <dependency>
     <groupId>io.sant.samplesdk</groupId>
     <artifactId>product-change-history-sdk</artifactId>
     <version>1.4.0</version>
   </dependency>
</dependencies>
```

### Usage:

To use the client, use the following snippet:

```java
import io.sant.samplesdk.model.Attributes;
import io.sant.samplesdk.model.Datasheet;
import io.sant.samplesdk.model.ExclusionRules;
import io.sant.samplesdk.model.TenantDefinition;
import io.sant.samplesdk.model.EnvironmentType;


@Autowired
HistoryUtil historyUtil;

UUID uuid = UUID.randomUUID();
TenantDefinition tenantDefinition = TenantDefinition.builder()
        .id(uuid.toString()).created_at(new Date()).created_by("San").environment("QE").last_modified_at(new Date())
        .last_modified_by("San").mcp_product_version("1232").mcp_sku("sku").object_reference_id("221c63c3-3e21-432d-9def-ed575312ad9f")
        .organization("ABC").product_key("12434").product_version(123).resource_class("class").variant_id("13234")
        .variant_sku("sku").action_performed("product_sync").correlation_id("1233")
        .build();

historyUtil.save(EnvironmentType.Int, tenantDefinition);

```
