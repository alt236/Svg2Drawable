package uk.co.alt236.s2d.converters;

import uk.co.alt236.s2d.enums.DPI;

/*package*/ final class ProviderUtils {
    private ProviderUtils() {
        // NOOP
    }

    public static String createFilename(final String path, final DPI dpi) {
        return path + "-" + dpi + ".png";
    }
}
