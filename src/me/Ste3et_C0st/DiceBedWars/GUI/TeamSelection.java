package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamSelection{
	public static HashMap<Player, Inventory> inv = new HashMap<Player, Inventory>();
	
	public static void openGui(Player p, Arena a){
		int slots = 45;
		int multiplayer = 1;
		if(a.getTeamSize() == 2){
			multiplayer = 3;
		}else if(a.getTeamSize() == 4){
			multiplayer = 2;
		}else if(a.getTeamSize() == 6){
			multiplayer = 1;
		}else if(a.getTeamSize() == 8){
			multiplayer = 2;
		}
		
		if(a.getTeamSize() <= 4){
			slots = 9;
		}else if(a.getTeamSize() <= 8){
			slots = 18;
		}
		
		Inventory in = Bukkit.createInventory(null, slots, "BedWars Team Menü");
			int i = 0;
			for(Team t : a.getTeams()){
				if(t != null){
					ItemStack is = new ItemStack(Material.WOOL);
					ItemMeta im = is.getItemMeta();
					List<String> lore = new ArrayList<String>();
					im.setDisplayName(ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + t.getName());
					lore.add(ChatColor.translateAlternateColorCodes('&', "&e") + t.getSize() + "/§9" + 4);
					lore.add("§7-----------------");
					if(t.getSize() > 0){
						for(Player pl : t.teamGetList()){
							lore.add(ChatColor.translateAlternateColorCodes('&', t.getTeamColor())  + "# " + pl.getName());
						}
						
						int ol = 3;
						ol = ol - t.getSize();
						
						for(int o = 0; o <= ol; o++ ){
							lore.add(ChatColor.translateAlternateColorCodes('&', t.getTeamColor())  + "# FREI");
						}
					}else{
						int ol = 3;
						for(int o = 0; o <= ol; o++ ){
							lore.add(ChatColor.translateAlternateColorCodes('&', t.getTeamColor())  + "# FREI");
						}
					}
					
					im.setLore(lore);
					is.setItemMeta(im);
					is.setAmount(i + 1);
					is.setDurability((short) returnDurability(t.teamColor));
					if(slots > 8){
						in.setItem(i, is);
					}else{
						in.setItem(i*multiplayer+1, is);
					}
					
					i++;
				}
			}
		inv.put(p, in);
		p.openInventory(in);
	}
	
	public static short returnDurability(String s){
		if(s == null){
			return 0;
		}else if(s.equalsIgnoreCase("&a")){
			return 5;
		}else if(s.equalsIgnoreCase("&b")){
			return 3;
		}else if(s.equalsIgnoreCase("&c")){
			return 14;
		}else if(s.equalsIgnoreCase("&d")){
			return 6;
		}else if(s.equalsIgnoreCase("&e")){
			return 4;
		}else if(s.equalsIgnoreCase("&f")){
			return 0;
		}else if(s.equalsIgnoreCase("&0")){
			return 15;
		}else if(s.equalsIgnoreCase("&1")){
			return 11;
		}else if(s.equalsIgnoreCase("&2")){
			return 13;
		}else if(s.equalsIgnoreCase("&3")){
			return 9;
		}else if(s.equalsIgnoreCase("&4")){
			return 14;
		}else if(s.equalsIgnoreCase("&5")){
			return 10;
		}else if(s.equalsIgnoreCase("&6")){
			return 1;
		}else if(s.equalsIgnoreCase("&7")){
			return 8;
		}else if(s.equalsIgnoreCase("&8")){
			return 7;
		}else if(s.equalsIgnoreCase("&9")){
			return 9;
		}else{
			return 0;
		}
	}
}