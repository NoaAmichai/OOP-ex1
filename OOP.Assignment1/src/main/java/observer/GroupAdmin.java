package observer;

import java.util.ArrayList;

<<<<<<< Updated upstream
public class GroupAdmin implements Sender{
=======
public class GroupAdmin implements Sender {
>>>>>>> Stashed changes
    private ArrayList<Member> members = new ArrayList<>();
    UndoableStringBuilder undoableStringBuilder = new UndoableStringBuilder();

    @Override
    public void register(Member obj) {
        members.add(obj);
    }

    @Override
    public void unregister(Member obj) {
        members.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
<<<<<<< Updated upstream

=======
        undoableStringBuilder.insert(offset, obj);
>>>>>>> Stashed changes
    }

    @Override
    public void append(String obj) {
        undoableStringBuilder.append(obj);
    }

    @Override
    public void delete(int start, int end) {
        undoableStringBuilder.delete(start, end);
    }

    @Override
    public void undo() {
        undoableStringBuilder.undo();
    }
}
