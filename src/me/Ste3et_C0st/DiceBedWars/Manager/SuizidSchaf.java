package me.Ste3et_C0st.DiceBedWars.Manager;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.Listener.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

@SuppressWarnings("deprecation")
public class SuizidSchaf {
	int shedular1;
	int shedular2;
	public SuizidSchaf(final Arena a, final Team t, Location l){
		final World w = l.getWorld();
		final Sheep s = (Sheep) w.spawnCreature(l, CreatureType.SHEEP);
		s.setSheared(false);
		s.setCanPickupItems(false);
		final int u = Utils.rnd(20, 30);
		DyeColor c1 = null;
        
        if(t.getTeamColor().equalsIgnoreCase("&a")){
        	c1 = DyeColor.LIME;
        }else if(t.getTeamColor().equalsIgnoreCase("&b")){
        	c1 = DyeColor.LIGHT_BLUE;
        }else if(t.getTeamColor().equalsIgnoreCase("&c")){
        	c1 = DyeColor.RED;
        }else if(t.getTeamColor().equalsIgnoreCase("&d")){
        	c1 = DyeColor.PINK;
        }else if(t.getTeamColor().equalsIgnoreCase("&e")){
        	c1 = DyeColor.YELLOW;
        }else if(t.getTeamColor().equalsIgnoreCase("&f")){
        	c1 = DyeColor.WHITE;
        }else if(t.getTeamColor().equalsIgnoreCase("&0")){
        	c1 = DyeColor.WHITE;
        }else if(t.getTeamColor().equalsIgnoreCase("&1")){
        	c1 = DyeColor.GREEN;
        }else if(t.getTeamColor().equalsIgnoreCase("&2")){
        	c1 = DyeColor.PURPLE;
        }else if(t.getTeamColor().equalsIgnoreCase("&3")){
        	c1 = DyeColor.PINK;
        }else if(t.getTeamColor().equalsIgnoreCase("&4")){
           c1 = DyeColor.RED;
        }else if(t.getTeamColor().equalsIgnoreCase("&5")){
        	c1 = DyeColor.PURPLE;
        }else if(t.getTeamColor().equalsIgnoreCase("&6")){
        	c1 = DyeColor.ORANGE;
        }else if(t.getTeamColor().equalsIgnoreCase("&7")){
        	c1 = DyeColor.GRAY;
        }else if(t.getTeamColor().equalsIgnoreCase("&8")){
        	c1 = DyeColor.GRAY;
        }else if(t.getTeamColor().equalsIgnoreCase("&9")){
        	c1 = DyeColor.CYAN;
        }
        

		
		s.setColor(c1);
		s.setFallDistance(0);
		s.setCustomNameVisible(true);
		s.setCustomName("");
		/*final FallingBlock etnt = w.spawnFallingBlock(s.getLocation().add(0,1,0), Material.TNT, (byte) 15);
		etnt.setDropItem(false);
		etnt.setFallDistance(0);
		s.setPassenger(etnt);*/
		s.setBaby();
		final DyeColor dc = c1;
		shedular1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {	
			int i = u;
			public void run() {
				i--;
				Player player = null;
				if(s.getLocation().getY() < 0){
					s.remove();
					Bukkit.getScheduler().cancelTask(shedular1);
				}
				
				for(Entity st : w.getEntities() ){
					if(st instanceof Player){
						Player p = (Player) st;
						if(ArenaManager.getManager().isInGame(p)){
							if(GameListener.isInside(p.getLocation(), a.getCorner1(), a.getCorner2())){
								if(Team.hasTeam(p)){
									if(Team.getTeam(p) != t){
										player = p;
										break;
									}
								}
							}
						}
					}
				}
				
				String string = ""; 
				if(i < 10){
					string = "0";
				}
				
				s.setCustomName("Explodiert in:§c 00:" + string + i);
				Entity e = s;
				s.setNoDamageTicks(u + 1 * 20);

				if(e.getLocation().distance(player.getLocation()) >= 15){
					moveEntity((LivingEntity) e, player.getLocation(),(float) 1.8);
				}else{
					moveEntity((LivingEntity) e, player.getLocation(),(float) 1.5);
				}
				

				if(i <= u/2){
					s.setAdult();
				}
				
				if(i <= 10){
					if(dc != DyeColor.RED){
						if(s.getColor() != DyeColor.RED){
							s.setColor(DyeColor.RED);
						}else{
							s.setColor(dc);
						}
					}else{
						if(s.getColor() != DyeColor.RED){
							s.setColor(DyeColor.WHITE);
						}else{
							s.setColor(dc);
						}
					}
				}
				
				if(i <= 0){
					//etnt.remove();
					s.setSheared(true);
					s.remove();
					w.createExplosion(s.getLocation(), Utils.rnd(2, 3), false);
					s.setCustomNameVisible(false);
					Bukkit.getScheduler().cancelTask(shedular1);
				}
				
			}
			}, 0L, 20L);
	}
	
	public static void moveEntity(LivingEntity e,Location l,float speed){

    }
}
