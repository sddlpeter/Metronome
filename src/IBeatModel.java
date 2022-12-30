public interface IBeatModel {
    void registerObserver(IBPMObserver o);
    void removeObserver(IBPMObserver o);
    void notifyObserver();
}
