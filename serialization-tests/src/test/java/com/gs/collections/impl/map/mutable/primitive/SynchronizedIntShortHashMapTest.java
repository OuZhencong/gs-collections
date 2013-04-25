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

public class SynchronizedIntShortHashMapTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEljb20uZ3MuY29sbGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuU3lu\n"
                        + "Y2hyb25pemVkSW50U2hvcnRIYXNoTWFwAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEvbGFuZy9P\n"
                        + "YmplY3Q7TAADbWFwdAA5TGNvbS9ncy9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2ZS9NdXRh\n"
                        + "YmxlSW50U2hvcnRNYXA7eHBxAH4AA3NyAD1jb20uZ3MuY29sbGVjdGlvbnMuaW1wbC5tYXAubXV0\n"
                        + "YWJsZS5wcmltaXRpdmUuSW50U2hvcnRIYXNoTWFwAAAAAAAAAAEMAAB4cHcIAAAAAD8AAAB4",
                new SynchronizedIntShortHashMap(new IntShortHashMap()));
    }
}