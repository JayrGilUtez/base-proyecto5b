package mx.edu.utez.baseproyecto5b.database;

import io.objectbox.BoxStore;
import mx.edu.utez.baseproyecto5b.model.MyObjectBox;

public class BoxStoreManager {
    // Creamos esta clase para que solo exista una instancia
    // de BoxStore y no se cree una nueva cada vez que se necesite
    private static BoxStore boxStore;
    // Con este metodo obtenemos podemos llamar a la instancia de BoxStore en cualquier otra parte del codigo
    // Este metodo crea la instancia de BoxStore si no existe la crea y la retorna
    public static BoxStore getBoxStore() {
        if (boxStore == null) {
            boxStore = MyObjectBox.builder().name("school-db").build();
        }
        return boxStore;
    }
}
