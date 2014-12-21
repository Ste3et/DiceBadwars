package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Listener.AchievementListener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AchievementGUI {

	@SuppressWarnings("deprecation")
	public static void openGUI(Player p){
		Inventory inv = Bukkit.createInventory(null, 45, "Achievement GUI");
		int i = 0;
		for(String s : AchievementListener.Achievents){
			List<String> lore = new ArrayList<String>();
			ItemStack is = new ItemStack(Material.getMaterial(160));
			ItemMeta im = is.getItemMeta();
			int dur = 14;
			if(AchievementListener.checkAchivement(p, i)){
				im.setDisplayName("§2" + AchievementListener.getHeader(i));
				lore.add("§a");
				lore.add("§aERLEDIGT");
				dur = 13;
			}else{
				im.setDisplayName("§c" + AchievementListener.getHeader(i));
				
				if(AchievementListener.Achievents.get(i) != null){
					String r = s;
					String[] a = r.split("#");
					lore.add("§a");
					if(a.length > 1){
						int u = 0;
						for(String sa : a){
							if(u != 0){
								lore.add("§7" + sa);
							}
							
							u++;
						}
					}else{
						lore.add("§7" + a[1]);
					}

					
					dur = 14;
				}
			}
			
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) dur); 
			inv.addItem(is);
			i++;
		}
		
		p.openInventory(inv);
	}
}
