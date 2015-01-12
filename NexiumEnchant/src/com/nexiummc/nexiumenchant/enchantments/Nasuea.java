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

public class Nasuea extends BaseEnchantment implements ToolEnchantment{


	public Nasuea() {
		super("Nasuea", null, (short) 0, (short) 3);
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
		int dur = (enchantLevel+1)*20;
		defender.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, dur, 1));
		defender.getWorld().playSound(defender.getLocation(), Sound.HURT_FLESH, 1f, 0.1f);
		defender.getWorld().playSound(defender.getLocation(), Sound.ANVIL_LAND, 0.1f, 10f);
		
	}

	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		// TODO Auto-generated method stub
		return null;
	}
}
