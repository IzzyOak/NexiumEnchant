package com.nexiummc.nexiumenchant.enchantment;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.enchantment.type.BookEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.EnchantType;
import com.nexiummc.nexiumenchant.enchantment.type.EnchantmentTableEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.FoodEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ParticleEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ProjectileEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;
import com.nexiummc.nexiumenchant.particle.Particle;

/**
 * Implements all other enchantments. For convenience.
 * @author searchndstroy
 * @see {@link BaseEnchantment}
 * @see {@link BookEnchantment}
 * @see {@link EnchantmentTableEnchantment}
 * @see {@link FoodEnchantment}
 * @see {@link ParticleEnchantment}
 * @see {@link ToolEnchantment}
 * @see {@link EnchantType}
 */
public abstract class UniversalEnchantment extends BaseEnchantment implements BookEnchantment, EnchantmentTableEnchantment, FoodEnchantment, ParticleEnchantment, ProjectileEnchantment, ToolEnchantment {
	
	protected UniversalEnchantment(String name, FileConfiguration config, short minLevel,
			short maxLevel) {
		super(name, config, minLevel, maxLevel);
	}
	
	@Override
	public void onConsume(Player player, ItemStack itemStack, short enchantLevel,
			PlayerItemConsumeEvent event) {}
	
	@Override
	public Particle applyParticles(LivingEntity attacker, LivingEntity defender,
			short enchantLevel, Event event, EnchantType type) {
		return null;
	}
	
	@Override
	public boolean onShotHitObject(ItemStack itemStack, int ticksTravelled,
			short enchantLevel, LivingEntity shooter, ProjectileHitEvent event) {
		return false;
	}
	
	@Override
	public void onShotSuccess(ItemStack itemStack, int ticksTravelled,
			short enchantLevel, LivingEntity attacker, LivingEntity defender,
			EntityDamageByEntityEvent event) {}
	
	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel, boolean usingCorrectToolType,
			EntityDamageByEntityEvent event) {
	}
	
	@Override
	public void onBreakBlock(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType, BlockBreakEvent event) {
	}
	
	@Override
	public void onInteract(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType, PlayerInteractEvent event) {
	}
	
	@Override
	public ToolType getToolType() {
		return ToolType.ALL;
	}
}