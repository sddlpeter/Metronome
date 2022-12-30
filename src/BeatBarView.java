import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class BeatBarView extends JProgressBar implements Runnable, IBPMObserver, IViewComponent {
    private int bpm;
    public BeatBarView() {
        super.setMaximum(100);
        this.bpm = 60; // default
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            int value = (int) (50 * Math.cos(Math.PI * this.bpm * (now - start) / 60 / 1000) + 50);
            super.setValue(value);
            super.repaint();
            // 20帧/秒
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {

            }
        }
    }

    @Override
    public void updateBPM(int bpm) {
        this.bpm = bpm;
    }

    @Override
    public void addChildComponent(IViewComponent ivc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeChildComponent(IViewComponent ivc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<IViewComponent> getChildren() {
        return new HashSet<>();
    }
}
