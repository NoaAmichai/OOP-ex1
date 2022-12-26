package observer;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

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
        assertEquals(admin.members.size(), 3);
        System.out.println(JvmUtilities.objectTotalSize(admin));
        admin.append("noa");
        admin.insert(2, "avi");
        assertEquals(admin.undoableStringBuilder, member1.getInfo());
        System.out.println(JvmUtilities.objectTotalSize(admin));
    }

    @Test
    void unregister() {
        admin.unregister(member1); //unregister a member before register
        System.out.println(JvmUtilities.objectTotalSize(admin));
        assertEquals(admin.undoableStringBuilder.toString(), "");

        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.append("");
        admin.insert(2, "avi");
        admin.unregister(member3);
        admin.append(" hi");
        assertEquals(admin.undoableStringBuilder, member1.getInfo());
        assertNotEquals(admin.undoableStringBuilder, member3.getInfo());

    }

    @Test
    void insert() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.insert(2, "avi");
        for (Member member : admin.members) {
            assertEquals(admin.undoableStringBuilder, ((ConcreteMember) member).getInfo());
        }
    }

    @Test
    void append() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.insert(2, "avi");
        admin.append("goodbye world");
        for (Member member : admin.members) {
            assertEquals(admin.undoableStringBuilder, ((ConcreteMember) member).getInfo());
        }
    }

    @Test
    void delete() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.insert(2, "avi");
        admin.append("goodbye world");
        admin.delete(3, 7);
        for (Member member : admin.members) {
            assertEquals(admin.undoableStringBuilder, ((ConcreteMember) member).getInfo());
        }
    }

    @Test
    void undo() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.insert(2, "avi");
        admin.append(" goodbye world");
        admin.delete(3, 7);
        admin.undo();
        for (Member member : admin.members) {
            assertEquals(admin.undoableStringBuilder, ((ConcreteMember) member).getInfo());
        }
    }

    @Test
    void jvmTest() {
        admin.register(member1);
        String adminSizeBefore = JvmUtilities.objectTotalSize(admin);
        admin.register(member2);
        admin.unregister(member2);
        String adminSizeAfter = JvmUtilities.objectTotalSize(admin);
        assertEquals(adminSizeBefore,adminSizeAfter); //check size of admin didn't change after register and unregister

        admin.register(member1); //duplicate register, should not change admin size
        assertEquals(JvmUtilities.objectTotalSize(admin),adminSizeAfter);

        String adminSizeB= JvmUtilities.objectTotalSize(admin);
        admin.unregister(member1);
        assertNotEquals(JvmUtilities.objectTotalSize(admin),adminSizeB); //after we unregistered member 1 the memory of admin is smaller


    }
}
