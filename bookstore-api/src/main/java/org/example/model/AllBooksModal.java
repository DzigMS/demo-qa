package org.example.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllBooksModal {

    @JsonProperty("books")
    private List<Book> allBooks;
}
