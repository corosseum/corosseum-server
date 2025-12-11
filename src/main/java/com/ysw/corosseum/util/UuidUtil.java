package com.ysw.corosseum.util;

import java.security.SecureRandom;
import java.util.UUID;

public class UuidUtil {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	public static String generate() {
		long timestamp = DateUtil.timestamp();
		return generateV7(timestamp).toString();
	}

	private static UUID generateV7(long timestamp) {
		// UUID v7 format: timestamp (48 bits) + version (4 bits) + rand_a (12 bits) + variant (2 bits) + rand_b (62 bits)
		// Most significant 64 bits: timestamp (48 bits) + version (4 bits) + rand_a (12 bits)
		long mostSignificantBits = ((timestamp & 0xFFFFFFFFFFFFL) << 16) | 0x7000L | (SECURE_RANDOM.nextInt() & 0xFFFL);

		// Least significant 64 bits: variant (2 bits) + rand_b (62 bits)
		long leastSignificantBits = 0x8000000000000000L | (SECURE_RANDOM.nextLong() & 0x3FFFFFFFFFFFFFFFL);

		return new UUID(mostSignificantBits, leastSignificantBits);
	}
}
