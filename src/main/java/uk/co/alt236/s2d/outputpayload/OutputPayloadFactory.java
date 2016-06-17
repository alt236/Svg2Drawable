package uk.co.alt236.s2d.outputpayload;

import uk.co.alt236.s2d.enums.DPI;
import uk.co.alt236.s2d.enums.IconType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class OutputPayloadFactory {

    private OutputPayloadFactory() {
        // NOOP
    }

    public static List<OutputPayload> createJobList(final IconType iconType,
                                                    final String svgPath,
                                                    final String outputDirectory) {

        final List<OutputPayload> list = new ArrayList<>();
        final File outputDirectoryAsFile = new File(outputDirectory);
        final String targetFilename = createPngFilename(svgPath);

        if (iconType == IconType.WEB_ICON) {
            list.add(new OutputPayload(
                    svgPath,
                    (int) iconType.getBaseSize(),
                    (int) iconType.getBaseSize(),
                    appendTargetFile(outputDirectoryAsFile, targetFilename),
                    iconType.toString()));
        } else {
            for (final DPI dpi : DPI.values()) {
                final int dim = (int) (dpi.getScale() * iconType.getBaseSize());
                final String dir = getDpiDirectoryName(iconType, dpi);

                list.add(new OutputPayload(
                        svgPath,
                        dim,
                        dim,
                        appendTargetFile(outputDirectoryAsFile, dir, targetFilename),
                        iconType.toString()));
            }
        }

        return list;
    }

    private static String getDpiDirectoryName(final IconType iconType, final DPI dpi) {
        final String prefix;
        if (iconType == IconType.LAUNCHER) {
            prefix = "mipmap";
        } else {
            prefix = "drawable";
        }

        return prefix + "-" + dpi.toString().toLowerCase(Locale.US) + File.separator;
    }


    private static String appendTargetFile(final File outputDirectory,
                                           final String targetFileName) {

        return new File(outputDirectory, targetFileName).getAbsolutePath();
    }

    private static String appendTargetFile(final File outputDirectory,
                                           final String subDir,
                                           final String targetFileName) {

        final File resFile = new File(outputDirectory, subDir);
        return new File(resFile, targetFileName).getAbsolutePath();
    }

    private static String createPngFilename(final String svgPath) {
        final File svg = new File(svgPath);
        return svg.getName() + ".png";
    }
}
