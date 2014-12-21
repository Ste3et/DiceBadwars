package me.Ste3et_C0st.DiceBedWars;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;

public class load
{
  public static config cc;
  public static FileConfiguration fc;
  

  
  public static void loadMap()
  {
	Main.debug("==================================");
	Main.debug("Dice main Map load hat begonnen");
	Main.debug("==================================");
    String name = "mapdata.yml";
    String folder = "/map";
	if(config.ExistMaps(folder) && config.isMaps(folder)){
		String[] ordner = new File("plugins/DiceBedwars" + folder).list();
		List<String> stringListe = new ArrayList<String>();
		for(String a : ordner){
			stringListe.add(a);
		}
		
		
		Collections.sort(stringListe);
		
		for(String map : stringListe){
		    cc = new config(null);
		    if(config.ExistMaps(folder + "/" + map)){
		    	fc = cc.getConfig(name, folder + "/" + map);
			    Main.debug("  Arena [" + map +  "] wird geladen");
			    String Aname = fc.getString("Arena.name");
			    Main.debug("  - Name: " + Aname);
			    String wn = fc.getString("Arena.world");
			    Main.debug("  - World Loadet");
			    Double x1 = Double.valueOf(fc.getDouble("Arena.corner1.x"));
			    Double y1 = Double.valueOf(fc.getDouble("Arena.corner1.y"));
			    Double z1 = Double.valueOf(fc.getDouble("Arena.corner1.z"));
			    Main.debug("  - Corner 1 Loadet");		    
			    Double x2 = Double.valueOf(fc.getDouble("Arena.corner2.x"));
			    Double y2 = Double.valueOf(fc.getDouble("Arena.corner2.y"));
			    Double z2 = Double.valueOf(fc.getDouble("Arena.corner2.z"));
			    Main.debug("  - Corner 2 Loadet");	
			    Double x5 = Double.valueOf(fc.getDouble("Arena.exit.x"));
			    Double y5 = Double.valueOf(fc.getDouble("Arena.exit.y"));
			    Double z5 = Double.valueOf(fc.getDouble("Arena.exit.z"));
			    Float ya5 = Float.valueOf((float) fc.getDouble("Arena.exit.yaw"));
			    Float pi5 = Float.valueOf((float) fc.getDouble("Arena.exit.pitch"));
			    String w5 = fc.getString("Arena.exit.world");
			    Double x6 = Double.valueOf(fc.getDouble("Arena.lobby.x"));
			    Double y6 = Double.valueOf(fc.getDouble("Arena.lobby.y"));
			    Double z6 = Double.valueOf(fc.getDouble("Arena.lobby.z"));
			    Float ya6 = Float.valueOf((float) fc.getDouble("Arena.lobby.yaw"));
			    Float pi6 = Float.valueOf((float) fc.getDouble("Arena.lobby.pitch"));
			    String lobbyWorld =  fc.getString("Arena.lobby.world");
			    String Builder =  fc.getString("Arena.builder");
			    Main.debug("  - Corner Exit Loadet");	

			    int mone = fc.getInt("Arena.money");
			    
			    World w1 = Bukkit.getWorld(wn);
			    World w2 = Bukkit.getWorld(w5);
			    World w4 = Bukkit.getWorld(lobbyWorld);
			    
			    Location corner1 = new Location(w1, x1, y1, z1);
			    Location corner2 = new Location(w1, x2, y2, z2);
			    Location lobby = new Location(w4, x6, y6, z6);
			    Location exit = new Location(w2, x5, y5, z5);
			    
			    lobby.setPitch(pi6);
			    exit.setPitch(pi5);
			    
			    lobby.setYaw(ya6);
			    exit.setYaw(ya5);
			    
			    ArenaManager.getManager().createArena(lobby, corner1, corner2, exit, Aname, Builder);
			    int AID = ArenaManager.getManager().getIDBackfromName(Aname);
			    Main.debug("  - Arena Registriert (" + AID + ")");	
			    ArenaManager.getManager().getArena(AID).setMoney(mone);
			    Main.debug("    - Money: " + mone);
			    
			    if(fc.isSet("Arena.team")){
			    	
			    	for(String s: fc.getConfigurationSection("Arena.team").getKeys(false)){
			    		Main.debug("  - Team " + s + " wird geladen");	
			    		String tName = fc.getString("Arena.team." + s + ".name");
			    		String tColor = fc.getString("Arena.team." + s + ".color");
			    		Double tx = fc.getDouble("Arena.team." + s + ".Location.x");
			    		Double ty = fc.getDouble("Arena.team." + s + ".Location.y");
			    		Double tz = fc.getDouble("Arena.team." + s + ".Location.z");
			    		Float tyaw = Float.valueOf((float) fc.getDouble("Arena.team." + s + ".Location.yaw"));
			    		Float tpitch = Float.valueOf((float) fc.getDouble("Arena.team." + s + ".Location.pitch"));
			    		
			    		Location tSpawn = new Location(w1, tx, ty, tz);
			    		tSpawn.setPitch(tpitch);
			    		tSpawn.setYaw(tyaw);
			    		
			    		Double bx = fc.getDouble("Arena.team." + s + ".Bed.x");
			    		Double by = fc.getDouble("Arena.team." + s + ".Bed.y");
			    		Double bz = fc.getDouble("Arena.team." + s + ".Bed.z");
			    		String bf = fc.getString("Arena.team." + s + ".Bed.facing");
			    		
			    		Location bed = new Location(w1, bx, by, bz);
			    		Block block = w1.getBlockAt(bed);
			    		BlockFace Bf = BlockFace.valueOf(bf);
			    		Team team = new Team(tColor, tName, tSpawn, block, Bf);
			    		team.setBed();
			    		ArenaManager.getManager().getArena(AID).getTeams().add(team);
			    		Main.debug("----------------------------");	
			    	}
			    }
			    
			    
			    
			    if(fc.isSet("Arena.spawner.gold")){
			    	Main.debug("     - Spawner Gold found: ");
			    	for(String s: fc.getConfigurationSection("Arena.spawner.gold").getKeys(false)){
			    		Main.debug("      - Spawner (" + s + ") found");
			    		Double x = fc.getDouble("Arena.spawner.gold." + s + ".x");
			    		Double y = fc.getDouble("Arena.spawner.gold." + s + ".y");
			    		Double z = fc.getDouble("Arena.spawner.gold." + s + ".z");
			    		Location loc = new Location(w1, x, y, z);
			    		ArenaManager.getManager().getArena(AID).addGold(loc);
			    	}
			    }
			    
			    if(fc.isSet("Arena.spawner.bronze")){
			    	Main.debug("     - Spawner Bronze found: ");
			    	for(String s: fc.getConfigurationSection("Arena.spawner.bronze").getKeys(false)){
			    		Main.debug("      - Spawner (" + s + ") found");
			    		Double x = fc.getDouble("Arena.spawner.bronze." + s + ".x");
			    		Double y = fc.getDouble("Arena.spawner.bronze." + s + ".y");
			    		Double z = fc.getDouble("Arena.spawner.bronze." + s + ".z");
			    		Location loc = new Location(w1, x, y, z);
			    		ArenaManager.getManager().getArena(AID).addBronze(loc);
			    		
			    	}
			    }
			    
			    if(fc.isSet("Arena.spawner.eisen")){
			    	Main.debug("     - Spawner Eisen found: ");
			    	for(String s: fc.getConfigurationSection("Arena.spawner.eisen").getKeys(false)){
			    		Main.debug("      - Spawner (" + s + ") found");
			    		Double x = fc.getDouble("Arena.spawner.eisen." + s + ".x");
			    		Double y = fc.getDouble("Arena.spawner.eisen." + s + ".y");
			    		Double z = fc.getDouble("Arena.spawner.eisen." + s + ".z");
			    		Location loc = new Location(w1, x, y, z);
			    		ArenaManager.getManager().getArena(AID).addEisen(loc);
			    	}
			    }
			    
			    if(fc.isSet("Arena.sign")){
			    	Main.debug("     - Signs found: ");
			    	int u = 0;
			    	for(String s: fc.getConfigurationSection("Arena.sign").getKeys(false)){
			    		Main.debug("      - Sign (" + u + ") found");
			    		Double x = fc.getDouble("Arena.sign." + s + ".x");
			    		Double y = fc.getDouble("Arena.sign." + s + ".y");
			    		Double z = fc.getDouble("Arena.sign." + s + ".z");
			    		String world = fc.getString("Arena.sign." + s + ".world");
			    		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			    		if(Bukkit.getWorld(world) != null){
			    			if(!loc.getBlock().isEmpty()){
				    			BlockState state = loc.getBlock().getState();
				    			if(state instanceof Sign){
						    		ArenaManager.getManager().getArena(AID).addSign(loc);
						    		ArenaManager.getManager().getArena(AID).updateSign(loc);
				    			}else{
				    				Main.debug("       - No Sign Found ");
				    			}
				    		}else{
				    			Main.debug("       - No Block Found ");
				    		}
			    		}else{
			    			Main.debug("       - World not found ");
			    		}
			    		
			    		u++;
			    	}
			    }else{
			    	Main.debug("     - No signs found");
			    }
			    fc = null;
			    cc = null;
			    	Main.debug("----------------------------------");
		    }
		    
		}
	}
	Main.debug("==================================");
  }
}
