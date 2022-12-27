package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private ArrayList<Member> members = new ArrayList<>();
    private UndoableStringBuilder undoableStringBuilder = new UndoableStringBuilder();

    //Getter for members list
    public ArrayList<Member> getMembers() {
        return members;
    }

    //Getter for undoableStringBuilder
    public UndoableStringBuilder getUndoableStringBuilder() {
        return undoableStringBuilder;
    }

    @Override
    public void register(Member obj) {
        if(members.contains(obj)){
            return;
        }
        members.add(obj);
        obj.update(undoableStringBuilder);
    }

    @Override
    public void unregister(Member obj) {
        members.remove(obj);
        obj.update(null);
    }

    @Override
    public void insert(int offset, String obj) {
        undoableStringBuilder.insert(offset, obj);
    }

    /***
     * Append gets a string and append it to our undoableStringBuilder.
     * Update all members with the new undoableStringBuilder
     * @param obj Sting that we add to our undoableStringBuilder.
     */
    @Override
    public void append(String obj) {
        undoableStringBuilder.append(obj);
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
    }

    /**
     * Undoes the last action performed on the Sequence.
     * Update all members.
     */
    @Override
    public void undo() {
        undoableStringBuilder.undo();
    }

    public void reverse(){
        undoableStringBuilder.reverse();
    }

}
