package me.Ste3et_C0st.DiceBedWars;

import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class save {
	  public static config cc;
	  public static FileConfiguration fc;
	  
	  public static String getCharForNumber(int i) {
		    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		    if (i > 25) {
		        return null;
		    }
		    return Character.toString(alphabet[i]);
		} 
	  
	public static void saveMap(String Aname){
		 Main.debug("Dice main Map Save hat begonnen");
		    Main.debug("==================================");
				Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(Aname.toLowerCase()));
				String id = 0 + "";
				if(a.getId() < 10){
					id = "0" + a.getId() + "";
				}else{
					id = a.getId() + "";
				}
				
				Main.debug(" Arena [" + id + "]");
			    String name = "mapdata.yml";
			    
			    String folder = "/map/" + id + Aname.toLowerCase() + "";
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
			    		int m = 0;
			    		
			    		for(Team t : a.getTeams()){
			    			fc.set("Arena.team." + m + ".name", t.getName());
					    	fc.set("Arena.team." + m + ".color", t.getTeamColor());
					    	fc.set("Arena.team." + m + ".Location.x", t.getSpawn().getX());
					    	fc.set("Arena.team." + m + ".Location.y", t.getSpawn().getY());
					    	fc.set("Arena.team." + m + ".Location.z", t.getSpawn().getZ());
					    	fc.set("Arena.team." + m + ".Location.yaw", t.getSpawn().getYaw());
					    	fc.set("Arena.team." + m + ".Location.pitch", t.getSpawn().getPitch());
					    	
					    	fc.set("Arena.team." + m + ".Bed.x", t.getBed().getLocation().getX());
					    	fc.set("Arena.team." + m + ".Bed.y", t.getBed().getLocation().getY());
					    	fc.set("Arena.team." + m + ".Bed.z", t.getBed().getLocation().getZ());
					    	fc.set("Arena.team." + m + ".Bed.facing",  t.getFace().toString());
					    	m++;
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
