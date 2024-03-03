package ch4.reservation;

import ch4.Money;
import ch4.customer.Customer;
import ch4.movie.Screening;

public class ReservationAgency {
    public Reservation reserve(final Screening screening, final Customer customer, final int audienceCount) {
        final Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}
