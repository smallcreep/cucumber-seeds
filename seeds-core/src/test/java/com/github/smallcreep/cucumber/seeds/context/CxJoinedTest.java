/*
 * MIT License
 *
 * Copyright (c) 2018 Ilia Rogozhin (@smallcreep) <ilia.rogozhin@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.smallcreep.cucumber.seeds.context;

import com.github.smallcreep.cucumber.seeds.Context;
import java.util.HashMap;
import java.util.Map;
import javax.management.openmbean.KeyAlreadyExistsException;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test Case for {@link CxJoined}.
 * @since 0.1.1
 */
public class CxJoinedTest {

    /**
     * A rule for handling an exception.
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Check that value by key returned.
     */
    @Test
    public void checkValueByKey() {
        final Object expected = new Object();
        final String key = "key1";
        MatcherAssert.assertThat(
            new CxJoined(
                new CxSimple(
                    new MapOf<String, Object>(
                        new MapEntry<>(
                            key,
                            expected
                        )
                    )
                )
            ).value(key),
            CoreMatchers.equalTo(expected)
        );
    }

    /**
     * Check adding new value works.
     */
    @Test
    public void checkAddingNewValue() {
        final Map<String, Object> values = new HashMap<>();
        final Context context = new CxJoined(
            new IterableOf<>(
                new CxSimple()
            ),
            new CxSimple(values)
        );
        final Object expected = new Object();
        final String key = "key_add";
        context.add(key, expected);
        MatcherAssert.assertThat(
            values.get(key),
            CoreMatchers.equalTo(expected)
        );
    }

    /**
     * Check adding existing key threw exception.
     */
    @Test()
    public void checkAddingExistingKey() {
        final String key = "key_threw";
        this.exception.expect(KeyAlreadyExistsException.class);
        this.exception.expectMessage(
            String.format(
                "In scenario already exist key '%s'",
                key
            )
        );
        new CxJoined(
            new CxSimple(
                new MapOf<String, Object>(
                    new MapEntry<>(
                        key,
                        "first"
                    )
                )
            )
        ).add(key, "second");
    }

    /**
     * Check contains return true.
     */
    @Test
    public void checkContainsValue() {
        final Object expected = new Object();
        final String key = "key_contains";
        MatcherAssert.assertThat(
            new CxJoined(
                new CxSimple(
                    new MapOf<String, Object>(
                        new MapEntry<>(
                            key,
                            expected
                        )
                    )
                )
            ).contains(key),
            CoreMatchers.equalTo(true)
        );
    }
}
