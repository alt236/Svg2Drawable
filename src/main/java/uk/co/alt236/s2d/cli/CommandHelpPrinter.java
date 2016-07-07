package uk.co.alt236.s2d.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import uk.co.alt236.s2d.resources.S2DStrings;

import java.util.ResourceBundle;

public class CommandHelpPrinter {

    private final String binaryName;
    private final Options options;

    public CommandHelpPrinter(final Options options, final String binaryName) {
        this.binaryName = binaryName;
        this.options = options;
    }

    public void printHelp() {

        final ResourceBundle labels = new S2DStrings();

        final String header = labels.getString("cli_help_message_header");
        final String footer = labels.getString("cli_help_message_footer");

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(binaryName, header, options, footer, true);
    }
}
