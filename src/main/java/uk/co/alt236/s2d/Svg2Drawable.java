package uk.co.alt236.s2d;

import uk.co.alt236.s2d.cli.CommandLineWrapper;
import uk.co.alt236.s2d.converters.Converter;
import uk.co.alt236.s2d.converters.ConverterResolver;
import uk.co.alt236.s2d.enums.IconType;
import uk.co.alt236.s2d.exceptions.ValidationException;
import uk.co.alt236.s2d.outputpayload.OutputPayload;
import uk.co.alt236.s2d.outputpayload.OutputPayloadFactory;

import java.io.File;
import java.util.List;

public class Svg2Drawable {
//    private static final String SOURCE = "/home/alex/tmp/image/WifiKeyRecovery.svg";
//    private static final String DEST = "/home/alex/tmp/image/";

    private final CommandLineWrapper commandLine;
    private final Converter converter;

    public Svg2Drawable(final CommandLineWrapper commandLine) {
        this.commandLine = commandLine;

        final ConverterResolver resolver = new ConverterResolver();
        converter = resolver.resolve(commandLine.getConverter());
    }

    private void validate(final List<OutputPayload> payloadList) {
        for (final OutputPayload payload : payloadList) {
            final File outFile = new File(payload.getTargetFile());
            final File inFile = new File(payload.getSvgPath());

            if (!inFile.exists()) {
                throw new ValidationException("Input file does not exist! " + inFile);
            }

            if (outFile.exists() && !commandLine.isOverwriteEnabled()) {
                throw new ValidationException("Overwrite is not enabled and target file '" + outFile + "' exists!");
            }

            if (!outFile.exists()) {
                if (!outFile.getParentFile().mkdirs()) {
                    throw new ValidationException("Failed to create directory structure for " + outFile);
                }
            }
        }
    }

    public void convert() throws Exception {
        final List<OutputPayload> payloadList = OutputPayloadFactory.createJobList(
                IconType.fromString(commandLine.getIconType()),
                commandLine.getSvgFile(),
                commandLine.getOutputDirectory());

        validate(payloadList);

        converter.convert(payloadList);
    }
}
