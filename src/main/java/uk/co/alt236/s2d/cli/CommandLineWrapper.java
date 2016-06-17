package uk.co.alt236.s2d.cli;

import org.apache.commons.cli.CommandLine;

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

    public String getConverter() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_CONVERTER);
    }

    public boolean isOverwriteEnabled() {
        return commandLine.hasOption(OptionsBuilder.ARG_OVERWRITE);
    }
}
