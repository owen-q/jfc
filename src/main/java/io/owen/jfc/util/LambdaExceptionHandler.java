package io.owen.jfc.util;

/**
 * Created by owen_q on 2018. 7. 20..
 */

import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface  LambdaExceptionHandler <F extends Function, S extends Supplier, R> {
    R apply(F f, S s);
}
