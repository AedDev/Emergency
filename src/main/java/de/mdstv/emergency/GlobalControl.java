package de.mdstv.emergency;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GlobalControl
  implements Listener
{
  public Emergency plugin;
  
  public GlobalControl(Emergency instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    event.getPlayer().sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Emergency" + ChatColor.GRAY + "] If you need help, type " + ChatColor.GREEN + "/hlp");
  }
}
