import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MyFirstHttpHandler implements HttpHandler {

    private String hash = "";

    public MyFirstHttpHandler(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return this.hash;
    }
    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {

        if (!this.hash.isEmpty() && httpRequestToBeSent.isInScope()) {
            HttpRequest request = httpRequestToBeSent.withAddedHeader("X-Hash", this.hash);
            return RequestToBeSentAction.continueWith(request);
        }

        return null;
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {

        if (httpResponseReceived.initiatingRequest().isInScope()) {
            String input = "";

            if (httpResponseReceived.hasHeader("Age")) {
                input += httpResponseReceived.headerValue("Age");
            }

            if (httpResponseReceived.hasHeader("Date")) {
                input += httpResponseReceived.headerValue("Date");
            }

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.update(input.getBytes(StandardCharsets.UTF_8));
                this.hash = HexFormat.of().formatHex(digest.digest());
                MAPI.getAPI().logging().logToOutput("Hash generated: " + this.hash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}