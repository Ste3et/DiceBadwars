package me.Ste3et_C0st.BedWars.Listener;

import org.bukkit.entity.Player;
import me.Ste3et_C0st.DiceBedWars.GUI.main;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryItemClickEvent {
	
	
	public void onInventoryClickEvent(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if((e.getInventory() == null) || (e.getCurrentItem() == null) || (e.getCurrentItem().hasItemMeta() == false) ||
			(e.getCurrentItem().getItemMeta().hasDisplayName() == false) || main.invList.get(p) == null){
			return;
		}
		
		if(main.invList.get(p).equals(e.getInventory())){
			
		}
	}
}
