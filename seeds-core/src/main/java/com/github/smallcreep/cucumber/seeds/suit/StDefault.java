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

package com.github.smallcreep.cucumber.seeds.suit;

import com.github.smallcreep.cucumber.seeds.Context;
import com.github.smallcreep.cucumber.seeds.Suit;
import com.github.smallcreep.cucumber.seeds.context.CxJoined;
import com.github.smallcreep.cucumber.seeds.context.CxProperties;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

/**
 * Default suit.
 * @since 0.1.1
 */
public final class StDefault extends StEnvelope {

    /**
     * Ctor.
     * @param origin Origin suit
     */
    public StDefault(final Suit origin) {
        this(
            origin,
            new CxProperties(System.getProperties()),
            new CxProperties()
        );
    }

    /**
     * Ctor.
     * @param origin Origin suit
     * @param ctxs Contexts
     */
    StDefault(final Suit origin, final Context... ctxs) {
        this(
            origin,
            new IterableOf<>(ctxs)
        );
    }

    /**
     * Ctor.
     * @param origin Origin suit
     * @param ctxs Contexts
     */
    StDefault(final Suit origin, final Iterable<Context> ctxs) {
        this(
            origin,
            new CxJoined(
                new Joined<>(
                    origin.context(),
                    ctxs
                )
            )
        );
    }

    /**
     * Ctor.
     * @param origin Origin suit
     * @param ctx Joined context with properties context
     */
    StDefault(final Suit origin, final CxJoined ctx) {
        super(
            new StSimple(
                origin,
                ctx
            )
        );
    }
}
