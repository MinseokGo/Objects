package ch1;

public class Bag {
    private Long amount;
    private final Invitation invitation;
    private Ticket ticket;

    public Bag(final Long amount) {
        this(null, amount);
    }

    public Bag(final Invitation invitation, final Long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    public Long hold(final Ticket ticket) {
        setTicket(ticket);
        if (hasInvitation()) {
            return 0L;
        } else {
            final Long ticketFee = ticket.getFee();
            minusAmount(ticketFee);
            return ticketFee;
        }
    }

    private boolean hasInvitation() {
        return invitation != null;
    }

    private void setTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    private void minusAmount(final Long amount) {
        this.amount -= amount;
    }
}
