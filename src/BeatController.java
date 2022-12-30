public class BeatController {
    private final BeatModel model;
    private AbstractActionHandleStrategy strategy;

    public BeatController(BeatModel model) {
        this.model = model;
    }

    public void setBPM(int bpm) {
        this.model.setBpm(bpm);
    }

    public void increaseBPM() {
        this.model.setBpm(this.model.getBpm() + 1);
    }

    public void decreaseBPM() { this.model.setBpm(this.model.getBpm() - 1); }

    public void setActionHandleStrategy(AbstractActionHandleStrategy strategy) {
        this.strategy = strategy;
    }

    public void handleAction() {
        this.strategy.handleAction();
    }
}
