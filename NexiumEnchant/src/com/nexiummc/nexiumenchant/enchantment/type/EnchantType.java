package com.nexiummc.nexiumenchant.enchantment.type;

import com.nexiummc.nexiumenchant.enchantment.UniversalEnchantment;

/**
 * Valid Enchantment types.
 * @author searchndstroy
 * @see {@link BookEnchantment}
 * @see {@link EnchantmentTableEnchantment}
 * @see {@link FoodEnchantment}
 * @see {@link ParticleEnchantment}
 * @see {@link ToolEnchantment}
 * @see {@link UniversalEnchantment}
 */
public enum EnchantType {
	
	/**
	 * @see {@link BookEnchantment}
	 */
	BOOK,
	
	/**
	 * @see {@link EnchantmentTableEnchantment}
	 */
	ENCHANT_TABLE,
	
	/**
	 * @see {@link FoodEnchantment}
	 */
	FOOD,
	
	/**
	 * @see {@link ParticleEnchantment}
	 */
	PARTICLE,
	
	/**
	 * @see {@link ProjectileEnchantment}
	 */
	PROJECTILE,
	
	/**
	 * @see {@link ToolEnchantment}
	 */
	TOOL;
}