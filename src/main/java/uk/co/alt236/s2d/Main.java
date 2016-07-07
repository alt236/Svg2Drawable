package uk.co.alt236.s2d;

import org.apache.commons.cli.*;
import uk.co.alt236.s2d.cli.CommandHelpPrinter;
import uk.co.alt236.s2d.cli.CommandLineWrapper;
import uk.co.alt236.s2d.cli.OptionsBuilder;
import uk.co.alt236.s2d.exceptions.S2DException;
import uk.co.alt236.s2d.resources.S2DStrings;

import java.io.File;

public class Main {

    private static String getJarName() {
        final File f = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toString());
        return f.getName();
    }

    public static void main(final String[] args) throws Exception {
        final S2DStrings strings = new S2DStrings();
        final CommandLineParser parser = new DefaultParser();
        final Options options = new OptionsBuilder().compileOptions();

        if (args.length == 0) {
            new CommandHelpPrinter(options, getJarName()).printHelp();
        } else {
            CommandLine line = null;

            try {
                line = parser.parse(options, args);
            } catch (final ParseException exp) {
                final String message = strings.getString("error_cli_parsing_failed", exp.getMessage());
                printErrorAndDie(message);
            }

            try {
                new Svg2Drawable(new CommandLineWrapper(line)).convert();
            } catch (S2DException e) {
                printErrorAndDie(e.getMessage());
            }
        }
    }

    private static void printErrorAndDie(final String error) {
        System.err.println(error);
        System.exit(1);
    }
}
