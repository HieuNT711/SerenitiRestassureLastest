package example.until;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

public class LogUtils {
    //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger(LogUtils.class);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //Info Level Logs
    public static void info(String message) {
        Log.info(message);
    }

    public static void info(Object object) {
        Log.info(object);
    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
    }

    public static void warn(Object object) {
        Log.warn(object);
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error(message);
    }

    public static void error(Object object) {
        Log.error(object);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug(message);
    }

    public static void debug(Object object) {
        Log.debug(object);
    }

    public static void logRequest(RequestSpecification requestSpecification) {
        // Kiểm tra xem requestSpecification có thể lọc không
        if (!(requestSpecification instanceof FilterableRequestSpecification)) {
            Log.warn("RequestSpecification cannot be filtered for logging.");
            return;
        }
        // Ép kiểu requestSpecification sang FilterableRequestSpecification
        FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) requestSpecification;
        // Tạo một ByteArrayOutputStream để ghi log request
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        // Tạo một PrintStream từ ByteArrayOutputStream để ghi log request
        PrintStream requestPrintStream = new PrintStream(requestOutputStream, true);
        // Thêm RequestLoggingFilter vào RequestSpecification để ghi log request
        filterableRequestSpecification.filter(new RequestLoggingFilter(requestPrintStream));
        // Ghi log request vào ByteArrayOutputStream
        ((RequestSpecificationImpl) requestSpecification).log().all();
        // In ra log của request
        Log.info("Request: \n{}", requestOutputStream.toString());
    }

    public static void logResponse(Response response) {
        // Lấy thông tin về response
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        // Format response body
        String formattedResponseBody = formatJson(responseBody);

        // Ghi log về response
        Log.info("Response Status Code: {}", statusCode);
        Log.info("Response Body: \n{}", formattedResponseBody);
    }

    private static String formatJson(String json) {
        try {
            // Sử dụng Gson để parse và format JSON
            Object jsonObject = GSON.fromJson(json, Object.class);
            return GSON.toJson(jsonObject);
        } catch (Exception e) {
            // Nếu có lỗi, trả về JSON không được format
            return json;
        }
    }

}
