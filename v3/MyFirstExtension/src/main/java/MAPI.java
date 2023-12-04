import burp.api.montoya.MontoyaApi;

public final class MAPI {

    private static MontoyaApi INSTANCE;

    private MAPI() {}

    public static void initialize(MontoyaApi api) {
        if (INSTANCE == null) {
            INSTANCE = api;
        }
    }

    public static MontoyaApi getAPI() {
        return INSTANCE;
    }
}
