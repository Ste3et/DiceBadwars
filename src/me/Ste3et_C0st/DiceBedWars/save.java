package me.Ste3et_C0st.DiceBedWars;

import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class save {
	  public static config cc;
	  public static FileConfiguration fc;
	  
	public static void saveMap(String Aname){
		 Main.debug("Dice main Map Save hat begonnen");
		    Main.debug("==================================");
				Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(Aname.toLowerCase()));
				Main.debug(" Arena [" + a.getId() + "]");
			    String name = "mapdata.yml";
			    String folder = "/map/" + a.getId() + Aname.toLowerCase() + "";
			    cc = new config(null);
			    fc = cc.getConfig(name, folder);
			    
			    fc.set("Arena", "");
			    cc.saveConfig(name, fc, folder);
			    
			    cc = new config(null);
			    fc = cc.getConfig(name, folder);
			    fc.set("Arena.name", a.getName());
			    Main.debug("  - Name Saving");
			    fc.set("Arena.world", a.getCorner1().getWorld().getName());
			    Main.debug("  - World Name Saving");
			    fc.set("Arena.lobbyTimer", a.getRoundTimer());
			    Main.debug("  - Lobby Timer Saving");
			    fc.set("Arena.GameTimer", a.getGameTimer());
			    Main.debug("  - Game Timer Saving");
			    fc.set("Arena.money", a.getMoney());
			    Main.debug("  - Money Saving");
			    fc.set("Arena.builder", a.getBuilder());
			    Main.debug("  - Money Saving");
			    fc.set("Arena.corner1.x", a.getCorner1().getX());
			    fc.set("Arena.corner1.y", a.getCorner1().getY());
			    fc.set("Arena.corner1.z", a.getCorner1().getZ());
			    Main.debug("  - Corner 1 Saving");
			    fc.set("Arena.lobby.x", a.getLobby().getX());
			    fc.set("Arena.lobby.y", a.getLobby().getY());
			    fc.set("Arena.lobby.z", a.getLobby().getZ());
			    fc.set("Arena.lobby.yaw", a.getLobby().getYaw());
			    fc.set("Arena.lobby.pitch", a.getLobby().getPitch());
			    fc.set("Arena.lobby.world", a.getLobby().getWorld().getName());
			    Main.debug("  - Lobby Saving");
			    fc.set("Arena.corner2.x", a.getCorner2().getX());
			    fc.set("Arena.corner2.y", a.getCorner2().getY());
			    fc.set("Arena.corner2.z", a.getCorner2().getZ());
			    Main.debug("  - Corner 2 Saving");
			    fc.set("Arena.exit.x", a.getExit().getX());
			    fc.set("Arena.exit.y", a.getExit().getY());
			    fc.set("Arena.exit.z", a.getExit().getZ());
			    fc.set("Arena.exit.yaw", a.getExit().getYaw());
			    fc.set("Arena.exit.pitch", a.getExit().getPitch());
			    fc.set("Arena.exit.world", a.getExit().getWorld().getName());
			    Main.debug("  - Exit Saving");
			    		if(a.getColor(1) != null){
					    	fc.set("Arena.team.1.name", a.getName(1));
					    	fc.set("Arena.team.1.color", a.returnColor(1));
					    	fc.set("Arena.team.1.Location.x", a.getTeam(1).getX());
					    	fc.set("Arena.team.1.Location.y", a.getTeam(1).getY());
					    	fc.set("Arena.team.1.Location.z", a.getTeam(1).getZ());
					    	fc.set("Arena.team.1.Location.yaw", a.getTeam(1).getYaw());
					    	fc.set("Arena.team.1.Location.pitch", a.getTeam(1).getPitch());
					    	
					    	fc.set("Arena.team.1.Bed.x", a.getBed(1).getX());
					    	fc.set("Arena.team.1.Bed.y", a.getBed(1).getY());
					    	fc.set("Arena.team.1.Bed.z", a.getBed(1).getZ());
					    	fc.set("Arena.team.1.Bed.facing", a.getRotation(1).toString());
			    		}

				    	
			    		if(a.getColor(2) != null){
					    	fc.set("Arena.team.2.name", a.getName(2));
					    	fc.set("Arena.team.2.color", a.returnColor(2));
					    	fc.set("Arena.team.2.Location.x", a.getTeam(2).getX());
					    	fc.set("Arena.team.2.Location.y", a.getTeam(2).getY());
					    	fc.set("Arena.team.2.Location.z", a.getTeam(2).getZ());
					    	fc.set("Arena.team.2.Location.yaw", a.getTeam(2).getYaw());
					    	fc.set("Arena.team.2.Location.pitch", a.getTeam(2).getPitch());
					    	
					    	fc.set("Arena.team.2.Bed.x", a.getBed(2).getX());
					    	fc.set("Arena.team.2.Bed.y", a.getBed(2).getY());
					    	fc.set("Arena.team.2.Bed.z", a.getBed(2).getZ());
					    	fc.set("Arena.team.2.Bed.facing", a.getRotation(2).toString());
			    		}
			    		
			    		if(a.getColor(3) != null){
					    	fc.set("Arena.team.3.name", a.getName(3));
					    	fc.set("Arena.team.3.color", a.returnColor(3));
					    	fc.set("Arena.team.3.Location.x", a.getTeam(3).getX());
					    	fc.set("Arena.team.3.Location.y", a.getTeam(3).getY());
					    	fc.set("Arena.team.3.Location.z", a.getTeam(3).getZ());
					    	fc.set("Arena.team.3.Location.yaw", a.getTeam(3).getYaw());
					    	fc.set("Arena.team.3.Location.pitch", a.getTeam(3).getPitch());
					    	
					    	fc.set("Arena.team.3.Bed.x", a.getBed(3).getX());
					    	fc.set("Arena.team.3.Bed.y", a.getBed(3).getY());
					    	fc.set("Arena.team.3.Bed.z", a.getBed(3).getZ());
					    	fc.set("Arena.team.3.Bed.facing", a.getRotation(3).toString());
			    		}
			    		
			    		if(a.getColor(4) != null){
					    	fc.set("Arena.team.4.name", a.getName(4));
					    	fc.set("Arena.team.4.color", a.returnColor(4));
					    	fc.set("Arena.team.4.Location.x", a.getTeam(4).getX());
					    	fc.set("Arena.team.4.Location.y", a.getTeam(4).getY());
					    	fc.set("Arena.team.4.Location.z", a.getTeam(4).getZ());
					    	fc.set("Arena.team.4.Location.yaw", a.getTeam(4).getYaw());
					    	fc.set("Arena.team.4.Location.pitch", a.getTeam(4).getPitch());
					    	
					    	fc.set("Arena.team.4.Bed.x", a.getBed(4).getX());
					    	fc.set("Arena.team.4.Bed.y", a.getBed(4).getY());
					    	fc.set("Arena.team.4.Bed.z", a.getBed(4).getZ());
					    	fc.set("Arena.team.4.Bed.facing", a.getRotation(4).toString());
			    		}
			    		
			    		if(a.getColor(5) != null){
					    	fc.set("Arena.team.5.name", a.getName(5));
					    	fc.set("Arena.team.5.color", a.returnColor(5));
					    	fc.set("Arena.team.5.Location.x", a.getTeam(5).getX());
					    	fc.set("Arena.team.5.Location.y", a.getTeam(5).getY());
					    	fc.set("Arena.team.5.Location.z", a.getTeam(5).getZ());
					    	fc.set("Arena.team.5.Location.yaw", a.getTeam(5).getYaw());
					    	fc.set("Arena.team.5.Location.pitch", a.getTeam(5).getPitch());
					    	
					    	fc.set("Arena.team.5.Bed.x", a.getBed(5).getX());
					    	fc.set("Arena.team.5.Bed.y", a.getBed(5).getY());
					    	fc.set("Arena.team.5.Bed.z", a.getBed(5).getZ());
					    	fc.set("Arena.team.5.Bed.facing", a.getRotation(5).toString());
			    		}
			    		
			    		if(a.getColor(6) != null){
					    	fc.set("Arena.team.6.name", a.getName(6));
					    	fc.set("Arena.team.6.color", a.returnColor(6));
					    	fc.set("Arena.team.6.Location.x", a.getTeam(6).getX());
					    	fc.set("Arena.team.6.Location.y", a.getTeam(6).getY());
					    	fc.set("Arena.team.6.Location.z", a.getTeam(6).getZ());
					    	fc.set("Arena.team.6.Location.yaw", a.getTeam(6).getYaw());
					    	fc.set("Arena.team.6.Location.pitch", a.getTeam(6).getPitch());
					    	
					    	fc.set("Arena.team.6.Bed.x", a.getBed(6).getX());
					    	fc.set("Arena.team.6.Bed.y", a.getBed(6).getY());
					    	fc.set("Arena.team.6.Bed.z", a.getBed(6).getZ());
					    	fc.set("Arena.team.6.Bed.facing", a.getRotation(6).toString());
			    		}
			    		
			    		if(a.getColor(7) != null){
					    	fc.set("Arena.team.7.name", a.getName(7));
					    	fc.set("Arena.team.7.color", a.returnColor(7));
					    	fc.set("Arena.team.7.Location.x", a.getTeam(7).getX());
					    	fc.set("Arena.team.7.Location.y", a.getTeam(7).getY());
					    	fc.set("Arena.team.7.Location.z", a.getTeam(7).getZ());
					    	fc.set("Arena.team.7.Location.yaw", a.getTeam(7).getYaw());
					    	fc.set("Arena.team.7.Location.pitch", a.getTeam(7).getPitch());
					    	
					    	fc.set("Arena.team.7.Bed.x", a.getBed(7).getX());
					    	fc.set("Arena.team.7.Bed.y", a.getBed(7).getY());
					    	fc.set("Arena.team.7.Bed.z", a.getBed(7).getZ());
					    	fc.set("Arena.team.7.Bed.facing", a.getRotation(7).toString());
			    		}
			    		
			    		if(a.getColor(8) != null){
					    	fc.set("Arena.team.8.name", a.getName(8));
					    	fc.set("Arena.team.8.color", a.returnColor(8));
					    	fc.set("Arena.team.8.Location.x", a.getTeam(8).getX());
					    	fc.set("Arena.team.8.Location.y", a.getTeam(8).getY());
					    	fc.set("Arena.team.8.Location.z", a.getTeam(8).getZ());
					    	fc.set("Arena.team.8.Location.yaw", a.getTeam(8).getYaw());
					    	fc.set("Arena.team.8.Location.pitch", a.getTeam(8).getPitch());
					    	
					    	fc.set("Arena.team.8.Bed.x", a.getBed(8).getX());
					    	fc.set("Arena.team.8.Bed.y", a.getBed(8).getY());
					    	fc.set("Arena.team.8.Bed.z", a.getBed(8).getZ());
					    	fc.set("Arena.team.8.Bed.facing", a.getRotation(8).toString());
			    		}
			    
			    if(a.returnGold().size() > 0){
			    	Main.debug("   - Gold Spawner found");
			    	int u = 0;
			    	for(Location l : a.returnGold()){
			    		Main.debug("    - (" + u + ") Sign save");
			    		fc.set("Arena.spawner.gold." + u + ".x", l.getX());
			    		fc.set("Arena.spawner.gold." + u + ".y", l.getY());
			    		fc.set("Arena.spawner.gold." + u + ".z", l.getZ());
			    		fc.set("Arena.spawner.gold." + u + ".world", l.getWorld().getName());
			    		u++;
			    	}
			    }
			    
			    if(a.returnBronze().size() > 0){
			    	Main.debug("   - Bronze Spawner found");
			    	int u = 0;
			    	for(Location l : a.returnBronze()){
			    		Main.debug("    - (" + u + ") Sign save");
			    		fc.set("Arena.spawner.bronze." + u + ".x", l.getX());
			    		fc.set("Arena.spawner.bronze." + u + ".y", l.getY());
			    		fc.set("Arena.spawner.bronze." + u + ".z", l.getZ());
			    		fc.set("Arena.spawner.bronze." + u + ".world", l.getWorld().getName());
			    		u++;
			    	}
			    }
			    
			    if(a.returnEisen().size() > 0){
			    	Main.debug("   - Eisen Spawner found");
			    	int u = 0;
			    	for(Location l : a.returnEisen()){
			    		Main.debug("    - (" + u + ") Sign save");
			    		fc.set("Arena.spawner.eisen." + u + ".x", l.getX());
			    		fc.set("Arena.spawner.eisen." + u + ".y", l.getY());
			    		fc.set("Arena.spawner.eisen." + u + ".z", l.getZ());
			    		fc.set("Arena.spawner.eisen." + u + ".world", l.getWorld().getName());
			    		u++;
			    	}
			    }
			    
			    if(a.getSign().size() > 0){
			    	Main.debug("   - Signs found");
			    	int u = 0;
			    	for(Location l : a.getSign()){
				    		Main.debug("    - (" + u + ") Sign save");
				    		fc.set("Arena.sign." + u + ".x", l.getX());
				    		fc.set("Arena.sign." + u + ".y", l.getY());
				    		fc.set("Arena.sign." + u + ".z", l.getZ());
				    		fc.set("Arena.sign." + u + ".world", l.getWorld().getName());
			    		u++;
			    	}
			    }
			    cc.saveConfig(name, fc, folder);
			    Main.debug("----------------------------------");
		    Main.debug("==================================");
	}
}
