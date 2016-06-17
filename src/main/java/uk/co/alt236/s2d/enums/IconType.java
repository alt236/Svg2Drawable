package uk.co.alt236.s2d.enums;

public enum IconType {

    LAUNCHER(48),
    ACTIONBAR(32),
    DIALOG(32),
    TAB(32),
    NOTIFICATION(24),
    CONTEXTUAL(16),
    WEB_ICON(512);

    private final float baseline;

    IconType(final int baseline) {
        this.baseline = baseline;
    }

    public static IconType fromString(final String string) {
        IconType retVal = null;

        for (final IconType iconType : IconType.values()) {
            if (iconType.name().equalsIgnoreCase(string)) {
                retVal = iconType;
                break;
            }
        }

        return retVal;
    }

    public float getBaseSize() {
        return baseline;
    }
}
