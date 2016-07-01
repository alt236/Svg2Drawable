package uk.co.alt236.s2d.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class CommandHelpPrinter {

    private final String binaryName;
    private final Options options;

    public CommandHelpPrinter(final Options options, final String binaryName) {
        this.binaryName = binaryName;
        this.options = options;
    }

    public void printHelp() {
        String header = "Convert a SVG file to a bunch on Android PNG drawables.\n\n";
        String footer =
                "\nSource code: https://github.com/alt236/Svg2Drawable/" +
                        "\nPlease report issues at https://github.com/alt236/Svg2Drawable/issues";

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(binaryName, header, options, footer, true);
    }
}
