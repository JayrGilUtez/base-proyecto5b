package mx.edu.utez.baseproyecto5b.database;

import io.objectbox.BoxStore;
import mx.edu.utez.baseproyecto5b.model.MyObjectBox;

public class BoxStoreManager {
    private static BoxStore boxStore;
    public static BoxStore getBoxStore() {
        if (boxStore == null) {
            boxStore = MyObjectBox.builder().name("school-db").build();
        }
        return boxStore;
    }
}
