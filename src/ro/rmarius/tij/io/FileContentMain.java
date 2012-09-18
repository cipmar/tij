package ro.rmarius.tij.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class FileContentMain {
	static Integer countBytesRead = 0;

	public static void main(String[] args) throws IOException, InterruptedException {
		FileContent fileContent = new FileContent();

		final InputStream is = fileContent.newInputStream();
		final OutputStream os = fileContent.newOutputStream();

		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		};

		ExecutorService executor = Executors.newCachedThreadPool(threadFactory);

		// mai multe thread-uri care scriu
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							os.write(new Long(System.currentTimeMillis()).toString().getBytes());
							TimeUnit.MICROSECONDS.sleep(100);
						} catch (InterruptedException | IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}

		// thread-uri care citesc
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					byte[] buffer = new byte[1024];

					while (true) {
						try {
							int count = is.read(buffer);
							TimeUnit.MICROSECONDS.sleep(100);
							System.out.println(count > 0 ? count + " bytes read" : "no data to read");

							synchronized (countBytesRead) {
								countBytesRead += count;
							}
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}

		TimeUnit.SECONDS.sleep(20);
		executor.shutdown();
	}
}
