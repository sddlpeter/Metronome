import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class MetronomeView implements ActionListener, IBPMObserver, IViewComponent {
        private JLabel bpmOutputLabel;
        private JTextField bpmTextField;
        private JButton setBPMButton;
        private JButton increaseBPMButton;
        private JButton decreaseBPMButton;

        private final BeatModel model;
        private final BeatController controller;
        private final Set<IViewComponent> childViews;
        private final AbstractActionHandleStrategy setBpmStrategy;
        private final AbstractActionHandleStrategy increBpmStrategy;
        private final AbstractActionHandleStrategy decreBpmStrategy;

        public MetronomeView(BeatController controller, BeatModel model) {
            this.controller = controller;
            this.model = model;
            this.model.registerObserver(this);
            this.childViews = new HashSet<>();
            this.setBpmStrategy = new SetBpmStrategy(this.controller);
            this.increBpmStrategy = new IncreBpmStrategy(this.controller);
            this.decreBpmStrategy = new DecreBpmStrategy(this.controller);
        }

        public void createView() {
            JPanel viewPanel = new JPanel(new GridLayout(1, 2));
            JFrame viewFrame = new JFrame("View");
            viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // viewFrame.setSize(new Dimension(300, 80));
            viewFrame.setBounds(100, 100, 400, 80);
            this.bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);

            BeatBarView beatBarView = new BeatBarView();
            beatBarView.setValue(0);
            this.addChildComponent(beatBarView);

            JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
            bpmPanel.add(beatBarView);
            bpmPanel.add(this.bpmOutputLabel);

            viewPanel.add(bpmPanel);
            viewPanel.add(new JLabel("test"));
            viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
            // viewFrame.pack();
            viewFrame.setVisible(true);
        }

        public void createControls() {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame controlFrame = new JFrame("Control");
            controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // controlFrame.setSize(new Dimension(300, 80));
            controlFrame.setBounds(100, 200, 300, 80);
            JPanel controlPanel = new JPanel(new GridLayout(1, 2));
            this.bpmTextField = new JTextField(2);
            JLabel bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
            this.setBPMButton = new JButton("Set");
            this.setBPMButton.setSize(new Dimension(10, 40));
            this.increaseBPMButton = new JButton(">>");
            this.decreaseBPMButton = new JButton("<<");
            this.setBPMButton.addActionListener(this);
            this.increaseBPMButton.addActionListener(this);
            this.decreaseBPMButton.addActionListener(this);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(this.decreaseBPMButton);
            buttonPanel.add(this.increaseBPMButton);
            JPanel enterPanel = new JPanel(new GridLayout(1, 2));
            enterPanel.add(bpmLabel);
            enterPanel.add(this.bpmTextField);
            JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
            insideControlPanel.add(enterPanel);
            insideControlPanel.add(this.setBPMButton);
            insideControlPanel.add(buttonPanel);
            controlPanel.add(insideControlPanel);

            bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            controlFrame.getRootPane().setDefaultButton(this.setBPMButton);
            controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

            // controlFrame.pack();
            controlFrame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == this.setBPMButton) {
                int bpm = Integer.parseInt(this.bpmTextField.getText());
                this.setBpmStrategy.setBpm(bpm);
                this.controller.setActionHandleStrategy(this.setBpmStrategy);
            } else if (event.getSource() == this.increaseBPMButton) {
                this.controller.setActionHandleStrategy(this.increBpmStrategy);
            } else if (event.getSource() == this.decreaseBPMButton) {
                this.controller.setActionHandleStrategy(this.decreBpmStrategy);
            }

            this.controller.handleAction();
        }

    @Override
    public void updateBPM(int bpm) {
        if (bpm == 0) {
            if (this.bpmOutputLabel != null) {
                this.bpmOutputLabel.setText("offline");
            }
        } else {
            if (this.bpmOutputLabel != null) {
                this.bpmOutputLabel.setText("Current BPM: " + this.model.getBpm());
            }
        }
    }

    @Override
    public void addChildComponent(IViewComponent ivc) {
        this.childViews.add(ivc);
    }

    @Override
    public void removeChildComponent(IViewComponent ivc) {
        this.childViews.remove(ivc);
    }

    @Override
    public Set<IViewComponent> getChildren() {
        return this.childViews;
    }
}
