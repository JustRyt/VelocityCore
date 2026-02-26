package net.justryt.vcore.commands;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandMeta;
import net.justryt.vcore.VelocityCore;
import org.jetbrains.annotations.NotNull;

public abstract class BrigCommand {
    protected VelocityCore core;

    public BrigCommand(@NotNull VelocityCore core) {
        this.core = core;
    }

    public abstract BrigadierCommand getCommand();
    public abstract CommandMeta getCommandMeta();
}
