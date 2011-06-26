package org.servalproject.dna;

import java.nio.ByteBuffer;

public class OpPad implements Operation {
	
	static byte getCode(){return (byte)0xfe;}
	
	public void parse(ByteBuffer b, byte code) {
		int len = (b.get())&0xff;
		b.position(b.position()+len);
	}

	public void write(ByteBuffer b) {
		int len=Packet.rand.nextInt(16);
		if (len==0) return;
		
		b.put(getCode());
		b.put((byte)len);
		byte padding[]=new byte[len];
		Packet.rand.nextBytes(padding);
		b.put(padding);
	}
	
	public boolean visit(Packet packet, OpVisitor v) {return false;}

	@Override
	public String toString() {
		return "Padding";
	}
}
