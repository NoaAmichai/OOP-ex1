package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {

    ArrayList<Member> members = new ArrayList<>();
    UndoableStringBuilder undoableStringBuilder = new UndoableStringBuilder();

    @Override
    public void register(Member obj) {
        members.add(obj);
    }

    @Override
    public void unregister(Member obj) {
        UndoableStringBuilder rem = new UndoableStringBuilder();
        members.remove(obj);
        obj.update(rem);
    }

    @Override
    public void insert(int offset, String obj) {
        undoableStringBuilder.insert(offset, obj);
        for (Member member : this.members) {
            member.update(undoableStringBuilder);
        }
    }

    /***
     * Append gets a string and append it to our undoableStringBuilder.
     * Update all members with the new undoableStringBuilder
     * @param obj Sting that we add to our undoableStringBuilder.
     */
    @Override
    public void append(String obj) {
        undoableStringBuilder.append(obj);
        for (Member member : this.members) {
            member.update(undoableStringBuilder);
        }
    }

    /**
     * Removes the characters in a substring of this sequence.
     * Update all members with the new undoableStringBuilder
     * @param start-the start of the substring that will be removed.
     * @param end-the   end of the substring that will be removed.
     */
    @Override
    public void delete(int start, int end) {
        undoableStringBuilder.delete(start, end);
        for (Member member : this.members) {
            member.update(undoableStringBuilder);
        }
    }

    /**
     * Undoes the last action performed on the Sequence.
     * Update all members.
     */
    @Override
    public void undo() {
        undoableStringBuilder.undo();
        for (Member member : this.members) {
            member.update(undoableStringBuilder);
        }
    }
}
