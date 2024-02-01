package ch2.discount;

import ch2.Money;
import ch2.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(final Screening screening) {
        return Money.ZERO;
    }
}
