package uk.co.alt236.s2d.enums;

public enum DPI {
    MDPI(1.0f),
    HDPI(1.5f),
    XHDPI(2.0f),
    XXHDPI(3.0f),
    XXXHDPI(4.0f);

    private final float scale;

    DPI(final float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }
}
