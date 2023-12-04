import burp.api.montoya.extension.ExtensionUnloadingHandler;

public class UnloadingHandler implements ExtensionUnloadingHandler {

    private MyFirstHttpHandler handler;

    public UnloadingHandler(MyFirstHttpHandler handler) {
        this.handler = handler;
    }
    @Override
    public void extensionUnloaded() {
        MAPI.getAPI().persistence().preferences().setString("hash", this.handler.getHash());
    }
}