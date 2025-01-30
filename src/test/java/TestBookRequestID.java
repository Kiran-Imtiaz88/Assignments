import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.AddItems;
import org.example.ListBookRequestwithID;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestBookRequestID extends TestBase {
   public String FirstName = "";
   public String LastName = "";
   public String TotalPrice = "";
   public Boolean DepositId ;
   public String CheckIn = "";
   public String CheckOut = "";



    @Test(priority = 11)
    void VerifyStatusCode(){
        getListBookRequestwithID();
        test = extent.createTest("Verify the Booking of User ID:" + BookingID);
        FirstName = GetIDResponse.jsonPath().getString("firstname");
        LastName = GetIDResponse.jsonPath().getString("lastname");
        // System.out.println(LastName);
        TotalPrice  = GetIDResponse.jsonPath().getString("totalprice");
        //System.out.println(TotalPrice);
        DepositId = GetIDResponse.jsonPath().getBoolean("depositpaid");

        CheckIn = GetIDResponse.jsonPath().getString("bookingdates.checkin");
        CheckOut = GetIDResponse.jsonPath().getString("bookingdates.checkout");

        test.log(Status.PASS,"Response Status Code is: " + GetIDResponse.getStatusCode());;

    }
    @Test(priority = 12)
    void VerifyRequestIDFirstName() {
        test = extent.createTest("Verify the Booking of User ID:" + BookingID + " has First Name ");
        try {
            Assert.assertNotNull(FirstName);
            Assert.assertEquals(FirstName, "SmitH");
            test.log(Status.PASS, FirstName);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage()); // Mark test as failed

        }
    }
    @Test (priority = 17)
    void VerifyRequestIDLastName(){
        test = extent.createTest("Verify the Booking of User ID:" + BookingID + " has Last Name ");
        Assert.assertNotNull(LastName);
        System.out.println("Booking LastName is  : "+LastName);
        Assert.assertEquals(LastName,"lastName");
    }
    @Test(priority = 13)
    void VerifyTotalPrice(){
        test = extent.createTest("Verify the Booking of User ID:" + BookingID + " has Total Price ");
        Assert.assertNotNull(TotalPrice);
        System.out.println("Total Price is  : "+TotalPrice);
        if (TotalPrice.contains("102")) {

            System.out.println("price is displayed in round figure.");
        }else {
            System.out.println("Price is not according to request value.");
        }

    }
    @Test(priority = 14)
    void VerifyDepositId(){
        test = extent.createTest("Verify the Booking of User ID:" + BookingID + " has Deposit ID ");
        System.out.println("Booking DepositId is  : "+DepositId);

        Assert.assertTrue(DepositId,"Booking has been Done.");
    }
    @Test(priority = 15)
    void VerifyBookingCheckIn(){
        test = extent.createTest("Verify the Booking of User ID:" + BookingID + " has Booking Check IN ");
        try {
            Assert.assertNotNull(CheckIn);
            Assert.assertEquals(CheckIn, "2025-01-01");

            test.log(Status.PASS, "Booking Check-IN is correct: " + CheckIn);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage()); // Mark test as failed
        }

    }
    @Test (priority = 16)
    void VerifyBookingCheckOut(){
        test = extent.createTest("Verify the Booking of User ID:" + BookingID + " has Booking Check out ");
        Assert.assertNotNull(CheckOut);
        Assert.assertEquals(CheckOut,"2024-01-01");
    }

}
