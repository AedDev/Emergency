package de.mdstv.emergency2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Emergency Master Class
 *
 * @author Morph
 * @version 1.0.0.0
 */
public class Emergency extends JavaPlugin {

    private File emergencyLevelsConfigFile;
    private YamlConfiguration emLevelsConfig;

    private final ArrayList<EmergencyLevel> levelList = new ArrayList<>();

    @Override
    public void onEnable() {
        super.onEnable();

        this.initializeConfiguration();
    }

    /**
     * Initializes the entire Emergeny2 Plugin configuration
     */
    private void initializeConfiguration() {
        // If there is still no datafolder, we're going to create one.
        if (!this.getDataFolder().isDirectory()) {
            this.getDataFolder().mkdirs();
        }

        // Now get the emlevels.yml file, which contains the emergency levels
        this.emergencyLevelsConfigFile = new File(this.getDataFolder(), "emlevels.yml");

        // If emlevels.yml isn't existing, create the default one.
        if (!this.emergencyLevelsConfigFile.isFile()) {
            this.writeDefaultEmergencyLevelsConfig();
        }
        
        // Load the emlevels.yml configuration
        this.loadEmergencyLevelsConfig();
    }

    /**
     * Writes the default configuration for {@link Ticket.EmergencyLevel}s
     */
    private void writeDefaultEmergencyLevelsConfig() {
        try {
            String resPath = "/de/mdstv/emergency2/res/emlevels.default.yml";
            InputStream emLevelsInput = System.class.getResourceAsStream(resPath);
            FileOutputStream fos = new FileOutputStream(this.emergencyLevelsConfigFile);

            byte[] writeBuffer = new byte[4096];
            while (emLevelsInput.read(writeBuffer) > -1) {
                fos.write(writeBuffer);
            }

            fos.flush();
            fos.close();

            emLevelsInput.close();
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not write default emlevels.yml", e);
        }
    }
    
    /**
     * Loads the Emergency Levels
     */
    private void loadEmergencyLevelsConfig() {
        this.emLevelsConfig = YamlConfiguration.loadConfiguration(this.emergencyLevelsConfigFile);

        // Load emerhency levels
        ConfigurationSection emSec = this.emLevelsConfig.getConfigurationSection("EmergencyLevels");
        Set<String> levels = emSec.getKeys(false);

        Iterator<String> typeIt = levels.iterator();
        while (typeIt.hasNext()) {
            // Load values for emergency level
            String levelName    = typeIt.next();
            Integer levelPoints = emSec.getInt(levelName + ".Value", 0);
            String  levelColor  = emSec.getString(levelName + ".Color", "F"); // Default: ChatColor.WHITE
            Boolean isDefault   = emSec.getBoolean(levelName + ".Default", false);

            // Check if Color value is valid
            if (levelColor.length() > 1) {
                getLogger().log(Level.SEVERE, "Invalid color value for Emergency Level {0}", levelName);
            }

            // Create Emergency Level
            EmergencyLevel level = new EmergencyLevel();
            level.setLevelName(levelName);
            level.setLevelPoints(levelPoints);
            level.setIsDefault(isDefault);
            level.setLevelColor(ChatColor.getByChar(levelColor.charAt(0)));

            // Add the new level to global list
            this.levelList.add(level);
        }

        this.emLevelsConfig.getConfigurationSection("");
    }
    
    /**
     * Returns the list of {@link EmergencyLevel}s
     * @return All {@link EmergencyLevel}s
     */
    public ArrayList<EmergencyLevel> getEmergencyLevels() {
        return this.levelList;
    }
}
