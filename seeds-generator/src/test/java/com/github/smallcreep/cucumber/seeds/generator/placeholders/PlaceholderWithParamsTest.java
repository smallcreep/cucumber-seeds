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

package com.github.smallcreep.cucumber.seeds.generator.placeholders;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link PlaceholderWithParams}.
 * @since 0.2.0
 */
public final class PlaceholderWithParamsTest {

    /**
     * PlaceholderWithParams can wrap open round brackets.
     * @throws Exception if fails
     */
    @Test
    public void checkPlaceholder() throws Exception {
        final String expected = "expected";
        MatcherAssert.assertThat(
            new PlaceholderWithParams(
                (first, second) -> second,
                "#Func",
                new PlaceholderSimple(
                    expected
                )
            ) {
            }.apply(
                "#Func(()"
            ),
            CoreMatchers.equalTo(expected)
        );
    }

    /**
     * PlaceholderWithParams can wrap close round brackets.
     * @throws Exception if fails
     */
    @Test
    public void checkPlaceholderWrapCloseRoundBrackets() throws Exception {
        MatcherAssert.assertThat(
            new PlaceholderWithParams(
                (first, second) -> String.format("start %s end", second),
                "#Func2",
                new PlaceholdersAll(
                    new PlaceholderRegexp(
                        new PlaceholderSimple(
                            "close)bracket"
                        ),
                        "#Random#String"
                    )
                )
            ) {
            }.apply(
                "#Func2(#Random#String)"
            ),
            CoreMatchers.equalTo("start close)bracket end")
        );
    }
}
