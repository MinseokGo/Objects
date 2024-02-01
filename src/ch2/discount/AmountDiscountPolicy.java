package ch2.discount;

import ch2.Money;
import ch2.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
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
