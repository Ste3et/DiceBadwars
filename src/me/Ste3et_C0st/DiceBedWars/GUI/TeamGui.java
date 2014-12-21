package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.DiceBedWars.Manager.TeamCreate;
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
		int slots = 45;
		
		Inventory in = Bukkit.createInventory(null, slots, "Team Selector");
		if(TeamCreate.player.get(p) != null){
			String s = TeamCreate.player.get(p);
			if(Editor.getSize(s) > 0){
				for(int i = 1; i <= Editor.getSize(s); i++){
					int u = i - 1;
					if(TeamCreate.getTeam(u, p) != null){
						Team t = TeamCreate.getTeam(u, p);
						if(t.getSpawn() != null){
							String teamName = t.getName();
							String teamColor = t.getTeamColor();
							List<String> lore = new ArrayList<String>();
							lore.add("§2Gesetzt:");
							lore.add(" §b- Spawn gesetzt");
							lore.add(" §b- Color gesetzt");
							lore.add(" §b- Name gesetzt");
							
							if(t.check()){
								lore.add(" §b- Bett Gesetzt");
							}else{
								lore.add(" §c- Bett nicht Gefunden");
							}
							in.setItem(i - 1, Editor.is(Material.WOOL, "Team: " + ChatColor.translateAlternateColorCodes('&', teamColor) + teamName, lore, Utils.returnDurability(teamColor), i));
						}else{
							in.setItem(i - 1, Editor.is(Material.WOOL, "Team " + i, null, 0, i));
						}
					}else{
						in.setItem(i - 1, Editor.is(Material.WOOL, "Team " + i, null, 0, i));
					}
				}
			
			inv.put(p, in);
			p.openInventory(inv.get(p));
		}}
	}
	
	public static void openInv2(Player p, int i){
		Inventory in = Bukkit.createInventory(null, 18, "Team Setzen [" + i + "]");
		if(TeamCreate.player.get(p) != null){
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
