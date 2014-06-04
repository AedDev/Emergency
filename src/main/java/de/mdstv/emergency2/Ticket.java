/*
 * Copyright (C) 2014 Morph
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
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
