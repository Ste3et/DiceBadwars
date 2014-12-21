package me.Ste3et_C0st.DiceBedWars.Listener;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.save;
import me.Ste3et_C0st.DiceBedWars.GUI.AchievementGUI;
import me.Ste3et_C0st.DiceBedWars.GUI.SpawnerGUI;
import me.Ste3et_C0st.DiceBedWars.GUI.SpielerGUI;
import me.Ste3et_C0st.DiceBedWars.GUI.TeamGui;
import me.Ste3et_C0st.DiceBedWars.GUI.TeamSelection;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.DiceBedWars.Manager.SuizidSchaf;
import me.Ste3et_C0st.DiceBedWars.Manager.TeamCreate;
import me.Ste3et_C0st.DiceBedWars.Manager.Utils;
import me.Ste3et_C0st.language.Messages;
import net.minecraft.server.v1_8_R1.Achievement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
//import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

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
		
		if(e.getAction() == Action.LEFT_CLICK_AIR){
			if(e.getItem() != null){
				if(e.getItem().getType().equals(Material.MOB_SPAWNER)){
					if(Editor.isInEditor(p)){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								SpawnerGUI.openInventory(p, Editor.getEditorName(p), e.getItem());
							}
						}
					}
				}
			}
		}
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR){
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
			if(ArenaManager.getManager().getSpec(p) != null){
				e.setCancelled(true);
			}
			
			
			if(e.getItem() != null){
				
				if(e.getItem().hasItemMeta()){
					if(e.getItem().getItemMeta().hasDisplayName()){
						if(e.getItem().getType().equals(Material.BED)){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Team Editor")){
									TeamGui.openInv1(p);
									e.setCancelled(true);
									return;
								}
					}
				}
				
				if(e.getItem().getType().equals(Material.WOOL)){
					if(ArenaManager.getManager().isInGame(p)){
								if(e.getItem().getItemMeta().getDisplayName().equals(Main.head + "Team Selector")){
									TeamSelection.openGui(p, ArenaManager.getManager().returnArena(p));
									e.setCancelled(true);
									return;
								}
							}
				}
				
				if(e.getItem().getType().equals(Material.BOOK)){
					AchievementGUI.openGUI(p);
				}
				
				if(e.getItem().getType().equals(Material.GLASS)){
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
				
				if(e.getItem().getType().equals(Material.MONSTER_EGG)){
					if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.stripColor("Suizid Schaf"))){
						new SuizidSchaf(ArenaManager.getManager().returnArena(p), Team.getTeam(p), p.getLocation());
						int Slot = p.getInventory().getHeldItemSlot();
						ItemStack is = p.getItemInHand();
						int iA = is.getAmount() - 1;
						is.setAmount(iA);
						p.updateInventory();
						p.getInventory().setItem(Slot, is);
						p.updateInventory();
					}
				}
				
				if(e.getItem().getType().equals(Material.WOOD_PLATE)){
					if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.stripColor("Tret Mine (Holz)"))){
						if(e.getClickedBlock().getRelative(e.getBlockFace().getOppositeFace()).getType() == null){
							Block b = e.getClickedBlock().getRelative(e.getBlockFace().getOppositeFace());
							b.setType(Material.WOOD_PLATE);
							b.setTypeId(5);
						}
					}
				}
				
				if(e.getItem().getType().equals(Material.SKULL_ITEM)){
						String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
						s = s.replace(Main.head, "");
						s = ChatColor.stripColor(s);
						if(s.startsWith("Set")){
							p.chat("/npc create &6Shop --type VILLAGER");
							e.setCancelled(true);
							return;
						}
				}
				
				if(e.getItem().getType().equals(Material.DIAMOND)){
						String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
						s = s.replace(Main.head, "");
						s = ChatColor.stripColor(s);
						if(s.startsWith("Finish")){
							Editor.create(p);
						}
				}
				
				if(e.getItem().getType().equals(Material.COMPASS)){
					String s = p.getInventory().getItemInHand().getItemMeta().getDisplayName();
					s = s.replace(Main.head, "");
					s = ChatColor.stripColor(s);
					if(s.startsWith("Spieler")){
						SpielerGUI.openInventory(p, ArenaManager.getManager().getSpec(p));
					}
				}
				
				if(e.getItem().getType().equals(Material.NETHER_STAR)){
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
											Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
											ArenaManager.getManager().removePlayer(p);
											a.getPlayers().remove(p);
											a.getLobbyPlayer().remove(p);
											
											if(!a.getSign().isEmpty()){
										        for(Location loc : a.getSign()){
										               a.updateSign(loc);
										        }
											}
											ArenaManager.getManager().hideP();
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
									ArenaManager.getManager().hideP();
								}
							}else if(ArenaManager.getManager().getSpec(p) != null){
								ArenaManager.getManager().leave(p, true, false);
								ArenaManager.getManager().hideP();
							}else if(GameListener.checkIFLobby(p) != null){
								Arena a = GameListener.checkIFLobby(p);
								Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, a, p);
								a.getLobbyPlayer().remove(p);
								p.teleport(a.getExit());
								ArenaManager.getManager().hideP();
							}
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
			Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
			 if(ArenaManager.getManager().checkTeam(a, p, false) == false){
				 ArenaManager.getManager().removePlayer(p);
				 a.getPlayers().remove(p);
				 a.getLobbyPlayer().remove(p);
				 if(!a.getSign().isEmpty()){
					 for(Location loc : a.getSign()){
						 a.updateSign(loc);
					 }
				 }
			 }
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		if(ArenaManager.getManager().getSpec(e.getPlayer()) != null){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void itemPickup(PlayerPickupItemEvent e){
		if(ArenaManager.getManager().getSpec(e.getPlayer()) != null){
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		ArenaManager.getManager().leave(p, true, false);
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			Utils.sendRound3("Der Spieler: " + p.getName() + " hat das Spiel Verlassen", true, ArenaManager.getManager().returnArena(p), p);
			 if(ArenaManager.getManager().checkTeam(a, p, false) == false){
				 ArenaManager.getManager().removePlayer(p);
				 a.getPlayers().remove(p);
				 a.getLobbyPlayer().remove(p);
				 if(!a.getSign().isEmpty()){
					 for(Location loc : a.getSign()){
						 a.updateSign(loc);
					 }
				 }
			 }
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		ArenaManager.getManager().hideP();
	}
	
	/*@EventHandler
	public void onTag(AsyncPlayerReceiveNameTagEvent event){
		Player p = event.getNamedPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			if(Team.hasTeam(p)){
				Team t = Team.getTeam(p);
				event.setTag(ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + p.getName());
				return;
			}else{
				event.setTag(p.getName());
			}
		}
	}*/
	
	@SuppressWarnings("deprecation")
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
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Achievement GUI")){
			e.setCancelled(true);
		}
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Spawner Gui")){
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.ARROW)){
				String s = e.getCurrentItem().getItemMeta().getDisplayName();
				s = ChatColor.stripColor(s);
				ItemStack i = e.getInventory().getItem(31);
				ItemStack is = p.getInventory().getItem(2);
				if(s.equalsIgnoreCase("Weniger Spawner")){
					if(i.getAmount() > 1){
						i.setAmount(i.getAmount() - 1);
						e.getInventory().setItem(31, i);
						is.setAmount(i.getAmount());
						p.getInventory().setItem(2, is);
						p.updateInventory();
						return;
					}
				}
				
				if(s.equalsIgnoreCase("Mehr Spawner")){
					if(i.getAmount() < 64){
						i.setAmount(i.getAmount() + 1);
						e.getInventory().setItem(31, i);
						is.setAmount(i.getAmount());
						p.getInventory().setItem(2, is);
						p.updateInventory();
						return;
					}
				}
			}
			
			if(e.getCurrentItem().getType().equals(Material.MOB_SPAWNER)){
				String[] string = p.getInventory().getItem(2).getItemMeta().getDisplayName().split(" ");
				String s = string[2];
				String[] string2 = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
				if(string2[0].startsWith("Spawner")){
					if(string2[1].equalsIgnoreCase("Anzeigen")){
						
						String eName = Editor.getEditorName(p);
						if(s.equalsIgnoreCase("eisen")){
							if(Editor.eisen.get(eName) != null){
								for(final Location loc : Editor.eisen.get(eName)){
									final World w = p.getWorld();
									w.getBlockAt(loc).setType(Material.IRON_BLOCK);
								}
							}
							
						}else if(s.equalsIgnoreCase("bronze")){
							if(Editor.bronze.get(eName) != null){
								for(final Location loc : Editor.bronze.get(eName)){
									final World w = p.getWorld();
									w.getBlockAt(loc).setType(Material.BRICK);
								}
							}
							
						}else if(s.equalsIgnoreCase("gold")){
							if(Editor.gold.get(eName) != null){
								for(final Location loc : Editor.gold.get(eName)){
									final World w = p.getWorld();
									w.getBlockAt(loc).setType(Material.GOLD_BLOCK);
								}
							}
							
						}
					}else if(string2[1].equalsIgnoreCase("Verbergen")){
						String eName = Editor.getEditorName(p);
						if(s.equalsIgnoreCase("eisen")){
							if(Editor.eisen.get(eName) != null){
								for(final Location loc : Editor.eisen.get(eName)){
									final World w = p.getWorld();
									w.getBlockAt(loc).setType(Material.AIR);
								}
							}
							
						}else if(s.equalsIgnoreCase("bronze")){
							if(Editor.bronze.get(eName) != null){
								for(final Location loc : Editor.bronze.get(eName)){
									final World w = p.getWorld();
									w.getBlockAt(loc).setType(Material.AIR);
								}
							}
							
						}else if(s.equalsIgnoreCase("gold")){
							if(Editor.gold.get(eName) != null){
								for(final Location loc : Editor.gold.get(eName)){
									final World w = p.getWorld();
									w.getBlockAt(loc).setType(Material.AIR);
								}
							}
							
						}
					}
				}
			}
		}
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Spieler Wechseln")){
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
				String s = e.getCurrentItem().getItemMeta().getDisplayName();
				Player pl = Bukkit.getPlayer(ChatColor.stripColor(s));
				p.teleport(pl);
				p.closeInventory();
				return;
			}
		}
		
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
			int i = Integer.parseInt(s);
			if(i > 0){
				int u = e.getCurrentItem().getAmount() - 1;
				String color = Utils.returnColor(u);
				String name = Utils.returnColorName(u);
				TeamCreate.setColor(color, p, i - 1);
				TeamCreate.setName(name, p, i - 1);
				TeamCreate.setSpawn(p.getLocation(), p, i - 1);
				
				
				ItemStack newResult = (ItemStack) new ItemStack(Material.BED);
				ItemMeta newMeta = newResult.getItemMeta();
				String Null = "";
				if(i < 10){
					Null = "0";
				}
				newMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.returnColor(e.getCurrentItem().getAmount() - 1)) + "Team: " + Utils.returnColorName(e.getCurrentItem().getAmount() - 1) + " #" + Null +i);
				newMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				newResult.setItemMeta(newMeta);
				p.getInventory().setItem(6, newResult);
				p.closeInventory();
				p.getInventory().setHeldItemSlot(6);
				p.updateInventory();
			}else{
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
					for(Team t : a.getTeams()){
						if(t != null){
							if(e.getBlock().getLocation().equals(t.getBed().getLocation()) || e.getBlock().getRelative(t.getFace()).equals(t.getBed())){
								e.setCancelled(true);
								break;
							}
						}
					}
				}
			}
			
			return;
		}
		
		if(e.getBlock() == null){
			return;
		}
		
		if(e.getBlock().getType().equals(Material.GOLD_BLOCK)){
			if(Editor.gold.get(Editor.getEditorName(p)) != null){
				if(Editor.gold.get(Editor.getEditorName(p)).contains(e.getBlock().getLocation())){
					Editor.gold.get(Editor.getEditorName(p)).remove(e.getBlock().getLocation());
					Messages.sendMessage(p, "Gold Spawner wurde entfernt", true);
					return;
				}
			}
			
		}
		
		if(e.getBlock().getType().equals(Material.IRON_BLOCK)){
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
			if(Editor.getEditorName(p) == null){
				for(Player pl : Editor.player.keySet()){
					String s = Editor.player.get(pl);
					for(int i = 1; i <= Editor.getSize(s); i++){
						Team t = TeamCreate.getTeam(i - 1, pl);
						if(t.getBed() != null && t.getFace() != null){
							if(t.getBed().equals(e.getBlock())){
								e.setCancelled(true);
								return;
							}
							
							if(t.getBed().equals(e.getBlock().getRelative(t.getFace()))){
								e.setCancelled(true);
								return;						
							}
						}
					}
				}
				return;
			}
				for(int i = 1; i <= Editor.getSize(Editor.getEditorName(p)); i++){
					int u = i - 1;
					if(TeamCreate.getTeam(u, p) != null){
						Team t = TeamCreate.getTeam(u, p);
						if(TeamCreate.getBed(u, p) != null){
							if(t.getBed().equals(e.getBlock())){
								e.setCancelled(true);
								Block bl = e.getBlock().getRelative(t.getFace().getOppositeFace());
								e.getBlock().setType(Material.AIR);
								bl.setType(Material.AIR);
								t.removeBed();
								Messages.sendMessage(p, "Das bett von Team " + i + " wurde gelöscht", true);
								return;
							}
							
							if(t.getBed().equals(e.getBlock().getRelative(t.getFace()))){
								e.setCancelled(true);
								Block bl = e.getBlock().getRelative(TeamCreate.getFace(u, p).getOppositeFace());
								Block Bl = e.getBlock().getRelative(TeamCreate.getFace(u, p));
								Bl.setType(Material.AIR);
								bl.setType(Material.AIR);
								t.removeBed();
								Messages.sendMessage(p, "Das bett von Team " + i + " wurde gelöscht", true);
								return;		
							}
						}
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
							for(int i = 0; i <= p.getInventory().getItemInHand().getAmount(); i++){
								if(Editor.gold.get(Editor.getEditorName(p)) == null){
									ArrayList<Location> loc = new ArrayList<Location>();
									loc.add(e.getBlock().getLocation());
									Editor.gold.put(Editor.getEditorName(p), loc);
								}else{
									ArrayList<Location> loc = Editor.gold.get(Editor.getEditorName(p));
									loc.add(e.getBlock().getLocation());
									Editor.gold.put(Editor.getEditorName(p), loc);
								}
							}
							Messages.sendMessage(p, "Gold Spawner wurde gesetzt", true);
							e.setCancelled(true);
							return;
						}
						
						if(s.endsWith("Bronze")){
							for(int i = 0; i <= p.getInventory().getItemInHand().getAmount(); i++){
								if(Editor.bronze.get(Editor.getEditorName(p)) == null){
									ArrayList<Location> loc = new ArrayList<Location>();
									loc.add(e.getBlock().getLocation());
									Editor.bronze.put(Editor.getEditorName(p), loc);
								}else{
									ArrayList<Location> loc = Editor.bronze.get(Editor.getEditorName(p));
									loc.add(e.getBlock().getLocation());
									Editor.bronze.put(Editor.getEditorName(p), loc);
								}
							}
							Messages.sendMessage(p, "Bronze Spawner wurde gesetzt", true);
							e.setCancelled(true);
							return;
						}
						
						if(s.endsWith("Eisen")){
							for(int i = 0; i <= p.getInventory().getItemInHand().getAmount(); i++){
								if(Editor.eisen.get(Editor.getEditorName(p)) == null){
									ArrayList<Location> loc = new ArrayList<Location>();
									loc.add(e.getBlock().getLocation());
									Editor.eisen.put(Editor.getEditorName(p), loc);
								}else{
									ArrayList<Location> loc = Editor.eisen.get(Editor.getEditorName(p));
									loc.add(e.getBlock().getLocation());
									Editor.eisen.put(Editor.getEditorName(p), loc);
								}
							}
							Messages.sendMessage(p, "Eisen Spawner wurde gesetzt", true);
							e.setCancelled(true);
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
						String[] s1 = s.split(" ");
						s = s1[2].replace("#", "");
						if(Utils.isInt(s)){
							int i = Integer.parseInt(s);
							if(i > 0 && i < 46){
								TeamCreate.addBed(e.getBlock(), i - 1, p);
								Messages.sendMessage(p, "Bett für Team " + i +  " wurde gesetzt", true);
								p.getInventory().setItem(6, null);
								p.updateInventory();
								p.getInventory().setHeldItemSlot(1);
								p.updateInventory();
								return;
							}
						}
					}
				}

			}
		}
	}
}