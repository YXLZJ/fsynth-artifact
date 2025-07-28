package erepair.program.db.statistics;

import java.util.*;
import java.util.logging.Level;

/**
 **/
public abstract class StatisticsTable extends Statistics {
    /**
     * The general headers that come above the column headers
     */
    private final List<String> generalHeaders;
    /**
     * The column headers. Each column header consists of a (Key,DisplayText) tuple.
     */
    private final List<Map.Entry<String, String>> columnHeaders;

    /**
     * The data of the table. Each key represents one unique table entry, each value is its respective data, i.e. the mapping column header key -> Display Data
     */
    private final Map<String, Map<String, String>> data;
    /**
     * All keys of the data hashmap for iteration.
     */
    private final List<String> dataKeys;

    /**
     * Initialize a new Statistics Table
     *
     * @param key
     * @param name
     */
    public StatisticsTable(String key, String name) {
        super(key, name);
        this.generalHeaders = new ArrayList<>();
        this.columnHeaders = new ArrayList<>();
        this.data = new HashMap<>();
        this.dataKeys = new ArrayList<>();
    }

    /**
     * Add a general header which is a header that comes before the column headers.
     *
     * @param header Header to add
     */
    void addGeneralHeader(String header) {
        this.generalHeaders.add(header);
    }

    /**
     * Add a column header.
     * Keeps the order added.
     * Note that each column header MUST be unique!
     * Takes the header itself as header key.
     * Note that the first column header is taken as the header of the data that is mapped to values!
     *
     * @param header Header to add
     */
    void addColumnHeader(String header) {
        this.addColumnHeader(header, header);
    }

    /**
     * Add a column header with the given key.
     * If each column header's display text is unique, you can use the overloaded {@link StatisticsTable#addColumnHeader(String)} instead
     * Note that the first column header is taken as the header of the data that is mapped to values!
     *
     * @param key    Key
     * @param header Display Text of the header
     */
    void addColumnHeader(String key, String header) {
        this.columnHeaders.add(new AbstractMap.SimpleImmutableEntry<>(key, header));
    }

    /**
     * Add a cell to the data of the given dataKey.
     * The dataKey will be automatically added to the table, the order of all data keys will be the order of the first access in this method.
     * If the first column header it passed as headerKey, the display value of the cell of the data itself will be changed.
     *
     * @param dataKey
     * @param headerKey
     * @param data
     */
    void set(String dataKey, String headerKey, String data) {
        if (!this.data.containsKey(dataKey)) {
            this.data.put(dataKey, new HashMap<>());
            this.dataKeys.add(dataKey);
        }
        this.data.get(dataKey).put(headerKey, data);
    }

    /**
     * Print the next cell into the table
     *
     * @param cell Cell to print
     */
    abstract void printNextCell(String cell);

    /**
     * Print a newline character in the table and start in the first cell of the next line
     */
    abstract void nextLine();

    /**
     * Get the number of columns
     *
     * @return the number of columns
     */
    public int getNumberOfColumns() {
        return columnHeaders.size();
    }

    /**
     * Get the number of rows
     *
     * @return the number of rows
     */
    public int getNumberOfRows() {
        return this.generalHeaders.size() + 1 + this.dataKeys.size(); // + 1 for the caption row of each column
    }

    /**
     * Build the complete table data
     */
    abstract void buildTable();

    /**
     * Finalize the table after it is built.
     * This method is called whether the build succeeded or not.
     *
     * @param succeeded If true, the build succeeded.
     */
    abstract void finalizeTable(boolean succeeded);

    /**
     * Prepare the table before it is printed. This method is called before the first call of {@link StatisticsTable#nextLine()} and {@link StatisticsTable#printNextCell(String)}.
     *
     * @return the success status. If false, the table will be cancelled.
     */
    abstract boolean prepareTable();

    /**
     * Build the table
     *
     * @return true on success
     */
    @Override
    boolean buildStatistics() {
        try {
            if (!this.prepareTable()) {
                log(Level.WARNING, "The table could not be prepared!");
                return false;
            }
            this.buildTable();//Build the whole table data
            //Print everything into the table:
            for (String header : this.generalHeaders) {
                this.printNextCell(header);
                this.nextLine();
            }
            for (Map.Entry<String, String> pair : this.columnHeaders) {
                this.printNextCell(pair.getValue());
            }
            this.nextLine();
            for (int keyindex = 0; keyindex < this.dataKeys.size(); keyindex++) {
                final String dataKey = this.dataKeys.get(keyindex);
                int start;
                final Map<String, String> data = this.data.get(dataKey);
                //Check if the data itself has a display key
                if (data.containsKey(this.columnHeaders.get(0).getKey())) {
                    start = 0;
                } else {
                    this.printNextCell(dataKey);
                    start = 1;
                }
                for (int i = start; i < this.columnHeaders.size(); i++) {
                    final String key = this.columnHeaders.get(i).getKey();
                    this.printNextCell(data.getOrDefault(key, ""));
                }
                if (keyindex < (this.dataKeys.size() - 1)) {
                    this.nextLine();
                }
            }
            this.finalizeTable(true);
            return true;
        } catch (Exception e) {
            log(Level.SEVERE, "There was a " + e.getClass().getCanonicalName() + " trying to build the table!", e);
            this.finalizeTable(false);
            return false;
        }
    }
}
