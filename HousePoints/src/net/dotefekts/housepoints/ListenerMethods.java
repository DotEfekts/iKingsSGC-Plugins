package net.dotefekts.housepoints;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ListenerMethods implements Listener, BindingExecutionDelegate {
	
	Logger log = Logger.getLogger("Minecraft");
	HousePoints plugin = null;
	FileConfiguration file = null;
	Set<String> houses = new HashSet<String>();
	Map<String, Map<Block, Long>> signCooldown = new HashMap<String, Map<Block, Long>>();
	Map<String, Long> loginCooldown = new HashMap<String, Long>();
	String prefix = ChatColor.BLUE + "[" + ChatColor.RED + "HousePoints" + ChatColor.BLUE + "] " + ChatColor.GOLD;

	public void storeInstance(HousePoints o){
		plugin = o;
		file = o.getConfig();
		
		houses.add("gryffindor");
		houses.add("hufflepuff");
		houses.add("ravenclaw");
		houses.add("slytherin");	
	}
	
	@EventHandler
	public void playerLogin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		PermissionManager permissions = PermissionsEx.getPermissionManager();
		int points = file.getInt("LoginPoints");
		plugin.createOverlay((SpoutPlayer)event.getPlayer());
		if(loginCooldown.containsKey(event.getPlayer().getName())){
			if(loginCooldown.get(event.getPlayer().getName())<System.currentTimeMillis()){
				for(String str : permissions.getUser(player).getGroupsNames()){
		 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
		 				int currentPoints = file.getInt("Gryffindor.Points");
					 	currentPoints += points;
					 	file.set("Gryffindor.Points", currentPoints);
					 	plugin.saveConfig();
					 	plugin.updatePoints();
					 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Gryffindor");
		 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
		 				int currentPoints = file.getInt("Hufflepuff.Points");
					 	currentPoints += points;
					 	file.set("Hufflepuff.Points", currentPoints);
					 	plugin.saveConfig();
					 	plugin.updatePoints();
					 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Hufflepuff");
		 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
		 				int currentPoints = file.getInt("Ravenclaw.Points");
					 	currentPoints += points;
					 	file.set("Ravenclaw.Points", currentPoints);
					 	plugin.saveConfig();
					 	plugin.updatePoints();
					 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Ravenclaw");
		 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
		 				int currentPoints = file.getInt("Slytherin.Points");
					 	currentPoints += points;
					 	file.set("Slytherin.Points", currentPoints);
					 	plugin.saveConfig();
					 	plugin.updatePoints();
					 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Slytherin");
		 			}
				}
				loginCooldown.put(player.getName(), System.currentTimeMillis()+(60000*60));
			}
		} else {
			for(String str : permissions.getUser(player).getGroupsNames()){
	 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
	 				int currentPoints = file.getInt("Gryffindor.Points");
				 	currentPoints += points;
				 	file.set("Gryffindor.Points", currentPoints);
				 	plugin.saveConfig();
				 	plugin.updatePoints();
				 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Gryffindor");
	 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
	 				int currentPoints = file.getInt("Hufflepuff.Points");
				 	currentPoints += points;
				 	file.set("Hufflepuff.Points", currentPoints);
				 	plugin.saveConfig();
				 	plugin.updatePoints();
				 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Hufflepuff");
	 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
	 				int currentPoints = file.getInt("Ravenclaw.Points");
				 	currentPoints += points;
				 	file.set("Ravenclaw.Points", currentPoints);
				 	plugin.saveConfig();
				 	plugin.updatePoints();
				 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Ravenclaw");
	 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
	 				int currentPoints = file.getInt("Slytherin.Points");
				 	currentPoints += points;
				 	file.set("Slytherin.Points", currentPoints);
				 	plugin.saveConfig();
				 	plugin.updatePoints();
				 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Slytherin");
	 			}
			}
			loginCooldown.put(player.getName(), System.currentTimeMillis()+(60000*60));
		}
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent event){
		if(plugin.playerLabels.containsKey(event.getPlayer().getName())){
			plugin.playerLabels.remove(event.getPlayer().getName());
		}
	}
	
	@EventHandler
	 public void commandPreprocess(PlayerCommandPreprocessEvent event) {
		 Player player = event.getPlayer();
		 PermissionManager permissions = PermissionsEx.getPermissionManager();
		 String cmd = event.getMessage();
		 String[] command = cmd.split("\\s+");
		 if(command[0].equalsIgnoreCase("/points")){
			 if(command.length==4){
		 
				 if(command[1].equalsIgnoreCase("give")){
					 if(permissions.has(player, "housepoints.give")){
						 if(command[3].matches("^[0-9]+$")){
							 int points = Integer.parseInt(command[3]);
						 	if(houses.contains(command[2].toLowerCase())){
						 		int currentPoints = file.getInt(WordUtils.capitalize(command[2].toLowerCase())+".Points");
							 	currentPoints += points;
							 	file.set(WordUtils.capitalize(command[2].toLowerCase())+".Points", currentPoints);
							 	plugin.saveConfig();
							 	plugin.updatePoints();
							 	player.sendMessage(ChatColor.GREEN + command[3] + " points given to "+WordUtils.capitalize(command[2].toLowerCase()));
							 	Bukkit.broadcastMessage(prefix + command[3] + " points given to " + WordUtils.capitalize(command[2].toLowerCase()));
						 	} else if(Bukkit.getPlayer(command[2])!=null) {
						 		Player targetPlayer = Bukkit.getPlayer(command[2]);
						 		for(String str : permissions.getUser(targetPlayer).getGroupsNames()){
						 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
						 				int currentPoints = file.getInt("Gryffindor.Points");
									 	currentPoints += points;
									 	file.set("Gryffindor.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3] + " points given to Gryffindor");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points given to Gryffindor");
						 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
						 				int currentPoints = file.getInt("Hufflepuff.Points");
									 	currentPoints += points;
									 	file.set("Hufflepuff.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3] + " points given to Hufflepuff");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points given to Hufflepuff");
						 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
						 				int currentPoints = file.getInt("Ravenclaw.Points");
									 	currentPoints += points;
									 	file.set("Ravenclaw.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3] + " points given to Ravenclaw");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points given to Ravenclaw");
						 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
						 				int currentPoints = file.getInt("Slytherin.Points");
									 	currentPoints += points;
									 	file.set("Slytherin.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3] + " points given to Slytherin");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points given to Slytherin");
						 			} else {
						 				player.sendMessage(ChatColor.RED+"Player is not in a house.");
						 			}
						 		}
						 	} else {
						 		player.sendMessage(ChatColor.RED+"House or player not found");
						 	}
					 	} else {
					 		player.sendMessage(ChatColor.RED+"You need to input a number");
					 	}
					 } else {
						 player.sendMessage("You don't have permission to do that.");
					 }
				 } else if(command[1].equalsIgnoreCase("take")){
					 if(permissions.has(player, "housepoints.take")){
						 if(command[3].matches("^[0-9]+$")){
							 int points = Integer.parseInt(command[3]);
						 	if(houses.contains(command[2].toLowerCase())){
						 		int currentPoints = file.getInt(WordUtils.capitalize(command[2].toLowerCase())+".Points");
							 	currentPoints -= points;
							 	file.set(WordUtils.capitalize(command[2].toLowerCase())+".Points", currentPoints);
							 	plugin.saveConfig();
							 	plugin.updatePoints();
							 	player.sendMessage(ChatColor.GREEN+command[3]+" points taken from "+WordUtils.capitalize(command[2].toLowerCase()));
							 	Bukkit.broadcastMessage(prefix + command[3] + " points taken from " + WordUtils.capitalize(command[2].toLowerCase()));
						 	}  else if(Bukkit.getPlayer(command[2])!=null) {
						 		Player targetPlayer = Bukkit.getPlayer(command[2]);
						 		for(String str : permissions.getUser(targetPlayer).getGroupsNames()){
						 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
						 				int currentPoints = file.getInt("Gryffindor.Points");
									 	currentPoints -= points;
									 	file.set("Gryffindor.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3]+" points taken from Gryffindor");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points taken from Gryffindor");
						 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
						 				int currentPoints = file.getInt("Hufflepuff.Points");
									 	currentPoints -= points;
									 	file.set("Hufflepuff.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3]+" points taken from Hufflepuff");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points taken from Hufflepuff");
						 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
						 				int currentPoints = file.getInt("Ravenclaw.Points");
									 	currentPoints -= points;
									 	file.set("Ravenclaw.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3]+" points taken from Ravenclaw");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points taken from Ravenclaw");
						 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
						 				int currentPoints = file.getInt("Slytherin.Points");
									 	currentPoints -= points;
									 	file.set("Slytherin.Points", currentPoints);
									 	plugin.saveConfig();
									 	plugin.updatePoints();
									 	player.sendMessage(ChatColor.GREEN+command[3]+" points taken from Slytherin");
									 	Bukkit.broadcastMessage(prefix + command[3] + " points taken from Slytherin");
						 			} else {
						 				player.sendMessage(ChatColor.RED+"Player is not in a house.");
						 			}
						 		}
						 	} else {
						 		player.sendMessage(ChatColor.RED+"House or player not found");
						 	}
					 	} else {
					 		player.sendMessage(ChatColor.RED+"You need to input a number");
					 	}
					 } else {
						 player.sendMessage("You don't have permission to do that.");
					 }
				 } else {
					player.sendMessage(ChatColor.RED+"HousePoints commands:");
					player.sendMessage(ChatColor.BLUE+"/points "+ChatColor.GREEN+"give "+ChatColor.YELLOW+"<Player/House> <Amount>");
					player.sendMessage(ChatColor.BLUE+"/points "+ChatColor.GREEN+"take "+ChatColor.YELLOW+"<Player/House> <Amount>");
					player.sendMessage(ChatColor.AQUA+"<Required>");
				 }
			} else {
				player.sendMessage(ChatColor.RED+"HousePoints commands:");
				player.sendMessage(ChatColor.BLUE+"/points "+ChatColor.GREEN+"give "+ChatColor.YELLOW+"<Player/House> <Amount>");
				player.sendMessage(ChatColor.BLUE+"/points "+ChatColor.GREEN+"take "+ChatColor.YELLOW+"<Player/House> <Amount>");
				player.sendMessage(ChatColor.AQUA+"<Required>");
			}
		 }
	 }

	public void keyPressed(KeyBindingEvent event) {
		if (event.getScreenType() == ScreenType.GAME_SCREEN){
			if(event.getBinding().getDefaultKey().equals(Keyboard.KEY_J)){
				if(plugin.playerLabels.containsKey(event.getPlayer().getName())){
					for(GenericLabel label : plugin.playerLabels.get(event.getPlayer().getName())){
						if(label.isVisible()){
							label.setVisible(false);
							label.setDirty(true);
						} else {
							label.setVisible(true);
							label.setDirty(true);
						}
					}
					if(plugin.playerGradients.get(event.getPlayer().getName()).isVisible()){
						plugin.playerGradients.get(event.getPlayer().getName()).setVisible(false);
						plugin.playerGradients.get(event.getPlayer().getName()).setDirty(true);
					} else {
						plugin.playerGradients.get(event.getPlayer().getName()).setVisible(true);
						plugin.playerGradients.get(event.getPlayer().getName()).setDirty(true);
					}
				} else {
					plugin.createOverlay(event.getPlayer());
				}
			}
		}
	}

	public void keyReleased(KeyBindingEvent event) {
	}
	
	
	@EventHandler
	 public void signRightClick(PlayerInteractEvent event){
		 PermissionManager permissions = PermissionsEx.getPermissionManager();
		 Player player = event.getPlayer();
		 if(event.hasBlock()){
			 Block block = event.getClickedBlock();
			 if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
				 if(event.getClickedBlock().getTypeId()==63||event.getClickedBlock().getTypeId()==68){
					 Sign sign = (Sign) block.getState();
					 if(sign.getLine(1).equals("[HousePoints]")){
						 if(permissions.has(player, "housepoints.sign.use")){
							  if(signCooldown.containsKey(player.getName())){
								  if(signCooldown.get(player.getName()).containsKey(block)){
									  if(signCooldown.get(player.getName()).get(block)>System.currentTimeMillis()){
										  long time = ((long)Math.ceil(((signCooldown.get(player.getName()).get(block)-System.currentTimeMillis())/1000)));
										  if(time>60){
											  long timeM = ((long)Math.floor(signCooldown.get(player.getName()).get(block)-System.currentTimeMillis())/60000);
											  time -= timeM*60;
											  player.sendMessage(ChatColor.RED+"You can't use that sign for "+timeM+" minutes and "+time+" seconds.");
										  } else {
											  player.sendMessage(ChatColor.RED+"You can't use that sign for "+time+" seconds.");
										  }
									  } else {
										  int points = Integer.parseInt(sign.getLine(3));
										  for(String str : permissions.getUser(player).getGroupsNames()){
									 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
									 				int currentPoints = file.getInt("Gryffindor.Points");
												 	currentPoints += points;
												 	file.set("Gryffindor.Points", currentPoints);
												 	plugin.saveConfig();
												 	plugin.updatePoints();
												 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Gryffindor");
									 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
									 				int currentPoints = file.getInt("Hufflepuff.Points");
												 	currentPoints += points;
												 	file.set("Hufflepuff.Points", currentPoints);
												 	plugin.saveConfig();
												 	plugin.updatePoints();
												 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Hufflepuff");
									 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
									 				int currentPoints = file.getInt("Ravenclaw.Points");
												 	currentPoints += points;
												 	file.set("Ravenclaw.Points", currentPoints);
												 	plugin.saveConfig();
												 	plugin.updatePoints();
												 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Ravenclaw");
									 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
									 				int currentPoints = file.getInt("Slytherin.Points");
												 	currentPoints += points;
												 	file.set("Slytherin.Points", currentPoints);
												 	plugin.saveConfig();
												 	plugin.updatePoints();
												 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Slytherin");
									 			} else {
									 				player.sendMessage(ChatColor.RED+"You're not in a house.");
									 			}
									 			long cooldown = Integer.parseInt(sign.getLine(2));
									 			cooldown *= 60000;
									 			if(signCooldown.containsKey(player.getName())){
									 				signCooldown.get(player.getName()).put(block, System.currentTimeMillis()+cooldown);
									 			} else {
									 				Map<Block, Long> blocks = new HashMap<Block, Long>();
									 				blocks.put(block, System.currentTimeMillis()+cooldown);
									 				signCooldown.put(player.getName(), blocks);
									 			}
										  }
									  }
								  } else {
									  int points = Integer.parseInt(sign.getLine(3));
									  for(String str : permissions.getUser(player).getGroupsNames()){
								 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
								 				int currentPoints = file.getInt("Gryffindor.Points");
											 	currentPoints += points;
											 	file.set("Gryffindor.Points", currentPoints);
											 	plugin.saveConfig();
											 	plugin.updatePoints();
											 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Gryffindor");
								 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
								 				int currentPoints = file.getInt("Hufflepuff.Points");
											 	currentPoints += points;
											 	file.set("Hufflepuff.Points", currentPoints);
											 	plugin.saveConfig();
											 	plugin.updatePoints();
											 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Hufflepuff");
								 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
								 				int currentPoints = file.getInt("Ravenclaw.Points");
											 	currentPoints += points;
											 	file.set("Ravenclaw.Points", currentPoints);
											 	plugin.saveConfig();
											 	plugin.updatePoints();
											 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Ravenclaw");
								 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
								 				int currentPoints = file.getInt("Slytherin.Points");
											 	currentPoints += points;
											 	file.set("Slytherin.Points", currentPoints);
											 	plugin.saveConfig();
											 	plugin.updatePoints();
											 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Slytherin");
								 			} else {
								 				player.sendMessage(ChatColor.RED+"You're not in a house.");
								 			}
								 			long cooldown = Integer.parseInt(sign.getLine(2));
								 			cooldown *= 60000;
								 			if(signCooldown.containsKey(player.getName())){
								 				signCooldown.get(player.getName()).put(block, System.currentTimeMillis()+cooldown);
								 			} else {
								 				Map<Block, Long> blocks = new HashMap<Block, Long>();
								 				blocks.put(block, System.currentTimeMillis()+cooldown);
								 				signCooldown.put(player.getName(), blocks);
								 			}
									  }
								  }
							  } else {
								  int points = Integer.parseInt(sign.getLine(3));
								  for(String str : permissions.getUser(player).getGroupsNames()){
							 			if(str.equalsIgnoreCase(file.getString("Gryffindor.GroupName"))){
							 				int currentPoints = file.getInt("Gryffindor.Points");
										 	currentPoints += points;
										 	file.set("Gryffindor.Points", currentPoints);
										 	plugin.saveConfig();
										 	plugin.updatePoints();
										 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Gryffindor");
							 			} else if(str.equalsIgnoreCase(file.getString("Hufflepuff.GroupName"))){
							 				int currentPoints = file.getInt("Hufflepuff.Points");
										 	currentPoints += points;
										 	file.set("Hufflepuff.Points", currentPoints);
										 	plugin.saveConfig();
										 	plugin.updatePoints();
										 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Hufflepuff");
							 			} else if(str.equalsIgnoreCase(file.getString("Ravenclaw.GroupName"))){
							 				int currentPoints = file.getInt("Ravenclaw.Points");
										 	currentPoints += points;
										 	file.set("Ravenclaw.Points", currentPoints);
										 	plugin.saveConfig();
										 	plugin.updatePoints();
										 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Ravenclaw");
							 			} else if(str.equalsIgnoreCase(file.getString("Slytherin.GroupName"))){
							 				int currentPoints = file.getInt("Slytherin.Points");
										 	currentPoints += points;
										 	file.set("Slytherin.Points", currentPoints);
										 	plugin.saveConfig();
										 	plugin.updatePoints();
										 	player.sendMessage(ChatColor.GREEN+""+points+" points given to Slytherin");
							 			} else {
							 				player.sendMessage(ChatColor.RED+"You're not in a house.");
							 			}
							 			long cooldown = Integer.parseInt(sign.getLine(2));
							 			cooldown *= 60000;
							 			if(signCooldown.containsKey(player.getName())){
							 				signCooldown.get(player.getName()).put(block, System.currentTimeMillis()+cooldown);
							 			} else {
							 				Map<Block, Long> blocks = new HashMap<Block, Long>();
							 				blocks.put(block, System.currentTimeMillis()+cooldown);
							 				signCooldown.put(player.getName(), blocks);
							 			}
								  }
							  }
						 } else {
							 player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
						 }
						 event.setCancelled(true);
					 }
				 }
			 }
		 }
	 }
	 
	 @EventHandler
	 public void signChangeEvent(SignChangeEvent event){
		 PermissionManager permissions = PermissionsEx.getPermissionManager();
		 Block block = event.getBlock();
		 Player player = event.getPlayer();
		 if(event.getLine(1).equals("[HousePoints]")){
			if(permissions.has(player, "housepoints.sign.create")){
				if(event.getLine(2).matches("^[0-9]+$")||event.getLine(2).equals("")){
					if(event.getLine(3).matches("^[0-9]+$")){
						player.sendMessage(ChatColor.GREEN+"Sign created.");
					} else {
						block.breakNaturally();
						player.sendMessage(ChatColor.RED+"The points needs to be a number.");
					}
				} else {
					block.breakNaturally();
					player.sendMessage(ChatColor.RED+"The cooldown needs to be a number or empty.");
				}
			} else {
				block.breakNaturally();
				player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
			}
		 } 
	}
	 
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		PermissionManager permissions = PermissionsEx.getPermissionManager();
		 Block block = event.getBlock();
		 Player player = event.getPlayer();
		 if(block.getTypeId()==63||block.getTypeId()==68){
			 Sign sign = (Sign) block.getState();
			 if(sign.getLine(1).equals("[HousePoints]")){
				 if(!permissions.has(player, "housepoints.sign.remove")){
					 event.setCancelled(true);
					 player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
				 }
			 }
		 }
	}
	
	
}