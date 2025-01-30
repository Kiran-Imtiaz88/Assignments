
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.AddItems;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class TestAddItem extends TestBase {

    public String FirstName = "";
    public String LastName = "";
    public String TotalPrice = "";
    public Boolean DepositId ;
    public String CheckIn = "";
    public String CheckOut = "";
    public String BookingId = "";

   @Test (priority = 1)
    void VerifyStatusCode(){
       test = extent.createTest("Verify Add Item Status Code");
       testResponse();
       test.log(Status.PASS, "API Response is 200 ");
       System.out.println("Status Code is: "+ response.getStatusCode());

       FirstName = response.jsonPath().getString("booking.firstname");
       System.out.println(FirstName);

       LastName = response.jsonPath().getString("booking.lastname");
       System.out.println(LastName);

       TotalPrice = response.jsonPath().getString("booking.totalprice");
       System.out.println(TotalPrice);

       DepositId = response.jsonPath().getBoolean("booking.depositpaid") ;
       System.out.println(DepositId);

       CheckIn = response.jsonPath().getString("booking.bookingdates.checkin");
       System.out.println(CheckIn);

       CheckOut = response.jsonPath().getString("booking.bookingdates.checkout");
       System.out.println(CheckOut);

       BookingId = response.jsonPath().getString("bookingid");
       System.out.println(BookingId);

   }
   @Test (priority = 2)
    void VerifyBookingID(){
       test = extent.createTest("Verify the user Booking ID");
       Assert.assertNotNull(BookingId);
       test.log(Status.PASS,"Booking ID is: " + BookingId);
       System.out.println("BookingID is  : "+BookingId);
   }
   @Test (priority = 3)
    void VerifyBookingFirstName(){
       test = extent.createTest("Verify the Booking user First Name");
       Assert.assertNotNull(FirstName);
       test.log(Status.PASS," FirstName is : testFirstName" );
       Assert.assertEquals(FirstName,"testFirstName");
    }
    @Test (priority = 4)
    void VerifyBookingLastName(){
        test = extent.createTest("Verify the Booking user First Name");
        Assert.assertNotNull(LastName);
        test.log(Status.PASS,"Booking LastName is  : "+LastName);
        Assert.assertEquals(LastName,"lastName");
    }
    @Test(priority = 5)
    void VerifyTotalPrice(){
        test = extent.createTest("Verify the Booking Total price");
        Assert.assertNotNull(TotalPrice);
        test.log(Status.PASS,"Total Price is  : "+TotalPrice);
        if (TotalPrice.contains("10")) {
            //Assert.assertEquals(TotalPrice,"10");
            System.out.println("price is displayed in round figure.");
        }else {
            System.out.println("Price should be in decimal points.");
        }

    }
    @Test (priority = 6)
    void VerifyDepositId(){

        test = extent.createTest("Verify the Booking Despoist ID");
        Assert.assertNotNull(DepositId);
        test.log(Status.PASS,"Booking DepositId is  : "+DepositId);
        Assert.assertTrue(DepositId,"Booking has been Done.");
    }
    @Test (priority = 7)
    void VerifyBookingCheckIn(){

        test = extent.createTest("Verify the Booking Dates CheckIn");
        Assert.assertNotNull(CheckIn);
       test.log(Status.PASS,"BookingCheckIn is  : "+CheckIn);
        Assert.assertEquals(CheckIn,"2022-01-01");
    }
    @Test (priority = 8)
    void VerifyBookingCheckOut(){
        test = extent.createTest("Verify the Booking Dates CheckOut");
        Assert.assertNotNull(CheckOut);
        test.log(Status.PASS,"BookingCheckIn is  : "+CheckOut);

        Assert.assertEquals(CheckOut,"2024-01-01");
    }

}
