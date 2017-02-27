package net.dotefekts.ikingsredeem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ListenerMethods implements Listener {
	
	Logger log = Logger.getLogger("Minecraft");
	iKingsRedeem plugin = null;
	FileConfiguration file = null;
	Map<Integer, Character> letterMap = new HashMap<Integer, Character>();
	
	public void storeInstance(iKingsRedeem o){
		plugin = o;
		file = o.getConfig();
		
		letterMap.put(1, "a".charAt(0));
		letterMap.put(2, "b".charAt(0));
		letterMap.put(3, "c".charAt(0));
		letterMap.put(4, "d".charAt(0));
		letterMap.put(5, "e".charAt(0));
		letterMap.put(6, "f".charAt(0));
		letterMap.put(7, "g".charAt(0));
		letterMap.put(8, "h".charAt(0));
		letterMap.put(9, "i".charAt(0));
		letterMap.put(10, "j".charAt(0));
		letterMap.put(11, "k".charAt(0));
		letterMap.put(12, "l".charAt(0));
		letterMap.put(13, "m".charAt(0));
		letterMap.put(14, "n".charAt(0));
		letterMap.put(15, "o".charAt(0));
		letterMap.put(16, "p".charAt(0));
		letterMap.put(17, "q".charAt(0));
		letterMap.put(18, "r".charAt(0));
		letterMap.put(19, "s".charAt(0));
		letterMap.put(20, "t".charAt(0));
		letterMap.put(21, "u".charAt(0));
		letterMap.put(22, "v".charAt(0));
		letterMap.put(23, "w".charAt(0));
		letterMap.put(24, "x".charAt(0));
		letterMap.put(25, "y".charAt(0));
		letterMap.put(26, "z".charAt(0));
		letterMap.put(27, "A".charAt(0));
		letterMap.put(28, "B".charAt(0));
		letterMap.put(29, "C".charAt(0));
		letterMap.put(30, "D".charAt(0));
		letterMap.put(31, "E".charAt(0));
		letterMap.put(32, "F".charAt(0));
		letterMap.put(33, "G".charAt(0));
		letterMap.put(34, "H".charAt(0));
		letterMap.put(35, "I".charAt(0));
		letterMap.put(36, "J".charAt(0));
		letterMap.put(37, "K".charAt(0));
		letterMap.put(38, "L".charAt(0));
		letterMap.put(39, "M".charAt(0));
		letterMap.put(40, "N".charAt(0));
		letterMap.put(41, "O".charAt(0));
		letterMap.put(42, "P".charAt(0));
		letterMap.put(43, "Q".charAt(0));
		letterMap.put(44, "R".charAt(0));
		letterMap.put(45, "S".charAt(0));
		letterMap.put(46, "T".charAt(0));
		letterMap.put(47, "U".charAt(0));
		letterMap.put(48, "V".charAt(0));
		letterMap.put(49, "W".charAt(0));
		letterMap.put(50, "X".charAt(0));
		letterMap.put(51, "Y".charAt(0));
		letterMap.put(52, "Z".charAt(0));
	}
	
	Random rand = new Random(System.currentTimeMillis());
	
	 @EventHandler
	 public void commandPreprocess(PlayerCommandPreprocessEvent event) {
		 Player player = event.getPlayer();
		 PermissionManager permissions = PermissionsEx.getPermissionManager();
		 String cmd = event.getMessage();
		 String[] command = cmd.split("\\s+");
		 if(command[0].equalsIgnoreCase("/redeem")){
		 	if(command.length==1){
		 		player.sendMessage(ChatColor.RED+"iKingsRedeem commands:");
				player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"[Code]");
				player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"list "+ChatColor.YELLOW+"[Page]");
				player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"create "+ChatColor.YELLOW+"[Uses] <Command>");
				player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"remove "+ChatColor.YELLOW+"<Code>");
				player.sendMessage(ChatColor.AQUA+"<Required> [Optional]");
		 	} else if(command.length==2){
				 if(command[1].equalsIgnoreCase("list")){
					 if(permissions.has(player, "ikingsredeem.list")){
						 if(file.contains("Coupons")){
							 Set<String> couponArray = file.getConfigurationSection("Coupons").getKeys(false); 
							int pages = (int) Math.ceil(couponArray.size()/4.0);
							player.sendMessage(ChatColor.RED+"Coupon : Uses : Command (Page 1/"+pages+")");
							int i = 0;
							if(couponArray.size()==0){
								player.sendMessage(ChatColor.RED+"No coupons exist.");
								return;
							}
							for (String coupon : couponArray) {
								if(i==4){
									return;
								}
								i++;
								player.sendMessage(ChatColor.GREEN+""+coupon+ChatColor.BLUE+" : "+ChatColor.GREEN+file.getInt("Coupons."+coupon+".Count")+ChatColor.BLUE+" : "+ChatColor.GREEN+file.getString("Coupons."+coupon+".Command"));
							}
						 } else {
							player.sendMessage(ChatColor.RED+"No coupons exist.");
						 } 
					} else {
						 player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
					 }
				 } else {
					 if(permissions.has(player, "ikingsredeem.use")){
						 if(file.contains("Coupons."+command[1])){
							 player.sendMessage(ChatColor.GREEN+"Coupon redeemed.");
							 String commandStr = file.getString("Coupons."+command[1]+".Command");
							 commandStr = commandStr.replaceAll("%player%", player.getName());
							 plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),commandStr);
							 if(file.getInt("Coupons."+command[1]+".Count")<=1){
								 file.set("Coupons."+command[1], null);
								 plugin.saveConfig();
							 } else {
								 file.set("Coupons."+command[1]+".Count", (file.getInt("Coupons."+command[1]+".Count"))-1);
							 }
						 } else {
							 player.sendMessage(ChatColor.RED+"Coupon not found. (Coupons are case sensitive");
						 }
					 } else {
						 player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
					 }
				 }
			 } else if(command.length==3){
				 if(command[1].equalsIgnoreCase("list")){
					 if(permissions.has(player, "ikingsredeem.list")){
						 if(command[2].matches("^[0-9]+$")){
							 if(file.contains("Coupons")){
								 Set<String> couponArray = file.getConfigurationSection("Coupons").getKeys(false); 
								 int pages = (int) Math.ceil(couponArray.size()/4.0);
								 int page = Integer.parseInt(command[2]);
								 if(couponArray.size()==0){
									 player.sendMessage(ChatColor.RED+"No coupons exist.");
									 return;
								 }
								 if(page>pages){
									 player.sendMessage(ChatColor.RED+"Page doesn't exist.");
									return;
								}
								player.sendMessage(ChatColor.RED+"Coupon : Uses : Command (Page "+command[2]+"/"+pages+")");
								int i = 0;
								for (String coupon : couponArray) {
									if(page*4-4<=i&&i<page*4){
										player.sendMessage(ChatColor.GREEN+""+coupon+ChatColor.BLUE+" : "+ChatColor.GREEN+file.getInt("Coupons."+coupon+".Count")+ChatColor.BLUE+" : "+ChatColor.GREEN+file.getString("Coupons."+coupon+".Command"));	
									}
									i++;
								}
							 } else {
								 player.sendMessage(ChatColor.RED+"No coupons exist.");
							 } 
						 } else {
							 player.sendMessage(ChatColor.RED+"You need to input a number.");
						 }
					 } else {
						 player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
					 }
				 } else if(command[1].equalsIgnoreCase("create")){
					if(permissions.has(player, "ikingsredeem.create")){
						String coupon = "";
						{
						for(int i=0;i<10;i++){
							if(rand.nextInt(2)==1){
								coupon += rand.nextInt(10)-1;
							} else {
								coupon += letterMap.get(rand.nextInt(52));
							}
						}
						} while(file.contains(coupon));
						file.set("Coupons."+coupon+".Command", command[2]);
						file.set("Coupons."+coupon+".Count", 1);
						plugin.saveConfig();
						player.sendMessage(ChatColor.GREEN+"Coupon created.");
					} else {
						player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
					}
				 } else if(command[1].equalsIgnoreCase("remove")) {
					 if(permissions.has(player, "ikingsredeem.remove")){
							if(file.contains("Coupons."+command[2])){
								file.set("Coupons."+command[1], null);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GREEN+"Coupon removed.");
							} else {
								player.sendMessage(ChatColor.RED+"Coupon not found.");
							}
					} else {
						player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
					}
				 } else {
					player.sendMessage(ChatColor.RED+"iKingsRedeem commands:");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"[Code]");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"list "+ChatColor.YELLOW+"[Page]");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"create "+ChatColor.YELLOW+"<Command> [Uses]");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"remove "+ChatColor.YELLOW+"<Code>");
					player.sendMessage(ChatColor.AQUA+"<Required> [Optional]");
				 }
			} else {
				if(command[1].equalsIgnoreCase("create")){
					if(permissions.has(player, "ikingsredeem.create")){
						String coupon = "";
						{
							for(int i=0;i<10;i++){
								if(rand.nextInt(2)==1){
									coupon += rand.nextInt(10)-1;
								} else {
									coupon += letterMap.get(rand.nextInt(52));
								}
							}
						} while(file.contains(coupon));
						String commandCoupon = "";
						if(command[2].matches("^[0-9]+$")){
							file.set("Coupons."+coupon+".Count", Integer.parseInt(command[2]));
						} else {
							file.set("Coupons."+coupon+".Count", 1);
							commandCoupon = command[2]+" ";
						}
						int i = 0;
						for(String str : command){
							if(i>2){
								commandCoupon += str+" ";
							}
							i++;
						}
						commandCoupon = commandCoupon.substring(0, commandCoupon.length()-1);
						file.set("Coupons."+coupon+".Command", commandCoupon);
						plugin.saveConfig();
						player.sendMessage(ChatColor.GREEN+"Coupon created.");
					} else {
						player.sendMessage(ChatColor.RED+"You don't have permission to do that.");
					}
				} else {
					player.sendMessage(ChatColor.RED+"iKingsRedeem commands:");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"[Code]");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"list "+ChatColor.YELLOW+"[Page]");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"create "+ChatColor.YELLOW+"<Command> [Uses]");
					player.sendMessage(ChatColor.BLUE+"/redeem "+ChatColor.GREEN+"remove "+ChatColor.YELLOW+"<Code>");
					player.sendMessage(ChatColor.AQUA+"<Required> [Optional]");
				}
			}
		 }
	 }
}