package ch2;

import ch2.discount.DefaultDiscountPolicy;
import ch2.discount.DiscountPolicy;
import java.time.Duration;

public class Movie {
    private final String title;
    private final Duration runningTime;
    private final Money fee;
    private final DiscountPolicy discountPolicy;

    public Movie(final String title, final Duration runningTime, final Money fee, final DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(final Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
