package application;

import java.util.ArrayList;
import java.util.HashMap;

public class Directory {
    private final HashMap<Integer, Account> directory;

    public Directory() {
        this.directory = new HashMap<>();
    }

    public HashMap<Integer, Account> getDirectory() {
        return this.directory;
    }

}
