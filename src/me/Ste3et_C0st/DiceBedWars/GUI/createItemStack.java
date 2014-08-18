package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class createItemStack {
	
	public static ItemStack returnIS(String s, Material m, List<String> lore, int amount){
		ItemStack is = new ItemStack(m);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(s);
		
		if(lore != null){
			im.setLore(lore);
		}
		
		is.setItemMeta(im);
		
		if(m == null){
			return null;
		}
		
		return is;
		
	}
}
