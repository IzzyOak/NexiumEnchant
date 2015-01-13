package com.nexiummc.nexiumenchant.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;

public class Poison extends BaseEnchantment implements ToolEnchantment{


	public Poison() {
		super("Poison", null, (short) 0, (short) 2);
	}
	

	@Override
	public ToolType getToolType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onBreakBlock(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType, BlockBreakEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInteract(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType,
			PlayerInteractEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel,
			boolean usingCorrectToolType, EntityDamageByEntityEvent event) {
		
		int dur = enchantLevel*20;
		
		defender.addPotionEffect(new PotionEffect(PotionEffectType.POISON, dur, enchantLevel));
		defender.getWorld().playSound(defender.getLocation(), Sound.SPIDER_IDLE, 0.1f, 10f);
		
	}

	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		return new ChatColor[] {ChatColor.GREEN};
	}
}