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
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

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
