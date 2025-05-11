package erepair.program.db.statistics;

import com.google.api.client.json.gson.GsonFactory;
import erepair.program.Main;
import erepair.program.Parsing;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Pattern;

import static erepair.program.Main.TOKENS_DIRECTORY_PATH;

/**
 **/
public abstract class GoogleSheet extends StatisticsTable {
    //Regex to check if a String can be parsed to a Double, as suggested from the Javadoc of Oracle Java SE 14
    @SuppressWarnings("HardcodedFileSeparator")
    static final String Digits = "(\\p{Digit}+)";
    @SuppressWarnings("HardcodedFileSeparator")
    static final String HexDigits = "(\\p{XDigit}+)";
    // an exponent is 'e' or 'E' followed by an optionally
    // signed decimal integer.
    static final String Exp = "[eE][+-]?" + Digits;
    @SuppressWarnings("HardcodedFileSeparator")
    static final String fpRegex =
            ("[\\x00-\\x20]*" +  // Optional leading "whitespace"
                    "[+-]?(" + // Optional sign character
                    "NaN|" +           // "NaN" string
                    "Infinity|" +      // "Infinity" string

                    // A decimal floating-point string representing a finite positive
                    // number without a leading sign has at most five basic pieces:
                    // Digits . Digits ExponentPart FloatTypeSuffix
                    //
                    // Since this method allows integer-only strings as input
                    // in addition to strings of floating-point literals, the
                    // two sub-patterns below are simplifications of the grammar
                    // productions from section 3.10.2 of
                    // The Java Language Specification.

                    // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                    "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +

                    // . Digits ExponentPart_opt FloatTypeSuffix_opt
                    "(\\.(" + Digits + ")(" + Exp + ")?)|" +

                    // Hexadecimal strings
                    "((" +
                    // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                    "(0[xX]" + HexDigits + "(\\.)?)|" +

                    // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                    "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

                    ")[pP][+-]?" + Digits + "))" +
                    "[fFdD]?))" +
                    "[\\x00-\\x20]*");// Optional trailing "whitespace"
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    @SuppressWarnings("HardcodedFileSeparator")
    private static final String CREDENTIALS_FILE_PATH = "/google-sheets-credentials.json";

    private static final Map<String, String> googleSheetIDs = new HashMap<>();
    private static final Map<String, String> googleSheetSheets = new HashMap<>();
    List<RowData> newData = new ArrayList<>();
    private String key;
    private Sheets service = null;
    private String spreadsheetID = null;
    private String spreadsheetName = null;
    private ValueRange valueRange;
    private int currentRow, currentCol;
    private int numericalSheetName;
    private int oldSheetWidth;
    private int oldSheetHeight;
    private List<Request> preFillRequests = new ArrayList<Request>();
    private List<ProtectedRange> oldProtectedRanges = null;

    public GoogleSheet(String key, String name) {
        super(key, name);
        this.key = key;
    }

    /**
     * Load the JSON file for the google sheets
     */
    public static void initialize() {
        final String IDKey = "sheetID";
        final String sampleID = "TODO Insert a valid Google Sheets ID here";
        final String sheetKey = "sheetName";
        final String sampleKey = "TODO Insert a valid Google Sheets Sheet Identifier here";
        try {
            if (Main.GOOGLE_SHEETS_IDS_PATH.toFile().isFile()) {
                final String jsonContents = Parsing.readStringFromFile(Main.GOOGLE_SHEETS_IDS_PATH);
                final JSONObject jsonObject = new JSONObject(jsonContents);
                for (String key : jsonObject.keySet()) {
                    final JSONObject vals = jsonObject.getJSONObject(key);
                    if (vals.has(IDKey)) {
                        googleSheetIDs.put(key, vals.getString(IDKey));
                    } else {
                        googleSheetIDs.put(key, sampleID);
                    }
                    if (vals.has(sheetKey)) {
                        googleSheetSheets.put(key, vals.getString(sheetKey));
                    } else {
                        googleSheetSheets.put(key, sampleKey);
                    }
                }
            }
            boolean changed = false;
            for (Statistics availableStatistics : STATISTICS) {
                if (availableStatistics.isGoogleSheet()) {
                    final String key = availableStatistics.getKey();
                    if (!googleSheetIDs.containsKey(key)) {
                        googleSheetIDs.put(key, sampleID);
                        changed = true;
                    }
                    if (!googleSheetSheets.containsKey(key)) {
                        googleSheetSheets.put(key, sampleKey);
                        changed = true;
                    }
                }
            }
            if (changed) {
                JSONObject mainObject = new JSONObject();
                for (String key : googleSheetIDs.keySet()) {
                    JSONObject theMap = new JSONObject();
                    theMap.put(IDKey, googleSheetIDs.get(key));
                    theMap.put(sheetKey, googleSheetSheets.get(key));
                    mainObject.put(key, theMap);
                }
                Parsing.writeStringToFile(Main.GOOGLE_SHEETS_IDS_PATH, mainObject.toString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleSheet.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(TOKENS_DIRECTORY_PATH.toFile()))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @Override
    final boolean isGoogleSheet() {
        return true;
    }

    /**
     * Print a String in the next cell of the Google Sheet
     *
     * @param cell Cell to print
     */
    @Override
    void printNextCell(@Nonnull String cell) {
        if (cell == null) {
            throw new NullPointerException("The cell data must not be null!");
        }
        assert (currentCol < this.getNumberOfColumns());
        if (Pattern.matches(fpRegex, cell)) {
            newData.get(currentRow).getValues().add(new CellData().setUserEnteredValue(new ExtendedValue().setNumberValue(Double.valueOf(cell))));
        } else {
            newData.get(currentRow).getValues().add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(cell)));
        }
        currentCol++;
    }

    @Override
    void nextLine() {
        newData.add(new RowData()
                .setValues(new ArrayList<CellData>())
        );
        currentRow++;
        assert (currentRow < newData.size());
    }

    @Override
    void finalizeTable(boolean succeeded) {
        try {
            final String range = this.getSheetDimensions();
            this.clearSheet();
            preFillRequests.add(new Request()
                    .setUpdateCells(new UpdateCellsRequest()
                            .setRange(new GridRange()
                                    .setSheetId(this.numericalSheetName)
                                    .setStartColumnIndex(0)
                                    .setEndColumnIndex(this.getNumberOfColumns()) // GridRange End Indexes are exclusive!!!
                                    .setStartRowIndex(0)
                                    .setEndRowIndex(Math.max(this.getNumberOfRows(), 1))) // GridRange End Indexes are exclusive!!!
                            .setRows(this.newData)
                            .setFields("*"))
            );
            preFillRequests.add(new Request()
                    .setAddProtectedRange(new AddProtectedRangeRequest()
                            .setProtectedRange(new ProtectedRange()
                                    .setRange(new GridRange().setSheetId(this.numericalSheetName))
                                    .setDescription("This sheet was auto-generated by " + Main.APPLICATION_NAME + " and should NOT be changed by hand! All changes WILL BE LOST after the next batch update!")
                                    .setWarningOnly(true)
                            )
                    )
            );
            BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(preFillRequests);
            var request = this.service.spreadsheets().batchUpdate(this.spreadsheetID, requestBody);
            log(Level.INFO, "Modifying Sheet Data...");
            var response = request.execute();
            log(Level.FINE, "Sheet modified. Response: " + response.toString());
//            this.valueRange = new ValueRange()
//                    .setRange(range)
//                    .setMajorDimension("ROWS")
//                    .setValues(this.newData);
//            UpdateValuesResponse result =
//                    this.service.spreadsheets()
//                            .values()
//                            .update(this.spreadsheetID, this.valueRange.getRange(), this.valueRange)
//                            .setValueInputOption("USER_ENTERED") // USER_ENTERED to interpret formulas
//                            .execute();
//            log(Level.INFO, result.getUpdatedCells() + " cells updated.");
        } catch (Exception e) {
            log(Level.SEVERE, "Could not update Google Sheet", e);
        }
    }

    private String getLetterRange(int index) {
        StringBuilder lastLetter = new StringBuilder(3);
        while (index > 0) {
            lastLetter.insert(0, (char) ((index % 26) + (int) 'A'));
            index /= 26;
        }
        return lastLetter.toString();
    }

    /**
     * Get the whole sheet dimensions and add a resize request to the request list, if necessary.
     *
     * @return a ValueRange to be modified
     * @throws IOException if the ValueRange could not be opened
     */
    private String getSheetDimensions() {
        final int number = this.getNumberOfRows();
        final String lastLetter = this.getLetterRange(this.getNumberOfColumns());
        final String range = this.spreadsheetName + "!A1:" + lastLetter + (number);
        if (this.oldSheetWidth < this.getNumberOfColumns()) {
            log(Level.INFO, "Resizing Sheet columns...");
            preFillRequests.add(new Request()
                    .setAppendDimension(new AppendDimensionRequest()
                            .setDimension("COLUMNS")
                            .setLength(this.getNumberOfColumns() - this.oldSheetWidth)
                            .setSheetId(this.numericalSheetName))
            );
        }
        if (this.oldSheetHeight < this.getNumberOfRows()) {
            log(Level.INFO, "Resizing Sheet rows from " + this.oldSheetHeight + " to " + this.getNumberOfRows() + "...");
            preFillRequests.add(new Request()
                    .setAppendDimension(new AppendDimensionRequest()
                            .setDimension("ROWS")
                            .setLength(this.getNumberOfRows() - this.oldSheetHeight)
                            .setSheetId(this.numericalSheetName))
            );
        }
        return range;
    }

    /**
     * Add a "Clear Sheet" request to the BatchUpdate.
     * Clears the whole sheet and deletes all protected ranges, if any.
     *
     * @throws IOException
     */
    private void clearSheet() throws IOException {
        preFillRequests.add(new Request()
                .setUpdateCells(new UpdateCellsRequest()
                        .setFields("*")
                        .setRange(new GridRange()
                                .setSheetId(this.numericalSheetName))
                )
        );
        if (this.oldProtectedRanges != null) {
            for (ProtectedRange pr : this.oldProtectedRanges) {
                //noinspection ObjectAllocationInLoop
                preFillRequests.add(new Request()
                        .setDeleteProtectedRange(new DeleteProtectedRangeRequest()
                                .setProtectedRangeId(pr.getProtectedRangeId())
                        )
                );
            }
        }
    }

    @Override
    boolean prepareTable() {
        try {
            // Build a new authorized API client service.
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            this.spreadsheetID = googleSheetIDs.get(this.getKey());
            if (this.spreadsheetID.startsWith("TODO ")) {
                log(Level.WARNING, "Please insert a valid spreadsheet ID in the appropriate file!");
                return false;
            }
            if (this.spreadsheetID == null) {
                throw new Exception("The Spreadsheet ID for " + this.getKey() + " was not found");
            }
            this.spreadsheetName = googleSheetSheets.get(this.getKey());
            final Credential cred = getCredentials(HTTP_TRANSPORT);
            this.service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred)
                    .setApplicationName(Main.APPLICATION_NAME)
                    .build();
            currentRow = 0;
            currentCol = 0;
            newData.add(new RowData().setValues(new ArrayList<CellData>()));
            //Get the properties of the current sheet
            Spreadsheet mySheet;
            try {
                mySheet = this.service.spreadsheets().get(this.spreadsheetID).setIncludeGridData(false).execute();
            } catch (GoogleJsonResponseException e) {
                if (e.getDetails().getCode() == 404) {
                    log(Level.SEVERE, "The spreadsheet \"" + this.spreadsheetID + "\" was not found! Please create the spreadsheet manually or change the reference in " + Main.GOOGLE_SHEETS_IDS_PATH + " and re-run the statistics generation!\nMessage: " + e.getDetails().getMessage());
                } else if (e.getDetails().getCode() == 429) {
                    log(Level.WARNING, "The server reported 429 - Too many requests! (Message " + e.getDetails().getMessage() + ") - Waiting 100 seconds before retrying...");
                    Thread.sleep(100000L);
                    return prepareTable();//TODO cancel after a certain number of tries?
                } else {
                    log(Level.SEVERE, "The Google Sheets Service returned status " + e.getDetails().getCode() + " with message " + e.getDetails().getMessage());
                }
                return false;
            } catch (TokenResponseException e) {
                if (e.getDetails().getError().equals("invalid_grant")) {
                    log(Level.SEVERE, "The oauth2 token has been revoked! Please remove all tokens in " + TOKENS_DIRECTORY_PATH + " and try again! Error description: " + e.getDetails().getErrorDescription());
                } else {
                    throw e;
                }
                return false;
            }
            var maybeSheet = mySheet.getSheets().stream()
                    .filter(sheet -> this.spreadsheetName.equals(sheet.getProperties().getTitle()))
                    .findFirst();
            if (maybeSheet.isEmpty()) {
                log(Level.WARNING, "Could not find sheet with name \"" + this.spreadsheetName + "\" in spreadsheet \"" + this.spreadsheetID + "\". Please create the sheet manually or rename the reference in " + Main.GOOGLE_SHEETS_IDS_PATH + ".");
                return false;
            }
            Sheet currentSheet = maybeSheet.get();

            this.numericalSheetName = currentSheet
                    .getProperties()
                    .getSheetId();
            this.oldSheetWidth = currentSheet
                    .getProperties()
                    .getGridProperties()
                    .getColumnCount();
            this.oldSheetHeight = currentSheet
                    .getProperties()
                    .getGridProperties()
                    .getRowCount();
            this.oldProtectedRanges = currentSheet.getProtectedRanges();
            return true;
        } catch (Exception e) {
            log(Level.SEVERE, "There was an exception during the authentication process with the Google Sheets Service!", e);
            return false;
        }
    }

    @Override
    public String getLocation() {
        //noinspection HardcodedFileSeparator
        return "https://docs.google.com/spreadsheets/d/" + this.spreadsheetID + "/edit#gid=" + this.numericalSheetName;
    }
}
