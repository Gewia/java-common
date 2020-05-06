package com.gewia.common.scope.test;

import com.gewia.common.scope.impl.ScopeFactory;
import com.gewia.common.scope.impl.MicroServiceScope;
import com.gewia.common.scope.impl.util.Limitation;
import com.gewia.common.scope.impl.util.Mode;
import org.junit.Assert;
import org.junit.Test;

public class MicroServiceScopeTest {

	@Test
	public void microServiceCreateAndReadAndDeleteTest() {
		MicroServiceScope scope = ScopeFactory.createMicroServiceScope(
			"testMicroService",
			"test",
			Mode.WRITE,
			Limitation.ALL
		);

		Assert.assertFalse("Scope parts empty", scope.getScopeParts().isEmpty());
		Assert.assertEquals("Scope parts size not zero", 4, scope.getScopePartsSize());

		Assert.assertEquals(
			"Scope does not fulfill expectation",
			scope,
			ScopeFactory.createBasicScope().addScopeParts("testMicroService", "test", "write", "all")
		);

		Assert.assertEquals("Scope output doesn't match", "testMicroService.test.write.all", scope.toString());
		Assert.assertEquals("Scope parts do not match size (list access)", 4, scope.getScopeParts().size());
		Assert.assertEquals("Scope parts do not match size", 4, scope.getScopePartsSize());

		Assert.assertThrows("Scope didn't catch out of bound remove", IndexOutOfBoundsException.class,() -> scope.removeScopePart(4));

		for (int index = 0; index < 3; index++) {
			int finalIndex = index;
			Assert.assertThrows("Scope didn't catch not schema-compliant remove", UnsupportedOperationException.class,() -> scope.removeScopePart(finalIndex));
		}

		Assert.assertEquals("Scope output doesn't match", "testMicroService.test.write.all", scope.toString());
		Assert.assertEquals("Scope parts do not match size (list access)", 4, scope.getScopeParts().size());
		Assert.assertEquals("Scope parts do not match size", 4, scope.getScopePartsSize());
	}

}
