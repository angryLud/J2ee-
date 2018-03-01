package ticket.config;

public class Message {
	
	boolean result;
    Object object;
    String reason;
    
    public Message() {
    	
    }

    public Message(boolean result, String reason) {
        this.result = result;
        this.reason = reason;
    }

    public Message(boolean result, Object object, String reason) {
        this.result = result;
        this.object = object;
        this.reason = reason;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
