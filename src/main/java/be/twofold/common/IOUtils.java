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

    public static String readAllText(Reader reader) throws IOException {
        Check.notNull(reader, "reader");

        StringWriter writer = new StringWriter();
        reader.transferTo(writer);
        return writer.toString();
    }

}
