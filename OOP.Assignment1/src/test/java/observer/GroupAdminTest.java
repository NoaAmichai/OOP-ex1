package observer;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {
    GroupAdmin admin = new GroupAdmin();
    ConcreteMember member1 = new ConcreteMember();
    ConcreteMember member2 = new ConcreteMember();
    ConcreteMember member3 = new ConcreteMember();

    @Test
    void register() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        if (admin.members.size() == 3) {
            System.out.println("YES");
        }
        admin.append("noa");
        admin.insert(2,"avi");
        System.out.println(admin.undoableStringBuilder.toString());
        assertEquals(admin.undoableStringBuilder,member1.getInfo());

    }

    @Test
    void unregister() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        if (admin.members.size() == 3) {
            System.out.println("YES");
        }
        admin.append("noa");
        admin.insert(2,"avi");
        admin.unregister(member3);
        admin.append(" hi");
        System.out.println(member1.toString());
        System.out.println(member2.toString());
        System.out.println(member3.toString());
        System.out.println(admin.undoableStringBuilder.toString());
        assertEquals(admin.undoableStringBuilder,member1.getInfo());
        assertNotEquals(admin.undoableStringBuilder,member3.getInfo());
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