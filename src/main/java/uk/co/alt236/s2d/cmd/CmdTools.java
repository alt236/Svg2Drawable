package uk.co.alt236.s2d.cmd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Consumer;

/*package*/ class CmdTools {
    /**
     * blocking read from process and copy to stdout
     */
    public static void read(final Process p, final int timeout) throws IOException, InterruptedException {
        read(p, timeout, System.out::print, System.err::print);
    }

    /**
     * blocking read from process
     *
     * @param timeout A timeout in milli seconds, or 0 for infinite
     */
    public static void read(final Process p, final int timeout, final Consumer<String> in, final Consumer<String> err) throws IOException, InterruptedException {
        try (InputStream inStream = p.getInputStream();
             InputStream errStream = p.getErrorStream();
             Reader inReader = new InputStreamReader(inStream);
             Reader errReader = new InputStreamReader(errStream)) {
            final long now = System.currentTimeMillis();
            final long end = now + timeout;
            int len;
            final char[] buffer = new char[4096];

            boolean canContinue = true;
            while (canContinue) {

                if (inStream.available() > 0) {
                    len = inReader.read(buffer, 0, Math.min(buffer.length, inStream.available()));
                    in.accept(new String(buffer, 0, len));
                }
                if (errStream.available() > 0) {
                    len = errReader.read(buffer, 0, Math.min(buffer.length, errStream.available()));
                    err.accept(new String(buffer, 0, len));
                }

                Thread.sleep(2);

                final boolean hasData = inStream.available() > 0 || errStream.available() > 0;
                if (timeout == 0) {
                    canContinue = hasData || p.isAlive();
                } else {
                    canContinue = System.currentTimeMillis() < end && (p.isAlive() || hasData);
                }
            }
        }
    }
}