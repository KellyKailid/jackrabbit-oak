/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.oak.security.user.autosave;

import org.apache.jackrabbit.guava.common.collect.Lists;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AuthorizableWrapperTest extends AbstractAutoSaveTest {

    @Test
    public void testApplyNull() {
        Iterator<Authorizable> it = AuthorizableWrapper.createIterator(Lists.newArrayList(null, (Authorizable) null).iterator(), autosaveMgr);
        while(it.hasNext()) {
            assertNull(it.next());
        }
        verify(autosaveMgr, never()).wrap(any(Authorizable.class));
    }

    @Test
    public void testApply() throws Exception {
        Iterator<Authorizable> it = AuthorizableWrapper.createIterator(Lists.newArrayList(getTestUser(), (Authorizable) null).iterator(), autosaveMgr);
        while(it.hasNext()) {
            it.next();
        }
        verify(autosaveMgr, times(1)).wrap(any(Authorizable.class));
    }
}