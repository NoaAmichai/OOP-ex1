package observer;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {
    GroupAdmin groupAdmin = new GroupAdmin();
    ConcreteMember member1 = new ConcreteMember();
    ConcreteMember member2 = new ConcreteMember();
    ConcreteMember member3 = new ConcreteMember();

    @Test
    void register() {
        groupAdmin.register(member1);
        groupAdmin.register(member2);
        groupAdmin.register(member3);
        if (groupAdmin.members.size() == 3) {
            System.out.println("YES");
        }
        groupAdmin.undoableStringBuilder.append("noa");

    }

    @Test
    void unregister() {
    }

    @Test
    void insert() {
    }

    @Test
    void append() {
    }

    @Test
    void delete() {
    }

    @Test
    void undo() {
    }
}