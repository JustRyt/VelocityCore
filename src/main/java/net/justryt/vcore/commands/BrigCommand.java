package net.justryt.vcore.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.CommandSource;
import net.justryt.vcore.VelocityCore;
import org.jetbrains.annotations.NotNull;

public abstract class BrigCommand {
    protected VelocityCore core;

    public BrigCommand(@NotNull VelocityCore core) {
        this.core = core;
    }

    public abstract LiteralCommandNode<CommandSource> getCommand();
    public abstract CommandMeta getCommandMeta();
}
