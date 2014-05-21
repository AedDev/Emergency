package de.mdstv.emergency2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Emergency Master Class
 *
 * @author Morph
 * @version 1.0.0.0
 */
public class Emergency extends JavaPlugin {
    private final HashMap<String, Ticket.EmergencyLevel> levels = new HashMap<>();

    private File emergencyTypeConfigFile;
    private FileConfiguration emergencyTypeConfig;
    
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
        
        // Now get the emtypes.yml file, which contains the emergency types
        this.emergencyTypeConfigFile = new File(this.getDataFolder(), "emtypes.yml");
        
        // If emtypes.yml isn't existing, create the default one.
        if (!this.emergencyTypeConfigFile.isFile()) {
            this.writeDefaultEmtypesConfig();
        }
        
        // Load the emtypes.yml configuration
        try {
            this.emergencyTypeConfig.load(this.emergencyTypeConfigFile);
            
            // At least, load the plugin.yml
        } catch (IOException ex) {
            this.getLogger().log(Level.SEVERE, "Could not load emtypes.yml configuration", ex);
        } catch (InvalidConfigurationException ex) {
            this.getLogger().log(Level.SEVERE, "emtypes.yml configuration is invalid", ex);
        }
    }
    
    /**
     * Writes the default configuration for {@link Ticket.EmergencyLevel}s
     */
    private void writeDefaultEmtypesConfig() {
        try {
                String resPath = "/de/mdstv/emergency2/res/emtypes.default.yml";
                InputStream emtypesInput = System.class.getResourceAsStream(resPath);
                FileOutputStream fos = new FileOutputStream(this.emergencyTypeConfigFile);
                
                byte[] writeBuffer = new byte[4096];
                while (emtypesInput.read(writeBuffer) > -1) {
                    fos.write(writeBuffer);
                }
                
                fos.flush();
                fos.close();
                
                emtypesInput.close();
            } catch (IOException e) {
                getLogger().log(Level.SEVERE, "Could not write default emtypes.yml", e);
            }
    }
}
