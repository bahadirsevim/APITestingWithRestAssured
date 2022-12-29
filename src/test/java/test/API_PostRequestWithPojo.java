package test;

import baseURLDeposu.HerokuappBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Booking;
import pojos.BookingDates;
import pojos.BookingId;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_PostRequestWithPojo extends HerokuappBaseURL {
    @Test
    public void test01(){
        specHerokuapp.pathParam("pp1","booking");
        BookingDates bookingdates = new BookingDates("2022-09-09", "2022-09-21");
        Booking booking = new Booking("Ahmet", "Bulut",15000,true,bookingdates);

        Response response = given().
                contentType(ContentType.JSON).
                spec(specHerokuapp).
                body(booking).
                when().
                post("{pp1}");

        BookingId respBody = response.as(BookingId.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(booking.getFirstname(), respBody.getBooking().getFirstname());
        assertEquals(booking.getLastname(), respBody.getBooking().getLastname());
        assertEquals(booking.getTotalprice(), respBody.getBooking().getTotalprice());
        assertEquals(booking.isDepositpaid(), respBody.getBooking().isDepositpaid());
        assertEquals(booking.getBookingdates().getCheckin(), respBody.getBooking().getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(), respBody.getBooking().getBookingdates().getCheckout());

    }
}
