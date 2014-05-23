package de.mdstv.emergency2;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents a emergency ticket
 *
 * @author Morph
 */
public class Ticket {

    private Player reporter;
    private Player assignee;
    private EmergencyLevel level;
    private Location loc;
    private String message;
    
    /**
     * Creates a new {@link Ticket} based on given data
     *
     * @param reporter The {@link Player} who reported the emergency
     * @param assignee The {@link Player} which got the ticket assigned
     * @param level The {@link EmergencyLevel} for this ticket
     * @param message The message for the {@link Ticket}
     * @return The generated {@link Ticket}
     */
    public static Ticket createTicket(Player reporter, Player assignee,
            EmergencyLevel level, String... message) {
        String messageText = StringUtils.join(message, " ");

        Ticket ticket = new Ticket();
        ticket.reporter = reporter;
        ticket.assignee = assignee;
        ticket.level = level;
        ticket.loc = reporter.getLocation();
        ticket.message = messageText;

        return ticket;
    }
}
