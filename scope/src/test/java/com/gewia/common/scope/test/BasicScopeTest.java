package com.gewia.common.scope.test;

import com.gewia.common.scope.Scope;
import com.gewia.common.scope.impl.ScopeFactory;
import org.junit.Assert;
import org.junit.Test;

public class BasicScopeTest {

	@Test
	public void basicCreateAndReadAndDeleteTest() {
		Scope scope = ScopeFactory.createBasicScope();

		Assert.assertTrue("Scope parts not empty", scope.getScopeParts().isEmpty());
		Assert.assertEquals("Scope parts size not zero", 0, scope.getScopePartsSize());
		Assert.assertEquals("Output not empty", 0, scope.toString().length());

		Assert.assertEquals(
			"Returned scope not the same",
			scope,
			scope.addScopeParts("user", "email", "read")
		);

		Assert.assertEquals("Scope output doesn't match", "user.email.read", scope.toString());
		Assert.assertEquals("Scope parts do not match size (list access)", 3, scope.getScopeParts().size());
		Assert.assertEquals("Scope parts do not match size", 3, scope.getScopePartsSize());

		Assert.assertThrows("Scope didn't catch out of bound remove", IndexOutOfBoundsException.class,() -> scope.removeScopePart(4));

		scope.removeScopePart(1);

		Assert.assertEquals("Scope output doesn't match", "user.read", scope.toString());
		Assert.assertEquals("Scope parts do not match size (list access)", 2, scope.getScopeParts().size());
		Assert.assertEquals("Scope parts do not match size", 2, scope.getScopePartsSize());
	}

}
