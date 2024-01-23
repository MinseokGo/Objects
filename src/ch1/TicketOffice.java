package ch1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    private Long amount;
    private final List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(final Long amount, final Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public void sellTicketTo(final Audience audience) {
        final Long ticketFee = audience.buy(getTicket());
        plusAmount(ticketFee);
    }

    private Ticket getTicket() {
        return tickets.remove(0);
    }

    private void plusAmount(final Long amount) {
        this.amount += amount;
    }
}
