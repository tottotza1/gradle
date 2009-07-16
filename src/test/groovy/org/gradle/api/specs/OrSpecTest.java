/*
 * Copyright 2007-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.specs;

import org.gradle.api.artifacts.Dependency;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Hans Dockter
 */
public class OrSpecTest extends AbstractCompositeTest {
    private JUnit4Mockery context = new JUnit4Mockery();

    public CompositeSpec createCompositeSpec(Spec... specs) {
        return new OrSpec(specs);
    }

    @Test
    public void isSatisfiedWhenNoSpecs() {
        assertTrue(new OrSpec().isSatisfiedBy(new Object()));
    }
    
    @Test
    public void isSatisfiedByWithOneTrue() {
        assertTrue(new OrSpec(createAtomicElements(false, true, false)).isSatisfiedBy(context.mock(Dependency.class)));
    }

    @Test
    public void isSatisfiedByWithAllFalse() {
        assertFalse(new AndSpec(createAtomicElements(false, false, false)).isSatisfiedBy(context.mock(Dependency.class)));
    }
}
