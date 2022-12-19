package observer;

import java.util.ArrayList;

public class GroupAdmin extends UndoableStringBuilder implements Sender{
    private ArrayList<Member> members = new ArrayList<>();

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
    }

    @Override
    public void append(String obj) {

    }

    @Override
    public void delete(int start, int end) {

    }

    @Override
    public void undo() {

    }
}
