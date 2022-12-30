public class DecreBpmStrategy extends AbstractActionHandleStrategy{
    protected DecreBpmStrategy(BeatController beatController) {
        super(beatController);
    }

    @Override
    public void handleAction() {
        super.beatController.decreaseBPM();
    }
}
