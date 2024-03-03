package ch4.movie;

import ch4.discount.DiscountCondition;
import ch4.Money;
import ch4.discount.DiscountConditionType;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Movie {
    private final String title;
    private final Duration runningTime;
    private final Money fee;
    private final List<DiscountCondition> discountConditions;
    private final MovieType movieType;
    private final Money discountAmount;
    private final double discountPercent;

    public Movie(final String title, final Duration runningTime, final Money fee,
                 final List<DiscountCondition> discountConditions,
                 final MovieType movieType, final Money discountAmount, final double discountPercent) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = discountConditions;
        this.movieType = movieType;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
    }

    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        final Money discountAmount = fee.times(discountPercent);
        return fee.minus(discountAmount);
    }

    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee;
    }

    public boolean isDiscountable(final LocalDateTime whenScreened, final int sequence) {
        for (DiscountCondition discountCondition : discountConditions) {
            final DiscountConditionType discountConditionType = discountCondition.getType();
            if (discountConditionType == DiscountConditionType.PERIOD) {
                final DayOfWeek dayOfWeek = whenScreened.getDayOfWeek();
                final LocalTime time = whenScreened.toLocalTime();
                if (discountCondition.isDiscountable(dayOfWeek, time)) {
                    return true;
                }
            }
            if (discountConditionType == DiscountConditionType.SEQUENCE) {
                if (discountCondition.isDiscountable(sequence)) {
                    return true;
                }
                //return discountCondition.isDiscountable(sequence);
            }
        }

        return false;
    }

    public MovieType getMovieType() {
        return movieType;
    }
}
