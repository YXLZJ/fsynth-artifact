package erepair.program.db.statistics;

import erepair.program.Main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

/**
 **/
public abstract class CSVReport extends StatisticsTable {
    private final Path outFile;
    private OutputStream outputStream;
    private boolean isFirstCell = true;

    /**
     * Initialize a new Statistics Table
     *
     * @param key  Unique key, used for the file name of the CSV file
     * @param name Human-readable name of this sheet
     */
    public CSVReport(String key, String name) {
        super(key, name);
        this.outFile = Main.REPORTS_FOLDER.resolve(key + ".csv");
    }

    @Override
    void printNextCell(String cell) {
        try {
            if (isFirstCell) {
                outputStream.write(cell.getBytes(StandardCharsets.UTF_8));
                isFirstCell = false;
            } else {
                outputStream.write(("," + cell).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            log(Level.SEVERE, "Could not write cell " + cell, e);
        }
    }

    @Override
    void nextLine() {
        try {
            outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log(Level.SEVERE, "Could not write Newline character", e);
        }
        isFirstCell = true;
    }

    @Override
    void finalizeTable(boolean succeeded) {
        try {
            outputStream.close();
        } catch (IOException e) {
            log(Level.SEVERE, "Could not close output stream", e);
        }
    }

    @Override
    boolean prepareTable() {
        if (!Files.isDirectory(this.outFile.getParent())) {
            this.outFile.getParent().toFile().mkdirs();
        }
        try {
            this.outputStream = new FileOutputStream(this.outFile.toFile());
        } catch (FileNotFoundException e) {
            log(Level.SEVERE, "Could not open the CSV file at " + this.outFile.toString(), e);
            return false;
        }
        return true;
    }

    @Override
    public String getLocation() {
        return this.outFile.toString();
    }
}
