/*
 * Copyright 2013 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.map.mutable.primitive;

import com.gs.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedIntLongHashMapTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhjb20uZ3MuY29sbGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuU3lu\n"
                        + "Y2hyb25pemVkSW50TG9uZ0hhc2hNYXAAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9sYW5nL09i\n"
                        + "amVjdDtMAANtYXB0ADhMY29tL2dzL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0aXZlL011dGFi\n"
                        + "bGVJbnRMb25nTWFwO3hwcQB+AANzcgA8Y29tLmdzLmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFi\n"
                        + "bGUucHJpbWl0aXZlLkludExvbmdIYXNoTWFwAAAAAAAAAAEMAAB4cHcIAAAAAD8AAAB4",
                new SynchronizedIntLongHashMap(new IntLongHashMap()));
    }
}