
package arch.sm213.machine.student;

import static org.junit.Assert.*;
import machine.AbstractMainMemory.InvalidAddressException;

import org.junit.Before;
import org.junit.Test;

public class MainMemoryTest {
	
	private MainMemory mainMemory;
	
	@Before
	public void createMemory()
	{
		mainMemory = new MainMemory(128);
	}

  @Test
  public void testGetAndSet() throws InvalidAddressException {
    byte[] iv0 = {23, 47, 13, 105};
    byte[] iv1 = {93, 22, 87, 121};
    byte[] iv2 = {32, 48, 16, 8};
    mainMemory.set (32, iv0);
    mainMemory.set (36, iv1);
    mainMemory.set (40, iv2);
    byte[] ov0 = mainMemory.get (32, 4);
    byte[] ov1 = mainMemory.get (36, 4);
    byte[] ov2 = mainMemory.get (40, 4);
    assertTrue (ov0[0]==iv0[0] && ov0[1]==iv0[1] && ov0[2]==iv0[2] && ov0[3]==iv0[3]);
    assertTrue (ov1[0]==iv1[0] && ov1[1]==iv1[1] && ov1[2]==iv1[2] && ov1[3]==iv1[3]);
    assertTrue (ov2[0]==iv2[0] && ov2[1]==iv2[1] && ov2[2]==iv2[2] && ov2[3]==iv2[3]);
  }

	@Test
	public void testIsAccessAlignedTrue() {
		assertTrue(mainMemory.isAccessAligned(4,2));
		assertTrue(mainMemory.isAccessAligned(0,4));
		assertTrue(mainMemory.isAccessAligned(6,3));
	}

	@Test
	public void testIsAccessAlignedFalse() {
		assertFalse(mainMemory.isAccessAligned(4,3));
		assertFalse(mainMemory.isAccessAligned(3,5));
		assertFalse(mainMemory.isAccessAligned(5,2));
	}

	@Test
	public void testBytesToInteger() {
		int res1 = mainMemory.bytesToInteger((byte) 0, (byte) 0, (byte) 0, (byte) 0);
		assertEquals(res1, (int) 0x0);
		int res2 = mainMemory.bytesToInteger((byte) 0x13, (byte) 0xff, (byte) 0x3a, (byte) 0x10);
		assertEquals(res2, (int) 0x13ff3a10);
		int res3 = mainMemory.bytesToInteger((byte) 0xa7, (byte) 0xa7, (byte) 0xa7, (byte) 0xa7);
		assertEquals(res3, (int) 0xa7a7a7a7);
	}

	@Test
	public void testIntegerToBytes() {
		int a = 0xff1357e6;
		byte bytes[] = new byte[4];
		bytes = mainMemory.integerToBytes(a);
		assertEquals(bytes[0], (byte) 0xff);
		assertEquals(bytes[1], (byte) 0x13);
		assertEquals(bytes[2], (byte) 0x57);
		assertEquals(bytes[3], (byte) 0xe6);
	}
}
