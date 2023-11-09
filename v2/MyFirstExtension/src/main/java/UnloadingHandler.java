import burp.api.montoya.MontoyaApi;
import burp.api.montoya.extension.ExtensionUnloadingHandler;

public class UnloadingHandler implements ExtensionUnloadingHandler {

    private MontoyaApi api;
    private MyFirstHttpHandler handler;

    public UnloadingHandler(MontoyaApi apiArg, MyFirstHttpHandler handler) {
        this.api = apiArg;
        this.handler = handler;
    }
    @Override
    public void extensionUnloaded() {
        this.api.persistence().preferences().setString("hash", this.handler.getHash());
    }
}