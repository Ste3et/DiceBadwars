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
import org.bukkit.block.BlockState;
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
			Team.enterTeam(p, s);
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
			   getExit(a) == null || getlobby(a) == null){
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
				
				
				if(size.get(a) == 2){
					if(Team.team1.get(a) == null || Team.team1.get(a) == false){
						Messages.sendMessage(p, " §7Team 1: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 1: §2OK", false);
					}
					
					if(Team.team2.get(a) == null || Team.team2.get(a) == false){
						Messages.sendMessage(p, " §7Team 2: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 2: §2OK", false);
					}
				}else if(size.get(a) == 4){
					if(Team.team1.get(a) == null || Team.team1.get(a) == false){
						Messages.sendMessage(p, " §7Team 1: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 1: §2OK", false);
					}
					
					if(Team.team2.get(a) == null || Team.team2.get(a) == false){
						Messages.sendMessage(p, " §7Team 2: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 2: §2OK", false);
					}
					
					if(Team.team3.get(a) == null || Team.team3.get(a) == false){
						Messages.sendMessage(p, " §7Team 3: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 3: §2OK", false);
					}
					
					if(Team.team4.get(a) == null || Team.team4.get(a) == false){
						Messages.sendMessage(p, " §7Team 4: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 4: §2OK", false);
					}
				}else if(size.get(a) == 6){
					if(Team.team1.get(a) == null || Team.team1.get(a) == false){
						Messages.sendMessage(p, " §7Team 1: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 1: §2OK", false);
					}
					
					if(Team.team2.get(a) == null || Team.team2.get(a) == false){
						Messages.sendMessage(p, " §7Team 2: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 2: §2OK", false);
					}
					
					if(Team.team3.get(a) == null || Team.team3.get(a) == false){
						Messages.sendMessage(p, " §7Team 3: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 3: §2OK", false);
					}
					
					if(Team.team4.get(a) == null || Team.team4.get(a) == false){
						Messages.sendMessage(p, " §7Team 4: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 4: §2OK", false);
					}
					
					if(Team.team5.get(a) == null || Team.team5.get(a) == false){
						Messages.sendMessage(p, " §7Team 5: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 5: §2OK", false);
					}
					
					if(Team.team6.get(a) == null || Team.team6.get(a) == false){
						Messages.sendMessage(p, " §7Team 6: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 6: §2OK", false);
					}
				}else if(size.get(a) == 8){
					if(Team.team1.get(a) == null || Team.team1.get(a) == false){
						Messages.sendMessage(p, " §7Team 1: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 1: §2OK", false);
					}
					
					if(Team.team2.get(a) == null || Team.team2.get(a) == false){
						Messages.sendMessage(p, " §7Team 2: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 2: §2OK", false);
					}
					
					if(Team.team3.get(a) == null || Team.team3.get(a) == false){
						Messages.sendMessage(p, " §7Team 3: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 3: §2OK", false);
					}
					
					if(Team.team4.get(a) == null || Team.team4.get(a) == false){
						Messages.sendMessage(p, " §7Team 4: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 4: §2OK", false);
					}
					
					if(Team.team5.get(a) == null || Team.team5.get(a) == false){
						Messages.sendMessage(p, " §7Team 5: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 5: §2OK", false);
					}
					
					if(Team.team6.get(a) == null || Team.team6.get(a) == false){
						Messages.sendMessage(p, " §7Team 6: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 6: §2OK", false);
					}
					
					if(Team.team7.get(a) == null || Team.team7.get(a) == false){
						Messages.sendMessage(p, " §7Team 7: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 7: §2OK", false);
					}
					
					if(Team.team8.get(a) == null || Team.team8.get(a) == false){
						Messages.sendMessage(p, " §7Team 8: §cNot Set", false);
					}else{
						Messages.sendMessage(p, " §7Team 8: §2OK", false);
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
	
	public static void create(Player p){
		if(!isInEditor(p)){
			Messages.sendMessage(p, "Du bist nicht im Editor Modus", true);
		}
		
		if(debug(p) == false){
			return;
		}
		Messages.sendMessage(p, "0", true);

		Messages.sendMessage(p, "0.1", true);
		
		String a = player.get(p);
		
		if(ArenaManager.getManager().ArenaExist(a)){
			Messages.sendMessage(p, "Arena Existiert bereits", true);
			return;
		}
		
		Messages.sendMessage(p, "0.2", true);
		ArenaManager.getManager().createArena(getlobby(a), getc1(a), getc2(a), getExit(a), a, getBuilder(a));
		int i = ArenaManager.getManager().getIDBackfromName(player.get(p));
		Arena arena = ArenaManager.getManager().getArena(i);
		
		Messages.sendMessage(p, "1", true);
		if(size.get(a) == 2){
			arena.team_1_color = Team.team1_c.get(arena.getName());
			arena.team_2_color = Team.team2_c.get(arena.getName());
			
			arena.team_1_name = Team.team1_n.get(arena.getName());
			arena.team_2_name = Team.team2_n.get(arena.getName());
			
			arena.team_1_spawn = Team.team1_s.get(arena.getName());
			arena.team_2_spawn = Team.team2_s.get(arena.getName());
			
			arena.team_1_bed = Team.team1_b.get(arena.getName());
			arena.team_2_bed = Team.team2_b.get(arena.getName());
			
			Bed b1 = (Bed) arena.team_1_bed.getState().getData();
			Bed b2 = (Bed) arena.team_2_bed.getState().getData();
			
			arena.team_1_bed_f = b1.getFacing().getOppositeFace();
			arena.team_2_bed_f = b2.getFacing().getOppositeFace();
		}else if(size.get(a) == 4){
			arena.team_1_color = Team.team1_c.get(arena.getName());
			arena.team_2_color = Team.team2_c.get(arena.getName());
			arena.team_3_color = Team.team3_c.get(arena.getName());
			arena.team_4_color = Team.team4_c.get(arena.getName());
			
			arena.team_1_name = Team.team1_n.get(arena.getName());
			arena.team_2_name = Team.team2_n.get(arena.getName());
			arena.team_3_name = Team.team3_n.get(arena.getName());
			arena.team_4_name = Team.team4_n.get(arena.getName());
			
			arena.team_1_spawn = Team.team1_s.get(arena.getName());
			arena.team_2_spawn = Team.team2_s.get(arena.getName());
			arena.team_3_spawn = Team.team3_s.get(arena.getName());
			arena.team_4_spawn = Team.team4_s.get(arena.getName());
			
			arena.team_1_bed = Team.team1_b.get(arena.getName());
			arena.team_2_bed = Team.team2_b.get(arena.getName());
			arena.team_3_bed = Team.team3_b.get(arena.getName());
			arena.team_4_bed = Team.team4_b.get(arena.getName());
			
			Bed b1 = (Bed) arena.team_1_bed.getState().getData();
			Bed b2 = (Bed) arena.team_2_bed.getState().getData();
			Bed b3 = (Bed) arena.team_3_bed.getState().getData();
			Bed b4 = (Bed) arena.team_4_bed.getState().getData();
			
			arena.team_1_bed_f = b1.getFacing().getOppositeFace();
			arena.team_2_bed_f = b2.getFacing().getOppositeFace();
			arena.team_3_bed_f = b3.getFacing().getOppositeFace();
			arena.team_4_bed_f = b4.getFacing().getOppositeFace();
		}else if(size.get(a) == 6){
			arena.team_1_color = Team.team1_c.get(arena.getName());
			arena.team_2_color = Team.team2_c.get(arena.getName());
			arena.team_3_color = Team.team3_c.get(arena.getName());
			arena.team_4_color = Team.team4_c.get(arena.getName());
			arena.team_5_color = Team.team5_c.get(arena.getName());
			arena.team_6_color = Team.team6_c.get(arena.getName());
			
			arena.team_1_name = Team.team1_n.get(arena.getName());
			arena.team_2_name = Team.team2_n.get(arena.getName());
			arena.team_3_name = Team.team3_n.get(arena.getName());
			arena.team_4_name = Team.team4_n.get(arena.getName());
			arena.team_5_name = Team.team5_n.get(arena.getName());
			arena.team_6_name = Team.team6_n.get(arena.getName());
			
			arena.team_1_spawn = Team.team1_s.get(arena.getName());
			arena.team_2_spawn = Team.team2_s.get(arena.getName());
			arena.team_3_spawn = Team.team3_s.get(arena.getName());
			arena.team_4_spawn = Team.team4_s.get(arena.getName());
			arena.team_5_spawn = Team.team5_s.get(arena.getName());
			arena.team_6_spawn = Team.team6_s.get(arena.getName());
			
			arena.team_1_bed = Team.team1_b.get(arena.getName());
			arena.team_2_bed = Team.team2_b.get(arena.getName());
			arena.team_3_bed = Team.team3_b.get(arena.getName());
			arena.team_4_bed = Team.team4_b.get(arena.getName());
			arena.team_5_bed = Team.team5_b.get(arena.getName());
			arena.team_6_bed = Team.team6_b.get(arena.getName());
			
			Bed b1 = (Bed) arena.team_1_bed.getState().getData();
			Bed b2 = (Bed) arena.team_2_bed.getState().getData();
			Bed b3 = (Bed) arena.team_3_bed.getState().getData();
			Bed b4 = (Bed) arena.team_4_bed.getState().getData();
			Bed b5 = (Bed) arena.team_5_bed.getState().getData();
			Bed b6 = (Bed) arena.team_6_bed.getState().getData();
			
			arena.team_1_bed_f = b1.getFacing().getOppositeFace();
			arena.team_2_bed_f = b2.getFacing().getOppositeFace();
			arena.team_3_bed_f = b3.getFacing().getOppositeFace();
			arena.team_4_bed_f = b4.getFacing().getOppositeFace();
			arena.team_5_bed_f = b5.getFacing().getOppositeFace();
			arena.team_6_bed_f = b6.getFacing().getOppositeFace();
		}else if(size.get(a) == 8){
			arena.team_1_color = Team.team1_c.get(arena.getName());
			arena.team_2_color = Team.team2_c.get(arena.getName());
			arena.team_3_color = Team.team3_c.get(arena.getName());
			arena.team_4_color = Team.team4_c.get(arena.getName());
			arena.team_5_color = Team.team5_c.get(arena.getName());
			arena.team_6_color = Team.team6_c.get(arena.getName());
			arena.team_7_color = Team.team7_c.get(arena.getName());
			arena.team_8_color = Team.team8_c.get(arena.getName());
			
			arena.team_1_name = Team.team1_n.get(arena.getName());
			arena.team_2_name = Team.team2_n.get(arena.getName());
			arena.team_3_name = Team.team3_n.get(arena.getName());
			arena.team_4_name = Team.team4_n.get(arena.getName());
			arena.team_5_name = Team.team5_n.get(arena.getName());
			arena.team_6_name = Team.team6_n.get(arena.getName());
			arena.team_7_name = Team.team7_n.get(arena.getName());
			arena.team_8_name = Team.team8_n.get(arena.getName());
			
			arena.team_1_spawn = Team.team1_s.get(arena.getName());
			arena.team_2_spawn = Team.team2_s.get(arena.getName());
			arena.team_3_spawn = Team.team3_s.get(arena.getName());
			arena.team_4_spawn = Team.team4_s.get(arena.getName());
			arena.team_5_spawn = Team.team5_s.get(arena.getName());
			arena.team_6_spawn = Team.team6_s.get(arena.getName());
			arena.team_7_spawn = Team.team7_s.get(arena.getName());
			arena.team_8_spawn = Team.team8_s.get(arena.getName());
			
			arena.team_1_bed = Team.team1_b.get(arena.getName());
			arena.team_2_bed = Team.team2_b.get(arena.getName());
			arena.team_3_bed = Team.team3_b.get(arena.getName());
			arena.team_4_bed = Team.team4_b.get(arena.getName());
			arena.team_5_bed = Team.team5_b.get(arena.getName());
			arena.team_6_bed = Team.team6_b.get(arena.getName());
			arena.team_7_bed = Team.team7_b.get(arena.getName());
			arena.team_8_bed = Team.team8_b.get(arena.getName());
			
			Bed b1 = (Bed) arena.team_1_bed.getState().getData();
			Bed b2 = (Bed) arena.team_2_bed.getState().getData();
			Bed b3 = (Bed) arena.team_3_bed.getState().getData();
			Bed b4 = (Bed) arena.team_4_bed.getState().getData();
			Bed b5 = (Bed) arena.team_5_bed.getState().getData();
			Bed b6 = (Bed) arena.team_6_bed.getState().getData();
			Bed b7 = (Bed) arena.team_7_bed.getState().getData();
			Bed b8 = (Bed) arena.team_8_bed.getState().getData();
			
			arena.team_1_bed_f = b1.getFacing().getOppositeFace();
			arena.team_2_bed_f = b2.getFacing().getOppositeFace();
			arena.team_3_bed_f = b3.getFacing().getOppositeFace();
			arena.team_4_bed_f = b4.getFacing().getOppositeFace();
			arena.team_5_bed_f = b5.getFacing().getOppositeFace();
			arena.team_6_bed_f = b6.getFacing().getOppositeFace();
			arena.team_7_bed_f = b7.getFacing().getOppositeFace();
			arena.team_8_bed_f = b8.getFacing().getOppositeFace();
		}

		Messages.sendMessage(p, "2", true);
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
		
		Messages.sendMessage(p, "§2Arena wurde erstellt", true);
		
		save.saveMap(player.get(p));
		
		if(size.get(a) == 2){
			setbed(arena, 1);
			setbed(arena, 2);
		}else if(size.get(a) == 4){
			setbed(arena, 1);
			setbed(arena, 2);
			setbed(arena, 3);
			setbed(arena, 4);
		}else if(size.get(a) == 6){
			setbed(arena, 1);
			setbed(arena, 2);
			setbed(arena, 3);
			setbed(arena, 4);
			setbed(arena, 5);
			setbed(arena, 6);
		}else if(size.get(a) == 8){
			setbed(arena, 1);
			setbed(arena, 2);
			setbed(arena, 3);
			setbed(arena, 4);
			setbed(arena, 5);
			setbed(arena, 6);
			setbed(arena, 7);
			setbed(arena, 8);
		}

		
		
	}
	
	private static String getBuilder(String a) {
		return bu.get(a);
	}

	public static BlockFace getRotation(Player p, int i){
		if(!isInEditor(p)){
			Messages.sendMessage(p, "Du bist nicht im Editor Modus", true);
		}
		
		String a = player.get(p);
		
		if(ArenaManager.getManager().ArenaExist(a)){
			Messages.sendMessage(p, "Arena Existiert bereits", true);
			return null;
		}
		
		if(i == 1){
			if(Team.team1_b.get(a) != null){
				Bed b1 = (Bed) Team.team1_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		if(i == 2){
			if(Team.team2_b.get(a) != null){
				Bed b1 = (Bed) Team.team2_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		
		if(i == 3){
			if(Team.team3_b.get(a) != null){
				Bed b1 = (Bed) Team.team3_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		if(i == 4){
			if(Team.team4_b.get(a) != null){
				Bed b1 = (Bed) Team.team4_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		if(i == 5){
			if(Team.team5_b.get(a) != null){
				Bed b1 = (Bed) Team.team5_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		if(i == 6){
			if(Team.team6_b.get(a) != null){
				Bed b1 = (Bed) Team.team6_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		if(i == 7){
			if(Team.team7_b.get(a) != null){
				Bed b1 = (Bed) Team.team7_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		
		if(i == 8){
			if(Team.team8_b.get(a) != null){
				Bed b1 = (Bed) Team.team8_b.get(a).getState().getData();
				return b1.getFacing().getOppositeFace();
			}else{
				return null;
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static void setbed(Arena arena, int i){
		arena.setBedState(i, true);
		if(arena.getRotation(i) == BlockFace.NORTH){
            BlockState bedFoot = arena.getBlock(i).getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.SOUTH).getState();
            bedFoot.setType(Material.BED_BLOCK);
            bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 0);
            bedHead.setRawData((byte) 8);
            bedFoot.update(true, false);
            bedHead.update(true, true);
		}else if(arena.getRotation(i) == BlockFace.EAST){
			Location l = arena.getBed(i);
			l.getBlock().setType(Material.AIR);
			l.getBlock().setType(Material.BED_BLOCK);
			Block block = arena.getBlock(i);
			BlockState bedFoot = block.getState();
	        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.WEST).getState();
	        bedFoot.setType(Material.BED_BLOCK);
	        bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 1);
            bedHead.setRawData((byte) 9);
	        bedFoot.update(true, false);
	        bedHead.update(true, true);
		}else if(arena.getRotation(i) == BlockFace.SOUTH){
			Location l = arena.getBed(i);
			l.getBlock().setType(Material.AIR);
			l.getBlock().setType(Material.BED_BLOCK);
			Block block = arena.getBlock(i);
			BlockState bedFoot = block.getState();
	        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.NORTH).getState();
	        bedFoot.setType(Material.BED_BLOCK);
	        bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 2);
            bedHead.setRawData((byte) 10);
	        bedFoot.update(true, false);
	        bedHead.update(true, true);
		}else if(arena.getRotation(i) == BlockFace.WEST){
			Location l = arena.getBed(i);
			l.getBlock().setType(Material.AIR);
			l.getBlock().setType(Material.BED_BLOCK);
			Block block = arena.getBlock(i);
			BlockState bedFoot = block.getState();
	        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.EAST).getState();
	        bedFoot.setType(Material.BED_BLOCK);
	        bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 3);
            bedHead.setRawData((byte) 11);
	        bedFoot.update(true, false);
	        bedHead.update(true, true);
		}
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
