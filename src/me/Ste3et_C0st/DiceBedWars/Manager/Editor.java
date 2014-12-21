package me.Ste3et_C0st.DiceBedWars.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.save;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;

public class Editor {
	public static HashMap<String, ArrayList<Location>> gold = new HashMap<String, ArrayList<Location>>();
	public static HashMap<String, ArrayList<Location>> eisen = new HashMap<String, ArrayList<Location>>();
	public static HashMap<String, ArrayList<Location>> bronze = new HashMap<String, ArrayList<Location>>();
	static HashMap<String, Location> exit = new HashMap<String, Location>();
	static HashMap<String, Location> c1 = new HashMap<String, Location>();
	static HashMap<String, Location> c2 = new HashMap<String, Location>();
	static HashMap<String, Location> lobby = new HashMap<String, Location>();
	static HashMap<String, Location> l = new HashMap<String, Location>();
	static HashMap<String, String> bu = new HashMap<String, String>();
	
	static HashMap<String, Integer> size = new HashMap<String, Integer>();
	
	public static HashMap<Player, String> player = new HashMap<Player, String>();
	
	
	public static void enter(String string, Player p, String s, Integer si) {
		if(ArenaManager.getManager().ArenaExist(string)){
			Messages.sendMessage(p, "Diese Arena Existiert bereits", true);
			return;
		}else{
			if(!isInEditor(p)){
				size.put(string, si);
				enterEdit(p, string);
				Messages.sendMessage(p, "Arena Editor Modus §bbetreten", true);
				bu.put(string, s);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void enterEdit(Player p, String s) {
		if(!isInEditor(p)){
			p.getInventory().clear();
			p.updateInventory();
			List<String> select = new ArrayList<String>();
			select.add("§cCorner (1):");
			select.add(" §cWorld: N/A");
			select.add(" §cX: N/A");
			select.add(" §cY: N/A");
			select.add(" §cZ: N/A");
			select.add("§bCorner (2):");
			select.add(" §bWorld: N/A");
			select.add(" §bX: N/A");
			select.add(" §bY: N/A");
			select.add(" §bZ: N/A");
			p.getInventory().setItem(0, is(Material.NAME_TAG, Main.head + "Slector", select, 0, 1));
			p.getInventory().setItem(8, is(Material.BOWL, Main.head + "Exit Select Mode", null, 0, 1));
			p.getInventory().setItem(7, is(Material.DIAMOND, Main.head + "Finish Map", null, 0, 1));
			
			p.getInventory().setItem(2, is(Material.MOB_SPAWNER, Main.head + "Spawner: Bronze", null, 0, 1));
			p.getInventory().setItem(3, is(Material.DIAMOND_SWORD, Main.head + "Set Lobby", null, 0, 1));
			p.getInventory().setItem(5, is(Material.SKULL_ITEM, Main.head + "Set Villager", null, 3, 1));
			p.updateInventory();
			player.put(p, s);
			TeamCreate.enterTeam(p, s);
		}else{
			return;
		}
	}
	
	public static  Integer getSize(String s) {
		return size.get(s);
	}
	
	@SuppressWarnings("deprecation")
	public static void exit(Player p) {
		p.getInventory().clear();
		p.updateInventory();
		String a = getEditorName(p);

		c1.remove(a);
		c2.remove(a);
		exit.remove(a);
		l.remove(a);
		lobby.remove(a);
		player.remove(p);
		Messages.sendMessage(p, "Arena Modus wurde verlassen", true);
	}
	
	public static String getEditorName(Player p) {
		if(isInEditor(p)){
			return player.get(p);
		}
		return null;
	}

	public static boolean debug(Player p){
		if(isInEditor(p)){
			String a = getEditorName(p);
			if(getc1(a) == null || getc2(a) == null ||
			   getExit(a) == null || getlobby(a) == null || checkTeams(a, p) == false){
				Messages.sendMessage(p, "§8=====================", false);
				Messages.sendMessage(p, "§7Arena Debug", false);
				Messages.sendMessage(p, "§8=====================", false);
				Messages.sendMessage(p, " §7Arena Name: §2" + player.get(p), false);
				
				if(getlobby(a) == null){
					Messages.sendMessage(p, " §7Arena Lobby: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Lobby: §2Exist", false);
				}
				
				if(getExit(a) == null){
					Messages.sendMessage(p, " §7Arena Exit: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Exit: §2Exist", false);
				}
				
				if(getc1(a) == null){
					Messages.sendMessage(p, " §7Arena Corner 1: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Corner 1: §2Exist", false);
				}
				
				if(getc2(a) == null){
					Messages.sendMessage(p, " §7Arena Corner 2: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Corner 2: §2Exist", false);
				}
				
				if(eisen.get(a) == null || eisen.get(a).isEmpty()){
					Messages.sendMessage(p, " §7Arena Eisen Spawner: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Eisen Spawner: §2Exist", false);
				}
				
				if(gold.get(a) == null || gold.get(a).isEmpty()){
					Messages.sendMessage(p, " §7Arena Gold Spawner: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Gold Spawner: §2Exist", false);
				}
				
				if(bronze.get(a) == null || bronze.get(a).isEmpty()){
					Messages.sendMessage(p, " §7Arena Bronze Spawner: §cMissing", false);
				}else{
					Messages.sendMessage(p, " §7Arena Bronze Spawner: §2Exist", false);
				}
				
				
				for(int i = 0; i <= size.get(a); i++){
					if(TeamCreate.teams.get(p).get(i) != null){
						Messages.sendMessage(p, " §7Team (" + i +"): §2Exist", false);
						if(TeamCreate.teams.get(p).get(i).check()){
							Messages.sendMessage(p, " §7Team Bed (" + i +"): §2Valid", false);
						}else{
							Messages.sendMessage(p, " §7Team Bed (" + i +"): §cERROR", false);
						}
						
					}else{
						Messages.sendMessage(p, " §7Team (" + i +"): §cMissing", false);
					}
				}
				
				Messages.sendMessage(p, "§8=====================", false);
				return false;
			}else{
				Messages.sendMessage(p, "§8=====================", false);
				Messages.sendMessage(p, "§7Arena Debug", false);
				Messages.sendMessage(p, "§8---------------------", false);
				Messages.sendMessage(p, "§2- ALLES OK !", false);
				Messages.sendMessage(p, "§8=====================", false);
				return true;
			}
		}else{
			Messages.sendMessage(p, "Du befindest dich nicht im Editor Modus", true);
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	public static boolean checkTeams(String a, Player p){
		for(int i = 0; i <= size.get(a); i++){
			if(TeamCreate.teams.get(p).get(i) != null){
				if(!TeamCreate.teams.get(p).get(i).check()){
					return false;
				}else{
					return true;
				}
			}else{
				return false;
			}
		}
		return false;
	}
	
	public static void create(Player p){
		if(!isInEditor(p)){
			Messages.sendMessage(p, "Du bist nicht im Editor Modus", true);
		}
		
		if(debug(p) == false){
			return;
		}

		String a = player.get(p);
		
		if(ArenaManager.getManager().ArenaExist(a)){
			Messages.sendMessage(p, "Arena Existiert bereits", true);
			return;
		}

		ArenaManager.getManager().createArena(getlobby(a), getc1(a), getc2(a), getExit(a), a, getBuilder(a));
		int i = ArenaManager.getManager().getIDBackfromName(player.get(p));
		Arena arena = ArenaManager.getManager().getArena(i);

		arena.Gold = gold.get(arena.getName());
		arena.Bronze = bronze.get(arena.getName());
		arena.Eisen = eisen.get(arena.getName());
		ArrayList<Location> g = gold.get(player.get(p));
		for(Location l : g){
			l.getBlock().setType(Material.AIR);
		}
		
		ArrayList<Location> b = bronze.get(player.get(p));
		for(Location l : b){
			l.getBlock().setType(Material.AIR);
		}
		
		ArrayList<Location> e = eisen.get(player.get(p));
		for(Location l : e){
			l.getBlock().setType(Material.AIR);
		}
		
		for(int u = 0; u <= getSize(getEditorName(p)); u++){
			if(TeamCreate.getTeam(u, p) != null){
				if(TeamCreate.getTeam(u, p).getSpawn() != null){
					arena.Teams.add(TeamCreate.getTeam(u, p));
				}
			}
		}
		
		Messages.sendMessage(p, "§2Arena wurde erstellt", true);
		
		save.saveMap(player.get(p));
	}
	
	private static String getBuilder(String a) {
		return bu.get(a);
	}

	public static BlockFace getRotation(Player p, Block b){
		if(!isInEditor(p)){
			Messages.sendMessage(p, "Du bist nicht im Editor Modus", true);
		}
		
		String a = player.get(p);
		
		if(ArenaManager.getManager().ArenaExist(a)){
			Messages.sendMessage(p, "Arena Existiert bereits", true);
			return null;
		}
		
		Bed b1 = (Bed) b.getState().getData();
		return b1.getFacing().getOppositeFace();
	}
	
	public static Location getLoc(BlockFace b, Location l){
		if(b.equals(BlockFace.NORTH)){
			l.setX(l.getX() + 1);
			return l;
		}
		
		if(b.equals(BlockFace.EAST)){
			l.setZ(l.getZ() + 1);
			return l;
		}
		
		if(b.equals(BlockFace.SOUTH)){
			l.setX(l.getX() - 1);
			return l;
		}
		
		if(b.equals(BlockFace.WEST)){
			l.setZ(l.getZ() - 1);
			return l;
		}
		
		return l;
	}
	
	public static boolean isInEditor(Player p) {
		if(player.get(p) != null){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static Location getExit(String s){
		if(exit.get(s) != null){
			return exit.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getc1(String s){
		if(c1.get(s) != null){
			return c1.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getc2(String s){
		if(c2.get(s) != null){
			return c2.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getlobby(String s){
		if(lobby.get(s) != null){
			return lobby.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getl(String s){
		if(l.get(s) != null){
			return l.get(s);
		}else{
			return null;
		}
	}
	
	public static void setExit(Location l, String s){
		exit.put(s, l);
	}
	
	public static void setc1(Location l, String s){
		c1.put(s, l);
	}
	
	public static void setc2(Location l, String s){
		c2.put(s, l);
	}
	
	public static void setlobby(Location l, String s){
		lobby.put(s, l);
	}
	
	public static void setl(Location a, String s){
		l.put(s, a);
	}
	
	public static ItemStack is(Material m, String s, List<String> lore, int dur, int amount){
		ItemStack i = new ItemStack(m);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(s);
		if(lore != null){
			meta.setLore(lore);
		}
		
		i.setItemMeta(meta);
		i.setDurability((short) dur);
		i.setAmount(amount);
		return i;
	}
}
