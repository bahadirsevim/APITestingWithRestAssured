package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Aşağıdaki anotation'lar sayesinde yapıcı metotları, get set metotlarını ve toString metodunu yazmamıza gerek kalmıyor.
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookingId {
    private int bookingid;
    private Booking booking;
}
