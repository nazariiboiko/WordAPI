package net.nazariiboiko.wordapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordDto {
    private String id;
    private String name;
    private String definition;
    private String level;
}
