package io.owen.jfc.util;

/**
 * Created by owen_q on 2018. 7. 20..
 */
public class LambdaExceptionHandlerTest {

    /*
    public <F extends Function,S extends Supplier, R> Function wrapper(LambdaExceptionHandler<F, S, R> lambdaExceptionHandler){

        return arg -> {
            try {
                return lambdaExceptionHandler.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

    }

    @Test
    public void lambdaExceptionHandler() {
        // Given:
        Function<Integer, Integer> f1 = (a)->{
            return a+1;
        };

        Supplier s1 = () -> {
            return 123123;
        };

        LambdaExceptionHandler lambdaExceptionHandler = (f, s) -> {
            return arg -> {
                try {
                    return f.apply(arg);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };

            return s
        };

        lambdaExceptionHandler.apply(f1, s1);

        // When:

        // Then:
    }
    */

}
