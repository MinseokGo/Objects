package ch1;

public class Audience {
    private final Bag bag;

    public Audience(final Bag bag) {
        this.bag = bag;
    }

    public Long buy(final Ticket ticket) {
        return bag.hold(ticket);
    }
}
