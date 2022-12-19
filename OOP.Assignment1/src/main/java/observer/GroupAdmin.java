package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{

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
        undoableStringBuilder.insert(offset,obj);
        for (Member member:this.members) {
            member.update(undoableStringBuilder);
        }
    }


    @Override
    public void append(String obj) {
        undoableStringBuilder.append(obj);
        for (Member member:this.members) {
            member.update(undoableStringBuilder);
        }
    }

    @Override
    public void delete(int start, int end) {
        undoableStringBuilder.delete(start, end);
        for (Member member:this.members) {
            member.update(undoableStringBuilder);
        }
    }

    @Override
    public void undo() {
        undoableStringBuilder.undo();
        for (Member member:this.members) {
            member.update(undoableStringBuilder);
        }
    }
}
