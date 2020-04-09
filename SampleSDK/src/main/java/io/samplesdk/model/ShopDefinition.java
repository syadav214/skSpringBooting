package io.sant.samplesdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopDefinition {
    @NonNull
    private String id;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date created_at;
    @NonNull
    private String created_by;  
}
