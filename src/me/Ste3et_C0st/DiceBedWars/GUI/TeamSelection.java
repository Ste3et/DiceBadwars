package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		int slots = 9;
		if(a.getTeamSize() <= 4){
			slots = 9;
		}else{
			slots = 18;
		}
		Inventory in = Bukkit.createInventory(null, slots, "BedWars Team Menü");
			ItemStack is = new ItemStack(Material.WOOL);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(1)) + "Team: " + a.getName(1) + " "  + a.teamSize(1) + "/" + 4);
			List<String> team1s = new ArrayList<String>();
			//2ER Team
			if(a.getTeamSize() == 2){
				if(a.teamSize(1) > 0){

					for(Player pl : a.teamGetList(1)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(1).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(1)));
				in.setItem(2, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(2)) + "Team: " + a.getName(2) + " "  + a.teamSize(2) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(2) > 0){

					for(Player pl : a.teamGetList(2)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(2).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(2)));
				
				in.setItem(6, is);
			}else if(a.getTeamSize() == 4){
				if(a.teamSize(1) > 0){

					for(Player pl : a.teamGetList(1)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(1).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(1)));
				in.setItem(1, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(2)) + "Team: " + a.getName(2) + " "  + a.teamSize(2) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(2) > 0){

					for(Player pl : a.teamGetList(2)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(2).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(2)));
				
				in.setItem(3, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(3)) + "Team: " + a.getName(3) + " "  + a.teamSize(3) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(3) > 0){

					for(Player pl : a.teamGetList(3)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(3).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(3)));
				
				in.setItem(5, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(4)) + "Team: " + a.getName(4) + " "  + a.teamSize(4) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(4) > 0){

					for(Player pl : a.teamGetList(4)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(4).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(4)));
				
				in.setItem(7, is);
			}else if(a.getTeamSize() == 6){
				if(a.teamSize(1) > 0){

					for(Player pl : a.teamGetList(1)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(1).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(1)));
				in.setItem(2, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(2)) + "Team: " + a.getName(2) + " "  + a.teamSize(2) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(2) > 0){

					for(Player pl : a.teamGetList(2)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(2).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(2)));
				
				in.setItem(4, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(3)) + "Team: " + a.getName(3) + " "  + a.teamSize(3) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(3) > 0){

					for(Player pl : a.teamGetList(3)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(3).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(3)));
				
				in.setItem(6, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(4)) + "Team: " + a.getName(4) + " "  + a.teamSize(4) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(4) > 0){

					for(Player pl : a.teamGetList(4)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(4).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(4)));
				
				in.setItem(11, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(5)) + "Team: " + a.getName(5) + " "  + a.teamSize(5) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(5) > 0){

					for(Player pl : a.teamGetList(5)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(5))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(5).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(5))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(5))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(5)));
				
				in.setItem(13, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(6)) + "Team: " + a.getName(6) + " "  + a.teamSize(6) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(6) > 0){

					for(Player pl : a.teamGetList(6)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(6))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(6).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(6))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(6))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(6)));
				
				in.setItem(15, is);
			}else if(a.getTeamSize() == 8){
				if(a.teamSize(1) > 0){

					for(Player pl : a.teamGetList(1)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(1).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
					}
				}
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(1)));
				in.setItem(1, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(2)) + "Team: " + a.getName(2) + " "  + a.teamSize(2) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(2) > 0){

					for(Player pl : a.teamGetList(2)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(2).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(2)));
				
				in.setItem(3, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(3)) + "Team: " + a.getName(3) + " "  + a.teamSize(3) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(3) > 0){

					for(Player pl : a.teamGetList(3)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(3).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(3))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(3)));
				
				in.setItem(5, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(4)) + "Team: " + a.getName(4) + " "  + a.teamSize(4) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(4) > 0){

					for(Player pl : a.teamGetList(4)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(4).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(4)));
				
				in.setItem(7, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(5)) + "Team: " + a.getName(5) + " "  + a.teamSize(5) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(5) > 0){

					for(Player pl : a.teamGetList(5)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(5))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(5).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(5))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(5))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(5)));
				
				in.setItem(10, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(6)) + "Team: " + a.getName(6) + " "  + a.teamSize(6) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(6) > 0){

					for(Player pl : a.teamGetList(6)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(6))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(6).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(6))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(6))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(6)));
				
				in.setItem(12, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(7)) + "Team: " + a.getName(7) + " "  + a.teamSize(7) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(7) > 0){

					for(Player pl : a.teamGetList(7)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(7))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(7).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(7))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(7))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(7)));
				
				in.setItem(14, is);
				
				is = new ItemStack(Material.WOOL);
				im = is.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(8)) + "Team: " + a.getName(8) + " "  + a.teamSize(8) + "/" + 4);
				team1s.clear();
				
				if(a.teamSize(8) > 0){

					for(Player pl : a.teamGetList(8)){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(8))  + "# " + pl.getName());
					}
					
					int ol = 3;
					ol = ol - a.teamGetList(8).size();
					
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(8))  + "# FREI");
					}
				}else{
					int ol = 3;
					for(int o = 0; o <= ol; o++ ){
						team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(8))  + "# FREI");
					}
				}
				
				
				im.setLore(team1s);
				is.setItemMeta(im);
				is.setDurability((short) returnDurability(a.returnColor(8)));
				
				in.setItem(16, is);
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