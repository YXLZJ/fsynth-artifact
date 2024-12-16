package fsynth.program.userinterface;

import java.nio.file.Path;

public interface AutosaveController {
    public void doAutosave();

    public void addSaveable(Saveable file, Path pathToSave);
}
