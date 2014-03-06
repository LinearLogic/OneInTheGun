package com.entrocorp.linearlogic.oneinthegun.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Flying;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public class GameListener implements Listener {

    private boolean listening = false;

    public boolean isRegistered() {
        return listening;
    }

    public void setRegistered(boolean registered) {
        if (listening == registered)
            return;
        if (registered)
            OITG.instance.getServer().getPluginManager().registerEvents(this, OITG.instance);
        else
            HandlerList.unregisterAll(this);
        listening = registered;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBuild(BlockPlaceEvent event) {
        Arena arena = OITG.instance.getArenaManager().getArena(event.getPlayer());
        if (arena != null && !arena.isBlockPlacingAllowed()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "Block placing is disabled in this arena.");
            return;
        }
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (OITG.instance.getArenaManager().getArena(event.getPlayer()) == null)
            return;
        if (event.getMessage().toLowerCase().startsWith("/oitg") || event.getPlayer().hasPermission("oneinthegun.arena.allowcommands"))
            return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "Only OITG commands are permitted in an arena.");
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Player defender = (event.getEntity() instanceof Player ? (Player) event.getEntity() : null),
                attacker = (event.getDamager() instanceof Player ? (Player) event.getDamager() : null);
        if (defender == null && attacker == null)
            return;

        Arena defenderArena = OITG.instance.getArenaManager().getArena(defender),
                attackerArena = OITG.instance.getArenaManager().getArena(attacker);
        if (defenderArena != null) {
            if (!defenderArena.isIngame()) {
                event.setCancelled(true);
                return;
            }
            if (defenderArena.isPlayerInGodmode(defender)) {
                event.setCancelled(true);
                return;
            }
            if (attacker == null) {
                if (event.getCause().equals(DamageCause.PROJECTILE)) {
                    LivingEntity shooter = (LivingEntity) ((Projectile) event.getDamager()).getShooter();
                    if (shooter == null)
                        return;
                    if (shooter instanceof Player) {
                        attacker = (Player) shooter;
                        attackerArena = OITG.instance.getArenaManager().getArena(attacker);
                        if (!defenderArena.equals(attackerArena)) {
                            event.setCancelled(true);
                            return;
                        }
                        if (!(event.getDamager() instanceof Arrow))
                            return;
                        event.setCancelled(true);
                        if (defender == attacker)
                            return;
                        defenderArena.broadcast(ChatColor.GOLD + defender.getName() + ChatColor.GRAY + " was sniped by " +
                                ChatColor.GOLD + attacker.getName() + ChatColor.GRAY + "!");
                        attacker.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                        attacker.updateInventory();
                        defenderArena.killPlayer(defender);
                        defenderArena.incrementKills(attacker);
                        attacker.playSound(attacker.getLocation(), Sound.ORB_PICKUP, 1F, 0F);
                        return;
                    }
                    if ((shooter instanceof Creature || shooter instanceof Flying) && !defenderArena.isMobCombatAllowed()) {
                        event.setCancelled(true);
                        return;
                    }
                    if (defender.getHealth() - event.getDamage() <= 0.0) {
                        event.setCancelled(true);
                        defenderArena.broadcast(ChatColor.GOLD + defender.getName() + ChatColor.GRAY + " was killed by a " +
                        shooter.getType().toString().toLowerCase().replaceAll("_", " ") + "!");
                        defenderArena.killPlayer(defender);
                    }
                    return;
                }
                if (event.getCause().equals(DamageCause.ENTITY_ATTACK) || event.getCause().equals(DamageCause.ENTITY_EXPLOSION) ||
                        event.getCause().equals(DamageCause.WITHER)) {
                    if (!defenderArena.isMobCombatAllowed()) {
                        event.setCancelled(true);
                        return;
                    }
                    if (defender.getHealth() - event.getDamage() <= 0.0) {
                        event.setCancelled(true);
                        defenderArena.broadcast(ChatColor.GOLD + defender.getName() + ChatColor.GRAY + " was killed by a " +
                                (event.getCause().equals(DamageCause.ENTITY_EXPLOSION) ? "creeper" :
                                event.getCause().equals(DamageCause.WITHER) ? "wither" :
                                event.getDamager().getType().toString().toLowerCase().replaceAll("_", " ")) + "!");
                        defenderArena.killPlayer(defender);
                    }
                }
                return;
            }
            if (!defenderArena.equals(attackerArena)) {
                event.setCancelled(true);
                return;
            }
            if (defender.getHealth() - event.getDamage() <= 0.0) {
                event.setCancelled(true);
                defenderArena.broadcast(ChatColor.GOLD + defender.getName() + ChatColor.GRAY + " was killed by " +
                        ChatColor.GOLD + attacker.getName() + ChatColor.GRAY + "!");
                defenderArena.killPlayer(defender);
                defenderArena.incrementKills(attacker);
                attacker.playSound(attacker.getLocation(), Sound.ORB_PICKUP, 1F, 0F);
            }
            return;
        }
        if (attackerArena == null)
            return;
        if (defender != null) {
            event.setCancelled(true);
            return;
        }
        if (!attackerArena.isIngame()) {
            event.setCancelled(true);
            return;
        }
        if (!attackerArena.isMobCombatAllowed())
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();
        Arena arena = OITG.instance.getArenaManager().getArena(player);
        if (arena != null && !arena.isHealthRegenAllowed())
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onHunger(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();
        Arena arena = OITG.instance.getArenaManager().getArena(player);
        if (arena != null && !arena.isHungerAllowed())
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getInventory().getType().equals(InventoryType.CRAFTING)) // Survival inventory
            return;
        Arena arena = OITG.instance.getArenaManager().getArena((Player) event.getWhoClicked());
        if (arena != null && !arena.isItemDroppingAllowed() && event.getSlot() == -999)
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemDrop(PlayerDropItemEvent event) {
        Arena arena = OITG.instance.getArenaManager().getArena(event.getPlayer());
        if (arena == null || arena.isItemDroppingAllowed())
            return;
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemPickup(PlayerPickupItemEvent event) {
        Arena arena = OITG.instance.getArenaManager().getArena(event.getPlayer());
        if (arena == null || arena.isItemPickupAllowed())
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow))
            return;
        if (!(event.getEntity().getShooter() instanceof Player))
            return;
        if (OITG.instance.getArenaManager().getArena((Player) event.getEntity().getShooter()) != null)
            event.getEntity().remove();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Arena arena = OITG.instance.getArenaManager().getArena(event.getPlayer());
        if (arena == null)
            return;
        arena.removePlayer(event.getPlayer(), true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerRespawnEvent event) {
        Arena arena = OITG.instance.getArenaManager().getArena(event.getPlayer());
        if (arena == null)
            return;
        if (!arena.isIngame()) {
            event.setRespawnLocation(arena.getLobby());
        }
        event.setRespawnLocation(arena.getRandomSpawn());
        arena.incrementDeaths(event.getPlayer());
        arena.armPlayer(event.getPlayer());
    }
}
