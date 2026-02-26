package net.justryt.vcore.commands.ping;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.justryt.vcore.VelocityCore;
import net.justryt.vcore.commands.BrigCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public class PingCommand extends BrigCommand {
    public PingCommand(@NotNull VelocityCore core) {
        super(core);
    }

    @Override
    public LiteralCommandNode<CommandSource> getCommand() {
        return BrigadierCommand
                .literalArgumentBuilder("ping")
                .requires(src -> src instanceof Player && src.hasPermission("access.not-suspended"))
                .executes(ctx -> {
                    final var player = (Player) ctx.getSource();
                    player.sendMessage(Component
                            .text("Your ping is %sms".formatted(player.getPing()), NamedTextColor.GREEN));
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }

    @Override
    public CommandMeta getCommandMeta() {
        return core.getServer().getCommandManager().metaBuilder("ping").plugin(core).build();
    }
}
