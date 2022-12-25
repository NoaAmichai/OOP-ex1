package observer;

public class ConcreteMember implements Member {
    private UndoableStringBuilder info;

    @Override
    public void update(UndoableStringBuilder usb) {
        info = usb;
    }
    public UndoableStringBuilder getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "ConcreteMember{" +
                "info=" + info +
                '}';
    }
}