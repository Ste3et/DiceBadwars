package me.Ste3et_C0st.DiceBedWars.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.GUI.ItemShop;
import me.Ste3et_C0st.DiceBedWars.GUI.TeamSelection;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import me.Ste3et_C0st.DiceBedWars.Manager.Utils;
import me.Ste3et_C0st.GUI.Shop.Armor;
import me.Ste3et_C0st.GUI.Shop.Blocks;
import me.Ste3et_C0st.GUI.Shop.Bows;
import me.Ste3et_C0st.GUI.Shop.Chests;
import me.Ste3et_C0st.GUI.Shop.Food;
import me.Ste3et_C0st.GUI.Shop.Potions;
import me.Ste3et_C0st.GUI.Shop.Special;
import me.Ste3et_C0st.GUI.Shop.Switch;
import me.Ste3et_C0st.GUI.Shop.Swords;
import me.Ste3et_C0st.GUI.Shop.Tools;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
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
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.kitteh.tag.TagAPI;

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
			
				if(a.teamGetPlayer(e.getPlayer()) == 0){
					return;
				}
				int i = a.teamGetPlayer(e.getPlayer());
				
				if(e.getBlock().getLocation().equals(a.getBed(i)) || e.getBlock().getRelative(a.getRotation(i)).equals(a.getBlock(i))){
					e.setCancelled(true);
					Messages.sendMessage(e.getPlayer(), "Du kannst dein eigenes Bett nicht kaputt machen -.-", true);
					return;
				}
				
				if(!a.teamGetList(1).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(1))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(1) ) + a.getName(1) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(1)).setType(Material.AIR);
						a.setBedState(1, false);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(1)).equals(a.getBlock(1))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(1) ) + a.getName(1) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(1));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(1, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}

				if(!a.teamGetList(2).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(2))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(2) ) + a.getName(2) + " §7wurde abgebaut", true, a);
						a.setBedState(2, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(2)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(2)).equals(a.getBlock(2))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(2) ) + a.getName(2) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						a.setBedState(1, false);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(2));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}
				
				if(!a.teamGetList(3).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(3))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(3) ) + a.getName(3) + " §7wurde abgebaut", true, a);
						a.setBedState(3, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(3)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(3)).equals(a.getBlock(3))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(3) ) + a.getName(3) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(3));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(3, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}

				if(!a.teamGetList(4).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(4))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(4) ) + a.getName(4) + " §7wurde abgebaut", true, a);
						a.setBedState(4, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(4)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(4)).equals(a.getBlock(4))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(4) ) + a.getName(4) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(4));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(4, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}

				if(!a.teamGetList(5).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(5))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(5) ) + a.getName(5) + " §7wurde abgebaut", true, a);
						a.setBedState(5, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(5)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(5)).equals(a.getBlock(5))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(5) ) + a.getName(5) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(5));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(5, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}

				if(!a.teamGetList(6).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(6))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(6) ) + a.getName(6) + " §7wurde abgebaut", true, a);
						a.setBedState(6, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(6)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(6)).equals(a.getBlock(6))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(6) ) + a.getName(6) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(6));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(6, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}
				
				
				if(!a.teamGetList(7).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(7))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(7) ) + a.getName(7) + " §7wurde abgebaut", true, a);
						a.setBedState(7, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(7)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(7)).equals(a.getBlock(7))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(7) ) + a.getName(7) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(7));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(7, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}
				
				if(!a.teamGetList(8).isEmpty()){
					if(e.getBlock().getLocation().equals(a.getBed(8))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(8) ) + a.getName(8) + " §7wurde abgebaut", true, a);
						a.setBedState(8, false);
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getRelative(a.getRotation(8)).setType(Material.AIR);
						return;
					}
					
					if(e.getBlock().getRelative(a.getRotation(8)).equals(a.getBlock(8))){
						Utils.sendRound("Das Bett von Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(8) ) + a.getName(8) + " §7wurde abgebaut", true, a);
						e.setCancelled(true);
						Block bl = e.getBlock();
						Block bL = e.getBlock().getRelative(a.getRotation(8));
						bL.setType(Material.AIR);
						bl.setType(Material.AIR);
						a.setBedState(8, false);
						return;
					}
				}else{
					Messages.sendMessage(e.getPlayer(), "Dieses Team ist Leer", true);
					return;
				}
				
				
		}else{
			if(a.getBlock().contains(e.getBlock())){
				if(e.getBlock().getType().equals(Material.ENDER_CHEST)){
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.ENDER_CHEST));
				}
				a.getBlock().remove(e.getBlock());
				return;
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			if(a.isStartet()){
				if(a.teamGetPlayer(p) != 0){
					int i = a.teamGetPlayer(p);
					event.setCancelled(true);
					if(event.getMessage().startsWith("@all") || event.getMessage().startsWith("@All")){
						for(Player pl : Bukkit.getOnlinePlayers()){
							if(ArenaManager.getManager().isInGame(pl)){
								pl.sendMessage("§7[@all] " + ChatColor.translateAlternateColorCodes('&', a.returnColor(i)) + p.getName() + "§r:" + event.getMessage().replace("@all", ""));
							}else{
								event.getRecipients().remove(pl);
							}
						}
						return;
					}
					
					for(Player pl : Bukkit.getOnlinePlayers()){
						if(a.teamGetPlayer(pl) != i){
							event.getRecipients().remove(pl);
						}else{
							pl.sendMessage(ChatColor.translateAlternateColorCodes('&', a.returnColor(i)) + p.getName() + "§r: " + event.getMessage());
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
			  Special.openInv(p);
			  ItemShop.inven.remove(p);
			  return;
		  }
		  
		  if(event.getCurrentItem().getType().equals(Material.EMERALD)){
			  Switch.openInv(p);
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
						  Integer teamID = a.returnTeamFromColor(Color.split(" ")[1]);
						  if(teamID != 0){
								Integer oldTeam = a.teamGetPlayer(p);
								if(oldTeam != 0){
									int Size = a.teamSize(teamID) + 1;
									if(Size <= 4){
										a.teamRemovePlayer(p, a.teamGetPlayer(p));
										a.teamAddPlayer(p, teamID);
										p.closeInventory();
										TeamSelection.openGui(p, a);
										TagAPI.refreshPlayer(p);
										int i = 0;
										
										if(a.teamSize(1) >= 1){
										i++;	
										}
										
										if(a.teamSize(2) >= 1){
											i++;	
										}
										
										if(a.teamSize(3) >= 1){
											i++;	
										}
										
										if(a.teamSize(4) >= 1){
											i++;	
										}
										
										if(a.teamSize(5) >= 1){
											i++;	
										}
										
										if(a.teamSize(6) >= 1){
											i++;	
										}
										
										if(a.teamSize(7) >= 1){
											i++;	
										}
										
										if(a.teamSize(8) >= 1){
											i++;	
										}
										
										if(i >= 2){
											if(a.isLobby() == false){
								        		a.lobbytimer();
								        	}
										}
									}
								}else{
									int Size = a.teamSize(teamID) + 1;
									if(Size <= 4){
										a.teamAddPlayer(p, teamID);
										p.closeInventory();
										TeamSelection.openGui(p, a);
										TagAPI.refreshPlayer(p);
										int i = 0;
										
										if(a.teamSize(1) >= 1){
										i++;	
										}
										
										if(a.teamSize(2) >= 1){
											i++;	
										}
										
										if(a.teamSize(3) >= 1){
											i++;	
										}
										
										if(a.teamSize(4) >= 1){
											i++;	
										}
										
										if(a.teamSize(5) >= 1){
											i++;	
										}
										
										if(a.teamSize(6) >= 1){
											i++;	
										}
										
										if(a.teamSize(7) >= 1){
											i++;	
										}
										
										if(a.teamSize(8) >= 1){
											i++;	
										}
										
										if(i >= 2){
											if(a.isLobby() == false){
								        		a.lobbytimer();
								        	}
										}
									}
								}
								
								ItemStack ItemS = p.getItemInHand();
								ItemS.setDurability(TeamSelection.returnDurability(a.getColor(a.teamGetPlayer(p))));
								p.setItemInHand(ItemS);
								p.updateInventory();
								TagAPI.refreshPlayer(p);
								int i = 0;
								
								if(a.teamSize(1) >= 1){
								i++;	
								}
								
								if(a.teamSize(2) >= 1){
									i++;	
								}
								
								if(a.teamSize(3) >= 1){
									i++;	
								}
								
								if(a.teamSize(4) >= 1){
									i++;	
								}
								
								if(a.teamSize(5) >= 1){
									i++;	
								}
								
								if(a.teamSize(6) >= 1){
									i++;	
								}
								
								if(a.teamSize(7) >= 1){
									i++;	
								}
								
								if(a.teamSize(8) >= 1){
									i++;	
								}
								
								
								if(i >= 2){
									if(a.isLobby() == false){
						        		a.lobbytimer();
						        	}
								}
						  }
					}
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
			 if(!ArenaManager.getManager().isInGame((Player) event.getEntity()) || ArenaManager.getManager().getSpec(p) == null){
				 return;
			 }
			 
			 if(ArenaManager.getManager().getSpec(p) != null || ArenaManager.getManager().getSpec(p2) != null){
				 event.setDamage(0);
				 event.setCancelled(true);
			 }
			 
			 if(!ArenaManager.getManager().isInGame((Player) event.getDamager())){
				 event.setCancelled(true);
				 return;
			 }
			 
			 
			
			 Arena a =  ArenaManager.getManager().returnArena(p);

				if(a.isStartet() == false){
					return;
				}
				 
				 if(a.teamGetPlayer(p) == a.teamGetPlayer(p2)){
					 event.setDamage(0.0);
					 event.setCancelled(true);
					 event.setDamage(0.0);
					 return;
				 }
			 
			 Damageable dmg = p;
			 if(dmg.getHealth() - event.getDamage() <= 0){
				 if(a.isBed(a.teamGetPlayer(p))){
					 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9wurde von§2 " + p2.getName() + " §9getötet", true, a); 
					 event.setCancelled(true);
					 p.teleport(a.getTeam(a.teamGetPlayer(p)));
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
					 return;
				 }else{
					 event.setDamage(0);
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
					 p.teleport(a.getLobby());
					 if(ArenaManager.getManager().checkTeam(a, p) == false){
						 ItemStack is = new ItemStack(Material.NETHER_STAR);
					     ItemMeta im = is.getItemMeta();
					     im.setDisplayName(Main.head + "Back to the lobby");
					     is.setItemMeta(im);
					     p.getInventory().setItem(8, is);
					     p.updateInventory();
						 Messages.sendMessage(p, "Leider hast du diese Bedwars Runde Verloren :(", true);
						 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9kann nicht mehr Respawnen", true, a); 
						 return;
					 }else{
						 ItemStack is = new ItemStack(Material.NETHER_STAR);
					     ItemMeta im = is.getItemMeta();
					     im.setDisplayName(Main.head + "Back to the lobby");
					     is.setItemMeta(im);
					     p.getInventory().setItem(8, is);
					     p.updateInventory();
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
            	
            	a =  ArenaManager.getManager().returnArena(p);
            	
				 if(a.teamGetPlayer(p) == a.teamGetPlayer(p2)){
					 event.setDamage(0.0);
					 event.setCancelled(true);
					 event.setDamage(0.0);
					 return;
				 }
		   }else if(event.getDamager() instanceof Arrow){
		 		Arrow arrow = (Arrow)event.getDamager();
		 		Player p2 = null;
		 		Player p = null;
		 		Arena a =  null;
             	if(arrow.getShooter() instanceof Player) {
             		p2 = (Player) arrow.getShooter();
             		p = (Player) event.getEntity();
             	}
             	
             	a =  ArenaManager.getManager().returnArena(p);
             	
				 if(a.teamGetPlayer(p) == a.teamGetPlayer(p2)){
					 event.setDamage(0.0);
					 event.setCancelled(true);
					 event.setDamage(0.0);
					 return;
				 }
		   }
	 	}
	 }
	 
	 @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	 public void playerdeath(PlayerDeathEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException{
	 if(event.getEntity() instanceof Player){
			 Player p = event.getEntity();
			 
			 if(!ArenaManager.getManager().isInGame(p) || ArenaManager.getManager().getSpec(p) == null){
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
			 }
			 
			 Arena a =  ArenaManager.getManager().returnArena(p);
			 
				if(a.isStartet() == false){
					return;
				}
				 if(a.isBed(a.teamGetPlayer(p))){
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
					 
					 if(ArenaManager.getManager().checkTeam(a, p) == false){
						 	event.getDrops().remove(new ItemStack(Material.LEATHER_BOOTS));
						 	event.getDrops().remove(new ItemStack(Material.LEATHER_HELMET));
						 	event.getDrops().remove(new ItemStack(Material.LEATHER_LEGGINGS));
						 	event.setDroppedExp(0);
						 	event.setNewExp(0);
						 	event.setNewLevel(0);
						 	event.setDeathMessage(null);
						 	p.getInventory().clear();
						 	p.getInventory().setArmorContents(null);
						 	return;
				 }
			 }
		   }
	 	}
	 
	 @EventHandler(priority = EventPriority.NORMAL)
	 public void playerBlock(BlockPlaceEvent event){
		 	Player p = event.getPlayer();
			 if(!ArenaManager.getManager().isInGame(p)){
				 return;
			 }
			
			 if(event.getBlock().getType() == null){
				 return; 
			 }
			 Arena a =  ArenaManager.getManager().returnArena(p);
			 int Team = a.teamGetPlayer(p);
			 Location l = a.getTeam(Team);
			 
			 if(event.getBlock().getLocation().distance(l) < 2){
				 event.setCancelled(true);
				 return;
			 }
			 
			 
			 
				if(a.isStartet() == false){
					return;
				}
			 a.getBlock().add(event.getBlock());
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
				 if(a.isBed(a.teamGetPlayer(p))){
					 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9ist gestorben", true, a); 
					 p.teleport(a.getTeam(a.teamGetPlayer(p)));
					 event.setCancelled(true);
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
					 return;
				 }else{
					 event.setDamage(0);
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
					 p.teleport(a.getLobby());
					 Boolean state = ArenaManager.getManager().checkTeam(a, p);
					 
					 if(state == true){
						 return;
					 }else{
						 ItemStack is = new ItemStack(Material.NETHER_STAR);
					     ItemMeta im = is.getItemMeta();
					     im.setDisplayName(Main.head + "Back to the lobby");
					     is.setItemMeta(im);
					     p.getInventory().setItem(8, is);
					     p.updateInventory();
						 Messages.sendMessage(p, "Leider hast du diese Bedwars Runde Verloren :(", true);
						 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9ist gestorben & kann nicht mehr Respawnen", true, a); 
						 return;
					 }
				 }
			 }
		   
	 	}
	 }
	 
	@SuppressWarnings("deprecation")
	@EventHandler
	 public static void OnInventoryCloseEvent(InventoryCloseEvent e){
		 Player p = (Player) e.getPlayer();
		 Inventory inventory = e.getInventory();
		 if(inventory.getName().startsWith("Team Kiste: ")){
			 Arena a = ArenaManager.getManager().returnArena(p);
			 int i = a.teamGetPlayer(p);
			 a.setEnderchest(i, inventory);
			 
			 
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
					Arena a = ArenaManager.getManager().returnArena(p);
					int i = a.teamGetPlayer(p);
					if(a.getEnderchest(i) == null){
						Inventory inv = Bukkit.createInventory(null, 45, "Team Kiste: " + a.getName(a.teamGetPlayer(p)));
						p.openInventory(inv);
					}else{
						p.openInventory(a.getEnderchest(i));
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
	
	public void PlayerHide(Arena a, Player p) {
		for(Player player : a.getPlayers()){
			player.hidePlayer(p);
		}
	}
	
	public void onTeleport(PlayerTeleportEvent e){
		if(e.getFrom().getWorld() == e.getTo().getWorld()){
			ArenaManager.getManager().leave(e.getPlayer(), true, true);
		}else{
			ArenaManager.getManager().leave(e.getPlayer(), true, false);
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
			
			if(p.isFlying()){
				p.setFlying(false);
				p.setAllowFlight(false);
			}
			
			if(isInside(e.getTo(), a.getCorner1(), a.getCorner2()) == false){
				 if(a.isBed(a.teamGetPlayer(p))){
					 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9ist gestorben", true, a); 
					 p.setFallDistance(0);
					 p.teleport(a.getTeam(a.teamGetPlayer(p)));
					 p.setFallDistance(0);
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
					 p.damage(0.0);
					 return;
				 }else{
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.getInventory().clear();
					 p.getInventory().setArmorContents(null);
					 p.updateInventory();
					 p.teleport(a.getLobby());
					 if(ArenaManager.getManager().checkTeam(a, p) == false){
						 ItemStack is = new ItemStack(Material.NETHER_STAR);
					     ItemMeta im = is.getItemMeta();
					     im.setDisplayName(Main.head + "Back to the lobby");
					     is.setItemMeta(im);
					     p.getInventory().setItem(8, is);
					     p.updateInventory();
						 Messages.sendMessage(p, "Leider hast du diese Bedwars Runde Verloren :(", true);
						 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9kann nicht mehr Respawnen", true, a); 
						 return;
					 }else{
						 ItemStack is = new ItemStack(Material.NETHER_STAR);
					     ItemMeta im = is.getItemMeta();
					     im.setDisplayName(Main.head + "Back to the lobby");
					     is.setItemMeta(im);
					     p.getInventory().setItem(8, is);
					     p.updateInventory();
					 }
				 }
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
	
		@SuppressWarnings("deprecation")
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
					if(a.isBed(a.teamGetPlayer(p))){
						 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9ist gestorben", true, a); 
						 e.setRespawnLocation(a.getTeam(a.teamGetPlayer(p)));
						 p.setHealth(20.0);
						 p.setFoodLevel(20);
						 p.getInventory().clear();
						 p.getInventory().setArmorContents(null);
						 p.updateInventory();
						 return;
					 }else{
						 p.setHealth(20.0);
						 p.setFoodLevel(20);
						 p.getInventory().clear();
						 p.getInventory().setArmorContents(null);
						 p.updateInventory();
						 e.setRespawnLocation(a.getExit());
						 p.teleport(a.getLobby());
						 if(ArenaManager.getManager().checkTeam(a, p) == false){
							 ItemStack is = new ItemStack(Material.NETHER_STAR);
						     ItemMeta im = is.getItemMeta();
						     im.setDisplayName(Main.head + "Back to the lobby");
						     is.setItemMeta(im);
						     p.getInventory().setItem(8, is);
						     p.updateInventory();
							 Messages.sendMessage(p, "Leider hast du diese Bedwars Runde Verloren :(", true);
							 Utils.sendRound("§9Der Spieler §c" + p.getName() + " §9kann nicht mehr Respawnen", true, a); 
							 a.getPlayers().remove(p);
							 return;
						 }else{
							 ItemStack is = new ItemStack(Material.NETHER_STAR);
						     ItemMeta im = is.getItemMeta();
						     im.setDisplayName(Main.head + "Back to the lobby");
						     is.setItemMeta(im);
						     p.getInventory().setItem(8, is);
						     p.updateInventory();
						 }
					 }
				}else{
					e.setRespawnLocation(a.getLobby());
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