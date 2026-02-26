package net.justryt.vcore.utils;

import net.kyori.adventure.text.format.Style;
import solar.squares.pixelwidth.DefaultCharacterWidthFunction;

public class CustomCharacterWidthFunction extends DefaultCharacterWidthFunction {
    @Override
    public float handleMissing(int codepoint, Style style) {
        if (codepoint == 160) return 6F;
        return super.handleMissing(codepoint, style);
    }
}
