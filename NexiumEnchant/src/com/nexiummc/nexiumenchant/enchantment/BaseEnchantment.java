package com.nexiummc.nexiumenchant.enchantment;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.nexiummc.nexiumenchant.NexiumEnchant;

/**
 * BaseEnchantment class. Extend this class to register an enchantment. Implement interfaces from the package 'com.github.searchndstroy.customenchantsapi.enchantment.type'
 * to set enchantment types. After implementing an interface, it will give a list of methods to implement. (Unless your class is abstract.) Implement the method(s), and, these
 * will be invoked when a specific event happens. (Example, an entity is attacked with your {@link ToolEnchantment} and the method {@link ToolEnchantment#onAttackEntity(ItemStack, LivingEntity, LivingEntity, short, boolean, EntityDamageByEntityEvent)} is
 * invoked.
 * @author searchndstroy
 * @see {@link UniversalEnchantment}
 */
public abstract class BaseEnchantment {
	
	/**
	 * Enchantment's unique name.
	 */
	public final String name;
	
	/**
	 * Enchantment's min level.
	 */
	public final short minLevel;
	
	/**
	 * Enchantment's max level.
	 */
	public final short maxLevel;
	
	/**
	 * An enchantment's config. May be null.
	 */
	public final FileConfiguration config;
	
	/**
	 * Constructor.
	 * @param name Enchantment's name.
	 * @param config Enchantment's config.
	 * @param minLevel Minimum level required for enchanting.
	 * @param maxLevel Maximum level for enchanting.
	 */
	
	protected BaseEnchantment(String name, FileConfiguration config, short minLevel, short maxLevel) {
		Validate.notNull(name, "Name cannot be null!");
		if (name.endsWith(" ") || name.startsWith(" ")) {
			throw new IllegalArgumentException("Enchantment name cannot end or start with a space!");
		}
		this.name = name.replaceAll(" ", "_");
		if (name.contains(" ")) {
			NexiumEnchant.warning("Enchantment '" + name + "' had spaces in its name! Replaced with underscores.");
		}
		this.config = config;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		NexiumEnchant.registerEnchantment(this);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * @return Colours used in the enchantment lore prefix.
	 */
	public abstract ChatColor[] getEnchantmentLorePrefix();
}