/*
 * Copyright 2014 Goldman Sachs.
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

package com.gs.collections.impl.block.procedure;

import java.util.Comparator;
import java.util.NoSuchElementException;

import com.gs.collections.api.block.procedure.Procedure;

public abstract class ComparatorProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    protected final Comparator<? super T> comparator;
    protected boolean visitedAtLeastOnce;
    protected T result;

    protected ComparatorProcedure(Comparator<? super T> comparator)
    {
        this.comparator = comparator;
    }

    public boolean isVisitedAtLeastOnce()
    {
        return this.visitedAtLeastOnce;
    }

    public T getResult()
    {
        if (!this.visitedAtLeastOnce)
        {
            throw new NoSuchElementException();
        }
        return this.result;
    }
}
