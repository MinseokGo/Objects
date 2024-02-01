package ch2.discount;

import ch2.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
