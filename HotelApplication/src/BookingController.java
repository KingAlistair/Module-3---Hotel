import static java.time.temporal.ChronoUnit.DAYS;

public class BookingController {

    public int calculateLengthOfStay(Booking booking){
        return (int) DAYS.between(booking.getStartDate(), booking.getEndDate());

    }
}
