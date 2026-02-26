package net.justryt.vcore.configuration;

import net.justryt.vcore.VelocityCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public class MainConfig {
    private final VelocityCore core;
    private final YamlConfig config;

    public MainConfig(@NotNull VelocityCore core) {
        this.core = core;
        this.config = new YamlConfig(core, "config", true);
    }

    public Component getServerName() {
        var mmServerName = config
                .node()
                .node("server-info", "name")
                .getString();

        if (mmServerName == null) mmServerName = "";

        return MiniMessage
                .miniMessage()
                .deserialize(mmServerName);
    }

    public TagResolver getServerNameTagResolver() {
        return TagResolver
                .builder()
                .resolver(Placeholder.parsed("server-name", config
                        .node()
                        .node("server-info", "name")
                        .getString("")))
                .build();
    }

    public Component getMotdLineOne() {
        var mmLineOne = config
                .node()
                .node("motd", "lines", "1")
                .getString();

        if (mmLineOne == null) mmLineOne = "";

        return MiniMessage
                .miniMessage()
                .deserialize(mmLineOne, getServerNameTagResolver());
    }

    public Component getMotdLineTwo() {
        var mmLineTwo = config
                .node()
                .node("motd", "lines", "2")
                .getString();

        if (mmLineTwo == null) mmLineTwo = "";

        return MiniMessage
                .miniMessage()
                .deserialize(mmLineTwo, getServerNameTagResolver());
    }
}
