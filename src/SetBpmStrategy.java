public class SetBpmStrategy extends AbstractActionHandleStrategy{
    protected SetBpmStrategy(BeatController beatController) {
        super(beatController);
    }

    @Override
    public void handleAction() {
        super.beatController.setBPM(super.bpm);
    }
}
