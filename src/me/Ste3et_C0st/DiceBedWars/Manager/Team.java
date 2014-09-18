package me.Ste3et_C0st.DiceBedWars.Manager;

import java.util.HashMap;

import me.Ste3et_C0st.DiceBedWars.Main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Team {
	

	public static HashMap<Player, String> player = new HashMap<Player, String>();
	//Team Created ?
	public static HashMap<String, Boolean> team1 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> team2 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> team3 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> team4 = new HashMap<String, Boolean>();
	
	public static HashMap<String, Boolean> team5 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> team6 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> team7 = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> team8 = new HashMap<String, Boolean>();
	//Team Color
	public static HashMap<String, String> team1_c = new HashMap<String, String>();
	public static HashMap<String, String> team2_c = new HashMap<String, String>();
	public static HashMap<String, String> team3_c = new HashMap<String, String>();
	public static HashMap<String, String> team4_c = new HashMap<String, String>();
	
	public static HashMap<String, String> team5_c = new HashMap<String, String>();
	public static HashMap<String, String> team6_c = new HashMap<String, String>();
	public static HashMap<String, String> team7_c = new HashMap<String, String>();
	public static HashMap<String, String> team8_c = new HashMap<String, String>();
	//Team Name
	public static HashMap<String, String> team1_n = new HashMap<String, String>();
	public static HashMap<String, String> team2_n = new HashMap<String, String>();
	public static HashMap<String, String> team3_n = new HashMap<String, String>();
	public static HashMap<String, String> team4_n = new HashMap<String, String>();
	
	public static HashMap<String, String> team5_n = new HashMap<String, String>();
	public static HashMap<String, String> team6_n = new HashMap<String, String>();
	public static HashMap<String, String> team7_n = new HashMap<String, String>();
	public static HashMap<String, String> team8_n = new HashMap<String, String>();
	//Bed Name
	public static HashMap<String, Block> team1_b = new HashMap<String, Block>();
	public static HashMap<String, Block> team2_b = new HashMap<String, Block>();
	public static HashMap<String, Block> team3_b = new HashMap<String, Block>();
	public static HashMap<String, Block> team4_b = new HashMap<String, Block>();
	
	public static HashMap<String, Block> team5_b = new HashMap<String, Block>();
	public static HashMap<String, Block> team6_b = new HashMap<String, Block>();
	public static HashMap<String, Block> team7_b = new HashMap<String, Block>();
	public static HashMap<String, Block> team8_b = new HashMap<String, Block>();
	//Team Spawn
	public static HashMap<String, Location> team1_s = new HashMap<String, Location>();
	public static HashMap<String, Location> team2_s = new HashMap<String, Location>();
	public static HashMap<String, Location> team3_s = new HashMap<String, Location>();
	public static HashMap<String, Location> team4_s = new HashMap<String, Location>();
	
	public static HashMap<String, Location> team5_s = new HashMap<String, Location>();
	public static HashMap<String, Location> team6_s = new HashMap<String, Location>();
	public static HashMap<String, Location> team7_s = new HashMap<String, Location>();
	public static HashMap<String, Location> team8_s = new HashMap<String, Location>();
	
	@SuppressWarnings("deprecation")
	public static void enterTeam(Player p, String a){
			if(player.get(p) == null){
				p.updateInventory();
				player.put(p, a);
				p.getInventory().setItem(1, Editor.is(Material.BED,Main.head + "Team Editor", null, 0, 1));
				p.updateInventory();
				
				if(Editor.size.get(a) == 2){
					team1.put(a, false);
					team2.put(a, false);
				}

				if(Editor.size.get(a) == 4){
					team1.put(a, false);
					team2.put(a, false);
					team3.put(a, false);
					team4.put(a, false);
				}
				
				if(Editor.size.get(a) == 6){
					team1.put(a, false);
					team2.put(a, false);
					team3.put(a, false);
					team4.put(a, false);
					
					team5.put(a, false);
					team6.put(a, false);
				}
				
				if(Editor.size.get(a) == 8){
					team1.put(a, false);
					team2.put(a, false);
					team3.put(a, false);
					team4.put(a, false);
					
					team5.put(a, false);
					team6.put(a, false);
					team7.put(a, false);
					team8.put(a, false);
				}
			}
	}
	
	@SuppressWarnings("deprecation")
	public static void exitTeam(Player p, String a){
		if(ArenaManager.getManager().ArenaExist(a)){
			if(player.get(p) != null){
				String s = player.get(p);
				
				if(team1_c.get(s) != null){
					team1_c.remove(s);
				}
				
				if(team2_c.get(s) != null){
					team2_c.remove(s);
				}
				
				if(team3_c.get(s) != null){
					team3_c.remove(s);
				}
				
				if(team4_c.get(s) != null){
					team4_c.remove(s);
				}
				
				if(team5_c.get(s) != null){
					team5_c.remove(s);
				}
				
				if(team6_c.get(s) != null){
					team6_c.remove(s);
				}
				
				if(team7_c.get(s) != null){
					team7_c.remove(s);
				}
				
				if(team8_c.get(s) != null){
					team8_c.remove(s);
				}
				//
				if(team1_n.get(s) != null){
					team1_n.remove(s);
				}
				
				if(team2_n.get(s) != null){
					team2_n.remove(s);
				}
				
				if(team3_n.get(s) != null){
					team3_n.remove(s);
				}
				
				if(team4_n.get(s) != null){
					team4_n.remove(s);
				}
				
				if(team5_n.get(s) != null){
					team5_n.remove(s);
				}
				
				if(team6_n.get(s) != null){
					team6_n.remove(s);
				}
				
				if(team7_n.get(s) != null){
					team7_n.remove(s);
				}
				
				if(team8_n.get(s) != null){
					team8_n.remove(s);
				}
				//
				if(team1_b.get(s) != null){
					team1_b.remove(s);
				}
				
				if(team2_b.get(s) != null){
					team2_b.remove(s);
				}
				
				if(team3_b.get(s) != null){
					team3_b.remove(s);
				}
				
				if(team4_b.get(s) != null){
					team4_b.remove(s);
				}
				
				if(team5_b.get(s) != null){
					team5_b.remove(s);
				}
				
				if(team6_b.get(s) != null){
					team6_b.remove(s);
				}
				
				if(team7_b.get(s) != null){
					team7_b.remove(s);
				}
				
				if(team8_b.get(s) != null){
					team8_b.remove(s);
				}
				
				//
				
				if(team1_s.get(s) != null){
					team1_s.remove(s);
				}
				
				if(team2_s.get(s) != null){
					team2_s.remove(s);
				}
				
				if(team3_s.get(s) != null){
					team3_s.remove(s);
				}
				
				if(team4_s.get(s) != null){
					team4_s.remove(s);
				}
				
				if(team5_s.get(s) != null){
					team5_s.remove(s);
				}
				
				if(team6_s.get(s) != null){
					team6_s.remove(s);
				}
				
				if(team7_s.get(s) != null){
					team7_s.remove(s);
				}
				
				if(team8_s.get(s) != null){
					team8_s.remove(s);
				}
				
				
				if(team1.get(s) != null){
					team1.remove(s);
				}
				
				if(team2.get(s) != null){
					team2.remove(s);
				}
				
				if(team3.get(s) != null){
					team3.remove(s);
				}
				
				if(team4.get(s) != null){
					team4.remove(s);
				}
				
				if(team5.get(s) != null){
					team5.remove(s);
				}
				
				if(team6.get(s) != null){
					team6.remove(s);
				}
				
				if(team7.get(s) != null){
					team7.remove(s);
				}
				
				if(team8.get(s) != null){
					team8.remove(s);
				}
				player.remove(p);
				p.updateInventory();
				p.getInventory().setItem(2, null);
				p.updateInventory();
			}
		}
	}
	
	public static boolean debug(Player p, String s) {
		if(team1.get(s) == null || team2.get(s) == null || team3.get(s) == null || team4.get(s) == null){
			return false;
		}else{
			return true;
		}
	}
	
	
}
