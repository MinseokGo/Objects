package ch2.discount.policy;

import ch2.Money;
import ch2.Screening;
import ch2.discount.condition.DiscountCondition;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {
    private final double percent;

    public PercentDiscountPolicy(final double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(final Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
