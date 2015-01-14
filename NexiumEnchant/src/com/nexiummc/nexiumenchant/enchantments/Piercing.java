package com.nexiummc.nexiumenchant.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;

public class Piercing extends BaseEnchantment implements ToolEnchantment {

		public Piercing() {
			super("Piercing", null, (short) 0, (short) 3);
		}
		
			
		@Override
		public ChatColor[] getEnchantmentLorePrefix() {
			return new ChatColor[] {ChatColor.DARK_RED};
		}

		@Override
		public ToolType getToolType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onBreakBlock(ItemStack tool, Block block,
				short enchantLevel, Player player,
				boolean usingCorrectToolType, BlockBreakEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onInteract(ItemStack tool, Block block, short enchantLevel,
				Player player, boolean usingCorrectToolType,
				PlayerInteractEvent event) {
			// TODO Auto-generated method stub
			
		}

		
	//	public double	heal;
		@Override
		public void onAttackEntity(ItemStack tool, LivingEntity attacker,
				LivingEntity defender, short enchantLevel,
				boolean usingCorrectToolType, EntityDamageByEntityEvent event) {
			
			Player player = (Player) event.getDamager();
			Entity ent = event.getEntity();
			
			double trueDamage = (enchantLevel); 
			double currentHealth = ((Damageable) ent).getHealth();
			
			if(currentHealth > trueDamage) {
				((Damageable) ent).setHealth(currentHealth-trueDamage);
				player.getWorld().playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 0.1f, 0.1f);
			}
			
			
		}
		
	}

