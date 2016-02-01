package co.favorie.wearable.et.action;

/**
 * Created by quki on 2016-02-01.
 */
public interface ConnectAction {
    void onSuccessConnection();
    void onFailConnection();
    void onSuccessTransfer(String data);
}
