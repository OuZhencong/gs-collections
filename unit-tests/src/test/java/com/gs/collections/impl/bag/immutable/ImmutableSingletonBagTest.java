/*
 * Copyright 2015 Goldman Sachs.
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

package com.gs.collections.impl.bag.immutable;

import java.util.Comparator;
import java.util.Iterator;

import com.gs.collections.api.bag.Bag;
import com.gs.collections.api.bag.ImmutableBag;
import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.api.bag.primitive.ImmutableBooleanBag;
import com.gs.collections.api.bag.sorted.MutableSortedBag;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.multimap.bag.ImmutableBagMultimap;
import com.gs.collections.impl.bag.mutable.primitive.BooleanHashBag;
import com.gs.collections.impl.bag.sorted.mutable.TreeBag;
import com.gs.collections.impl.block.factory.Procedures;
import com.gs.collections.impl.block.function.AddFunction;
import com.gs.collections.impl.block.function.PassThruFunction0;
import com.gs.collections.impl.factory.Bags;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.factory.Maps;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.multimap.bag.HashBagMultimap;
import com.gs.collections.impl.set.mutable.UnifiedSet;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

import static com.gs.collections.impl.factory.Iterables.iBag;

public class ImmutableSingletonBagTest extends ImmutableBagTestCase
{
    private static final String VAL = "1";
    private static final String NOT_VAL = "2";

    @Override
    protected ImmutableBag<String> newBag()
    {
        return new ImmutableSingletonBag<>(VAL);
    }

    private ImmutableBag<String> newBagWithNull()
    {
        return new ImmutableSingletonBag<>(null);
    }

    @Override
    protected int numKeys()
    {
        return 1;
    }

    @Override
    public void toStringOfItemToCount()
    {
        Assert.assertEquals("{1=1}", new ImmutableSingletonBag<>(VAL).toStringOfItemToCount());
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();
        ImmutableSingletonBag<Integer> immutable = new ImmutableSingletonBag<>(1);
        Bag<Integer> mutable = Bags.mutable.of(1);
        Verify.assertEqualsAndHashCode(immutable, mutable);
        Assert.assertNotEquals(immutable, FastList.newList(mutable));
        Assert.assertNotEquals(immutable, Bags.mutable.of(1, 1));
        Verify.assertEqualsAndHashCode(UnifiedMap.newWithKeysValues(1, 1), immutable.toMapOfItemToCount());
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        Assert.assertTrue(this.newBag().allSatisfy(ignored -> true));
        Assert.assertFalse(this.newBag().allSatisfy(ignored -> false));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        super.noneSatisfy();
        Assert.assertFalse(this.newBag().noneSatisfy(ignored -> true));
        Assert.assertTrue(this.newBag().noneSatisfy(ignored -> false));
    }

    @Override
    @Test
    public void injectInto()
    {
        super.injectInto();
        Assert.assertEquals(1, new ImmutableSingletonBag<>(1).injectInto(0, AddFunction.INTEGER).intValue());
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        Assert.assertEquals(FastList.newListWith(VAL), this.newBag().toList());
    }

    @Override
    @Test
    public void toSortedList()
    {
        super.toSortedList();

        Assert.assertEquals(FastList.newListWith(VAL), this.newBag().toSortedList());
    }

    @Test
    public void toSortedListWithComparator()
    {
        Assert.assertEquals(FastList.newListWith(VAL), this.newBag().toSortedList(null));
    }

    @Override
    @Test
    public void toSet()
    {
        super.toSet();

        Assert.assertEquals(UnifiedSet.newSetWith(VAL), this.newBag().toSet());
    }

    @Override
    @Test
    public void toBag()
    {
        super.toBag();

        Assert.assertEquals(Bags.mutable.of(VAL), this.newBag().toBag());
    }

    @Override
    @Test
    public void toMap()
    {
        super.toMap();

        Assert.assertEquals(
                Maps.fixedSize.of(String.class, VAL),
                this.newBag().toMap(Object::getClass, String::valueOf));
    }

    @Test
    public void toArrayGivenArray()
    {
        Assert.assertArrayEquals(new String[]{VAL}, this.newBag().toArray(new String[1]));
        Assert.assertArrayEquals(new String[]{VAL}, this.newBag().toArray(new String[0]));
        Assert.assertArrayEquals(new String[]{VAL, null}, this.newBag().toArray(new String[2]));
    }

    @Test
    @Override
    public void min_null_throws()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        this.newBagWithNull().min(String::compareTo);
    }

    @Test
    @Override
    public void max_null_throws()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        this.newBagWithNull().max(String::compareTo);
    }

    @Test
    @Override
    public void max_null_throws_without_comparator()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        this.newBagWithNull().max();
    }

    @Test
    @Override
    public void min_null_throws_without_comparator()
    {
        // Collections with one element should not throw to emulate the JDK Collections behavior
        this.newBagWithNull().min();
    }

    @Override
    @Test
    public void newWith()
    {
        super.newWith();
        Assert.assertEquals(Bags.immutable.of(VAL, NOT_VAL), this.newBag().newWith(NOT_VAL));
    }

    @Override
    @Test
    public void newWithout()
    {
        super.newWithout();
        Assert.assertEquals(Bags.immutable.of(VAL), this.newBag().newWithout(NOT_VAL));
        Assert.assertEquals(Bags.immutable.of(), this.newBag().newWithout(VAL));
    }

    @Override
    @Test
    public void newWithAll()
    {
        super.newWithAll();
        Assert.assertEquals(Bags.immutable.of(VAL, NOT_VAL, "c"), this.newBag().newWithAll(FastList.newListWith(NOT_VAL, "c")));
    }

    @Override
    @Test
    public void newWithoutAll()
    {
        super.newWithoutAll();
        Assert.assertEquals(Bags.immutable.of(VAL), this.newBag().newWithoutAll(FastList.newListWith(NOT_VAL)));
        Assert.assertEquals(Bags.immutable.of(), this.newBag().newWithoutAll(FastList.newListWith(VAL, NOT_VAL)));
        Assert.assertEquals(Bags.immutable.of(), this.newBag().newWithoutAll(FastList.newListWith(VAL)));
    }

    @Override
    @Test
    public void testSize()
    {
        Verify.assertIterableSize(1, this.newBag());
    }

    @Override
    @Test
    public void isEmpty()
    {
        super.isEmpty();
        Assert.assertFalse(this.newBag().isEmpty());
    }

    @Test
    public void testNotEmpty()
    {
        Assert.assertTrue(this.newBag().notEmpty());
    }

    @Override
    @Test
    public void getFirst()
    {
        super.getFirst();
        Assert.assertEquals(VAL, this.newBag().getFirst());
    }

    @Override
    @Test
    public void getLast()
    {
        super.getLast();
        Assert.assertEquals(VAL, this.newBag().getLast());
    }

    @Override
    @Test
    public void contains()
    {
        super.contains();
        Assert.assertTrue(this.newBag().contains(VAL));
        Assert.assertFalse(this.newBag().contains(NOT_VAL));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        Assert.assertTrue(this.newBag().containsAllIterable(FastList.newListWith()));
        Assert.assertTrue(this.newBag().containsAllIterable(FastList.newListWith(VAL)));
        Assert.assertFalse(this.newBag().containsAllIterable(FastList.newListWith(NOT_VAL)));
        Assert.assertFalse(this.newBag().containsAllIterable(FastList.newListWith(42)));
        Assert.assertFalse(this.newBag().containsAllIterable(FastList.newListWith(VAL, NOT_VAL)));
    }

    @Test
    public void testContainsAllArguments()
    {
        Assert.assertTrue(this.newBag().containsAllArguments());
        Assert.assertTrue(this.newBag().containsAllArguments(VAL));
        Assert.assertFalse(this.newBag().containsAllArguments(NOT_VAL));
        Assert.assertFalse(this.newBag().containsAllArguments(42));
        Assert.assertFalse(this.newBag().containsAllArguments(VAL, NOT_VAL));
    }

    @Override
    @Test
    public void selectToTarget()
    {
        super.selectToTarget();
        MutableList<String> target = Lists.mutable.of();
        this.newBag().select(ignored1 -> false, target);
        Verify.assertEmpty(target);
        this.newBag().select(ignored -> true, target);
        Verify.assertContains(VAL, target);
    }

    @Override
    @Test
    public void rejectToTarget()
    {
        super.rejectToTarget();
        MutableList<String> target = Lists.mutable.of();
        this.newBag().reject(ignored -> true, target);
        Verify.assertEmpty(target);
        this.newBag().reject(ignored -> false, target);
        Verify.assertContains(VAL, target);
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        Assert.assertEquals(Bags.immutable.of(VAL), this.newBag().collect(String::valueOf));
    }

    @Override
    @Test
    public void collect_target()
    {
        super.collect_target();
        MutableList<Class<?>> target = Lists.mutable.of();
        this.newBag().collect(Object::getClass, target);
        Verify.assertContains(String.class, target);
    }

    @Override
    @Test
    public void collectIf()
    {
        super.collectIf();
        Assert.assertEquals(Bags.immutable.of(String.class), this.newBag().collectIf(ignored -> true, Object::getClass));
        Assert.assertEquals(Bags.immutable.of(), this.newBag().collectIf(ignored -> false, Object::getClass));
    }

    @Override
    @Test
    public void collectIfWithTarget()
    {
        super.collectIfWithTarget();
        MutableList<Class<?>> target = Lists.mutable.of();
        this.newBag().collectIf(ignored1 -> false, Object::getClass, target);
        Verify.assertEmpty(target);
        this.newBag().collectIf(ignored -> true, Object::getClass, target);
        Verify.assertContains(String.class, target);
    }

    @Override
    @Test
    public void flatCollect()
    {
        super.flatCollect();
        ImmutableBag<Integer> result = this.newBag().flatCollect(object -> Bags.mutable.of(1, 2, 3, 4, 5));
        Assert.assertEquals(Bags.immutable.of(1, 2, 3, 4, 5), result);
    }

    @Override
    @Test
    public void flatCollectWithTarget()
    {
        super.flatCollectWithTarget();
        MutableBag<Integer> target = Bags.mutable.of();
        MutableBag<Integer> result = this.newBag().flatCollect(object -> Bags.mutable.of(1, 2, 3, 4, 5), target);
        Assert.assertEquals(Bags.mutable.of(1, 2, 3, 4, 5), result);
    }

    @Override
    @Test
    public void detect()
    {
        super.detect();
        Assert.assertEquals(VAL, this.newBag().detect(ignored -> true));
        Assert.assertNull(this.newBag().detect(ignored -> false));
    }

    @Override
    @Test
    public void detectWith()
    {
        super.detectWith();

        Assert.assertEquals(VAL, this.newBag().detectWith(Object::equals, "1"));
    }

    @Override
    @Test
    public void detectWithIfNone()
    {
        super.detectWithIfNone();

        Assert.assertEquals(VAL, this.newBag().detectWithIfNone(Object::equals, "1", new PassThruFunction0<>("Not Found")));
        Assert.assertEquals("Not Found", this.newBag().detectWithIfNone(Object::equals, "10000", new PassThruFunction0<>("Not Found")));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();

        Assert.assertEquals(VAL, this.newBag().detectIfNone(ignored -> true, new PassThruFunction0<>(NOT_VAL)));
        Assert.assertEquals(NOT_VAL, this.newBag().detectIfNone(ignored -> false, new PassThruFunction0<>(NOT_VAL)));
    }

    @Override
    @Test
    public void count()
    {
        super.count();
        Assert.assertEquals(1, this.newBag().count(ignored -> true));
        Assert.assertEquals(0, this.newBag().count(ignored -> false));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        Assert.assertTrue(this.newBag().anySatisfy(ignored -> true));
        Assert.assertFalse(this.newBag().anySatisfy(ignored -> false));
    }

    @Test
    public void testGroupBy()
    {
        ImmutableBagMultimap<Class<?>, String> result = this.newBag().groupBy(Object::getClass);
        Assert.assertEquals(VAL, result.get(String.class).getFirst());
    }

    @Test
    public void testGroupByWithTarget()
    {
        HashBagMultimap<Class<?>, String> target = HashBagMultimap.newMultimap();
        this.newBag().groupBy(Object::getClass, target);
        Assert.assertEquals(VAL, target.get(String.class).getFirst());
    }

    @Override
    @Test
    public void groupByUniqueKey()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues("1", "1").toImmutable(), this.newBag().groupByUniqueKey(id -> id));
    }

    @Override
    @Test
    public void groupByUniqueKey_throws()
    {
        super.groupByUniqueKey_throws();

        Assert.assertEquals(UnifiedMap.newWithKeysValues("1", "1").toImmutable(), this.newBag().groupByUniqueKey(id -> id));
    }

    @Override
    @Test
    public void groupByUniqueKey_target()
    {
        Assert.assertEquals(
                UnifiedMap.newWithKeysValues("0", "0", "1", "1"),
                this.newBag().groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues("0", "0")));
    }

    @Test
    public void testOccurrencesOf()
    {
        Assert.assertEquals(1, this.newBag().occurrencesOf(VAL));
        Assert.assertEquals(0, this.newBag().occurrencesOf(NOT_VAL));
    }

    @Test
    public void testForEachWithOccurrences()
    {
        Object[] results = new Object[2];
        this.newBag().forEachWithOccurrences((each, index) -> {
            results[0] = each;
            results[1] = index;
        });
        Assert.assertEquals(VAL, results[0]);
        Assert.assertEquals(1, results[1]);
    }

    @Override
    @Test
    public void toMapOfItemToCount()
    {
        super.toMapOfItemToCount();

        Assert.assertEquals(Maps.fixedSize.of(VAL, 1), this.newBag().toMapOfItemToCount());
    }

    @Override
    @Test
    public void toImmutable()
    {
        super.toImmutable();

        ImmutableBag<String> immutableBag = this.newBag();
        Assert.assertSame(immutableBag, immutableBag.toImmutable());
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();
        Object[] results = new Object[1];
        this.newBag().forEach(Procedures.cast(each -> results[0] = each));
        Assert.assertEquals(VAL, results[0]);
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        super.forEachWithIndex();
        Object[] results = new Object[2];
        this.newBag().forEachWithIndex((each, index) -> {
            results[0] = each;
            results[1] = index;
        });
        Assert.assertEquals(VAL, results[0]);
        Assert.assertEquals(0, results[1]);
    }

    @Override
    @Test
    public void forEachWith()
    {
        super.forEachWith();
        Object[] results = new Object[2];
        this.newBag().forEachWith((each, index) -> {
            results[0] = each;
            results[1] = index;
        }, "second");
        Assert.assertEquals(VAL, results[0]);
        Assert.assertEquals("second", results[1]);
    }

    @Override
    @Test
    public void iterator()
    {
        super.iterator();
        Iterator<String> iterator = this.newBag().iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(VAL, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testSizeDistinct()
    {
        Assert.assertEquals(1, this.newBag().sizeDistinct());
    }

    @Override
    @Test
    public void selectInstancesOf()
    {
        ImmutableBag<Number> numbers = new ImmutableSingletonBag<>(1);
        Assert.assertEquals(iBag(1), numbers.selectInstancesOf(Integer.class));
        Assert.assertEquals(iBag(), numbers.selectInstancesOf(Double.class));
    }

    @Override
    @Test
    public void collectBoolean()
    {
        ImmutableBooleanBag result = this.newBag().collectBoolean("4"::equals);
        Assert.assertEquals(1, result.sizeDistinct());
        Assert.assertEquals(0, result.occurrencesOf(true));
        Assert.assertEquals(1, result.occurrencesOf(false));
    }

    @Override
    @Test
    public void collectBooleanWithTarget()
    {
        BooleanHashBag target = new BooleanHashBag();
        BooleanHashBag result = this.newBag().collectBoolean("4"::equals, target);
        Assert.assertSame("Target sent as parameter not returned", target, result);
        Assert.assertEquals(1, result.sizeDistinct());
        Assert.assertEquals(0, result.occurrencesOf(true));
        Assert.assertEquals(1, result.occurrencesOf(false));
    }

    @Override
    @Test
    public void toSortedBag()
    {
        ImmutableBag<String> immutableBag = this.newBag();
        MutableSortedBag<String> sortedBag = immutableBag.toSortedBag();

        Verify.assertSortedBagsEqual(TreeBag.newBagWith("1"), sortedBag);

        MutableSortedBag<String> reverse = immutableBag.toSortedBag(Comparator.<String>reverseOrder());
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Comparator.reverseOrder(), "1"), reverse);
    }

    @Override
    @Test
    public void toSortedBagBy()
    {
        ImmutableBag<String> immutableBag = this.newBag();
        MutableSortedBag<String> sortedBag = immutableBag.toSortedBagBy(String::valueOf);

        Verify.assertSortedBagsEqual(TreeBag.newBagWith("1"), sortedBag);
    }
}
