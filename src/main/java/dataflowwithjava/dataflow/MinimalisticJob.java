package dataflowwithjava.dataflow;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import java.util.Arrays;
import java.util.List;

public class MinimalisticJob {

    static class PrintFn extends DoFn<String, Void> {
        @ProcessElement
        public void processElement(@Element String element) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {

        // Define the options and create the pipeline
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline p = Pipeline.create(options);

        // Define the input data
        List<String> strings = Arrays.asList(
                "Line 1",
                "Line 2 some random words ",
                "Line3"
        );

        // Create a PCollection from the input data
        PCollection<String> stringPCollection = p.apply(Create.of(strings));

        // Apply a ParDo that prints each element of the PCollection
        stringPCollection.apply(ParDo.of(new PrintFn()));

        // Run the pipeline
        p.run().waitUntilFinish();

        System.out.println("Done");
    }
}

