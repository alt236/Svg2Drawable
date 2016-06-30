package uk.co.alt236.s2d.converters;

import uk.co.alt236.s2d.cmd.InkscapeWrapper;
import uk.co.alt236.s2d.enums.ConverterName;
import uk.co.alt236.s2d.outputpayload.OutputPayload;

import java.util.List;

/*package*/ class InkscapeConverter implements Converter {

    @Override
    public void convert(final List<OutputPayload> outputPayloadList) {
        final InkscapeWrapper wrapper = new InkscapeWrapper();

        for (final OutputPayload payload : outputPayloadList) {
            wrapper.execute(
                    payload.getSvgPath(),
                    payload.getTargetFile(),
                    payload.getWidth(),
                    payload.getHeight());
        }
    }

    @Override
    public ConverterName getName() {
        return ConverterName.INKSCAPE;
    }
}
