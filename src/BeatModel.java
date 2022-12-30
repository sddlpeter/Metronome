import java.util.HashSet;
import java.util.Set;

public class BeatModel implements IBeatModel{
    private Set<IBPMObserver> bpmObservers; // 只包含所有的根节点视图
    private int bpm;

    public BeatModel() {
        this.bpmObservers = new HashSet<>();
        this.bpm = 60; // default
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
        this.notifyObserver();
    }
    public int getBpm() {
        return this.bpm;
    }

    @Override
    public void registerObserver(IBPMObserver root) {
        this.bpmObservers.add(root);

    }

    @Override
    public void removeObserver(IBPMObserver root) {
        this.bpmObservers.remove(root);
    }

    @Override
    public void notifyObserver() {
        for (IBPMObserver bpmObserver : this.bpmObservers) {
            if (bpmObserver instanceof IViewComponent) {
                IViewComponent root = (IViewComponent) bpmObserver;
                this.noticeTree(root);
            }
        }
    }

    private void noticeTree(IViewComponent root) {
        if (root instanceof IBPMObserver) {
            ((IBPMObserver) root).updateBPM(this.bpm);
        }
        for (IViewComponent child : root.getChildren()) {
            this.noticeTree(child);
        }
    }
}
