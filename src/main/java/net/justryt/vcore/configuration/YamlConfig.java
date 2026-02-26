package net.justryt.vcore.configuration;

import net.justryt.vcore.VelocityCore;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

public final class YamlConfig {
    private final VelocityCore core;

    private final Path file;
    private final YamlConfigurationLoader loader;

    private ConfigurationNode root;

    public YamlConfig(@NotNull VelocityCore core, @NotNull String name) {
        this(core, name, false);
    }

    public YamlConfig(@NotNull VelocityCore core, @NotNull String name, boolean isDefault) {
        this.core = core;
        this.file = core.getDataDirectory().resolve("%s.yml".formatted(name));
        this.loader = YamlConfigurationLoader
                .builder()
                .path(file)
                .build();

        try {
            if (isDefault) createDefaultFile("%s.yml".formatted(name));
            load();
        } catch (Exception e) {
            core.getLogger().log(Level.SEVERE, "Failed to initialize config", e);
        }
    }

    private void createDefaultFile(@NotNull String name) throws IOException {
        Files.createDirectories(file.getParent());
        if (Files.notExists(file)) {
            try (InputStream in = core.getClass().getClassLoader().getResourceAsStream(name)) {
                if (in == null) throw new IllegalStateException("%s not found in default resources");
                Files.copy(in, file, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public void load() {
        try {
            this.root = loader.load();
        } catch (Exception e) {
            core.getLogger().log(Level.SEVERE, "Failed to load config file", e);
        }
    }

    public void save() {
        try {
            loader.save(root);
        } catch (Exception e) {
            core.getLogger().log(Level.SEVERE, "Failed to save config file", e);
        }
    }

    public ConfigurationNode node() {
        return root;
    }
}
