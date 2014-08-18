package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.fedmanddev.VillagerTrade;

public class main {
	
	public static HashMap<Player, Inventory> invList = new HashMap<Player, Inventory>();
	
	public void openGUI_1(Player p){
	
	}
	
	public void openVillager(String s, Material m, ItemStack reward){
		VillagerTrade trade = new VillagerTrade(createItemStack.returnIS(s, m, null, 5), null, reward);
		//VillagerTradeApi.addTrade(villager, trade);
	}
}
