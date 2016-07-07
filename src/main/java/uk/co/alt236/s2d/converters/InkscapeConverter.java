package uk.co.alt236.s2d.converters;

import uk.co.alt236.s2d.cmd.InkscapeWrapper;
import uk.co.alt236.s2d.enums.ConverterName;
import uk.co.alt236.s2d.outputpayload.OutputPayload;
import uk.co.alt236.s2d.resources.S2DStrings;

import java.util.List;

/*package*/ class InkscapeConverter implements Converter {
    private final S2DStrings strings;

    public InkscapeConverter() {
        this.strings = new S2DStrings();
    }

    @Override
    public void convert(final List<OutputPayload> outputPayloadList) {
        final InkscapeWrapper wrapper = new InkscapeWrapper();

        for (final OutputPayload payload : outputPayloadList) {
            System.out.println(strings.getString("status_exporting_file", payload.getTargetFile()));

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
