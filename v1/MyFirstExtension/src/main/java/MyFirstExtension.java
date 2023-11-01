import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;

public class MyFirstExtension implements BurpExtension, HttpHandler {
    @Override
    public void initialize(MontoyaApi api) {

        api.extension().setName("My First Extension");
        api.logging().logToOutput("Tib3rius");

        api.http().registerHttpHandler(this);
    }

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
        HttpRequest request = httpRequestToBeSent.withAddedHeader("New-Header", "Tib3rius");
        return RequestToBeSentAction.continueWith(request);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return null;
    }
}
