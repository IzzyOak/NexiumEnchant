package com.nexiummc.nexiumenchant.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.nexiummc.nexiumenchant.NexiumEnchant;

public class Timer {

	NexiumEnchant pl;

	List<LivingEntity> cooldown = new ArrayList<LivingEntity>();
	
	public boolean getHasCooldown(LivingEntity attacker) {
		if(cooldown.contains(attacker))
			return true;
		return false;
	}
	public void generateCooldown(final LivingEntity attacker, long cooldownT) {
	  if(cooldownT != 0) {
		new BukkitRunnable() {
			@Override
			public void run() {
				cooldown.remove(attacker);
			}
		}.runTaskLater(pl, cooldownT);
	  }
	}
}
