package fsynth.program.differencing;

import fsynth.program.Logging;
import fsynth.program.subject.Subjects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

public abstract class ImageHash extends DifferencingAlgorithm<Long> {
    private final Subjects myOracle;
    private final HashMap<Subjects, Path> tempfolder = new HashMap<>();

    public ImageHash(Subjects oracle) {
        super("ImageHash-" + oracle.toString());
        myOracle = oracle;
    }

    @Override
    public abstract DifferencingAlgorithms getKind();

    /**
     * Runs an oracle that automatically appends (0-8).png to the output file
     *
     * @param inFile  Object file to read
     * @param outFile Image prefix without .png
     * @return true, if oracle succeeds (which is expected)
     */
    abstract boolean runOracle(String inFile, String outFile);

    @Nullable
    @Override
    final Long runAlgorithm(@Nonnull Path file1, @Nonnull Path file2) {
        if (tempfolder.get(myOracle) == null) {
            try {
                tempfolder.put(myOracle, Files.createTempDirectory("imagehash-" + myOracle.toString()).normalize());
            } catch (IOException e) {
                log(Level.SEVERE, "Could not create Temp Folder", e);
            }
        }
        final Path myTempfolder = tempfolder.get(myOracle);
        try {
            final String fileprefix = file1.getFileName().toString().split("\\.")[0] + "-angle";
            final Path image1 = Paths.get(myTempfolder.toString(), "reference" + fileprefix + ".png");
            final Path image2 = Paths.get(myTempfolder.toString(), "compare" + fileprefix + ".png");
            String image1_nosuff = image1.toString();
            image1_nosuff = image1_nosuff.substring(0, image1_nosuff.length() - 4);//Strip away .png
            String image2_nosuff = image2.toString();
            image2_nosuff = image2_nosuff.substring(0, image2_nosuff.length() - 4);
            boolean appleseedSucceeded = runOracle(file1.normalize().toString(), image1_nosuff);
            appleseedSucceeded = appleseedSucceeded && runOracle(file2.normalize().toString(), image2_nosuff);
            if (!appleseedSucceeded) {
                log(Level.WARNING, this.myOracle.toString() + " failed for ImageHash! Aborting.");
                return null;
            }
            try {//COMPARE ALL CAMERA ANGLES
                long res = 0L;
                for (int i = 0; i < 9; i++) {
                    final Path diff1 = Paths.get(image1_nosuff + i + ".png");
                    final Path diff2 = Paths.get(image2_nosuff + i + ".png");
                    log(Level.FINER, "Comparing Camera Angle " + i + ": " + diff1.normalize().toString() + " --> " + diff2.normalize().toString());
                    final String result = runImageDiff(diff1, diff2).trim();
                    res += Long.parseLong(result);
                    Files.delete(diff1);
                    Files.delete(diff2);
                }
                return res;
            } catch (NumberFormatException e) {
                log(Level.WARNING, "The Image Diff Script did not return a valid integer", e);
                return null;
            } catch (InterruptedException e) {
                log(Level.WARNING, "The Python Process was interrupted", e);
                return null;
            } catch (TimeoutException e) {
                log(Level.WARNING, "Python timed out", e);
                return null;
            }
        } catch (IOException e) {
            Logging.error("ImageHash threw an exception", e);
            return null;
        }
    }

    private String runImageDiff(Path image1, Path image2) throws InterruptedException, IOException, TimeoutException {
        ProcessBuilder process = new ProcessBuilder(
                "python3", "compute-image-hash.py", image1.normalize().toString(), image2.normalize().toString()
        );
        process.redirectErrorStream(true);
        boolean timedout;
        Process result = null;
        result = process.start();
        timedout = !result.waitFor(10000, TimeUnit.MILLISECONDS);
        if (timedout) {
            throw new TimeoutException("The Python Script timed out!");
        }
        InputStream is = result.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder ret = new StringBuilder(15);
        while ((line = br.readLine()) != null) {
            log(Level.FINEST, line);
            ret.append(line);
        }
        return ret.toString();

    }
}
