package de.mdstv.emergency2;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents a emergency ticket
 * @author Morph
 */
public class Ticket {
    private Player         reporter;
    private Player         assignee;
    private EmergencyLevel level;
    private Location       loc;
    private String         message;

    /**
     * Describes the level of emergency
     */
    public class EmergencyLevel {
        private int    levelPoints;
        private String levelName;

        /**
         * Creates a new {@link EmergencyLevel}
         * @param levelPoints The level points represents the priority
         * @param levelName The unique name for this level
         */
        public EmergencyLevel(int levelPoints, String levelName) {
            this.levelPoints = levelPoints;
            this.levelName = levelName;
        }

        /**
         * Returns the amount of level points for this {@link EmergencyLevel}
         * @return Amount of level points
         */
        public int getLevelPoints() {
            return levelPoints;
        }

        /**
         * Sets the amount of level points for this {@link EmergencyLevel}
         * @param levelPoints Amount of level points
         */
        public void setLevelPoints(int levelPoints) {
            this.levelPoints = levelPoints;
        }

        /**
         * Returns the name for this {@link EmergencyLevel}
         * @return Name for this {@link EmergencyLevel}
         */
        public String getLevelName() {
            return levelName;
        }

        /**
         * Sets the name for this {@link EmergencyLevel}
         * @param levelName Name for this {@link EmergencyLevel}
         */
        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }
    }
    
    /**
     * Creates a new {@link Ticket} based on given data
     * @param reporter The {@link Player} who reported the emergency
     * @param assignee The {@link Player} which got the ticket assigned
     * @param level The {@link EmergencyLevel} for this ticket
     * @param message The message for the {@link Ticket}
     * @return The generated {@link Ticket}
     */
    public static Ticket createTicket(Player reporter, Player assignee,
            EmergencyLevel level, String... message) {
        String messageText = StringUtils.join(message, " ");
        
        Ticket ticket   = new Ticket();
        ticket.reporter = reporter;
        ticket.assignee = assignee;
        ticket.level    = level;
        ticket.loc      = reporter.getLocation();
        ticket.message  = messageText;
        
        return ticket;
    }
}
