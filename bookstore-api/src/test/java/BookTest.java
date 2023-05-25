import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.example.ApiClient;
import org.example.configuration.ConfigProvider;
import org.example.model.Book;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class BookTest {
    private ApiClient client;

    @BeforeClass
    public static void setUpAll(){
        RestAssured.baseURI = ConfigProvider.BASE_URL;
    }

    @BeforeMethod
    public void setUp() {
        client = new ApiClient(new OkHttpClient());
    }

    @Test
    @SneakyThrows(IOException.class)
    public void allBooksTest() {
        Response response = client.get("BookStore/v1/Books");
        String body = response.body().string();
        log.info(body);
        Assert.assertTrue(body.contains("9781449325862"));
    }

    @Test
    public void getAllBooksTest() {
        Book expected = new Book();

        expected.setAuthor("Richard E. Silverman");
        expected.setIsbn(9781449325862L);
        expected.setPages(234);
        expected.setTitle("Git Pocket Guide");

        List<Book> allBook = client.getAllBook();

        log.info(allBook.toString());


//        Assert.assertTrue(allBook.contains(expected));
    }

    @Test
    public void getAllBooksRATest(){
        when().get("BookStore/v1/Books")
                .then().statusCode(200)
                .body("books[0].isbn", Matchers.equalTo("9781449325862"));
    }
}
