package ch1;

public class Theater {
    private final TicketSeller ticketSeller;

    public Theater(final TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(final Audience audience) {
        final Bag audienceBag = audience.getBag();
        final TicketOffice ticketOffice = ticketSeller.getTicketOffice();
        if (audienceBag.hasInvitation()) {
            final Ticket ticket = ticketOffice.getTicket();
            audienceBag.setTicket(ticket);
        }
        if (!audienceBag.hasInvitation()) {
            final Ticket ticket = ticketOffice.getTicket();
            final Long ticketFee = ticket.getFee();
            audienceBag.minusAmount(ticketFee);
            ticketOffice.plusAmount(ticketFee);
            audienceBag.setTicket(ticket);
        }
    }
}
