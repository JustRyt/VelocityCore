package net.justryt.vcore.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import net.justryt.vcore.VelocityCore;
import net.justryt.vcore.utils.CustomCharacterWidthFunction;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import solar.squares.pixelwidth.PixelWidthSource;
import solar.squares.pixelwidth.utils.CenterAPI;

public class ServerDecorator {
    private final VelocityCore core;

    private static final float MOTD_LINE_WIDTH_PX = 270f;

    private static final TextComponent PAD = Component.text("\u00A0");

    private final PixelWidthSource pixelWidth;

    public ServerDecorator(@NotNull VelocityCore core) {
        this.core = core;
        this.pixelWidth = PixelWidthSource.pixelWidth(new CustomCharacterWidthFunction());
    }

    @Subscribe
    public void onPing(@NotNull ProxyPingEvent event) {
        Component line1 = safeCenter(core.getMainConfig().getMotdLineOne());
        Component line2 = safeCenter(core.getMainConfig().getMotdLineTwo());

        final var serverDescription = line1.appendNewline().append(line2);
        event.setPing(event
                .getPing()
                .asBuilder()
                .description(serverDescription)
                .build());
    }

    private Component safeCenter(@NotNull Component component) {
        float textW = pixelWidth.width(component);
        float padW  = pixelWidth.width(PAD);

        float perSide = (ServerDecorator.MOTD_LINE_WIDTH_PX - textW) / 2f;
        if (perSide < padW) return component;

        return CenterAPI.center(component, pixelWidth, PAD, ServerDecorator.MOTD_LINE_WIDTH_PX);
    }
}