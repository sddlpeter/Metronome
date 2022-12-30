public abstract class AbstractActionHandleStrategy {
    protected BeatController beatController;
    protected int bpm;
    protected AbstractActionHandleStrategy(BeatController beatController) {
        this.beatController = beatController;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public abstract void handleAction();
}
