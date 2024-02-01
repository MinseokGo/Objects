package ch2.movie;

import ch2.customer.Customer;
import ch2.Money;
import ch2.customer.Reservation;
import java.time.LocalDateTime;

public class Screening {
    private final Movie movie;
    private final int sequence;
    private final LocalDateTime whenScreened;

    public Screening(final Movie movie, final int sequence, final LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Reservation reserve(final Customer customer, final int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(final int audienceCount) {
        return movie.calculateMovieFee(this)
                .times(audienceCount);
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(final int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }
}
