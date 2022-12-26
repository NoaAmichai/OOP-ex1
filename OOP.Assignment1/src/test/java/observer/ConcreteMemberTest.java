package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {
    ConcreteMember member1 = new ConcreteMember();
    ConcreteMember member2 = new ConcreteMember();

    GroupAdmin admin = new GroupAdmin();

    @Test
    void update() {
        String concreteSize=JvmUtilities.objectTotalSize(member1);
        admin.register(member1);
        admin.unregister(member1);
        assertEquals(JvmUtilities.objectTotalSize(member1),concreteSize);

        admin.append("Check that the member doesn't get updates from the admin");
        admin.register(member1);
        admin.register(member2);
        assertEquals(JvmUtilities.objectTotalSize(member1),JvmUtilities.objectTotalSize(member2)); //both members should be in the same size

        admin.unregister(member1);
        assertNotEquals(JvmUtilities.objectTotalSize(member1),JvmUtilities.objectTotalSize(member2)); //after unregister members size should be different

        admin.register(member1);
        admin.append("Check that both members have received an update");
        assertEquals(member1.getInfo(),member2.getInfo());

        admin.unregister(member1);
        assertNotEquals(member1.getInfo(),member2.getInfo()); //Check that after unregistering the members are different.
        admin.delete(0, 100);
        admin.reverse();
        assertEquals("eta",member2.getInfo().toString());

        admin.unregister(member2);
        admin.unregister(member1);
        assertEquals(admin.members.size(),0);
    }
}