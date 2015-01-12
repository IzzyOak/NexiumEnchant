package com.nexiummc.nexiumenchant.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.handlers.EnchantmentHandler;
import com.nexiummc.nexiumenchant.scheduler.Scheduler;

/**
 * @author searchndstroy
 * Command class.
 */
public final class NexiumEnchantCommand implements TabExecutor {
	
	/**
	 * The only five valid first arguments.
	 */
	private static final List<String> emptyArguments = Collections.unmodifiableList(Arrays.asList("enchant", "list", "unenchant", "config", "unsafeenchant"));
	
	/**
	 * Used when a user does not have an item in their hand.
	 */
	private static final String nullItemStack = ChatColor.RED + "Put an item in your hand!";
	
	/**
	 * Used when a user inputs an enchantment that does not exist.
	 */
	private static final String enchantDoesNotExist = ChatColor.RED + "Enchantment '%s' doesn't exist!";
	
	/**
	 * Used when a user successfully enchants their item.
	 */
	private static final String enchantSuccess = ChatColor.GREEN + "Enchanted your '%s' with enchantment '%s' with tier level %d!";
	
	/**
	 * Used when a tier is out of a Short's range.
	 */
	private static final String invalidTier = ChatColor.RED + "Tier level '%d' is not between 0 and 32768!";
	
	/**
	 * Used when the tier isn't a number.
	 */
	private static final String failTier = ChatColor.RED + "Tier level has to be a number! '%s' isn't!";
	
	/**
	 * Used when a user attempts to enchant an item that already has the enchantment on it.
	 */
	private static final String alreadyEnchanted = ChatColor.RED + "Your %s already has the enchantment '%s' on it!";
	
	/**
	 * Used when safe enchanting is used and if the tier is not in the enchantment's range.
	 */
	private static final String illegalTier = ChatColor.RED +  "Enchantment '%s' only allows a tier level from %d through %d!";
	
	/**
	 * Used when a player requests the list of enchantments.
	 */
	private static final String listEnchantments = ChatColor.AQUA + "Enchantments:\n" + ChatColor.RED + "%s";
	
	/**
	 * Plugin instance.
	 */
	private final NexiumEnchant plugin; //TODO find a use...
	
	/**
	 * @param arg Argument to check.
	 * @return Integer code showing which argument was picked. -1 is returned if the argument does not exist.
	 */
	private static int argCode(String arg) {
		for (int i = 0; i < emptyArguments.size(); i++) {
			if (arg.equalsIgnoreCase(emptyArguments.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Constructor. Should only be called once.
	 */
	public NexiumEnchantCommand() {
		plugin = NexiumEnchant.instance();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if ((sender instanceof Player) && (args.length > 2)) {
			Player player = (Player) sender;
			ItemStack itemStack = player.getItemInHand();
			if (itemStack == null) {
				sender.sendMessage(nullItemStack);
				return true;
			}
			long tierLevel = 0;
			boolean failTier = false;
			String tier = null;
			switch (argCode(args[0])) {
				case 4:
					try {
						tierLevel = new Long((tier = args[args.length - 1]));
					} catch (NumberFormatException ex) {
						failTier = true;
					}
				case 0:
					boolean safe = tierLevel != 0;
					if (!safe && !failTier) {
						try {
							tierLevel = new Long((tier = args[args.length - 1]));
						} catch (NumberFormatException ex) {
							failTier = true;
						}
					}
					String enchantmentName = args[1];
					BaseEnchantment enchantment = EnchantmentHandler.getEnchantment(enchantmentName);
					if (enchantment == null) {
						sender.sendMessage(String.format(enchantDoesNotExist, enchantmentName));
						return true;
					}
					if (failTier) {
						sender.sendMessage(String.format(NexiumEnchantCommand.failTier, tier));
						return true;
					}
					if ((tierLevel < 1) || (tierLevel > Short.MAX_VALUE)) {
						sender.sendMessage(String.format(invalidTier, tierLevel));
						return true;
					}
					if (!safe && ((tierLevel > enchantment.maxLevel) || ((tierLevel < enchantment.minLevel)))) {
						sender.sendMessage(String.format(illegalTier, enchantment.name, enchantment.minLevel, enchantment.maxLevel));
						return true;
					}
					if (EnchantmentHandler.applyEnchantment(itemStack, enchantment, (short) tierLevel)) {
						sender.sendMessage(String.format(enchantSuccess, itemStack.getType(), enchantmentName, tierLevel));
					} else {
						sender.sendMessage(String.format(alreadyEnchanted, itemStack.getType(), enchantmentName));
					}
					return true;
					
			}
		} else if (args.length == 1) {
			switch (argCode(args[0])) {
				case 1:
					StringBuilder messageBuilder = new StringBuilder();
					for (String enchantment : Scheduler.getEnchantments()) {
						messageBuilder.append(", ").append(enchantment);
					}
					String message = messageBuilder.replace(0, 1, "").toString();
					sender.sendMessage(String.format(listEnchantments, message));
					return true;
			}
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		switch (args.length) {
			case 1:
				return emptyArguments;
			case 2:
				return Scheduler.getEnchantments();
				
		}
		return null;
	}
}