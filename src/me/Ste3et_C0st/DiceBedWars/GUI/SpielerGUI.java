package me.Ste3et_C0st.DiceBedWars.GUI;

import me.Ste3et_C0st.DiceBedWars.Manager.Arena;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpielerGUI {
	public static void openInventory(Player p, Arena a){
		if(a.isStartet()){
			if(!a.getPlayers().isEmpty()){
				int i = 0;
				Inventory inv = Bukkit.createInventory(null, 45, "Spieler Wechseln");
				
				for(Player pl : a.getPlayers()){
					ItemStack is = new ItemStack(Material.SKULL_ITEM);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName( "§a" + pl.getName());
					is.setItemMeta(im);
					is.setDurability((short) 3);
					inv.setItem(i, is);
					i++;
				}
				p.openInventory(inv);
			}
		}
	}
}
