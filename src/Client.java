public class Client {
    public static void main(String[] args) {
        BeatModel model = new BeatModel();
        BeatController controller = new BeatController(model);
        MetronomeView view = new MetronomeView(controller, model);
        view.createView();
        view.createControls();
    }
}
