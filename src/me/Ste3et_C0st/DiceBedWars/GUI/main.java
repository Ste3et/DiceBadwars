package me.Ste3et_C0st.DiceBedWars.GUI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.fedmanddev.VillagerTrade;
import com.gmail.fedmanddev.VillagerTradeApi;

public class main {
	
	public void openGUI_1(Player p){
	
	}
	
	public void openVillager(String s, Material m, ItemStack reward){
		VillagerTrade trade = new VillagerTrade(createItemStack.returnIS(s, m, null, 5), null, reward);
		//VillagerTradeApi.addTrade(villager, trade);
	}
}
