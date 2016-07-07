package uk.co.alt236.s2d.converters;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import uk.co.alt236.s2d.enums.ConverterName;
import uk.co.alt236.s2d.outputpayload.OutputPayload;
import uk.co.alt236.s2d.resources.S2DStrings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/*package*/ class BatikConverter implements Converter {
    private final S2DStrings strings;

    public BatikConverter() {
        this.strings = new S2DStrings();
    }

    @Override
    public void convert(final List<OutputPayload> outputPayloadList) throws Exception {
        // Create the transcoder input.

        for (final OutputPayload payload : outputPayloadList) {
            System.out.println(strings.getString("status_exporting_file", payload.getTargetFile()));

            final String svgPath = payload.getSvgPath();
            final String svgURI = new File(svgPath).toURL().toString();
            final TranscoderInput input = new TranscoderInput(svgURI);

            // Create a JPEG transcoder
            final PNGTranscoder t = new PNGTranscoder();

            final float floatWidth = Float.valueOf(payload.getWidth());
            final float floatHeight = Float.valueOf(payload.getHeight());

            t.addTranscodingHint(PNGTranscoder.KEY_WIDTH, floatWidth);
            t.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, floatHeight);

            // Create the transcoder output.
            final OutputStream ostream = new FileOutputStream(payload.getTargetFile());
            final TranscoderOutput output = new TranscoderOutput(ostream);
            // Save the image.
            t.transcode(input, output);

            // Flush and close the stream.
            ostream.flush();
            ostream.close();
        }
    }

    @Override
    public ConverterName getName() {
        return ConverterName.BATIK;
    }
}
