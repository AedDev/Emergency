package de.mdstv.emergency;

import java.io.Serializable;
import java.util.Date;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EmergencyType
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public String title = "";
  public int emergencyNumber = -1;
  public Player caller = null;
  public Location emergencyPoint = null;
  public Date time = null;
  public boolean isDone = false;
  public boolean isSupported = false;
  
  public static EmergencyType createEmergency(String reason, Player caller, Location emergencyPoint, Date time)
  {
    EmergencyType em = new EmergencyType();
    
    em.setTitle((reason == null) || (reason.isEmpty()) ? "No Reason" : reason);
    em.setCaller(caller);
    em.setEmergencyPoint(emergencyPoint == null ? caller.getLocation() : emergencyPoint);
    em.setTime(time == null ? new Date() : time);
    
    return em;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public Player getCaller()
  {
    return this.caller;
  }
  
  public void setCaller(Player caller)
  {
    this.caller = caller;
  }
  
  public Location getEmergencyPoint()
  {
    return this.emergencyPoint;
  }
  
  public void setEmergencyPoint(Location emergencyPoint)
  {
    this.emergencyPoint = emergencyPoint;
  }
  
  public Date getTime()
  {
    return this.time;
  }
  
  public void setTime(Date time)
  {
    this.time = time;
  }
  
  public int getEmergencyNumber()
  {
    return this.emergencyNumber;
  }
  
  public void setEmergencyNumber(int emergencyNumber)
  {
    this.emergencyNumber = emergencyNumber;
  }
  
  public boolean isDone()
  {
    return this.isDone;
  }
  
  public void setDone(boolean isDone)
  {
    this.isDone = isDone;
  }
  
  public boolean isSupported()
  {
    return this.isSupported;
  }
  
  public void setSupported(boolean isSupported)
  {
    this.isSupported = isSupported;
  }
}
