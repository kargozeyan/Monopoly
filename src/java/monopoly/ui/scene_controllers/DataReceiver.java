package monopoly.ui.scene_controllers;

public interface DataReceiver {
    default void receiveData(Object data) {};
    default void receiveData(Object... data) {};
}
