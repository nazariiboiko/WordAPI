package net.nazariiboiko.wordapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordFilterModel {
    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("level")
    private List<String> level;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotEmpty(keyword)) sb.append("keyword='").append(keyword).append("',");
        if(level != null) sb.append("level='").append(level).append("'.");

        return sb.toString();
    }
}
