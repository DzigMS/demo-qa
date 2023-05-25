package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.configuration.ConfigProvider;
import org.example.model.AllBooksModal;
import org.example.model.Book;

import java.io.IOException;
import java.util.List;

@Slf4j
public class ApiClient {

    private final OkHttpClient httpClient;

    private ObjectMapper mapper;


    public ApiClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Response get(String path){
        Request request = new Request.Builder().url(ConfigProvider.BASE_URL + path).get().build();
        try {
            return httpClient.newCall(request).execute();
        } catch (IOException e) {
            log.error("Something went wrong with request");
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBook(){

        Request request = new Request.Builder().url(ConfigProvider.BASE_URL + "BookStore/v1/Books").get().build();
        try {
            String body = httpClient.newCall(request).execute().body().string();
            AllBooksModal books = mapper.readValue(body, AllBooksModal.class);

            return books.getAllBooks();
        } catch (IOException e) {
            log.error("Something went wrong with request");
            throw new RuntimeException(e);
        }
    }
}
