package com.nexiummc.nexiumenchant.enchantment.type;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;

/**
 * ToolEnchantment interface. Implement this interface to make your enchantment a tool type. Override {@link ToolEnchantment#getToolType()} to specify
 * which tool your enchantment is. Override 3 other methods
 * @author searchndstroy
 */
public interface ToolEnchantment {
	
	/**
	 * This is used for determining if an entity is using the correct {@link ToolType} on an object.
	 * @return The tool type for this enchantment.
	 */
	ToolType getToolType();
	
	/**
	 * Invoked when an item with this enchantment breaks a block. Do not modify the block event.
	 * @param tool Tool used in this event.
	 * @param block Block associated with this event.
	 * @param enchantLevel Enchantment level.
	 * @param player Player who broke the block.
	 * @param usingCorrectToolType Whether the player is using the correct tool type for the block.
	 * @param event Event involved in this event. Called with Priority on MONITOR and ignoreCancelled = true.
	 */
	void onBreakBlock(ItemStack tool, Block block, short enchantLevel, Player player, boolean usingCorrectToolType, BlockBreakEvent event);
	
	/**
	 * Called when an item with this enchantment interacts with air or a block. Check if the block is null, because, it may be. Do not modify the interact event.
	 * @param tool Tool used in this event.
	 * @param block Block associated with this event. May be null if the block is air.
	 * @param enchantLevel Enchantment level.
	 * @param player Player who broke the block.
	 * @param usingCorrectToolType Whether the player is using the correct tool type for the block.
	 * @param event Event involved in this event. Called with Priority MONITOR and ignoreCancelled = false.
	 */
	void onInteract(ItemStack tool, Block block, short enchantLevel, Player player, boolean usingCorrectToolType, PlayerInteractEvent event);
	
	/**
	 * Invoked when an entity attacks another entity with an item with this enchantment on it. Do not modify the attack event.
	 * @param tool Tool used in this event.
	 * @param attacker Entity using the enchantment.
	 * @param defender Entity being attacked with the enchantment.
	 * @param enchantLevel Enchantment level.
	 * @param usingCorrectToolType
	 * @param event Event involved in this event. Called with Priority MONTIR and ignoreCancelled = true.
	 */
	void onAttackEntity(ItemStack tool, LivingEntity attacker, LivingEntity defender, short enchantLevel, boolean usingCorrectToolType, EntityDamageByEntityEvent event);
}