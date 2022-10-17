package be.twofold.common;

import java.io.*;

public final class IOUtils {

    private IOUtils() {
        throw new UnsupportedOperationException();
    }

    public static BufferedInputStream buffered(InputStream input) {
        Check.notNull(input, "input");

        return input instanceof BufferedInputStream
            ? (BufferedInputStream) input
            : new BufferedInputStream(input);
    }

    public static BufferedOutputStream buffered(OutputStream output) {
        Check.notNull(output, "output");

        return output instanceof BufferedOutputStream
            ? (BufferedOutputStream) output
            : new BufferedOutputStream(output);
    }

    public static BufferedReader buffered(Reader reader) {
        Check.notNull(reader, "reader");

        return reader instanceof BufferedReader
            ? (BufferedReader) reader
            : new BufferedReader(reader);
    }

    public static BufferedWriter buffered(Writer writer) {
        Check.notNull(writer, "writer");

        return writer instanceof BufferedWriter
            ? (BufferedWriter) writer
            : new BufferedWriter(writer);
    }

    public static long copy(InputStream input, OutputStream output) throws IOException {
        Check.notNull(input, "input");
        Check.notNull(output, "output");

        long count = 0;
        byte[] buffer = new byte[8192];
        while (true) {
            int read = input.read(buffer);
            if (read == -1) {
                break;
            }
            output.write(buffer, 0, read);
            count += read;
        }
        return count;
    }

    public static long copy(Reader reader, Writer writer) throws IOException {
        Check.notNull(reader, "reader");
        Check.notNull(writer, "writer");

        long count = 0;
        char[] buffer = new char[8192];
        while (true) {
            int read = reader.read(buffer);
            if (read == -1) {
                break;
            }
            writer.write(buffer, 0, read);
            count += read;
        }
        return count;
    }

}
