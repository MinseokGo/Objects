package ch4.reservation;

import ch4.Money;
import ch4.customer.Customer;
import ch4.discount.DiscountCondition;
import ch4.discount.DiscountConditionType;
import ch4.movie.Movie;
import ch4.movie.MovieType;
import ch4.movie.Screening;
import java.util.List;

public class ReservationAgency {
    public Reservation reserve(final Screening screening, final Customer customer, final int audienceCount) {
        Movie movie = screening.getMovie();

        // 할인 가능 여부 파악
        boolean discountable = false;
        final List<DiscountCondition> conditions = movie.getDiscountConditions();
        for (final DiscountCondition condition : conditions) {
            final DiscountConditionType discountConditionType = condition.getType();
            if (discountConditionType == DiscountConditionType.PERIOD) {
                discountable = screening.getWhenScreened()
                        .getDayOfWeek()
                        .equals(condition.getDayOfWeek()) &&
                        condition.getStartTime().isBefore(screening.getWhenScreened().toLocalTime()) &&
                        condition.getEndTime().isAfter(screening.getWhenScreened().toLocalTime());
            }
            if (discountConditionType == DiscountConditionType.SEQUENCE) {
                discountable = condition.getSequence() == screening.getSequence();
            }

            if (discountable) {
                break;
            }
        }

        // 할인 금액 계산 후 예매
        Money fee;
        if (discountable) {
            Money discountAmount = Money.ZERO;
            final MovieType movieType = movie.getMovieType();
            switch (movieType) {
                case AMOUNT_DISCOUNT -> discountAmount = movie.getDiscountAmount();
                case PERCENT_DISCOUNT -> discountAmount = movie.getFee().times(movie.getDiscountPercent());
            }

            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }

        return new Reservation(customer, screening, fee, audienceCount);
    }
}
