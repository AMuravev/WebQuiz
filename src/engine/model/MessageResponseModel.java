package engine.model;

import lombok.Getter;

@Getter
public class MessageResponseModel {
    private final boolean success;
    private final String feedback;

    public MessageResponseModel(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }
}
