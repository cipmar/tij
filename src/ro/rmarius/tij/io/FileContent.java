package ro.rmarius.tij.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * In memory file content. Provides an input stream and an output stream for reading and writing data.
 * 
 * @author Marius
 * 
 */
public class FileContent {
	private byte[] content = new byte[1024];

	private int count;

	/**
	 * @return A new input stream 
	 */
	public InputStream newInputStream() {
		return new InputStream() {
			private int pos;

			@Override
			public int read() throws IOException {
				int c = -1;

				synchronized (content) {
					c = (pos < count) ? (content[pos++] & 0xff) : -1;
				}

				return c;
			}
		};
	}

	public OutputStream newOutputStream() {
		return new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				synchronized (content) {
					ensureCapacity(count + 1);
					content[count] = (byte) b;
					count += 1;
				}
			}

			private void ensureCapacity(int minCapacity) {
				if (minCapacity - content.length > 0)
					grow(minCapacity);
			}

			private void grow(int minCapacity) {
				int oldCapacity = content.length;
				int newCapacity = oldCapacity << 1;

				if (newCapacity - minCapacity < 0)
					newCapacity = minCapacity;

				if (newCapacity < 0) {
					if (minCapacity < 0) // overflow
						throw new OutOfMemoryError();
					newCapacity = Integer.MAX_VALUE;
				}

				content = Arrays.copyOf(content, newCapacity);
			}
		};
	}

	public int getCount() {
		return count;
	}
}
