package com.gewia.common.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gewia.common.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;
import java.util.Collections;

public class JwtUtilTest {

	@Test
	public void testSuccessfulCreation() {
		JwtUtil util = new JwtUtil("testIssuer", Collections.singletonList("testAudience"), "SECRET");

		String jwt = util.create("testSubject", 5, Calendar.MINUTE);
		Assert.assertNotNull(jwt);
		Assert.assertFalse(jwt.isBlank());

		Pair<DecodedJWT, JwtUtil.VerificationResult> result = util.verify(jwt);
		Assert.assertSame(result.getRight(), JwtUtil.VerificationResult.SUCCESS);
	}

	@Test
	public void testManipulatedJWT() {
		JwtUtil originalUtil = new JwtUtil("testIssuer", Collections.singletonList("testAudience"), "SECRET");
		JwtUtil attackerUtil = new JwtUtil("testIssuer", Collections.singletonList("testAudience"), "SECRET2");

		String jwt = attackerUtil.create("victim", 5, Calendar.YEAR);

		Pair<DecodedJWT, JwtUtil.VerificationResult> result = originalUtil.verify(jwt);
		Assert.assertSame(result.getRight(), JwtUtil.VerificationResult.FAILED);
	}

	@Test
	public void testInvalidSignatureJWT() {
		JwtUtil util = new JwtUtil("testIssuer", Collections.singletonList("testAudience"), "SECRET");

		String jwt = util.create("testSubject", 5, Calendar.MINUTE);
		jwt += "makeThisSignatureInvalid";

		Pair<DecodedJWT, JwtUtil.VerificationResult> result = util.verify(jwt);
		Assert.assertSame(result.getRight(), JwtUtil.VerificationResult.UNKNOWN); //Base64 decoding exception
	}

	@Test
	public void testInvalidHeaderJWT() {
		JwtUtil util = new JwtUtil("testIssuer", Collections.singletonList("testAudience"), "SECRET");

		String jwt = util.create("testSubject", 5, Calendar.MINUTE);
		jwt = "makeThisSignatureInvalid" + jwt;

		Pair<DecodedJWT, JwtUtil.VerificationResult> result = util.verify(jwt);
		Assert.assertSame(result.getRight(), JwtUtil.VerificationResult.INVALID);
	}

	@Test
	public void testExpiredJWT() throws InterruptedException {
		JwtUtil util = new JwtUtil("testIssuer", Collections.singletonList("testAudience"), "SECRET");

		String jwt = util.create("testSubject", 1, Calendar.MILLISECOND);

		Thread.sleep(2L);

		Pair<DecodedJWT, JwtUtil.VerificationResult> result = util.verify(jwt);
		Assert.assertSame(result.getRight(), JwtUtil.VerificationResult.EXPIRED);
	}

}
