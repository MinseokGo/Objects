package ch2.discount.policy;

import ch2.Money;
import ch2.movie.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
