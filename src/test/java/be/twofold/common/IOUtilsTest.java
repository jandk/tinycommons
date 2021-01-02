package be.twofold.common;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

public class IOUtilsTest {

    @Test
    public void testConstructor() {
        TestUtils.testConstructor(IOUtils.class);
    }

    @Test
    public void testBufferedInputStream() {
        assertThatNullPointerException()
            .isThrownBy(() -> IOUtils.buffered((InputStream) null));

        InputStream input = new ByteArrayInputStream(new byte[0]);
        assertThat(IOUtils.buffered(input))
            .isInstanceOf(BufferedInputStream.class);

        BufferedInputStream bufferedInput = new BufferedInputStream(input);
        assertThat(IOUtils.buffered(bufferedInput))
            .isSameAs(bufferedInput);
    }

    @Test
    public void testBufferedOutputStream() {
        assertThatNullPointerException()
            .isThrownBy(() -> IOUtils.buffered((OutputStream) null));

        OutputStream output = new ByteArrayOutputStream();
        assertThat(IOUtils.buffered(output))
            .isInstanceOf(BufferedOutputStream.class);

        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
        assertThat(IOUtils.buffered(bufferedOutput))
            .isSameAs(bufferedOutput);
    }

    @Test
    public void testBufferedReader() {
        assertThatNullPointerException()
            .isThrownBy(() -> IOUtils.buffered((Reader) null));

        Reader reader = new StringReader("");
        assertThat(IOUtils.buffered(reader))
            .isInstanceOf(BufferedReader.class);

        BufferedReader bufferedReader = new BufferedReader(reader);
        assertThat(IOUtils.buffered(bufferedReader))
            .isSameAs(bufferedReader);
    }

    @Test
    public void testBufferedWriter() {
        assertThatNullPointerException()
            .isThrownBy(() -> IOUtils.buffered((Writer) null));

        Writer writer = new StringWriter();
        assertThat(IOUtils.buffered(writer))
            .isInstanceOf(BufferedWriter.class);

        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        assertThat(IOUtils.buffered(bufferedWriter))
            .isSameAs(bufferedWriter);
    }

}
