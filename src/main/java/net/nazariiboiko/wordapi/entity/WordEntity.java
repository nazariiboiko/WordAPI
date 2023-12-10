package net.nazariiboiko.wordapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "word")
public class WordEntity extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "definition")
    private String definition;

    @Column(name = "level")
    private String level;
}
