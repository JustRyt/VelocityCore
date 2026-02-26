package net.justryt.vcore;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import net.justryt.vcore.commands.BrigCommand;
import net.justryt.vcore.commands.ping.PingCommand;
import net.justryt.vcore.configuration.MainConfig;
import net.justryt.vcore.listeners.ServerDecorator;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(
        id = "vcore",
        name = "VelocityCore",
        version = "1.0.0",
        url = "https://justryt.net",
        description = "Main velocity handler for the JustRyt server.",
        authors = {"codedbypritam"}
)
@Getter
public class VelocityCore {
    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;

    private final MainConfig mainConfig;

    @Inject
    public VelocityCore(@NotNull ProxyServer server, @NotNull Logger logger, @DataDirectory @NotNull Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;

        this.mainConfig = new MainConfig(this);
    }

    @Subscribe
    public void onProxyInitialize(@NotNull ProxyInitializeEvent event) {
        server.getEventManager().register(this, new ServerDecorator(this));

        final var commandManager = server.getCommandManager();

        registerCommand(new PingCommand(this), commandManager);
    }

    private void registerCommand(@NotNull BrigCommand cmd, @NotNull CommandManager commandManager) {
        commandManager.register(cmd.getCommandMeta(), cmd.getCommand());
    }
}
