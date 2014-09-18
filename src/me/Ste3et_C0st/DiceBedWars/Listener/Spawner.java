package me.Ste3et_C0st.DiceBedWars.Listener;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Spawner {

	public Spawner(final ItemStack is, String s, final Arena a) {
		if(s.equalsIgnoreCase("Gold")){
			a.setSpawner("Gold", Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), 
				      new Runnable()
				      {
				        int cgold = 30;
				        public void run()
				        {
				        	
				          
				          
				          if (cgold == 0)
				          {
				        	 List<String> lore = new ArrayList<>();
				        	 ItemMeta im = is.getItemMeta();
				        	 lore.add(Utils.rnd(0, 50)  + "");
				        	 im.setLore(lore);
				        	 is.setItemMeta(im);
				        	 
				        	 for(Location loc : a.returnGold()){
					        	 Item id = loc.getWorld().dropItemNaturally(loc , is);
					        	 id.setVelocity(new Vector(0,0,0));
					        	 id.setFallDistance(0);
				        	 }
				            cgold = 45;
				          }
				          cgold -= 1;
				        }
				      }, 20L, 20L));
		}else if(s.equalsIgnoreCase("Eisen")){
			a.setSpawner("Eisen", Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), 
				      new Runnable()
				      {
				        int cgold = 7;
				        
				        public void run()
				        {
				        	
				          if (cgold == 0)
				          {
					        	 List<String> lore = new ArrayList<>();
					        	 ItemMeta im = is.getItemMeta();
					        	 lore.add(Utils.rnd(0, 50)  + "");
					        	 im.setLore(lore);
					        	 is.setItemMeta(im); 
					        	 
					        	 for(Location loc : a.returnEisen()){
					        		 Item id = loc.getWorld().dropItemNaturally(loc , is); 
					        		 id.setVelocity(new Vector(0,0,0));
					        		 id.setFallDistance(0);
					        	 }
				            cgold = 20;
				          }
				          cgold -= 1;
				        }
				      }, 20L, 20L));
		}else if(s.equalsIgnoreCase("Bronze")){
			a.setSpawner("Bronze", Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), 
				      new Runnable()
				      {
				        int cgold = 2;
				        
				        public void run()
				        {
						      
				          if (cgold == 0)
				          {
					        	 List<String> lore = new ArrayList<>();
					        	 ItemMeta im = is.getItemMeta();
					        	 lore.add(Utils.rnd(0, 50)  + "");
					        	 im.setLore(lore);
					        	 is.setItemMeta(im);
					        	 for(Location loc : a.returnBronze()){
					        		 Item id = loc.getWorld().dropItemNaturally(loc , is); 
					        		 id.setVelocity(new Vector(0,0,0));
					        		 id.setFallDistance(0);
					        	 }
					        	 
					        	 lore.clear();
					        	 
					        	 lore = new ArrayList<>();
					        	 im = is.getItemMeta();
					        	 lore.add(Utils.rnd(0, 50)  + "");
					        	 im.setLore(lore);
					        	 is.setItemMeta(im);
					        	 for(Location loc : a.returnBronze()){
					        		 Item id = loc.getWorld().dropItemNaturally(loc , is); 
					        		 id.setVelocity(new Vector(0,0,0));
					        		 id.setFallDistance(0);
					        	 }
				            cgold = 4;
				          }
				          cgold -= 1;
				        }
				      }, 20L, 20L));
		}
	}

}
