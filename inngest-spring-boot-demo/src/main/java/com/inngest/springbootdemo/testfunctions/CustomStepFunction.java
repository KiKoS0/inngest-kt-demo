package com.inngest.springbootdemo.testfunctions;

import com.inngest.*;

@FunctionConfig(id = "custom-result-fn", name = "Custom Result Function")
@FunctionEventTrigger(event = "test/custom.result.step")
public class CustomStepFunction extends InngestFunction {

    private final int count = 0;

    @Override
    public TestFuncResult execute(FunctionContext ctx, Step step) {
        int step1 = step.run("step1", () -> count + 1, Integer.class);
        int tmp1 = step1 + 1;

        int step2 = step.run("step2", () -> tmp1 + 1, Integer.class);
        int tmp2 = step2 + 1;

        return step.run("cast-to-type-add-one", () -> {
            System.out.println("-> running step 1!! " + tmp2);
            return new TestFuncResult(tmp2 + 1);
        }, TestFuncResult.class);
    }
}

