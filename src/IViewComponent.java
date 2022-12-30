import java.util.*;

public interface IViewComponent {
    void addChildComponent(IViewComponent ivc);
    void removeChildComponent(IViewComponent ivc);
    Set<IViewComponent> getChildren();
}
