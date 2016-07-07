package uk.co.alt236.s2d;

import uk.co.alt236.s2d.cli.CommandLineWrapper;
import uk.co.alt236.s2d.converters.Converter;
import uk.co.alt236.s2d.converters.ConverterResolver;
import uk.co.alt236.s2d.enums.IconType;
import uk.co.alt236.s2d.outputpayload.OutputPayload;
import uk.co.alt236.s2d.outputpayload.OutputPayloadFactory;

import java.util.List;

/*package*/ class Svg2Drawable {
    private final CommandLineWrapper commandLine;
    private final Converter converter;

    public Svg2Drawable(final CommandLineWrapper commandLine) {
        this.commandLine = commandLine;

        final ConverterResolver resolver = new ConverterResolver();
        converter = resolver.resolve(commandLine.getConverter());
    }


    public void convert() throws Exception {
        final List<OutputPayload> payloadList = OutputPayloadFactory.createJobList(
                IconType.fromString(commandLine.getIconType()),
                commandLine.getSvgFile(),
                commandLine.getOutputDirectory());


        new Validator(commandLine).validate(payloadList);

        converter.convert(payloadList);
    }
}
