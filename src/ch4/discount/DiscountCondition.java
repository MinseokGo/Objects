package ch4.discount;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private final DiscountConditionType type;
    private final int sequence;
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public DiscountCondition(final DiscountConditionType type, final int sequence, final DayOfWeek dayOfWeek,
                             final LocalTime startTime,
                             final LocalTime endTime) {
        this.type = type;
        this.sequence = sequence;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isDiscountable(final DayOfWeek dayOfWeek, final LocalTime time) {
        if (type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }

        return this.dayOfWeek.equals(dayOfWeek) &&
                this.startTime.isBefore(time) &&
                this.endTime.isAfter(time);
    }

    public boolean isDiscountable(final int sequence) {
        if (type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }

        return this.sequence == sequence;
    }

    public DiscountConditionType getType() {
        return type;
    }
}
