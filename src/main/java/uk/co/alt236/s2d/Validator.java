package uk.co.alt236.s2d;

import uk.co.alt236.s2d.cli.CommandLineWrapper;
import uk.co.alt236.s2d.exceptions.ValidationException;
import uk.co.alt236.s2d.outputpayload.OutputPayload;
import uk.co.alt236.s2d.resources.S2DStrings;

import java.io.File;
import java.util.List;

/*package*/ class Validator {
    private final CommandLineWrapper commandLine;
    private final S2DStrings strings;

    public Validator(final CommandLineWrapper commandLineWrapper) {
        this.commandLine = commandLineWrapper;
        this.strings = new S2DStrings();
    }

    public void validate(final List<OutputPayload> payloadList) {

        for (final OutputPayload payload : payloadList) {
            final File outFile = new File(payload.getTargetFile());
            final File inFile = new File(payload.getSvgPath());

            if (!inFile.exists()) {
                throw new ValidationException(strings.getString("error_input_file_does_not_exist", inFile));
            }

            if (!inFile.canRead()) {
                throw new ValidationException(strings.getString("error_input_file_not_readable", inFile));
            }

            if (outFile.exists() && !commandLine.isOverwriteEnabled()) {
                throw new ValidationException(strings.getString("error_output_file_already_exists", outFile));
            }

            if (!outFile.exists()) {
                if (!outFile.getParentFile().mkdirs()) {
                    throw new ValidationException(strings.getString("error_failed_to_create_directory_structure", outFile));
                }
            }
        }
    }
}
