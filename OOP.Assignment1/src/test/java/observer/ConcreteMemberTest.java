package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {
    ConcreteMember member1 = new ConcreteMember();
    ConcreteMember member2 = new ConcreteMember();
    GroupAdmin admin = new GroupAdmin();

    @Test
    void update() {
        System.out.println(JvmUtilities.objectFootprint(admin));
        admin.register(member1);
        admin.unregister(member1);
        admin.register(member1);
        admin.unregister(member1);
        admin.register(member1);
        admin.unregister(member1);
        admin.register(member1);
        admin.unregister(member1);
        admin.register(member1);
        admin.unregister(member1);
        admin.register(member1);
        System.out.println(JvmUtilities.objectFootprint(admin));
        admin.register(member2);
        System.out.println(JvmUtilities.objectFootprint(admin));
        admin.unregister(member2);
        admin.unregister(member1);
        System.out.println(JvmUtilities.objectTotalSize(admin));
        admin.append("Check that the member doesn't get updates from the admin");
//        assertEquals(member1.getInfo().toString(),"");
        admin.register(member1);
        System.out.println("member 1 size");
        System.out.println(JvmUtilities.objectTotalSize(member1));
        admin.register(member1);
        admin.register(member2);
        System.out.println("member 2 size after register");
        System.out.println(JvmUtilities.objectFootprint(member2));
        admin.append("Check that both members have received an update");
        assertEquals(member1.getInfo(),member2.getInfo());

        admin.unregister(member1);
//        assertNotEquals(member1.getInfo(),member2.getInfo()); //Check that after unregistering the members are different.

        admin.delete(0, 100);
        admin.reverse();
        assertEquals("eta",member2.getInfo().toString());

        admin.unregister(member2);
        admin.unregister(member1);
        assertEquals(admin.members.size(),0);
    }
}