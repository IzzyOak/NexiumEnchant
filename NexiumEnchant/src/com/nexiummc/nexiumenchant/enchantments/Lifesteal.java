package com.nexiummc.nexiumenchant.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;

public class Lifesteal extends BaseEnchantment implements ToolEnchantment {


		public Lifesteal() {
			super("Lifesteal", null, (short) 0, (short) 3);
		}
		
			
		@Override
		public ChatColor[] getEnchantmentLorePrefix() {
			return new ChatColor[] {ChatColor.DARK_GREEN};
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
			
			Player damager = (Player) event.getDamager();
			double dmg = event.getDamage();
			double dmgmult = dmg/5;
			double enchantmult = enchantLevel/2;
			if(damager.getGameMode().equals(GameMode.CREATIVE))
				return;
			double newhp = ((Damageable) damager).getHealth() + dmgmult + enchantmult;

			if(newhp < ((Damageable) damager).getMaxHealth())
				damager.setHealth(newhp);
			else
				damager.setHealth(((Damageable) damager).getMaxHealth());
			
			
		}
		
	}

