package de.mdstv.emergency;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Emergency
  extends JavaPlugin
{
  public final GlobalControl gcListener = new GlobalControl(this);
  public static ArrayList<EmergencyType> emergencies = new ArrayList();
  
  @Override
  public void onDisable()
  {
    super.onDisable();
    getLogger().info("[EMERGENCY] Emergency Plugin disabled!");
  }
  
  @Override
  public void onEnable()
  {
    super.onEnable();
    
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new GlobalControl(this), this);
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender instanceof Player))
    {
      if (command.getName().equalsIgnoreCase("hlp"))
      {
        if (sender.hasPermission("emergency.hlp"))
        {
          if (args.length > 0)
          {
            String reason = "";
            for (String s : args) {
              reason = reason + s + " ";
            }
            sendEmergency((Player)sender, reason.trim());
          }
          else
          {
            sendEmergency((Player)sender, null);
            return true;
          }
        }
        else {
          sender.sendMessage(ChatColor.DARK_RED + "Sorry, you don't have the permission to perform this command");
        }
      }
      else
      {
        if ((command.getName().equalsIgnoreCase("tpem")) && (sender.isPermissionSet("emergency.tpem")))
        {
          if (args.length < 1)
          {
            sender.sendMessage("Please enter a mission number like this: /tpem <mission_number>");
            return false;
          }
          if (args.length > 1)
          {
            sender.sendMessage("Please enter a mission number like this: /tpem <mission_number>");
            return false;
          }
          int emnr = Integer.parseInt(args[0]);
          EmergencyType et = getEmergencyByNumber(emnr);
          if (et == null)
          {
            sender.sendMessage(ChatColor.DARK_RED + "Mission with ID " + String.valueOf(emnr) + " not found!");
            return false;
          }
          ((Player)sender).teleport(et.getEmergencyPoint());
          et.setSupported(true);
          
          return true;
        }
        if ((command.getName().equalsIgnoreCase("em")) && (sender.hasPermission("emergency.em")))
        {
          if (args.length > 0)
          {
            if (args[0].equalsIgnoreCase("done"))
            {
              if (args.length < 2) {
                return false;
              }
              int emnr = Integer.parseInt(args[1]);
              EmergencyType et = getEmergencyByNumber(emnr);
              if (et == null)
              {
                sender.sendMessage(ChatColor.DARK_RED + "Mission with ID " + String.valueOf(emnr) + " not found!");
                return true;
              }
              et.setDone(true);
              sender.sendMessage(ChatColor.DARK_GREEN + "Mission #" + et.getEmergencyNumber() + " done!");
              emergencies.remove(et);
              
              return true;
            }
            if (args[0].equalsIgnoreCase("list"))
            {
              if (emergencies.size() < 1)
              {
                sender.sendMessage(ChatColor.DARK_GREEN + "No emergencies in list!");
                return true;
              }
              for (EmergencyType emt : emergencies)
              {
                SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd.MM.yy");
                String etdate = date.format(emt.getTime());
                
                sender.sendMessage(ChatColor.RED + "#" + emt.getEmergencyNumber() + ChatColor.GRAY + " (" + ChatColor.AQUA + emt.getCaller().getName() + ChatColor.GRAY + ") [" + ChatColor.GOLD + etdate + ChatColor.GRAY + "] [" + (emt.isSupported() ? ChatColor.DARK_GREEN + "S" : new StringBuilder().append(ChatColor.DARK_RED).append("NS").toString()) + ChatColor.GRAY + "]" + " -> " + ChatColor.WHITE + emt.getTitle());
              }
              return true;
            }
            if (args[0].equalsIgnoreCase("info"))
            {
              sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "EMERGENCY PLUGIN" + ChatColor.GRAY + "]");
              sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.DARK_AQUA + (String)getDescription().getAuthors().get(0));
              sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.DARK_AQUA + getDescription().getVersion());
              
              return true;
            }
            if (args[0].equalsIgnoreCase("clear"))
            {
              emergencies.clear();
              sender.sendMessage(ChatColor.DARK_GREEN + "Mission List cleared!");
              return true;
            }
            sender.sendMessage(ChatColor.DARK_RED + "Illegal command!");
            return true;
          }
          return false;
        }
      }
      return false;
    }
    return false;
  }
  
  public void sendEmergency(Player sender, String reason)
  {
    Player[] onlinePlayers = getServer().getOnlinePlayers();
    int emnr = getNextEmergencyID();
    
    int opcnt = 0;
    for (Player p : onlinePlayers) {
      if (p.hasPermission("emergency.receive"))
      {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "EMERGENCY" + ChatColor.GRAY + "]");
        p.sendMessage(ChatColor.GRAY + "From: " + ChatColor.AQUA + sender.getDisplayName());
        p.sendMessage(ChatColor.GRAY + "MissionID: " + ChatColor.AQUA + emnr);
        p.sendMessage(ChatColor.GRAY + "Reason: " + ChatColor.AQUA + ((reason == null) || (reason.isEmpty()) ? "-- NONE --" : reason));
        
        opcnt++;
      }
    }
    EmergencyType et = EmergencyType.createEmergency(reason, sender, sender.getLocation(), new Date());
    et.setEmergencyNumber(emnr);
    emergencies.add(et);
    
    sender.sendMessage(ChatColor.RED + "Your Emergency call was sent to " + ChatColor.BLUE + String.valueOf(opcnt) + ChatColor.RED + " receivers!");
  }
  
  public EmergencyType getEmergencyByNumber(int emnr)
  {
    for (EmergencyType et : emergencies) {
      if (et.getEmergencyNumber() == emnr) {
        return et;
      }
    }
    return null;
  }
  
  public int getNextEmergencyID()
  {
    if (emergencies.size() < 1) {
      return 1;
    }
    return ((EmergencyType)emergencies.get(emergencies.size() - 1)).getEmergencyNumber() + 1;
  }
  
  public static boolean hasEmergency(String playerName)
  {
    for (EmergencyType et : emergencies) {
      if (et.getCaller().getName().equalsIgnoreCase(playerName)) {
        return true;
      }
    }
    return false;
  }
}
