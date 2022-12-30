public class IncreBpmStrategy extends AbstractActionHandleStrategy{
    protected IncreBpmStrategy(BeatController beatController) {
        super(beatController);
    }

    @Override
    public void handleAction() {
        super.beatController.increaseBPM();
    }
}
