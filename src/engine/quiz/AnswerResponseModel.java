package engine.quiz;

public class AnswerResponseModel {
    private final boolean success;
    private final String feedback;

    public AnswerResponseModel(boolean success) {
        this.success = success;
        this.feedback = success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
