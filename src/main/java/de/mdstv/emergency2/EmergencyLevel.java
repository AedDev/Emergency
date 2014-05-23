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

import org.bukkit.ChatColor;

/**
 * Describes the level of emergency
 */
public class EmergencyLevel {

    private int levelPoints;
    private String levelName;
    private ChatColor levelColor;

    private boolean isDefault = false;

    /**
     * Creates a new {@link EmergencyLevel}
     *
     * @param levelPoints The level points represents the priority
     * @param levelName The unique name for this level
     * @param isDefault Sets this {@link EmergencyLevel} as default
     * @param levelColor The color for this {@link EmergencyLevel}
     */
    public EmergencyLevel(int levelPoints, String levelName, boolean isDefault, ChatColor levelColor) {
        this.levelPoints = levelPoints;
        this.levelName = levelName;
        this.isDefault = isDefault;
        this.levelColor = levelColor;
    }

    /**
     * Creates a new {@link EmergencyLevel}
     */
    public EmergencyLevel() {
    }

    /**
     * Returns the amount of level points for this {@link EmergencyLevel}
     *
     * @return Amount of level points
     */
    public int getLevelPoints() {
        return levelPoints;
    }

    /**
     * Sets the amount of level points for this {@link EmergencyLevel}
     *
     * @param levelPoints Amount of level points
     */
    public void setLevelPoints(int levelPoints) {
        this.levelPoints = levelPoints;
    }

    /**
     * Returns the name for this {@link EmergencyLevel}
     *
     * @return Name for this {@link EmergencyLevel}
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Sets the name for this {@link EmergencyLevel}
     *
     * @param levelName Name for this {@link EmergencyLevel}
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Set to <code>true</code>, if these {@link EmergencyLevel} is the default
     * level
     *
     * @param isDefault <code>true</code> if this is default, <code>false</code>
     * if not
     */
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Returns <code>true</code> if this is the default {@link EmergencyLevel}
     * or <code>false</code> if not
     *
     * @return <code>true</code> if this {@link EmergencyLevel} is default
     * <code>false</code> if not
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Returns the {@link ChatColor} for this {@link EmergencyLevel}
     * @return {@link ChatColor} for this {@link EmergencyLevel}
     */
    public ChatColor getLevelColor() {
        return levelColor;
    }

    /**
     * Sets the {@link ChatColor} for this {@link EmergencyLevel}
     * @param levelColor {@link ChatColor} to use
     */
    public void setLevelColor(ChatColor levelColor) {
        this.levelColor = levelColor;
    }
}
