package com.james090500.VelocityWhitelist.helpers;

import com.james090500.VelocityWhitelist.VelocityWhitelist;
import com.james090500.VelocityWhitelist.config.Configs;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import static com.james090500.VelocityWhitelist.config.Configs.whitelist;

public class WhitelistHelper {

    private final VelocityWhitelist velocityWhitelist;
    private final CommandSource source;

    public WhitelistHelper(VelocityWhitelist velocityWhitelist, CommandSource source) {
        this.velocityWhitelist = velocityWhitelist;
        this.source = source;
    }

    /**
     * Check if the player is in the whitelist OR has bypass permissions
     * @param player
     * @return If player is whitelisted or has bypass
     */
    public static boolean check(Player player) {
        return player.hasPermission("vwhitelist.bypass") || whitelist.contains(player.getUsername());
    }

    /**
     * Add a player to the whitelist
     * @param username
     */
    public void add(String username) {
        velocityWhitelist.getServer().getScheduler().buildTask(velocityWhitelist, () -> {
            if(username == null) {
                source.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&c" + velocityWhitelist.PREFIX + username + " is not a valid username"));
            } else if(whitelist.contains(username)) {
                source.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&a" + velocityWhitelist.PREFIX + username + " is already in the whitelist"));
            } else {
                whitelist.add(username);
                Configs.saveWhitelist(velocityWhitelist);
                source.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&a" + velocityWhitelist.PREFIX + username + " has been added to the whitelist"));
            }
        }).schedule();
    }

    /**
     * Remove a player from the whitelist
     * @param username
     */
    public void remove(String username) {
        velocityWhitelist.getServer().getScheduler().buildTask(velocityWhitelist, () -> {
            if(username == null) {
                source.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&c" + velocityWhitelist.PREFIX + username + " is not a valid username"));
            } else if(!whitelist.contains(username)) {
                source.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&a" + velocityWhitelist.PREFIX + username + " is not in the whitelist"));
            } else {
                whitelist.remove(username);
                Configs.saveWhitelist(velocityWhitelist);
                source.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&a" + velocityWhitelist.PREFIX + username + " has been removed from the whitelist"));
            }
        }).schedule();
    }

}
