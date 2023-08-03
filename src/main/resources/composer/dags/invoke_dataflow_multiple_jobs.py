import datetime
from airflow import models
from airflow.contrib.operators.dataflow_operator import DataflowTemplateOperator
from airflow.utils.dates import days_ago

bucket_path = "gs://gcpsampleall/gcp_java/gcp-dataflow-withjava-1.0-SNAPSHOT-bundled.jar"
project_id = "sanguine-anthem-393416"

default_args = {
    'start_date': days_ago(1),
    'dataflow_default_options': {
        'project': project_id,
        'tempLocation': 'gs://gcpsampleall/dataflow_common/temp'
    }
}

with models.DAG(
        'dataflowfromcomposer',
        schedule_interval=datetime.timedelta(days=1),
        default_args=default_args) as dag:

    t1 = DataFlowJavaOperator(
        task_id='rundataflowjobfirst',
        jar=bucket_path,
        job_class='dataflowwithjava.dataflow.MinimalisticJob'
    )

    t2 = BigQueryInsertJobOperator(
    task_id="call_stored_procedure",
    configuration={
        "query": {
            "query": "CALL `{}.{}.populate_customer`(); ".format(gcp_project_id,dataset_id),
            "useLegacySql": False,
        }
    },

    t1 >> t2
