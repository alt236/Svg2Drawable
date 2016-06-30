package uk.co.alt236.s2d.enums;

public enum ConverterName {
    INKSCAPE,
    BATIK;

    public static ConverterName fromString(final String string) {
        ConverterName retVal = null;

        for (final ConverterName iconType : ConverterName.values()) {
            if (iconType.name().equalsIgnoreCase(string)) {
                retVal = iconType;
                break;
            }
        }

        return retVal;
    }
}
