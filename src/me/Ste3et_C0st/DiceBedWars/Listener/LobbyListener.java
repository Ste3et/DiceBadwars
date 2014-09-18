package me.Ste3et_C0st.DiceBedWars.Listener;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.save;
import me.Ste3et_C0st.DiceBedWars.GUI.TeamGui;
import me.Ste3et_C0st.DiceBedWars.GUI.TeamSelection;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.DiceBedWars.Manager.Team;
import me.Ste3et_C0st.DiceBedWars.Manager.Utils;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class LobbyListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void SignChangEvent(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("[BedWars]")){
			if(e.getPlayer().hasPermission("BedWars.admin")){
				e.getLine(0);
				e.setLine(0, "§8[§2BedWars§8]");
			Player p = e.getPlayer();
				
			if(!ArenaManager.getManager().ArenaExist(ChatColor.stripColor(e.getLine(1)))){
				Messages.sendMessage(p, "Diese Arena Existiert nicht", true);
				e.setCancelled(true);
			}else{
				int Aid = ArenaManager.getManager().getIDBackfromName(ChatColor.stripColor(e.getLine(1)));
				Arena a = ArenaManager.getManager().getArena(Aid);
				e.setLine(1, "§8BedWars-" + a.getId());
				e.setLine(2, "§8Map : §2" + e.getLine(2));
				e.setLine(3, "§8" + a.getPlayers().size() + "/" + 4*4);
				a.updateSign(e.getBlock().getLocation());
				p.sendMessage(a.getName());
				save.saveMap(a.getName());
			}
			}else{
				Messages.sendMessage(e.getPlayer(), "Dazu hast du keine berechtigung", true);
				e.setCancelled(true);
			}
			}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock() != null){
				if(e.getClickedBlock().getType().equals(Material.SIGN) || e.getClickedBlock().getType().equals(Material.SIGN_POST) ||
				   e.getClickedBlock().getType().equals(Material.WALL_SIGN)){
					Sign s = (Sign) e.getClickedBlock().getState();
					if(ChatColor.stripColor(s.getLine(0)).equalsIgnoreCase("[BedWars]")){
						String l2 = ChatColor.stripColor(s.getLine(1));
						l2 = l2.replace("BedWars-", "");
						if(ArenaManager.getManager().getArena(Integer.parseInt(l2)) != null){
							int Aid = Integer.parseInt(l2);
							Arena a = ArenaManager.getManager().getArena(Aid);
							if(ArenaManager.getManager().isInGame(p)){
								Messages.sendMessage(p, "Du befindest dich bereits in einer Arena", true);
								return;
							}
							
							if(a.isStartet() == true){
								ArenaManager.getManager().addSpec(a, p);
								return;
							}else{
								ArenaManager.getManager().addPlayer(p, Aid);
							}
							
							
						}
					}
				}
			}
			if(e.getItem() != null){
				if(e.getItem().getType().equals(Material.NAME_TAG)){
					if(Editor.isInEditor(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Slector")){
									Location l =  e.getClickedBlock().getLocation();
									String s = Editor.getEditorName(p);
									if(e.getAction() == Action.LEFT_CLICK_BLOCK){
										Editor.setc1(l, s);
										Messages.sendMessage(p, "Arena Corner 1 wurde gesetzt", true);
										
										ItemStack is = e.getItem();
										ItemMeta im = is.getItemMeta();
										List<String> lore = im.getLore();
										lore.set(1, " §cWorld:" + l.getWorld().getName());
										lore.set(2, " §cX: " + l.getBlockX());
										lore.set(3, " §cY: " + l.getBlockY());
										lore.set(4, " §cZ: " + l.getBlockZ());
										im.setLore(lore);
										is.setItemMeta(im);
										p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
										p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
										p.updateInventory();
										e.setCancelled(true);
										return;
									}
									
									if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
										Editor.setc2(l, s);
										Messages.sendMessage(p, "Arena Corner 2 wurde gesetzt", true);
										
										ItemStack is = e.getItem();
										ItemMeta im = is.getItemMeta();
										List<String> lore = im.getLore();
										lore.set(6, " §bWorld:" + l.getWorld().getName());
										lore.set(7, " §bX: " + l.getBlockX());
										lore.set(8, " §bY: " + l.getBlockY());
										lore.set(9, " §bZ: " + l.getBlockZ());
										im.setLore(lore);
										is.setItemMeta(im);
										p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
										p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
										p.updateInventory();
										e.setCancelled(true);
										return;
									}
								}
							}
						}
					}
				}else if(e.getItem().getType().equals(Material.DIAMOND_SWORD)){
					if(Editor.isInEditor(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Set Lobby")){
									Location l = e.getClickedBlock().getLocation();
									l.setY(l.getY() + 1);
									Editor.setlobby(l, Editor.getEditorName(p));
									Messages.sendMessage(p, "Arena Lobby wurde gesetzt", true);
								}
								
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Set Exit")){
									Location l = e.getClickedBlock().getLocation();
									l.setY(l.getY() + 1);
									Editor.setExit(l, Editor.getEditorName(p));
									Messages.sendMessage(p, "Arena Exit wurde gesetzt", true);
								}
							}
						}
					}
				}
			}
		}
		
		if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR){
			if(e.getItem() != null){
				if(e.getItem().getType().equals(Material.MOB_SPAWNER)){
					if(Editor.isInEditor(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Spawner: Bronze")){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									im.setDisplayName(Main.head + "Spawner: Eisen");
									is.setItemMeta(im);
									p.getInventory().setItem(2, is);
									p.updateInventory();
									return;
								}
								
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Spawner: Eisen")){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									im.setDisplayName(Main.head + "Spawner: Gold");
									is.setItemMeta(im);
									p.getInventory().setItem(2, is);
									p.updateInventory();
									return;
								}
								
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Spawner: Gold")){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									im.setDisplayName(Main.head + "Spawner: Bronze");
									is.setItemMeta(im);
									p.getInventory().setItem(2, is);
									p.updateInventory();
									return;
								}
							}
						}
					}
				}else if(e.getItem().getType().equals(Material.DIAMOND_SWORD)){
					if(Editor.isInEditor(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Set Lobby")){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									im.setDisplayName(Main.head + "Set Exit");
									is.setItemMeta(im);
									p.getInventory().setItem(3, is);
									p.updateInventory();
									return;
								}
								
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Set Exit")){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									im.setDisplayName(Main.head + "Set Lobby");
									is.setItemMeta(im);
									p.getInventory().setItem(3, is);
									p.updateInventory();
									return;
								}
							}
						}
					}
				}
			}
		}
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK ||
		   e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR){
			if(e.getItem() != null){
				if(e.getItem().getType().equals(Material.BED)){
					if(Editor.isInEditor(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Team Editor")){
									TeamGui.openInv1(p);
									e.setCancelled(true);
									return;
								}
							}
						}
					}
				}
				
				if(e.getItem().getType().equals(Material.WOOL)){
					if(ArenaManager.getManager().isInGame(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Team Selector")){
									TeamSelection.openGui(p, ArenaManager.getManager().returnArena(p));
									e.setCancelled(true);
									return;
								}
							}
						}
					}
				}
				
				if(e.getItem().getType().equals(Material.GLASS)){
					if(p.getInventory().getItemInHand().hasItemMeta()){
						if(p.getInventory().getItemInHand().getItemMeta().hasDisplayName()){
							if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.stripColor("Rettungs"))){
								if(!p.isOnGround()){
									List<Location> circs =  Utils.circle(p, p.getLocation(), 2, 2, true, true, 1);
									Arena a  = ArenaManager.getManager().returnArena(p);
									int i = 0;
									double y = p.getLocation().getY() - 1;
									for (Location loc : circs) {
										loc.setY(y);
										if(loc.getBlock().isEmpty()){
											 i++;
											 loc.getBlock().setType(Material.GLASS);
											 a.getBlock().add(loc.getWorld().getBlockAt(loc));
										}
							        }
									
									if(i == 0){
										Messages.sendMessage(p, "Hier konnte die Platform nicht erstellt Werden", true);
									}else{
										int Slot = p.getInventory().getHeldItemSlot();
										ItemStack is = p.getItemInHand();
										int iA = is.getAmount() - 1;
										is.setAmount(iA);
										p.updateInventory();
										p.getInventory().setItem(Slot, is);
										p.updateInventory();
									}
								}
							}
						}
					}
				}
				
				if(e.getItem().getType().equals(Material.GLASS)){
					if(p.getInventory().getItemInHand().hasItemMeta()){
						String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
						s = s.replace(Main.head, "");
						s = ChatColor.stripColor(s);
						if(s.startsWith("Set")){
							p.chat("/npc create &6Shop --type VILLAGER");
							e.setCancelled(true);
							return;
						}
					}
				}
				
				if(e.getItem().getType().equals(Material.DIAMOND)){
					if(p.getInventory().getItemInHand().hasItemMeta()){
						String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
						s = s.replace(Main.head, "");
						s = ChatColor.stripColor(s);
						if(s.startsWith("Finish")){
							Editor.create(p);
						}
					}
				}
				
				if(e.getItem().getType().equals(Material.NETHER_STAR)){
					if(p.getInventory().getItemInHand().hasItemMeta()){
						String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
						s = s.replace(Main.head, "");
						s = ChatColor.stripColor(s);
						if(s.startsWith("Back to")){
							if(ArenaManager.getManager().isInGame(p)){
					            for(Player player : Bukkit.getOnlinePlayers()){
				            		p.showPlayer(player);
					            }
								if(ArenaManager.getManager().returnArena(p).isStartet() == true){
									if(ArenaManager.getManager().returnArena(p) != null){
											Arena a = ArenaManager.getManager().returnArena(p);
											p.teleport(ArenaManager.getManager().returnArena(p).getExit());
											ArenaManager.getManager().removePlayer(p);
											a.getPlayers().remove(p);
											Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
											a.getLobbyPlayer().remove(p);
											
											if(!a.getSign().isEmpty()){
										        for(Location loc : a.getSign()){
										               a.updateSign(loc);
										        }
											}
											
											return;
									}
	
								}
								if(ArenaManager.getManager().isLeave(p, ArenaManager.getManager().returnArena(p), 0) == false){
									if(ArenaManager.getManager().returnArena(p) != null){
										Arena a = ArenaManager.getManager().returnArena(p);
										p.teleport(ArenaManager.getManager().returnArena(p).getExit());
										ArenaManager.getManager().removePlayer(p);
										Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
										a.getPlayers().remove(p);
										a.getLobbyPlayer().remove(p);
										
										if(!a.getSign().isEmpty()){
									        for(Location loc : a.getSign()){
									               a.updateSign(loc);
									        }
										}
										
										return;
									}
									
								}
							}else if(ArenaManager.getManager().getSpec(p) != null){
								ArenaManager.getManager().leave(p, true, false);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDisconnect(PlayerQuitEvent e){
		Player p = e.getPlayer();
		ArenaManager.getManager().leave(p, true, false);
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			 if(ArenaManager.getManager().checkTeam(a, p) == false){
				 p.teleport(a.getExit());
				 ArenaManager.getManager().removePlayer(p);
				 a.getPlayers().remove(p);
				 if(ArenaManager.getManager().checkTeam(a, p) == false){
					 	Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
						a.getPlayers().remove(p);
						a.getLobbyPlayer().remove(p);
						
						if(!a.getSign().isEmpty()){
					        for(Location loc : a.getSign()){
					               a.updateSign(loc);
					        }
						}
				 }else{
					 	a.getPlayers().remove(p);
						Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
						a.getLobbyPlayer().remove(p);
						
						if(!a.getSign().isEmpty()){
					        for(Location loc : a.getSign()){
					               a.updateSign(loc);
					        }
						}
				 }
				 
			 }
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		ArenaManager.getManager().leave(p, true, false);
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			 if(ArenaManager.getManager().checkTeam(a, p) == false){
				 p.teleport(a.getExit());
				 ArenaManager.getManager().removePlayer(p);
				 a.getPlayers().remove(p);
				 if(ArenaManager.getManager().checkTeam(a, p) == false){
					 	a.getPlayers().remove(p);
					 	Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
						a.getLobbyPlayer().remove(p);
						
						if(!a.getSign().isEmpty()){
					        for(Location loc : a.getSign()){
					               a.updateSign(loc);
					        }
						}
				 }else{
						a.getLobbyPlayer().remove(p);
						a.getPlayers().remove(p);
						Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
						
						if(!a.getSign().isEmpty()){
					        for(Location loc : a.getSign()){
					               a.updateSign(loc);
					        }
						}
				 }
			 }
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		ArenaManager.getManager().hideP();
	}
	
	@EventHandler
	public void onTag(AsyncPlayerReceiveNameTagEvent event){
		Player p = event.getNamedPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			if(a.teamGetPlayer(p) != 0){
				int i = a.teamGetPlayer(p);
				event.setTag(ChatColor.translateAlternateColorCodes('&', a.getColor(i)) + p.getName());
				return;
			}else{
				event.setTag(p.getName());
			}
		}
	}
	
	@EventHandler
	public void onPlayerItem(InventoryClickEvent e){
		if(e.getInventory() == null){
			return; 
		}
		
		if(e.getWhoClicked() == null){
			return;
		}
		
		if(e.getCurrentItem() == null){
			return;
		}
		
		Player p = (Player) e.getWhoClicked();
		
		if(TeamGui.inv.get(p) == null){
			return;
		}
		
		if(!e.getInventory().equals(TeamGui.inv.get(p))){
			return;
		}
		
		if(!Editor.isInEditor(p)){
			return;
		}
		
		if(e.getCurrentItem() == null){
			return;
		}
		
		if(e.getInventory().getTitle().equals("Team Selector")){
			e.setCancelled(true);
			int amount = e.getCurrentItem().getAmount();
			TeamGui.openInv2(p, amount);
		}else if(e.getInventory().getTitle().startsWith("Team Setzen")){
			e.setCancelled(true);
			String s = e.getInventory().getTitle().replace("Team Setzen [", "");
			s = s.replace("]", "");
			String a = Editor.getEditorName(p);
			int i = Integer.parseInt(s);
			if(i == 1){
				Team.team1.put(a, false);
				Team.team1_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team1_s.put(a, p.getLocation());
				Team.team1_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #1");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 2){
				Team.team2.put(a, false);
				Team.team2_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team2_s.put(a, p.getLocation());
				Team.team2_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #2");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 3){
				Team.team3.put(a, false);
				Team.team3_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team3_s.put(a, p.getLocation());
				Team.team3_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #3");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 4){
				Team.team4.put(a, false);
				Team.team4_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team4_s.put(a, p.getLocation());
				Team.team4_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #4");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 5){
				Team.team5.put(a, false);
				Team.team5_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team5_s.put(a, p.getLocation());
				Team.team5_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #5");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 6){
				Team.team6.put(a, false);
				Team.team6_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team6_s.put(a, p.getLocation());
				Team.team6_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #6");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 7){
				Team.team7.put(a, false);
				Team.team7_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team7_s.put(a, p.getLocation());
				Team.team7_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #7");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else if(i == 8){
				Team.team8.put(a, false);
				Team.team8_c.put(a, Utils.returnColor(e.getCurrentItem().getAmount() - 1));
				Team.team8_s.put(a, p.getLocation());
				Team.team8_n.put(a, Utils.returnColorName(e.getCurrentItem().getAmount() - 1));
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #8");
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				return;
			}else{
				Messages.sendMessage(p, "§cEs ist ein fehler augetreten :(", true);
				return;
			}
		}else{
			return;
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		final Player p = e.getPlayer();
		
		if(ArenaManager.getManager().isInGame(p)){
			if(ArenaManager.getManager().returnArena(p).isStartet() == false){
				e.setCancelled(true);
				return;
			}
		}
		
		if(!Editor.isInEditor(p)){
			
			if(e.getBlock().getType().equals(Material.BED_BLOCK)){
				for(Arena a : ArenaManager.getManager().getArenaList()){
					if(e.getBlock().getLocation().equals(a.getBed(1)) || e.getBlock().getRelative(a.getRotation(1)).equals(a.getBlock(1))){
						e.setCancelled(true);
						return;
					}
					
					if(e.getBlock().getLocation().equals(a.getBed(2)) || e.getBlock().getRelative(a.getRotation(2)).equals(a.getBlock(2))){
						e.setCancelled(true);
						return;
					}
					
					if(e.getBlock().getLocation().equals(a.getBed(3)) || e.getBlock().getRelative(a.getRotation(3)).equals(a.getBlock(3))){
						e.setCancelled(true);
						return;
					}
					
					if(e.getBlock().getLocation().equals(a.getBed(4)) || e.getBlock().getRelative(a.getRotation(4)).equals(a.getBlock(4))){
						e.setCancelled(true);
						return;
					}
				}
			}
			
			return;
		}
		
		if(e.getBlock() == null){
			return;
		}
		
		if(e.getBlock().getType().equals(Material.GOLD_ORE)){
			if(Editor.gold.get(Editor.getEditorName(p)) != null){
				if(Editor.gold.get(Editor.getEditorName(p)).contains(e.getBlock().getLocation())){
					Editor.gold.get(Editor.getEditorName(p)).remove(e.getBlock().getLocation());
					Messages.sendMessage(p, "Gold Spawner wurde entfernt", true);
					return;
				}
			}
			
		}
		
		if(e.getBlock().getType().equals(Material.IRON_ORE)){
			if(Editor.eisen.get(Editor.getEditorName(p)) != null){
				if(Editor.eisen.get(Editor.getEditorName(p)).contains(e.getBlock().getLocation())){
					Editor.eisen.get(Editor.getEditorName(p)).remove(e.getBlock().getLocation());
					Messages.sendMessage(p, "Eisen Spawner wurde entfernt", true);
					return;
				}
			}
			
		}
		
		if(e.getBlock().getType().equals(Material.BRICK)){
			if(Editor.bronze.get(Editor.getEditorName(p)) != null){
				if(Editor.bronze.get(Editor.getEditorName(p)).contains(e.getBlock().getLocation())){
					Editor.bronze.get(Editor.getEditorName(p)).remove(e.getBlock().getLocation());
					Messages.sendMessage(p, "Bronze Spawner wurde entfernt", true);
					return;
				}
			}
			
		}
		
		if(e.getBlock().getType().equals(Material.BED_BLOCK)){
			if(Team.team1_b.get(Editor.getEditorName(p)) != null){
				if(Team.team1_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 1).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team1_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 1 wurde gelöscht", true);
					Team.team1.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team1_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 1)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 1).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 1));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team1_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 1 wurde gelöscht", true);
					Team.team1.put(Editor.getEditorName(p), false);
					return;
				}
			}

			if(Team.team2_b.get(Editor.getEditorName(p)) != null){
				if(Team.team2_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 2).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team2_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 2 wurde gelöscht", true);
					Team.team2.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team2_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 2)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 2).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 2));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team2_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 2 wurde gelöscht", true);
					Team.team2.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			if(Team.team3_b.get(Editor.getEditorName(p)) != null){
				if(Team.team3_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 3).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team3_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 3 wurde gelöscht", true);
					Team.team3.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team3_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 3)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 3).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 3));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team3_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 3 wurde gelöscht", true);
					Team.team3.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			if(Team.team4_b.get(Editor.getEditorName(p)) != null){
				if(Team.team4_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 4).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team4_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 4 wurde gelöscht", true);
					Team.team4.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team4_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 4)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 4).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 4));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team4_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 4 wurde gelöscht", true);
					Team.team4.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			if(Team.team4_b.get(Editor.getEditorName(p)) != null){
				if(Team.team4_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 4).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team4_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 4 wurde gelöscht", true);
					Team.team4.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team4_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 4)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 4).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 4));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team4_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 4 wurde gelöscht", true);
					Team.team4.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			if(Team.team5_b.get(Editor.getEditorName(p)) != null){
				if(Team.team5_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 5).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team5_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 5 wurde gelöscht", true);
					Team.team5.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team5_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 5)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 5).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 5));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team5_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 5 wurde gelöscht", true);
					Team.team5.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			
			if(Team.team6_b.get(Editor.getEditorName(p)) != null){
				if(Team.team6_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 6).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team6_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 6 wurde gelöscht", true);
					Team.team6.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team6_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 6)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 6).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 6));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team6_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 6 wurde gelöscht", true);
					Team.team6.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			
			if(Team.team7_b.get(Editor.getEditorName(p)) != null){
				if(Team.team7_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 7).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team7_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 7 wurde gelöscht", true);
					Team.team7.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team7_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 7)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 7).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 7));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team7_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 7 wurde gelöscht", true);
					Team.team7.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
			if(Team.team8_b.get(Editor.getEditorName(p)) != null){
				if(Team.team8_b.get(Editor.getEditorName(p)).equals(e.getBlock())){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 8).getOppositeFace());
					e.getBlock().setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team8_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 8 wurde gelöscht", true);
					Team.team8.put(Editor.getEditorName(p), false);
					return;
				}
				
				if(Team.team8_b.get(Editor.getEditorName(p)).equals(e.getBlock().getRelative(Editor.getRotation(p, 8)))){
					e.setCancelled(true);
					Block bl = e.getBlock().getRelative(Editor.getRotation(p, 8).getOppositeFace());
					Block Bl = e.getBlock().getRelative(Editor.getRotation(p, 8));
					Bl.setType(Material.AIR);
					bl.setType(Material.AIR);
					Team.team8_b.remove(Editor.getEditorName(p));
					Messages.sendMessage(p, "Das bett von Team 8 wurde gelöscht", true);
					Team.team8.put(Editor.getEditorName(p), false);
					return;
				}
			}
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void setBlock(final BlockPlaceEvent e){
		final Player p = e.getPlayer();
		
		if(ArenaManager.getManager().isInGame(p)){
			if(ArenaManager.getManager().returnArena(p).isStartet() == false){
				e.setCancelled(true);
				return;
			}
		}
		
		if(!Editor.isInEditor(p)){
			return;
		}
		
		if(e.getBlock() == null){
			return;
		}
		
		if(e.getBlock().getType().equals(Material.MOB_SPAWNER)){
			if(p.getInventory().getItemInHand().hasItemMeta()){
				if(p.getInventory().getItemInHand().hasItemMeta()){
					String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
					s = s.replace(Main.head, "");
					s = ChatColor.stripColor(s);
					if(s.startsWith("Spawner: ")){
						if(s.endsWith("Gold")){
							if(Editor.gold.get(Editor.getEditorName(p)) == null){
								ArrayList<Location> loc = new ArrayList<Location>();
								loc.add(e.getBlock().getLocation());
								Editor.gold.put(Editor.getEditorName(p), loc);
							}else{
								ArrayList<Location> loc = Editor.gold.get(Editor.getEditorName(p));
								loc.add(e.getBlock().getLocation());
								Editor.gold.put(Editor.getEditorName(p), loc);
							}
							Messages.sendMessage(p, "Gold Spawner wurde gesetzt", true);
							e.setCancelled(true);
							p.getWorld().spawnFallingBlock(e.getBlock().getLocation(), 14, (byte) 1);
							return;
						}
						
						if(s.endsWith("Bronze")){
							if(Editor.bronze.get(Editor.getEditorName(p)) == null){
								ArrayList<Location> loc = new ArrayList<Location>();
								loc.add(e.getBlock().getLocation());
								Editor.bronze.put(Editor.getEditorName(p), loc);
							}else{
								ArrayList<Location> loc = Editor.bronze.get(Editor.getEditorName(p));
								loc.add(e.getBlock().getLocation());
								Editor.bronze.put(Editor.getEditorName(p), loc);
							}
							Messages.sendMessage(p, "Bronze Spawner wurde gesetzt", true);
							e.setCancelled(true);
							p.getWorld().spawnFallingBlock(e.getBlock().getLocation(), 45, (byte) 1);
							return;
						}
						
						if(s.endsWith("Eisen")){
							if(Editor.eisen.get(Editor.getEditorName(p)) == null){
								ArrayList<Location> loc = new ArrayList<Location>();
								loc.add(e.getBlock().getLocation());
								Editor.eisen.put(Editor.getEditorName(p), loc);
							}else{
								ArrayList<Location> loc = Editor.eisen.get(Editor.getEditorName(p));
								loc.add(e.getBlock().getLocation());
								Editor.eisen.put(Editor.getEditorName(p), loc);
							}
							Messages.sendMessage(p, "Eisen Spawner wurde gesetzt", true);
							e.setCancelled(true);
							p.getWorld().spawnFallingBlock(e.getBlock().getLocation(), 15, (byte) 1);
							return;
						}
					}
				}
			}
		}

		
		if(e.getBlock().getType().equals(Material.BED_BLOCK)){
			if(p.getInventory().getItemInHand().hasItemMeta()){
				if(p.getInventory().getItemInHand().hasItemMeta()){
					String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
					s = ChatColor.stripColor(s);
					
					if(s.startsWith("Team: ")){
						s = s.substring(s.length() - 1);
						if(Utils.isInt(s)){
							int i = Integer.parseInt(s);
							if(i == 1){
								if(Team.team1_b.get(Editor.getEditorName(p)) == null){
									Team.team1_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team1.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 1 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 2){
								if(Team.team2_b.get(Editor.getEditorName(p)) == null){
									Team.team2_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team2.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 2 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 3){
								if(Team.team3_b.get(Editor.getEditorName(p)) == null){
									Team.team3_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team3.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 3 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 4){
								if(Team.team4_b.get(Editor.getEditorName(p)) == null){
									Team.team4_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team4.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 4 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 5){
								if(Team.team5_b.get(Editor.getEditorName(p)) == null){
									Team.team5_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team5.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 5 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 6){
								if(Team.team6_b.get(Editor.getEditorName(p)) == null){
									Team.team6_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team6.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 6 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 7){
								if(Team.team7_b.get(Editor.getEditorName(p)) == null){
									Team.team7_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team7.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 7 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else if(i == 8){
								if(Team.team8_b.get(Editor.getEditorName(p)) == null){
									Team.team8_b.put(Editor.getEditorName(p), e.getBlock());
									Team.team8.put(Editor.getEditorName(p), true);
									Messages.sendMessage(p, "Bett für Team " + 8 +  " wurde gesetzt", true);
								}else{
									Messages.sendMessage(p, "Es wurde bereits ein bett gefunden", true);
									e.setCancelled(true);
								}
							}else{
								Messages.sendMessage(p, "§cEin fehler ist aufgetreten :(", true);
							}
						}
					}
					
					p.getInventory().setItem(6, null);
					p.updateInventory();
					p.getInventory().setHeldItemSlot(1);
					p.updateInventory();
				}

			}
		}
	}
}