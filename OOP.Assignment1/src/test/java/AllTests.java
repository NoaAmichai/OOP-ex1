import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.JvmUtilities;
import observer.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;


public class AllTests {

    GroupAdmin admin = new GroupAdmin();
    ConcreteMember member1 = new ConcreteMember();
    ConcreteMember member2 = new ConcreteMember();
    ConcreteMember member3 = new ConcreteMember();

    //GroupAdmin Tests
    @Test
    void register() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        assertEquals(admin.getMembers().size(), 3);
        //System.out.println(JvmUtilities.objectTotalSize(admin));
        admin.append("noa");
        admin.insert(2, "avi");
        assertEquals(admin.getUndoableStringBuilder(), member1.getInfo());
        //System.out.println(JvmUtilities.objectTotalSize(admin));
    }

    @Test
    void unregister() {
        admin.unregister(member1); //unregister a member before register
        //System.out.println(JvmUtilities.objectTotalSize(admin));
        assertEquals(admin.getUndoableStringBuilder().toString(), "");

        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.append("");
        admin.insert(2, "avi");
        admin.unregister(member3);
        admin.append(" hi");
        assertEquals(admin.getUndoableStringBuilder(), member1.getInfo());
        assertNotEquals(admin.getUndoableStringBuilder(), member3.getInfo());

    }

    @Test
    void insert() {
        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        admin.append("noa");
        admin.insert(2, "avi");
        for (Member member : admin.getMembers()) {
            assertEquals(admin.getUndoableStringBuilder(), ((ConcreteMember) member).getInfo());
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
        for (Member member : admin.getMembers()) {
            assertEquals(admin.getUndoableStringBuilder(), ((ConcreteMember) member).getInfo());
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
        for (Member member : admin.getMembers()) {
            assertEquals(admin.getUndoableStringBuilder(), ((ConcreteMember) member).getInfo());
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
        for (Member member : admin.getMembers()) {
            assertEquals(admin.getUndoableStringBuilder(), ((ConcreteMember) member).getInfo());
        }
    }
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    @Test
    void jvmTest() {
        logger.info(()-> JvmUtilities.memoryStats(admin));
        logger.info(()-> JvmUtilities.memoryStats(member1));
        admin.register(member1);
        String adminSizeBefore = JvmUtilities.objectTotalSize(admin);
        admin.register(member2);
        admin.unregister(member2);
        String adminSizeAfter = JvmUtilities.objectTotalSize(admin);
        assertEquals(adminSizeBefore, adminSizeAfter); //check size of admin didn't change after register and unregister

        admin.register(member1); //duplicate register, should not change admin size
        assertEquals(JvmUtilities.objectTotalSize(admin), adminSizeAfter);
admin.append("hello");
        logger.info(()-> JvmUtilities.memoryStats(admin));
        logger.info(()-> JvmUtilities.memoryStats(member1));
        String adminSizeB = JvmUtilities.objectTotalSize(admin);
        admin.unregister(member1);
        assertNotEquals(JvmUtilities.objectTotalSize(admin), adminSizeB); //after we unregistered member 1 the memory of admin is smaller
        logger.info(()-> JvmUtilities.memoryStats(admin));
        logger.info(()-> JvmUtilities.memoryStats(member1));
    }

    //ConcreteMember Tests
    @Test
    void update() {
        String concreteSize = JvmUtilities.objectTotalSize(member1);
        admin.register(member1);
        admin.unregister(member1);
        assertEquals(JvmUtilities.objectTotalSize(member1), concreteSize);

        admin.append("Check that the member doesn't get updates from the admin");
        admin.register(member1);
        admin.register(member2);
        assertEquals(JvmUtilities.objectTotalSize(member1), JvmUtilities.objectTotalSize(member2)); //both members should be in the same size

        admin.unregister(member1);
        assertNotEquals(JvmUtilities.objectTotalSize(member1), JvmUtilities.objectTotalSize(member2)); //after unregister members size should be different

        admin.register(member1);
        admin.append("Check that both members have received an update");
        assertEquals(member1.getInfo(), member2.getInfo());

        admin.unregister(member1);
        assertNotEquals(member1.getInfo(), member2.getInfo()); //Check that after unregistering the members are different.
        admin.delete(0, 100);
        admin.reverse();
        assertEquals("eta", member2.getInfo().toString());

        admin.unregister(member2);
        admin.unregister(member1);
        assertEquals(admin.getMembers().size(), 0);
    }
}
