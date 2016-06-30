package uk.co.alt236.s2d.cli;

import org.apache.commons.cli.CommandLine;
import uk.co.alt236.s2d.Constants;
import uk.co.alt236.s2d.enums.ConverterName;

public class CommandLineWrapper {

    private final CommandLine commandLine;

    public CommandLineWrapper(final CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public String getSvgFile() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_INPUT);
    }

    public String getOutputDirectory() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_OUTPUT);
    }

    public String getIconType() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_ICON_TYPE);
    }

    public ConverterName getConverter() {
        final String converter = commandLine.getOptionValue(OptionsBuilder.ARG_CONVERTER);

        return converter == null
                ? Constants.DEFAULT_CONVERTER
                : ConverterName.fromString(converter);
    }

    public boolean isOverwriteEnabled() {
        return commandLine.hasOption(OptionsBuilder.ARG_OVERWRITE);
    }
}
