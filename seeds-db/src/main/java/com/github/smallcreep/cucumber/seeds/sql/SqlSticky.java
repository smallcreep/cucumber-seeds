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

package com.github.smallcreep.cucumber.seeds.sql;

import com.github.smallcreep.cucumber.seeds.Sql;
import org.cactoos.Func;
import org.cactoos.func.StickyFunc;

/**
 * Cached version of a Sql.
 *
 * <p>This {@link Sql} decorator technically is an in-memory
 * cache.</p>
 *
 * @since 0.2.1
 */
public final class SqlSticky implements Sql {

    /**
     * Func.
     */
    private final Func<Boolean, String> func;

    /**
     * Ctor.
     * @param sql The Sql to cache
     */
    public SqlSticky(final Sql sql) {
        this.func = new StickyFunc<>(
            input -> sql.query()
        );
    }

    @Override
    public String query() throws Exception {
        return this.func.apply(true);
    }
}