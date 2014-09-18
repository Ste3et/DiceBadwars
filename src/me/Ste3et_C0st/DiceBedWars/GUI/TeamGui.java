package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.DiceBedWars.Manager.Team;
import me.Ste3et_C0st.DiceBedWars.Manager.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamGui {
	public static HashMap<Player, Inventory> inv = new HashMap<Player, Inventory>();
	
	public static void openInv1(Player p){
		int slots = 9;
		if(Editor.getSize(Team.player.get(p)) <= 4){
			slots = 9;
		}else{
			slots = 9;
		}
		
		Inventory in = Bukkit.createInventory(null, slots, "Team Selector");
		if(Team.player.get(p) != null){
			String s = Team.player.get(p);
			if(Editor.getSize(s) == 2){
				if(Team.team1_n.get(s) != null){
					String teamName = Team.team1_n.get(s);
					String teamColor = Team.team1_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team1_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(2, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 1));
				}else{
					in.setItem(2, Editor.is(Material.WOOL, "Team 1", null, 0, 1));
				}
				
				if(Team.team2_n.get(s) != null){
					String teamName = Team.team2_n.get(s);
					String teamColor = Team.team2_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team2_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(6, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 2));
				}else{
					in.setItem(6, Editor.is(Material.WOOL, "Team 2", null, 0, 2));
				}
			}else if(Editor.getSize(s) == 4){
				if(Team.team1_n.get(s) != null){
					String teamName = Team.team1_n.get(s);
					String teamColor = Team.team1_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team1_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(1, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 1));
				}else{
					in.setItem(1, Editor.is(Material.WOOL, "Team 1", null, 0, 1));
				}
				
				if(Team.team2_n.get(s) != null){
					String teamName = Team.team2_n.get(s);
					String teamColor = Team.team2_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team2_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(3, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 2));
				}else{
					in.setItem(3, Editor.is(Material.WOOL, "Team 2", null, 0, 2));
				}
				
				if(Team.team3_n.get(s) != null){
					String teamName = Team.team3_n.get(s);
					String teamColor = Team.team3_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team3_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(5, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 3));
				}else{
					in.setItem(5, Editor.is(Material.WOOL, "Team 3", null, 0, 3));
				}
				
				if(Team.team4_n.get(s) != null){
					String teamName = Team.team4_n.get(s);
					String teamColor = Team.team4_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team4_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(7, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 4));
				}else{
					in.setItem(7, Editor.is(Material.WOOL, "Team 4", null, 0, 4));
				}
			}else if(Editor.getSize(s) == 6){
				if(Team.team1_n.get(s) != null){
					String teamName = Team.team1_n.get(s);
					String teamColor = Team.team1_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team1_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(0, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 1));
				}else{
					in.setItem(0, Editor.is(Material.WOOL, "Team 1", null, 0, 1));
				}
				
				if(Team.team2_n.get(s) != null){
					String teamName = Team.team2_n.get(s);
					String teamColor = Team.team2_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team2_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(1, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 2));
				}else{
					in.setItem(1, Editor.is(Material.WOOL, "Team 2", null, 0, 2));
				}
				
				if(Team.team3_n.get(s) != null){
					String teamName = Team.team3_n.get(s);
					String teamColor = Team.team3_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team3_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(2, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 3));
				}else{
					in.setItem(2, Editor.is(Material.WOOL, "Team 3", null, 0, 3));
				}
				
				if(Team.team4_n.get(s) != null){
					String teamName = Team.team4_n.get(s);
					String teamColor = Team.team4_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team4_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(3, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 4));
				}else{
					in.setItem(3, Editor.is(Material.WOOL, "Team 4", null, 0, 4));
				}
				
				if(Team.team5_n.get(s) != null){
					String teamName = Team.team5_n.get(s);
					String teamColor = Team.team5_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team5_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(4, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 5));
				}else{
					in.setItem(4, Editor.is(Material.WOOL, "Team 5", null, 0, 5));
				}
				
				if(Team.team6_n.get(s) != null){
					String teamName = Team.team6_n.get(s);
					String teamColor = Team.team6_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team6_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(5, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 6));
				}else{
					in.setItem(5, Editor.is(Material.WOOL, "Team 6", null, 0, 6));
				}
			}else if(Editor.getSize(s) == 8){
				if(Team.team1_n.get(s) != null){
					String teamName = Team.team1_n.get(s);
					String teamColor = Team.team1_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team1_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(0, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 1));
				}else{
					in.setItem(0, Editor.is(Material.WOOL, "Team 1", null, 0, 1));
				}
				
				if(Team.team2_n.get(s) != null){
					String teamName = Team.team2_n.get(s);
					String teamColor = Team.team2_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team2_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(1, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 2));
				}else{
					in.setItem(1, Editor.is(Material.WOOL, "Team 2", null, 0, 2));
				}
				
				if(Team.team3_n.get(s) != null){
					String teamName = Team.team3_n.get(s);
					String teamColor = Team.team3_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team3_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(2, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 3));
				}else{
					in.setItem(2, Editor.is(Material.WOOL, "Team 3", null, 0, 3));
				}
				
				if(Team.team4_n.get(s) != null){
					String teamName = Team.team4_n.get(s);
					String teamColor = Team.team4_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team4_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(3, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 4));
				}else{
					in.setItem(3, Editor.is(Material.WOOL, "Team 4", null, 0, 4));
				}
				
				if(Team.team5_n.get(s) != null){
					String teamName = Team.team5_n.get(s);
					String teamColor = Team.team5_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team5_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(4, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 5));
				}else{
					in.setItem(4, Editor.is(Material.WOOL, "Team 5", null, 0, 5));
				}
				
				if(Team.team6_n.get(s) != null){
					String teamName = Team.team6_n.get(s);
					String teamColor = Team.team6_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team6_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(5, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 6));
				}else{
					in.setItem(5, Editor.is(Material.WOOL, "Team 6", null, 0, 6));
				}
				
				if(Team.team7_n.get(s) != null){
					String teamName = Team.team7_n.get(s);
					String teamColor = Team.team7_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team7_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(6, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 7));
				}else{
					in.setItem(6, Editor.is(Material.WOOL, "Team 7", null, 0, 7));
				}
				
				if(Team.team8_n.get(s) != null){
					String teamName = Team.team8_n.get(s);
					String teamColor = Team.team8_c.get(s);
					List<String> lore = new ArrayList<String>();
					lore.add("§2Gesetzt:");
					lore.add(" §b- Spawn gesetzt");
					lore.add(" §b- Color gesetzt");
					lore.add(" §b- Name gesetzt");
					
					if(Team.team8_b.get(s) != null){
						lore.add(" §b- Bett gesetzt");
					}else{
						lore.add(" §c- Bett gesetzt");
					}
					in.setItem(7, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), 8));
				}else{
					in.setItem(7, Editor.is(Material.WOOL, "Team 8", null, 0, 8));
				}
			}
			
			inv.put(p, in);
			p.openInventory(inv.get(p));

		}else{
			return;
		}
	}
	
	public static void openInv2(Player p, int i){
		Inventory in = Bukkit.createInventory(null, 18, "Team Setzen [" + i + "]");
		if(Team.player.get(p) != null){
			int u = 15;
			for(int o = 0; o <= u; o++){
				ItemStack is = new ItemStack(Material.WOOL);
				ItemMeta im = is.getItemMeta();
				String cc = Utils.returnColor(o);
				im.setDisplayName("Team [" + i + "]: " + ChatColor.translateAlternateColorCodes('&', cc) + Utils.returnColorName(o) + " setzen");
				is.setItemMeta(im);
				is.setAmount(o + 1);
				is.setDurability(Utils.returnDurability(cc));
				in.setItem(o, is);
			}
			
			inv.put(p, in);
			p.openInventory(inv.get(p));

		}else{
			return;
		}
	}
	

}
