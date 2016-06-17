package uk.co.alt236.s2d;

import org.apache.commons.cli.*;
import uk.co.alt236.s2d.cli.CommandLineWrapper;
import uk.co.alt236.s2d.cli.OptionsBuilder;

public class Main {
    private static final String CMD_NAME = "svg2drawable";

    public static void main(final String[] args) throws Exception {
        final CommandLineParser parser = new DefaultParser();
        final Options options = new OptionsBuilder().compileOptions();

        if (args.length == 0) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(CMD_NAME, options, true);
        } else {
            try {
                final CommandLine line = parser.parse(options, args);
                new Svg2Drawable(new CommandLineWrapper(line)).convert();
            } catch (final ParseException exp) {
                System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            }
        }
    }
}
