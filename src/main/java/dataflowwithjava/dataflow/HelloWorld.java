package dataflowwithjava.dataflow;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

public class HelloWorld {
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        pipeline
                .apply("CreateData", org.apache.beam.sdk.transforms.Create.of("Hello, World!"))
                .apply("LogData", ParDo.of(new DoFn<String, Void>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        System.out.println(c.element());
                    }
                }));

        pipeline.run();
    }
}

