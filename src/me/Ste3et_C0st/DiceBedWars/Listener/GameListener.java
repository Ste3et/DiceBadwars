package me.Ste3et_C0st.DiceBedWars.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.GUI.ItemShop;
import me.Ste3et_C0st.DiceBedWars.GUI.TeamSelection;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import me.Ste3et_C0st.DiceBedWars.Manager.Utils;
import me.Ste3et_C0st.GUI.Shop.Armor;
import me.Ste3et_C0st.GUI.Shop.Blocks;
import me.Ste3et_C0st.GUI.Shop.Bows;
import me.Ste3et_C0st.GUI.Shop.Chests;
import me.Ste3et_C0st.GUI.Shop.Fallen;
import me.Ste3et_C0st.GUI.Shop.Food;
import me.Ste3et_C0st.GUI.Shop.Potions;
import me.Ste3et_C0st.GUI.Shop.Redstone;
import me.Ste3et_C0st.GUI.Shop.Special;
import me.Ste3et_C0st.GUI.Shop.Switch;
import me.Ste3et_C0st.GUI.Shop.Swords;
import me.Ste3et_C0st.GUI.Shop.TNT;
import me.Ste3et_C0st.GUI.Shop.Tools;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
//import org.kitteh.tag.TagAPI;

public class GameListener implements Listener{
	
	@EventHandler
	public void onCMD(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			if(!(e.getMessage().startsWith("/hub"))){
				p.sendMessage(Main.head + "Es ist nur /hub erlaubt");
				e.setCancelled(true);
			}
		}
	}
	
	public static void sendSound(List<Player> list, Sound s){
		for(Player p : list){
			p.playSound(p.getLocation(), s, 1, 1); 
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getPlayer() == null){
			return;
		}
		
		if(!ArenaManager.getManager().isInGame(e.getPlayer())){
			return;
		}
		
		Arena a = ArenaManager.getManager().returnArena(e.getPlayer());
		
		if(a.isStartet() == false){
			return;
		}
		
		if(e.getBlock().getType().equals(Material.BED_BLOCK)){
				Player p = e.getPlayer();
				if(!Team.hasTeam(p)){
					return;
				}
				Team team = Team.getTeam(p);
				
				if(e.getBlock().getLocation().equals(team.getBed().getLocation()) || e.getBlock().getRelative(team.getFace()).equals(team.getBed())){
					e.setCancelled(true);
					if(AchievementListener.checkAchivement(e.getPlayer(), 11)){
						Messages.sendMessage(e.getPlayer(), "Du kannst dein eigenes Bett nicht kaputt machen -.-", true);
					}else{
						AchievementListener.giveAchivement(e.getPlayer(), 11);
					}
					return;
				}
				
				for(Team t : a.getTeams()){
					if(t != null){
						if(e.getBlock().getLocation().equals(t.getBed().getLocation()) || e.getBlock().getRelative(t.getFace()).equals(t.getBed())){
							if(t.getState() == false){
								Messages.sendMessage(e.getPlayer(), "Das Team " + ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + t.getName() + " §9ist leer", true);
								e.setCancelled(true);
								break;
							}
							
							if(e.getBlock().getLocation().equals(t.getBed().getLocation())){
								Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', t.getTeamColor() ) + t.getName() + " §9wurde abgebaut", true, a);
								e.setCancelled(true);
								sendSound(a.getPlayers(), Sound.ENDERDRAGON_GROWL);
								e.getBlock().setType(Material.AIR);
								e.getBlock().getRelative(t.getFace()).setType(Material.AIR);
								t.setBedState(false);
								int l = AchievementListener.Bed.get(e.getPlayer());
								l++;
								AchievementListener.giveAchivement(e.getPlayer(), 5);
								AchievementListener.Bed.put(e.getPlayer(), l);
								if(l - (a.getTeamSize() - 1) == 0){
									AchievementListener.giveAchivement(e.getPlayer(), 3);
								}
								break;
							}
							
							if(e.getBlock().getRelative(t.getFace()).equals(t.getBed())){
								Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + t.getName() + " §9wurde abgebaut", true, a);
								e.setCancelled(true);
								Block bl = e.getBlock();
								sendSound(a.getPlayers(), Sound.ENDERDRAGON_GROWL);
								Block bL = e.getBlock().getRelative(t.getFace());
								bL.setType(Material.AIR);
								bl.setType(Material.AIR);
								t.setBedState(false);
								int l = AchievementListener.Bed.get(e.getPlayer());
								l++;
								AchievementListener.giveAchivement(e.getPlayer(), 5);
								AchievementListener.Bed.put(e.getPlayer(), l);
								if(l - (a.getTeamSize() - 1) == 0){
									AchievementListener.giveAchivement(e.getPlayer(), 3);
								}
								break;
							}
						}
					}
				}
		}else{
			if(a.getBlock().contains(e.getBlock())){
				if(e.getBlock().getType().equals(Material.ENDER_CHEST)){
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.ENDER_CHEST));
				}
				
				if(e.getBlock().getType().equals(Material.ICE)){
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.ICE));
				}
				a.getBlock().remove(e.getBlock());
				return;
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			if(a.isStartet()){
				if(Team.hasTeam(p)){
					Team team = Team.getTeam(p);
					event.setCancelled(true);
					if(event.getMessage().startsWith("@all") || event.getMessage().startsWith("@All")){
						for(Player pl : Bukkit.getOnlinePlayers()){
							if(ArenaManager.getManager().isInGame(pl)){
								Arena a1 = ArenaManager.getManager().returnArena(pl);
								Arena a2 = ArenaManager.getManager().returnArena(p);
								if(a1 == a2){
									pl.sendMessage("§7[@all] " + ChatColor.translateAlternateColorCodes('&', team.getTeamColor()) + p.getName() + "§r:" + event.getMessage().replace("@all", ""));
								}else{
									event.getRecipients().remove(pl);
								}
							}else{
								event.getRecipients().remove(pl);
							}
						}
						return;
					}
					
					for(Player pl : Bukkit.getOnlinePlayers()){
						if(ArenaManager.getManager().isInGame(pl)){
							if(!Team.getTeam(pl).equals(team)){
								event.getRecipients().remove(pl);
							}else{
								pl.sendMessage(ChatColor.translateAlternateColorCodes('&', team.getTeamColor()) + p.getName() + "§r: " + event.getMessage());
							}
						}else{
							event.getRecipients().remove(pl);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent event) {
		Player p = (Player) event.getWhoClicked();
		if(ArenaManager.getManager().isInGame(p)){
			event.setCancelled(true);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onIn(InventoryClickEvent event) {
	  Player p = (Player) event.getWhoClicked();
	 
	  if(event.getInventory() == null){
		  return;
	  }
	  
	  if(event.getCurrentItem() == null){
		  return;
	  }
	  
	  if(ItemShop.inven.get(p) == null){
		  return;
	  }
	  
	  if(event.getInventory().equals(ItemShop.inven.get(p))){
		  event.setCancelled(true);
		  if(event.getCurrentItem().getType().equals(Material.SANDSTONE)){
			  Blocks.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.CHAINMAIL_CHESTPLATE)){
			  Armor.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.STONE_PICKAXE)){
			  Tools.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.GOLD_SWORD)){
			  Swords.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.BOW)){
			  Bows.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.APPLE)){
			  Food.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.CHEST)){
			  Chests.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.getMaterial(373))){
			  Potions.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.TNT)){
			  TNT.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.BLAZE_POWDER)){
			  Special.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.EMERALD)){
			  Switch.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.REDSTONE)){
			  Redstone.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.ICE)){
			  Fallen.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
	  }
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInv(InventoryClickEvent event) {
	  Player p = (Player) event.getWhoClicked();
	 
	  if(event.getInventory() == null){
		  return;
	  }
	  
	  if(event.getCurrentItem() == null){
		  return;
	  }
	  
	  if(TeamSelection.inv.get(p) == null){
		  return;
	  }
	  
	  if(event.getInventory().equals(TeamSelection.inv.get(p))){
		  event.setCancelled(true);
		  	Arena a = ArenaManager.getManager().returnArena(p);
			if(event.getCurrentItem() != null && !a.isStartet()){
				ItemStack is = event.getCurrentItem();
				if(is.getType() != null && is.getType().equals(Material.WOOL)){
					if(is.hasItemMeta()){
						  String Color = is.getItemMeta().getDisplayName();
						  Color = ChatColor.stripColor(Color);
						  Integer teamID = is.getAmount() - 1;
						  if(teamID != null){
								if(Team.hasTeam(p)){
									Team newTeam = a.getTeams().get(teamID);
									int Size = newTeam.getSize() + 1;
									if(Size <= 4){
										Team.removePlayer(p);
										a.teamAddPlayer(p, teamID);
										p.closeInventory();
										TeamSelection.openGui(p, a);
										//TagAPI.refreshPlayer(p);
										
										ItemStack ist = p.getItemInHand();
										ist.setDurability(is.getDurability());
										p.getInventory().remove(0);
										p.updateInventory();
										p.getInventory().setItem(0, ist);
										p.updateInventory();
										
										if(a.getTSize() >= 2){
											if(!a.isLobby()){
												a.lobbytimer();
											}
										}else{
											if(a.isLobby()){
												a.CancelLobby();
											}
										}
										return;
									}else{
										Messages.sendMessage(p, "Dieses Team ist bereits Voll", true);
										return;
									}
								}else{
									if(a.getTeams().get(teamID) != null){
										int Size = a.getTeams().get(teamID).getSize() + 1;
										if(Size <= 4){
											a.teamAddPlayer(p, teamID);
											p.closeInventory();
											TeamSelection.openGui(p, a);
											//TagAPI.refreshPlayer(p);
											
											ItemStack ist = p.getInventory().getItem(0);
											ist.setDurability(is.getDurability());
											p.getInventory().remove(0);
											p.updateInventory();
											p.getInventory().setItem(0, ist);
											p.updateInventory();
											
											if(a.getTSize() >= 2){
												if(!a.isLobby()){
													a.lobbytimer();
												}
											}else{
												if(a.isLobby()){
													a.CancelLobby();
												}
											}
										}else{
											Messages.sendMessage(p, "Dieses Team ist bereits Voll", true);
											return;
										}
									}else{
										Messages.sendMessage(p, "Es ist ein fehler aufgetreten #1007", true);
										return;
									}
									
								}
						  }
					}
				}
			}

	  }
	}
	
	@EventHandler
	public void playerEat(PlayerItemConsumeEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			if(e.getItem().getType().equals(Material.COOKED_BEEF)){
				int i = AchievementListener.Steak.get(p);
				i++;
				AchievementListener.Steak.put(p, i);
				if(i >= 20){
					AchievementListener.giveAchivement(p, 10);
				}
			}
		}
	}
	
	
	 @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	 public void pleardamagebyplayer(EntityDamageByEntityEvent event){
	 if(event.getEntity() instanceof Player){
		 if(event.getDamager() instanceof Player){
			 Player p = (Player) event.getEntity();
			 Player p2 = (Player) event.getDamager();
			 if(!ArenaManager.getManager().isInGame((Player) event.getEntity()) && ArenaManager.getManager().getSpec(p) == null){
				 return;
			 }
			 if(!ArenaManager.getManager().isInGame((Player) event.getDamager())){
				 event.setCancelled(true);
				 return;
			 }
			 
			 if(ArenaManager.getManager().getSpec((Player) event.getEntity()) != null){
				 event.setCancelled(true);
				 return;
			 }
			 
			 
			if(ArenaManager.getManager().returnArena(p) == null){
				return;
			}
			
			 	Arena a =  ArenaManager.getManager().returnArena(p);

				if(a.isStartet() == false){
					return;
				}
				
				 if(ArenaManager.getManager().sameTeam(Team.getTeam(p), Team.getTeam(p2))){
					 event.setDamage(0.0);
					 event.setCancelled(true);
					 event.setDamage(0.0);
					 return;
				 }
			 
			 Damageable dmg = p;
			 if(dmg.getHealth() - event.getDamage() <= 0){
					event.setCancelled(true);
					event.setDamage(0);
					tot(p, p2, a, "getötet");	
			 }
			 
		   }else if(event.getDamager() instanceof Arrow){
			   if(event.getEntity() instanceof Player){
				   Arrow arrow = (Arrow) event.getDamager();
				   Player shooter = (Player) arrow.getShooter();
				   Player p2 = shooter;
				   Player p = (Player) event.getEntity();
				   if(ArenaManager.getManager().getSpec(p) != null){
					   	
					   	
					   	p2.sendMessage("");
					   	shooter.sendMessage("");
					   	shooter.teleport(p2.getLocation().add(0, 5, 0));
					   	shooter.setFlying(true);
					   	
		                Vector velocity = arrow.getVelocity();
		                Arrow newArrow = shooter.launchProjectile(Arrow.class);
		                
	                    newArrow.setShooter(shooter);
	                    newArrow.setVelocity(velocity);
	                    newArrow.setBounce(false);
	                    event.setCancelled(true);
	                    arrow.remove();
	                    
				   }else{
				 		Arena a =  null;
		            	if(arrow.getShooter() instanceof Player) {
		            		p2 = (Player) arrow.getShooter();
		            		p = (Player) event.getEntity();
		            	}
		            	
		            	 a =  ArenaManager.getManager().returnArena(p);
		            	
						 if(ArenaManager.getManager().sameTeam(Team.getTeam(p), Team.getTeam(p2))){
							 event.setDamage(0.0);
							 event.setCancelled(true);
							 event.setDamage(0.0);
							 return;
						 }else{
							 Damageable dmg = p;
							 if(dmg.getHealth() - event.getDamage() <= 0){
								event.setCancelled(true);
								event.setDamage(0);
								tot(p, p2, a, "erschossen");	 
							 }
						 }
				   }
			   }

		   }else if(event.getDamager() instanceof Projectile){
			   Projectile arrow = (Projectile)event.getDamager();
		 		Player p2 = null;
		 		Player p = null;
		 		Arena a =  null;

            	if(arrow.getShooter() instanceof Player) {
            		p2 = (Player) arrow.getShooter();
            		p = (Player) event.getEntity();
            	}
            	
		 		 Team t1 = Team.getTeam(p);
				 Team t2 = Team.getTeam(p2);
            	
            	 a =  ArenaManager.getManager().returnArena(p);
            	 Damageable dmg = p;
				 if(t1.equals(t2)){
					 event.setDamage(0.0);
					 event.setCancelled(true);
					 event.setDamage(0.0);
					 return;
				 }else{
					 if(dmg.getHealth() - event.getDamage() <= 0){
							event.setCancelled(true);
							event.setDamage(0);
							tot(p, p2, a, "getötet");	
					 }
				 }
		   }
	 	}
	 }
	 
	    @SuppressWarnings("deprecation")
		@EventHandler(priority = EventPriority.HIGHEST)
	    public void onPlayerHurtPlayer(EntityDamageByEntityEvent event) {
	        Entity entityDamager = event.getDamager();
	        Entity entityDamaged = event.getEntity();
	       
	        if(!(entityDamager instanceof Arrow)) {
	        	if(event.getEntity() instanceof Player){
	          		 if(ArenaManager.getManager().getSpec((Player) event.getEntity()) != null){
	        			 event.setDamage(0);
	        			 event.setCancelled(true);
	        		 }
	        	}
	        }

	        
	        if(entityDamager instanceof Arrow) {
	            if(entityDamaged instanceof Player && ((Arrow) entityDamager).getShooter() instanceof Player) {
	                
	     
	                
	                Player damaged = (Player) entityDamaged;
	     
	                if(ArenaManager.getManager().getSpec(damaged) != null) {
	                    
	                   
	                    

	                }
	            }
	        }
	    }
	 
	 public void tot(Player p, Player p2, Arena a, String s){
		 if(Team.getTeam(p).getState()){
		 Utils.sendRound("§9Der Spieler " + ChatColor.translateAlternateColorCodes('&', Team.getTeam(p).getTeamColor()) + p.getName() + " §9wurde von " + ChatColor.translateAlternateColorCodes('&', Team.getTeam(p2).getTeamColor()) + p2.getName() + " §9" + s, true, a); 
		 AchievementListener.giveAchivement(p2, 2);
		 int i = AchievementListener.Kill.get(p2);
		 i++;
		 AchievementListener.Kill.put(p2, i);
		 if(i >= 15){
			 AchievementListener.giveAchivement(p2, 6);
		 }
		 
		 if(Main.permission.playerInGroup(p, "Admin") || Main.permission.playerInGroup(p, "Owner") || Main.permission.playerInGroup(p, "Supporter") || Main.permission.playerInGroup(p, "Builder")
		 || Main.permission.playerInGroup(p, "Eventmanager")){
			 AchievementListener.giveAchivement(p2, 9);
		 }
		 
		 Inventory inv = p.getInventory();
		 
		 
		 p.setHealth(20.0);
		 p.setFoodLevel(20);
		 p.getInventory().clear();
		 p.getInventory().setArmorContents(null);
		 p.updateInventory();
		 p.setSaturation((float) 4.0);
		 p.setNoDamageTicks(0);
		 p.setFallDistance(0);
		 p.setVelocity(p.getVelocity().zero());
		 p.teleport(Team.getTeam(p).getSpawn());
		 p.setFallDistance(0);
		 p.setVelocity(p.getVelocity().zero());
		 
		 ItemStack[] is = inv.getContents();
			 if(is != null){
				 for(ItemStack istack : is){
					 if(istack != null){
						 if(istack.getType() != null){
							 Material m = istack.getType();
							 if(!(m.equals(Material.LEATHER_BOOTS) || m.equals(Material.LEATHER_CHESTPLATE) || m.equals(Material.LEATHER_HELMET) ||
								  m.equals(Material.LEATHER_LEGGINGS) || m.equals(Material.AIR))){
								 ItemStack ist = istack;
								 ist.setAmount(Utils.rnd(0, ist.getAmount()));
								 if(ist.getAmount() > 0 && ist != null && !ist.getType().equals(Material.AIR)){
									 p.getWorld().dropItem(p.getLocation(), ist);
								 }
							 }
						 }
					 }
				 }
			 }
		 return; 
		 }else{
			 p.setHealth(20.0);
			 p.setFoodLevel(20);
			 p.getInventory().clear();
			 p.getInventory().setArmorContents(null);
			 p.updateInventory();
			 p.teleport(a.getLobby());
			 if(ArenaManager.getManager().checkTeam(a, p, true) == false){
				 ItemStack is = new ItemStack(Material.NETHER_STAR);
			     ItemMeta im = is.getItemMeta();
			     im.setDisplayName(Main.head + "Back to the lobby");
			     is.setItemMeta(im);
			     p.getInventory().setItem(8, is);
			     p.updateInventory();
				 p.setSaturation((float) 4.0);
				 p.setNoDamageTicks(0);
				 Messages.sendMessage(p, "Leider hast du diese Bedwars Runde Verloren :(", true);
				 AchievementListener.giveAchivement(p, 1);
				 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9kann nicht mehr Respawnen", true, a); 
				 return;
			 }else{
				 ItemStack is = new ItemStack(Material.NETHER_STAR);
			     ItemMeta im = is.getItemMeta();
			     im.setDisplayName(Main.head + "Back to the lobby");
			     is.setItemMeta(im);
			     p.getInventory().setItem(8, is);
			     p.updateInventory();
			     return;
			 }
		 }
	 }
	 
	 @SuppressWarnings("deprecation")
	public void sterben(Player p, Arena a){
		 if(Team.getTeam(p).getState()){
			 Utils.sendRound("§c" + p.getName() + " §9ist gestorben", true, a);
			 p.setFallDistance(0);
			 p.setHealth(20.0);
			 p.setFoodLevel(20);
			 p.getInventory().clear();
			 p.getInventory().setArmorContents(null);
			 p.updateInventory();
			 p.setSaturation((float) 4.0);
			 p.setNoDamageTicks(0);
			 p.setFallDistance(0);
			 p.setVelocity(p.getVelocity().zero());
			 p.teleport(Team.getTeam(p).getSpawn());
			 p.setFallDistance(0);
			 p.setVelocity(p.getVelocity().zero());
			 return;
		 }else{
			 p.setLastDamage(0);
			 p.setHealth(20.0);
			 p.setFoodLevel(20);
			 p.getInventory().clear();
			 p.getInventory().setArmorContents(null);
			 p.updateInventory();
			 p.setFallDistance(0);
			 p.setFallDistance(0);
			 p.setVelocity(p.getVelocity().zero());
			 p.teleport(a.getLobby());
			 p.setFallDistance(0);
			 p.setVelocity(p.getVelocity().zero());
			 p.setFallDistance(0);
			 Boolean state = ArenaManager.getManager().checkTeam(a, p, true);
			 
			 if(state == true){
				 return;
			 }else{
				 ItemStack is = new ItemStack(Material.NETHER_STAR);
			     ItemMeta im = is.getItemMeta();
			     im.setDisplayName(Main.head + "Back to the lobby");
			     is.setItemMeta(im);
			     p.getInventory().setItem(8, is);
			     p.updateInventory();
				 p.setSaturation((float) 4.0);
				 p.setNoDamageTicks(0);
				 Messages.sendMessage(p, "Leider hast du diese Bedwars Runde Verloren :(", true);
				 AchievementListener.giveAchivement(p, 1);
				 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9ist gestorben & kann nicht mehr Respawnen", true, a); 
				 return;
			 }
		 }
	 }
	 
	 @SuppressWarnings("deprecation")
	@EventHandler
	 public void onProjectileLaunch(ProjectileLaunchEvent event) {
		 if (event.getEntity().getShooter() instanceof Player && event.getEntity() instanceof EnderPearl) {
			 Player p = (Player) event.getEntity().getShooter();
			 int i = AchievementListener.EnderPearl.get(p);
			 i++;
			 AchievementListener.EnderPearl.put(p, i);
			 if(i > 5){
				 AchievementListener.giveAchivement(p, 6);
			 }
		 }
	 }
	 
	 @EventHandler(priority = EventPriority.HIGHEST)
	 public void playerdeath(PlayerDeathEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException{
	 if(event.getEntity() instanceof Player){
			 Player p = event.getEntity();
			 
			 if(!ArenaManager.getManager().isInGame(p) && ArenaManager.getManager().getSpec(p) == null){
				 return;
			 }
			 
			 if(ArenaManager.getManager().getSpec(p) != null){
					Object nmsPlayer = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
		            Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
		            Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");
		            for (Object ob : enumClass.getEnumConstants()) {
		              if (ob.toString().equals("PERFORM_RESPAWN")) {
		                packet = packet.getClass().getConstructor(new Class[] { enumClass }).newInstance(new Object[] { ob });
		              }
		            }
		            Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
		            con.getClass().getMethod("a", new Class[] { packet.getClass() }).invoke(con, new Object[] { packet });
					
					if(!p.getGameMode().equals(GameMode.ADVENTURE)){
						p.setGameMode(GameMode.ADVENTURE);
					}
					event.getDrops().clear();
					event.setDroppedExp(0);
					return;
			 }
			 
			 Arena a =  ArenaManager.getManager().returnArena(p);
			 
				if(a.isStartet() == false){
					return;
				}
				 if(Team.getTeam(p).getState()){
					 	event.getDrops().clear();
					 	event.setDroppedExp(0);
					 	event.setNewExp(0);
					 	event.setNewLevel(0);
					 	event.setDeathMessage(null);
					 	p.getInventory().clear();
					 	p.getInventory().setArmorContents(null);
					 	p.setFireTicks(0);
					 	p.setFallDistance(0);
					 	p.setWalkSpeed((float) 0.0);
					 	p.setWalkSpeed((float) 0.2);
					 	Object nmsPlayer = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
			            Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
			            Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");
			            for (Object ob : enumClass.getEnumConstants()) {
			              if (ob.toString().equals("PERFORM_RESPAWN")) {
			                packet = packet.getClass().getConstructor(new Class[] { enumClass }).newInstance(new Object[] { ob });
			              }
			            }
			            Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
			            con.getClass().getMethod("a", new Class[] { packet.getClass() }).invoke(con, new Object[] { packet });

					 	return;
				 }else{
					 
					 	Object nmsPlayer = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
			            Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
			            Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");
			            for (Object ob : enumClass.getEnumConstants()) {
			              if (ob.toString().equals("PERFORM_RESPAWN")) {
			                packet = packet.getClass().getConstructor(new Class[] { enumClass }).newInstance(new Object[] { ob });
			              }
			            }
			            Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
			            con.getClass().getMethod("a", new Class[] { packet.getClass() }).invoke(con, new Object[] { packet });
			            
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
			 }
		   }
	 	}
	 
	 @EventHandler(priority = EventPriority.NORMAL)
	 public void playerBlock(PlayerDropItemEvent event){
		 if(ArenaManager.getManager().isInGame(event.getPlayer())){
			 Player p = event.getPlayer();
			 if(event.getItemDrop() != null){
				 Material m = event.getItemDrop().getItemStack().getType();
				 if(m.equals(Material.LEATHER_BOOTS) || m.equals(Material.LEATHER_CHESTPLATE) || m.equals(Material.LEATHER_HELMET) ||
					m.equals(Material.LEATHER_LEGGINGS)){
					 AchievementListener.giveAchivement(p, 4);
				 }
			 }
		 }
	 }
	 
	 @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	 public void playerBlock(BlockPlaceEvent event){
		 	Player p = event.getPlayer();
			 if(!ArenaManager.getManager().isInGame(p)){
				 return;
			 }
			
			 if(event.getBlock().getType() == null){
				 return; 
			 }
			 
			 if(event.getBlockReplacedState().getType().equals(Material.getMaterial(8)) || 
				event.getBlockReplacedState().getType().equals(Material.getMaterial(9))){
				 event.setCancelled(true);
				 return; 
			 }
			 
			 Arena a =  ArenaManager.getManager().returnArena(p);
			 Team team = Team.getTeam(p);
			 Location l = team.getSpawn();
			 
			 if(event.getBlock().getLocation().distance(l) < 2){
				 event.setCancelled(true);
				 return;
			 }
			 
			 
			 
				if(a.isStartet() == false){
					return;
				}
			 a.getBlock().add(event.getBlock());
			 
			 int i = AchievementListener.Blocks.get(p);
			 i++;
			 AchievementListener.Blocks.put(p, i);
			 if(i >= 500){
				 AchievementListener.giveAchivement(p, 8);
			 }
	 }
	 
		@EventHandler
		public void entityExplodeevent(EntityExplodeEvent e)
		{
			Location l = e.getLocation();
			for(Arena a : ArenaManager.getManager().getArenaList()){
				if(isInside(l, a.getCorner1(), a.getCorner2())){	
					List<Block> destroy = new ArrayList<Block>();
					for(Block b : e.blockList()){
						if(a.getBlock().contains(b)){
							destroy.add(b);
						}
					}
					
					e.blockList().clear();
					
					for(Block b : destroy){
						if(a.getBlock().contains(b)){
							e.blockList().add(b);
							a.getBlock().remove(b);
						}
					}
					
					
					break;
				}
			}
		}
		
	public static Arena checkIFLobby(Player p){
		if(ArenaManager.getManager().isInGame(p)){
			return ArenaManager.getManager().returnArena(p);
		}
		
		for(Arena a : ArenaManager.getManager().getArenaList()){
			if(a.isStartet()){
				if(a.getLobbyPlayer().contains(p)){
					return a;
				}
			}
		}
		
		return null;
	}
		
		
	 @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	 public void pleardamage(EntityDamageEvent event){
	 if(event.getEntity() instanceof Player){
		 	Player p = (Player) event.getEntity();

			 if(!ArenaManager.getManager().isInGame((Player) event.getEntity())){
				 return;
			 }
			
			 DamageCause i = event.getCause();
			 if(i.name().equalsIgnoreCase("Entity_Attack") || i.name().equalsIgnoreCase("FALL") || i.name().equalsIgnoreCase("PROJECTILE")){
			 	 return;
			 }
			 	
			
			 Arena a =  ArenaManager.getManager().returnArena(p);
			 
				if(a.isStartet() == false){
					return;
				}
			 Damageable dmg = p;
			 if(dmg.getHealth() - event.getDamage() <= 0.5D){
				 event.setDamage(0);
				 event.setCancelled(true);
				 sterben(p, a);
			 }
		   
	 	}
	 }
	 
	@EventHandler
	 public static void OnInventoryCloseEvent(InventoryCloseEvent e){
		 Player p = (Player) e.getPlayer();
		 Inventory inventory = e.getInventory();
		 if(inventory.getName().startsWith("Team Kiste: ")){
			 Team team = Team.getTeam(p);
			 team.setEnderchest(inventory);
			 
			 if(!e.getViewers().isEmpty()){
				 for(HumanEntity pl : e.getViewers()){
					 Player player = (Player) pl;
					 player.updateInventory();
				 }
			 }
		 }
	 }

		@SuppressWarnings("deprecation")
		@EventHandler(priority = EventPriority.HIGHEST)
		public void onPlayerInteract(PlayerInteractEvent e){
			Player p = e.getPlayer();
			
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.BED_BLOCK)){
				if(ArenaManager.getManager().isInGame(p) && ArenaManager.getManager().returnArena(p).isStartet()){
					e.setCancelled(true);
				}
			}
			
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
				if(ArenaManager.getManager().isInGame(p) && ArenaManager.getManager().returnArena(p).isStartet()){
					e.setCancelled(true);
					Team team = Team.getTeam(p);
					if(team.getEnderchest() == null){
						Inventory inv = Bukkit.createInventory(null, 45, "Team Kiste: " + team.getName());
						p.openInventory(inv);
						team.setEnderchest(inv);
					}else{
						p.openInventory(team.getEnderchest());
					}		
				}
			}
			
			if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK ||
			   e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR){
				if(ArenaManager.getManager().isInGame(p) && ArenaManager.getManager().returnArena(p).isStartet()){
					if(e.getItem() != null){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().startsWith(Main.head + "Mobiler")){
									if(e.getItem().getItemMeta().hasLore()){
										String s = e.getItem().getItemMeta().getLore().get(0);
										s = ChatColor.stripColor(s);
										s = s.replace("x Benutzbar", "");
										int i = Integer.parseInt(s);
										i--;
										if(i > 0){
											e.setCancelled(true);
											ItemStack is = p.getInventory().getItemInHand();
											ItemMeta im = is.getItemMeta();
											List<String> Lore = new ArrayList<String>();
											Lore.add(0, i + "x Benutzbar");
											im.setLore(Lore);
											is.setItemMeta(im);
											int Amount = is.getAmount();
											is.setAmount(Amount);
											int Slot = p.getInventory().getHeldItemSlot();
											p.getInventory().remove(Slot);
											p.updateInventory();
											p.getInventory().setItem(Slot, is);
											p.updateInventory();
											ItemShop.openshop(e.getPlayer(), true);
										}else{
												e.setCancelled(true);
												int slot = p.getInventory().getHeldItemSlot();
												ItemStack is = p.getItemInHand();
												ItemMeta im = is.getItemMeta();
												int Amount = is.getAmount() - 1;
												List<String> Lore = new ArrayList<String>();
												Lore.add(0, i + "x Benutzbar");
												im.setLore(Lore);
												is.setItemMeta(im);
												is.setAmount(Amount);
												p.getInventory().setItem(slot, is);
												p.updateInventory();
												ItemShop.openshop(e.getPlayer(), true);
												p.updateInventory();
						
										}
									}
								}
							}
						}
					}
				}
			}
		}
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
	  Entity entity = event.getRightClicked();
	  if (!(entity instanceof NPC))
	      return;
	  	  NPC npc = (NPC) entity;
	  	  
	  	  if(!ArenaManager.getManager().isInGame(event.getPlayer())){
	  		  return;
	  	  }
	  	  
	  	  if(!ArenaManager.getManager().returnArena(event.getPlayer()).isStartet()){
	  		  return;
	  	  }
	  	  
	  	if(npc.getCustomName() != null){
	  		String s = npc.getCustomName();
	  		s = ChatColor.stripColor(s);
		  	  if(s.equalsIgnoreCase("Shop")){
		  		  ItemShop.openshop(event.getPlayer(), false);
		  	  }
	  	}
	}

	
	public void onTeleport(PlayerTeleportEvent e){
		if(e.getFrom().getWorld() == e.getTo().getWorld()){
			ArenaManager.getManager().leave(e.getPlayer(), true, true);
		}else{
			ArenaManager.getManager().leave(e.getPlayer(), true, false);
		}
	}
	
	@EventHandler
	public void changeSlot(PlayerItemHeldEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().getSpec(p) != null){
			int oldSlot = p.getInventory().getHeldItemSlot();
			int newSlot = e.getNewSlot();
			
			//zurück
			if(oldSlot < newSlot){
				if(p.getFlySpeed() >= 0.2){
					p.setFlySpeed((float) (p.getFlySpeed() -0.1));
				}
			}
			
			//vorwärts
			if(oldSlot > newSlot){
				if(p.getFlySpeed() <= 0.9){
					p.setFlySpeed((float) (p.getFlySpeed() +0.1));
				}
			}
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().getSpec(p) != null){
				Arena a = ArenaManager.getManager().getSpec(p);
				if(a.isStartet()){
						if(isInside(e.getFrom(), a.getCorner1(), a.getCorner2()) == true){
							if(isInside(e.getTo(), a.getCorner1(), a.getCorner2()) == false){
								ArenaManager.getManager().enterSpec(a, p);
							}else{
								for(Entity entity : p.getNearbyEntities(2.0D, 0.0D, 2.0D)){
					                if(entity instanceof Player){
					                	
					                	Player nearby = (Player) entity;
					                	if(ArenaManager.getManager().getSpec(nearby) == null){
							                Vector direction = p.getLocation().toVector().subtract(nearby.getLocation().toVector()).normalize();
							                direction.setX( direction.getX()*2 );
							                direction.setY( direction.getY()*2 );
							                direction.setZ( direction.getZ()*2 );
							                p.setVelocity(direction);
					                	}
					                }
					            }
							}
						}
				}
		}else{
			if(!ArenaManager.getManager().isInGame(p)){
				return;
			}
			Arena a = ArenaManager.getManager().returnArena(p);
			if(a.isStartet() == false){
				p.setHealth(20.0);
				p.setFoodLevel(20);
				p.setFireTicks(0);
				return;
			}
			
			if(ArenaManager.getManager().isInGame(p)){
				 if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.WOOD_PLATE) {
					 p.sendMessage("");
					 Block b = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF);
					 int subid = b.getData();
					 p.sendMessage(subid + "");
				 }
			}
			
			if(p.isFlying()){
				p.setFlying(false);
				p.setAllowFlight(false);
			}
			
			if(isInside(e.getTo(), a.getCorner1(), a.getCorner2()) == false){
				 sterben(p, a);
			}
		}
	}
	
	
	
	  public static Boolean isInside(Location loc, Location corner1, Location corner2)
	  {
	    double xMin = 0.0D;
	    double xMax = 0.0D;
	    double yMin = 0.0D;
	    double yMax = 0.0D;
	    double zMin = 0.0D;
	    double zMax = 0.0D;
	    double x = loc.getX();
	    double y = loc.getY();
	    double z = loc.getZ();
	    
	    xMin = Math.min(corner1.getX(), corner2.getX());
	    xMax = Math.max(corner1.getX(), corner2.getX());
	    
	    yMin = Math.min(corner1.getY(), corner2.getY());
	    yMax = Math.max(corner1.getY(), corner2.getY());
	    
	    zMin = Math.min(corner1.getZ(), corner2.getZ());
	    zMax = Math.max(corner1.getZ(), corner2.getZ());
	    if ((x >= xMin) && (x <= xMax) && (y >= yMin) && (y <= yMax) && (z >= zMin) && (z <= zMax)) {
	      return Boolean.valueOf(true);
	    }
	    return Boolean.valueOf(false);
	  }
	
		@EventHandler(priority = EventPriority.HIGHEST)
		public void onPlayerPickup(PlayerRespawnEvent e){
			Player p = e.getPlayer();
			if(ArenaManager.getManager().getSpec(p) != null){
				Arena a = ArenaManager.getManager().getSpec(p);
				Vector v1 = new Vector();
				v1.add(a.getCorner1().toVector());
				
				Vector v2 = new Vector();
				v2.add(a.getCorner2().toVector());
				
				if(v1.getY() > v2.getY()){
					v2.setY(v1.getY() - 2);
				}
				
				if(v2.getY() < v1.getY()){
					v1.setY(v2.getY() - 2);
				}
				
				Vector middle = v1.getMidpoint(v2);
				
				
				Location l = middle.toLocation(a.getCorner1().getWorld());
				e.setRespawnLocation(l);
				
				p.setCanPickupItems(false);
				p.setAllowFlight(true);
				p.setFlying(true);
				p.setNoDamageTicks(Integer.MAX_VALUE);
			}
			
			if(ArenaManager.getManager().isInGame(p)){
				Arena a = ArenaManager.getManager().returnArena(p);
				if(a.isStartet()){
					 sterben(p, a);
				}else{
					e.setRespawnLocation(a.getLobby());
					return;
				}
			}

		}
	  
	@EventHandler
	public void onPlayerPickup(PlayerPickupItemEvent e){
		if(e.getPlayer() == null){
			return;
		}
		
		if(e.getItem() == null){
			return;
		}
		
		if(!ArenaManager.getManager().isInGame(e.getPlayer())){
			return;
		}
		
	  	  if(!ArenaManager.getManager().returnArena(e.getPlayer()).isStartet()){
	  		  return;
	  	  }
		
		if(e.getItem().getItemStack().hasItemMeta()){
			if(e.getItem().getItemStack().getItemMeta().hasDisplayName()){
				String s = ChatColor.stripColor(e.getItem().getItemStack().getItemMeta().getDisplayName());
				if(s.equalsIgnoreCase("Bronze")){
					if(e.getItem().getItemStack().getItemMeta().hasLore()){
						ItemStack is = e.getItem().getItemStack();
						ItemMeta im = is.getItemMeta();
						im.setLore(null);
						is.setItemMeta(im);
						e.getItem().setItemStack(is);
					}
				}else if(s.equalsIgnoreCase("Eisen")){
					if(e.getItem().getItemStack().getItemMeta().hasLore()){
						ItemStack is = e.getItem().getItemStack();
						ItemMeta im = is.getItemMeta();
						im.setLore(null);
						is.setItemMeta(im);
						e.getItem().setItemStack(is);
					}
				}else if(s.equalsIgnoreCase("Gold")){
					if(e.getItem().getItemStack().getItemMeta().hasLore()){
						ItemStack is = e.getItem().getItemStack();
						ItemMeta im = is.getItemMeta();
						im.setLore(null);
						is.setItemMeta(im);
						e.getItem().setItemStack(is);
					}
				}
			}
		}
	}
	
	
}