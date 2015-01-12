package com.nexiummc.nexiumenchant.listeners;

import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.FoodEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ProjectileEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.EnchantmentHandler;
import com.nexiummc.nexiumenchant.handlers.ToolHandler;

public class EnchantmentListener implements Listener {
	
	// TODO Fix ticks travelled. I have a solution, I just have to implement it.
	
	private static void addProjectileMeta(Projectile projectile, ItemStack itemstack) {
		projectile.setMetadata(NexiumEnchant.ITEMSTACK_METADATA, new FixedMetadataValue(NexiumEnchant.instance(), itemstack));
	}
	
	private static void removeProjectileMeta(Projectile projectile) {
		projectile.removeMetadata(NexiumEnchant.ITEMSTACK_METADATA, NexiumEnchant.instance());
	}
	
	private static ItemStack getItemStack(Projectile projectile) {
		if (projectile.hasMetadata(NexiumEnchant.ITEMSTACK_METADATA)) {
			return (ItemStack) projectile.getMetadata(NexiumEnchant.ITEMSTACK_METADATA).get(0).value();
		}
		return null;
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		Projectile projectile = event.getEntity();
		ProjectileSource shooter = projectile.getShooter();
		if (shooter != null) {
			ItemStack itemstack = ((LivingEntity)shooter).getEquipment().getItemInHand();
			addProjectileMeta(projectile, itemstack);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onProjectileHit(ProjectileHitEvent event) {
		Projectile projectile = event.getEntity();
		ItemStack itemStack = getItemStack(projectile);
		if (itemStack != null) {
			Map<BaseEnchantment, Short> enchantments = EnchantmentHandler.getEnchantments(itemStack, ProjectileEnchantment.class);
			ProjectileSource source = projectile.getShooter();
			if ((source != null) && (source instanceof LivingEntity)) {
				for (BaseEnchantment enchantment : enchantments.keySet()) {
					if (((ProjectileEnchantment) enchantment).onShotHitObject(itemStack, projectile.getTicksLived(), enchantments.get(enchantment), (LivingEntity) source, event)) {
						removeProjectileMeta(projectile);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			Entity attackingEntity = event.getDamager();
			if (attackingEntity instanceof Projectile) {
				Projectile projectile = (Projectile) attackingEntity;
				ProjectileSource source = projectile.getShooter();
				if ((source != null) && (source instanceof LivingEntity)) {
					LivingEntity attacker = (LivingEntity) source;
					ItemStack itemStack = attacker.getEquipment().getItemInHand();
					if (itemStack != null) {
						Map<BaseEnchantment, Short> enchantments = EnchantmentHandler.getEnchantments(itemStack, ProjectileEnchantment.class);
						for (BaseEnchantment enchantment : enchantments.keySet()) {
							short level = enchantments.get(enchantment);
							((ProjectileEnchantment) enchantment).onShotSuccess(itemStack, attackingEntity.getTicksLived(), level, attacker, (LivingEntity) event.getEntity(), event);
						}
					}
				}
			} else if (attackingEntity instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) attackingEntity;
				ItemStack itemStack = attacker.getEquipment().getItemInHand();
				if (itemStack != null) {
					Map<BaseEnchantment, Short> enchantments = EnchantmentHandler.getEnchantments(itemStack, ToolEnchantment.class);
					for (BaseEnchantment enchantment : enchantments.keySet()) {
						short level = enchantments.get(enchantment);
						ToolEnchantment toolEnchant = (ToolEnchantment) enchantment;
						toolEnchant.onAttackEntity(itemStack, attacker, (LivingEntity) event.getEntity(), level, (toolEnchant.getToolType() == ToolHandler.fromItem(itemStack)), event);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		ItemStack itemStack = event.getItem();
		Player player = event.getPlayer();
		Map<BaseEnchantment, Short> enchantments = EnchantmentHandler.getEnchantments(itemStack, FoodEnchantment.class);
		for (BaseEnchantment enchantment : enchantments.keySet()) {
			((FoodEnchantment) enchantment).onConsume(player, itemStack, enchantments.get(enchantment), event);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		ItemStack itemStack = player.getItemInHand();
		if (itemStack != null) {
			Map<BaseEnchantment, Short> enchantments = EnchantmentHandler.getEnchantments(itemStack, ToolEnchantment.class);
			for (BaseEnchantment enchantment : enchantments.keySet()) {
				short level = enchantments.get(enchantment);
				ToolEnchantment toolEnchant = (ToolEnchantment) enchantment;
				toolEnchant.onBreakBlock(itemStack, block, level, player, (toolEnchant.getToolType() == ToolHandler.fromItem(itemStack)), event);
			}
		}
	}
}