import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.AddItems;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class TestBase extends AddItems {
    public static ExtentReports extent;
    public static ExtentTest test;
    public String BookingID;
    @BeforeSuite
    public void setup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
    //===================================================================================================
    String RequestID = "{\n" +
            " \"firstname\": \"testFirstName\",\n" +
            " \"lastname\": \"lastName\",\n" +
            " \"totalprice\": 10,\n" +
            " \"depositpaid\": true,\n" +
            " \"bookingdates\": {\n" +
            " \"checkin\": \"2022-01-01\",\n" +
            " \"checkout\": \"2024-01-01\"\n" +
            " },\n" +
            " \"additionalneeds\": \"testAdd\"\n" +
            "}\n";
    public Response GetIDResponse;
    public  void getListBookRequestwithID() {
        testResponse();
        BookingID = response.jsonPath().getString("bookingid");
        System.out.println(" https://restful-booker.herokuapp.com/booking/" + BookingID);
        try {
            GetIDResponse = given()
                    .auth().none()
                    .contentType(ContentType.JSON)
                    .body(RequestID)
                    .get("https://restful-booker.herokuapp.com/booking/" + BookingID);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    //=====================================================================================

    @AfterSuite
    public void tearDown() {
        extent.flush();  // Generate the report
    }
}
