package observer;
import java.lang.StringBuilder;
        import java.util.EmptyStackException;
        import java.util.Stack;

/**
 * this Class performs all actions of StringBuilder with the addition of the Undo action.
 *
 * @author Avi ostroff and Noa Amichai.
 * @version 1.0
 */

public class UndoableStringBuilder {
    private StringBuilder str;
    private final Stack<String> stk;

    /**
     * constructor
     */
    public UndoableStringBuilder() {
        str = new StringBuilder();
        stk = new Stack<>();
    }

    /**
     * Removes the characters in a substring of this sequence.
     *
     * @param start-the start of the substring that will be removed.
     * @param end-the   end of the substring that will be removed.
     * @return returns the sequence after removing the substring.
     */
    public UndoableStringBuilder delete(int start, int end) {
        try {
            str.delete(start, end);
            stk.push(this.str.toString());
        } catch (StringIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            System.out.println("Invalid index");
        }
        return this;
    }

    /**
     * The method inserts the string into this character sequence.
     *
     * @param offset -The starting position of where to insert the String.
     * @param str    -The string we want to insert.
     * @return returns the Sequence after adding the String that was
     * requested to insert.
     */
    public UndoableStringBuilder insert(int offset, String str) {
        try {
            this.str.insert(offset, str);
            stk.push(this.str.toString());
        } catch (StringIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            System.out.println("the offset you have entered is out of bounds");
        }
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String.
     *
     * @param start-start of the string that will be replaced.
     * @param end-end     of the string that will be replaced.
     * @param str-the     string that will replace the old substring.
     * @return the method will return the new sequence.
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        if (str == null) {
            return this;
        }
        try {
            this.str.replace(start, end, str);
            stk.push(this.str.toString());
        } catch (StringIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            System.out.println("The input you have entered is invalid");
        }
        return this;
    }

    /**
     * This method gets a String as StringBuilder and Causes this character sequence
     * to be replaced by the reverse of the sequence.
     *
     * @return the method returns the reversed sequence.
     */
    public UndoableStringBuilder reverse() {
        str = str.reverse();
        stk.push(str.toString());
        return this;
    }

    /**
     * The method receives a string as input and
     * appends the specified string to this character sequence.
     *
     * @param str -the String the method receives.
     * @return the method returns the sequence after adding the String at the end.
     */
    public UndoableStringBuilder append(String str) {
        this.str.append(str);
        stk.push(this.str.toString());
        return this;
    }

    /**
     * Undoes the last action performed on the Sequence.
     */

    public void undo() {
        if (stk.size() == 0) {
            return;
        }
        if (stk.size() == 1) {
            int len = stk.pop().length();
            str.replace(0, len, "");
            return;
        }
        try {
            int len = stk.pop().length();
            str.replace(0, len, stk.peek());
        } catch (EmptyStackException ex) {
            ex.printStackTrace();
            System.out.println("Can't do undo on empty stack");
        }
        return;
    }

    @Override
    public String toString() {
        return str.toString();
    }
}