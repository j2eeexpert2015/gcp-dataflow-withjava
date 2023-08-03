package dataflowwithjava.dataflow;

import com.google.api.services.bigquery.model.TableRow;
import dataflowwithjava.common.GCPConstants;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;


public class BQRead {


    static class PrintFn extends DoFn<TableRow, Void> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            System.out.println(c.element());
        }
    }

    public static void main(String[] args) {
        System.out.println("@@@@@@@@@@ BQReadDataflow main started @@@@@@@@@@");
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        options.as(GcpOptions.class).setProject(GCPConstants.PROJECT_ID);
        options.setTempLocation("gs://"+GCPConstants.DATAFLOW_GCS_BUCKET_NAME+"/"+GCPConstants.DATAFLOW_TEMP_FOLDER);
        Pipeline pipeline = Pipeline.create(options);
        pipeline.apply("ReadFromBigQuery", BigQueryIO.readTableRows()
                        .from(GCPConstants.PROJECT_ID+":"+GCPConstants.DATASET_ID+"."+GCPConstants.GBQ_SOURCE_TABLE_NAME)
                .withoutValidation())
                .apply("Print", ParDo.of(new PrintFn()));

        pipeline.run().waitUntilFinish();
    }
}

