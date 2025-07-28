package erepair.program.subprocess;

import erepair.program.Main;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.OptionalLong;
import java.util.concurrent.*;

/**
 **/
public class SimpleSubprocess {
    private SimpleSubprocess() {
    }

    /**
     * Run a process with a timeout.
     * Spawns two {@link Future} tasks to get the stdout and stderr values of the process.
     *
     * @param timeoutPerFile Timeout per file or {@link OptionalLong#empty()}, if the default timeout from {@link Main} should be used.
     * @param commandline    Command line to run
     * @return the stdout of the process
     * @throws TimeoutException     if the process did not finish its execution before the timeout period expired
     * @throws IOException          if the process' executable could not be opened
     * @throws InterruptedException if the process was interrupted
     * @throws ExecutionException   if the stdout could not be read
     */
    public static ProcessReturnValue runProcess(@Nonnull OptionalLong timeoutPerFile, String... commandline) throws TimeoutException, IOException, InterruptedException, ExecutionException {
        Process process = Runtime.getRuntime().exec(commandline);
        return runProcess(timeoutPerFile, process);
    }

    /**
     * Run a process with a timeout.
     * Spawns two {@link Future} tasks to get the stdout and stderr values of the process.
     *
     * @param timeoutPerFile Timeout per file or {@link OptionalLong#empty()}, if the default timeout from {@link Main} should be used.
     * @param process        Started process to run
     * @return the stdout of the process
     * @throws TimeoutException     if the process did not finish its execution before the timeout period expired
     * @throws InterruptedException if the process was interrupted
     * @throws ExecutionException   if the stdout could not be read
     */
    public static ProcessReturnValue runProcess(@Nonnull OptionalLong timeoutPerFile, Process process) throws TimeoutException, InterruptedException, ExecutionException {
        final long startTime = System.currentTimeMillis();
        ExecutorService process_thread_pool = Executors.newFixedThreadPool(2);

        Future<String> stdout = process_thread_pool.submit(() -> IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8));
        Future<String> error = process_thread_pool.submit(() -> IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8));
        process_thread_pool.shutdown();
        if (!process.waitFor(timeoutPerFile.orElse(Main.timeoutPerFile), TimeUnit.MILLISECONDS)) {
            process.destroy();
            throw new TimeoutException("The subprocess timed out with a set timeout of " + timeoutPerFile.orElse(Main.timeoutPerFile) + "ms after " + (System.currentTimeMillis() - startTime) + "ms.");
        }
        return new ProcessReturnValue(stdout.get(), error.get(), process.exitValue());
    }
}
