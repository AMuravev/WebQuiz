package engine.model;

public class MessageResponseModel {
    private final boolean success;
    private final String feedback;

    public MessageResponseModel(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
