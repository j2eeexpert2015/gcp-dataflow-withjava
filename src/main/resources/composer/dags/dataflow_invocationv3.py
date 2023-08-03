import datetime
from airflow import models
from airflow.providers.google.cloud.operators.dataflow import DataflowCreateJavaJobOperator
from airflow.utils.dates import days_ago

bucket_path = "gs://gcpsampleall/gcp_java/gcp-dataflow-withjava-1.0-SNAPSHOT-bundled.jar"
project_id = "sanguine-anthem-393416"

default_args = {
    'start_date': days_ago(1),
    'dataflow_default_options': {
        'project': project_id,
        'retries':0,
        'tempLocation': 'gs://gcpsampleall/dataflow_common/temp'
        #,'runner': 'DataflowRunner'
    }
}

with models.DAG(
        'sampledataflowv3',
        schedule_interval=datetime.timedelta(days=1),
        default_args=default_args) as dag:

    #t1 = DataflowJavaOperator(
    t1 = DataflowCreateJavaJobOperator(
        task_id='rundataflowjobfirst',
        jar=bucket_path,
        job_class='dataflowwithjava.dataflow.MinimalisticJob'
    )

    t1
