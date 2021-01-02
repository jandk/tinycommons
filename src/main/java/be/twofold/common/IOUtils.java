package be.twofold.common;

import java.io.*;

public final class IOUtils {

    private static final int BufferSize = 8 * 1024;

    private IOUtils() {
        throw new UnsupportedOperationException();
    }

    public static BufferedInputStream buffered(InputStream input) {
        Check.notNull(input);
        return input instanceof BufferedInputStream
            ? (BufferedInputStream) input
            : new BufferedInputStream(input);
    }

    public static BufferedOutputStream buffered(OutputStream output) {
        Check.notNull(output);
        return output instanceof BufferedOutputStream
            ? (BufferedOutputStream) output
            : new BufferedOutputStream(output);
    }

    public static BufferedReader buffered(Reader reader) {
        return reader instanceof BufferedReader
            ? (BufferedReader) reader
            : new BufferedReader(reader);
    }

    public static BufferedWriter buffered(Writer writer) {
        return writer instanceof BufferedWriter
            ? (BufferedWriter) writer
            : new BufferedWriter(writer);
    }

    public static long copy(InputStream input, OutputStream output) throws IOException {
        Check.notNull(input);
        Check.notNull(output);

        byte[] buffer = new byte[BufferSize];
        long count = 0;
        while (true) {
            int read = input.read(buffer);
            if (read < 0) {
                return count;
            }
            output.write(buffer, 0, read);
            count += read;
        }
    }

    public static long copy(Reader reader, Writer writer) throws IOException {
        Check.notNull(reader);
        Check.notNull(writer);

        char[] buffer = new char[BufferSize];
        long count = 0;
        while (true) {
            int read = reader.read(buffer);
            if (read < 0) {
                return count;
            }
            writer.write(buffer, 0, read);
            count += read;
        }
    }

    public static byte[] readAllBytes(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(BufferSize);
        copy(input, output);
        return output.toByteArray();
    }

    public static String readAllText(Reader reader) throws IOException {
        StringWriter writer = new StringWriter(BufferSize);
        copy(reader, writer);
        return writer.toString();
    }

}
