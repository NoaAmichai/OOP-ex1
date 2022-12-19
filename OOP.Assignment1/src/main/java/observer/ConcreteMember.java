package observer;

import java.util.Stack;

public class ConcreteMember implements Member {
    private UndoableStringBuilder info;

    @Override
    public void update(UndoableStringBuilder usb) {
        info = usb;
    }
}
