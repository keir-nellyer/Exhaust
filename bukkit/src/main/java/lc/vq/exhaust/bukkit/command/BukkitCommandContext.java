package lc.vq.exhaust.bukkit.command;

import lc.vq.exhaust.command.AbstractCommandContext;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Bukkit-oriented command context.
 */
public final class BukkitCommandContext extends AbstractCommandContext<Server, CommandSender, Player> {

    /** The Bukkit server. */
    @Nonnull private final Server server;
    /** The command sender. */
    @Nonnull private final CommandSender sender;

    public BukkitCommandContext(@Nonnull final Server server, @Nonnull final CommandSender sender) {
        checkNotNull(server, "server");
        checkNotNull(sender, "sender");

        this.server = server;
        this.sender = sender;

        this.locals.put(Server.class, server);
        this.locals.put(CommandSender.class, sender);
        this.locals.put(Player.class, (sender instanceof Player) ? (Player) sender : null);
    }

    @Nonnull
    @Override
    public Server getServer() {
        return this.server;
    }

    @Nonnull
    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Nullable
    @Override
    public Player getPlayer() {
        final Player player = this.locals.get(Player.class);
        if(player != null) {
            return player;
        } else {
            return null;
        }
    }

    @Override
    public void respond(@Nonnull final String message) {
        checkNotNull(message, "message");
        this.sender.sendMessage(message);
    }
}
