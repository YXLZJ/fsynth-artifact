package fsynth.program.userinterface;

import fsynth.program.Loggable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.Level;

public class UIController extends Loggable implements AutosaveController {
    private HashMap<Path, Saveable> filesToAutosave = new HashMap<>();

    public UIController() {
        super("UIController");
    }

    @Override
    public void doAutosave() {
        for (var file : this.filesToAutosave.entrySet()) {
            log(Level.INFO, "Auto-Saving " + file.getKey());
            try {
                file.getValue().autosave(file.getKey());
            } catch (IOException e) {
                log(Level.WARNING, "Could not save file " + file.getKey(), e);
            } /*catch (InvalidClassException e) {
                log(Level.SEVERE, "Could not serialize object " + file.getKey(), e);
            }*/ catch (Throwable e) {
                log(Level.WARNING, "There was an error during the auto-save of " + file.getKey(), e);
                throw e;
            }
        }
    }

    @Override
    public void addSaveable(Saveable file, Path path) {
        filesToAutosave.put(path, file);
    }
}
