package com.kaluzny.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tag")
    @Column(name = "Value")
    private List<String> tags = new ArrayList<>();

    public Book(String name, String description, List<String> tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }
}
