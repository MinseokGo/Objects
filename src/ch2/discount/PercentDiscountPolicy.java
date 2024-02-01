package ch2.discount;

import ch2.Money;
import ch2.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {
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
