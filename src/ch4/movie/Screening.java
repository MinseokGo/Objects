package ch4.movie;

import ch4.Money;
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

    public Money calculateFee(final int audienceCount) {
        final MovieType movieType = movie.getMovieType();
        if (movieType == MovieType.AMOUNT_DISCOUNT) {
            if (movie.isDiscountable(whenScreened, sequence)) {
                return movie.calculateAmountDiscountedFee()
                        .times(audienceCount);
            }
        }
        if (movieType == MovieType.PERCENT_DISCOUNT) {
            if (movie.isDiscountable(whenScreened, sequence)) {
                return movie.calculatePercentDiscountedFee()
                        .times(audienceCount);
            }
        }

        //None Discount
        return movie.calculateNoneDiscountedFee()
                .times(audienceCount);
    }
}
