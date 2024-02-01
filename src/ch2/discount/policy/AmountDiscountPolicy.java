package ch2.discount.policy;

import ch2.Money;
import ch2.movie.Screening;
import ch2.discount.condition.DiscountCondition;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
    private final Money discountAmount;

    public AmountDiscountPolicy(final Money discountAmount, final DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(final Screening screening) {
        return discountAmount;
    }
}
