package me.Ste3et_C0st.DiceBedWars.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public static String createRandomRegistryId(String s)
	{  
	    String val = s;      
	    int ranChar = 65 + (new Random()).nextInt(90-65);
	    char ch = (char)ranChar;        
	    val += ch;      
	    Random r = new Random();
	    int numbers = 100000 + (int)(r.nextFloat() * 899900);
	    val += String.valueOf(numbers);
	    val += "-";
	    for(int i = 0; i<6;){
	        int ranAny = 48 + (new Random()).nextInt(90-65);
	        if(!(57 < ranAny && ranAny<= 65)){
	        char c = (char)ranAny;      
	        val += c;
	        i++;
	        }
	    }

	    return val;
	}
	
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
				        	 int i = Utils.rnd(1, 1);
				        	 for(int o = 0; o < i; o++){
					        	 for(Location loc : a.returnGold()){
					        		 is.setAmount(1);
						        	 List<String> lore = new ArrayList<>();
						        	 ItemMeta im = is.getItemMeta();
						        	 lore.add(createRandomRegistryId("GO-"));
						        	 im.setLore(lore);
						        	 is.setAmount(1);
						        	 is.setItemMeta(im);
						        	 
						        	 Item id = loc.getWorld().dropItemNaturally(loc , is);
						        	 id.setVelocity(new Vector(0,0,0));
						        	 id.setFallDistance(0);
					        	 }
				        	 }

				            cgold = Utils.rnd(35, 45);
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
					        	 int i = Utils.rnd(1, 2);
					        	 for(int o = 0; o < i; o++){
						        	 
						        	 for(Location loc : a.returnEisen()){
						        		 is.setAmount(1);
						        		 List<String> lore = new ArrayList<>();
							        	 ItemMeta im = is.getItemMeta();
							        	 lore.add(createRandomRegistryId("EI-"));
							        	 im.setLore(lore);
							        	 is.setAmount(1);
							        	 is.setItemMeta(im); 
							        	 
						        		 Item id = loc.getWorld().dropItemNaturally(loc , is); 
						        		 id.setVelocity(new Vector(0,0,0));
						        		 id.setFallDistance(0);
						        	 }
					        	 }
					        	
				            cgold = Utils.rnd(15, 20);
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
					        	 int i = Utils.rnd(1, 1);
					        	 for(int o = 0; o < i; o++){
						        	 
						        	 for(Location loc : a.returnBronze()){
						        		 is.setAmount(1);
						        		 List<String> lore = new ArrayList<>();
							        	 ItemMeta im = is.getItemMeta();
							        	 lore.add(createRandomRegistryId("BR-"));
							        	 im.setLore(lore);
							        	 is.setAmount(1);
							        	 is.setItemMeta(im); 
							        	 
						        		 Item id = loc.getWorld().dropItemNaturally(loc , is); 
						        		 id.setVelocity(new Vector(0,0,0));
						        		 id.setFallDistance(0);
						        	 }
					        	 }
				            cgold = Utils.rnd(1, 4);
				          }
				          cgold -= 1;
				        }
				      }, 20L, 20L));
		}
	}

}
