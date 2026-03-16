package concurrency8_lw3_example.code;

public class Request {
    private final int requestId;
    private final String clientName;

    public Request(int requestId, String clientName) {
        this.requestId = requestId;
        this.clientName = clientName;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
