package pt.bayonne.sensei.RemoteChunking.manager;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;

import java.io.IOException;
import java.util.Date;

public class JobExecutionAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter out, String value) throws IOException {

    }

    @Override
    public String read(JsonReader jsonReader) throws IOException {
        try{
            if (jsonReader == null) {
                return null;
            }

            String s = jsonReader.nextString();
            var nextDateValue = jsonReader.toString();
            if (nextDateValue.endsWith("$.stepContribution.stepExecution.jobExecutionId")){
                jsonReader.skipValue();
                return null;
            }
            return jsonReader.nextString();
        }catch (Exception e){
            return null;
        }

    }
}
