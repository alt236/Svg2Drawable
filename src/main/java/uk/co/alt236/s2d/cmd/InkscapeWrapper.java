package uk.co.alt236.s2d.cmd;

import java.io.IOException;
import java.util.Locale;

public class InkscapeWrapper {


    public void execute(final String svgImage,
                        final String outPath,
                        final int width,
                        final int height) {
        final String cmdTemplate = "inkscape -z -f=%s --export-png=%s -w=%d -h=%d";
        final String fullCommand = String.format(Locale.US, cmdTemplate, svgImage, outPath, width, height);

        execute(fullCommand);
    }

    private void execute(final String command) {
        final Runtime run = Runtime.getRuntime();

        final Process pr;
        try {
            pr = run.exec(command);
            CmdTools.read(pr, 1000);
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }

    }
}
