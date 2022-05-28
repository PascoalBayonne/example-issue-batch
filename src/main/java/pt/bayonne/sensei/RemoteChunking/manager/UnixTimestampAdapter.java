package pt.bayonne.sensei.RemoteChunking.manager;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

public class UnixTimestampAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date value) throws IOException {

        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.getTime() / 1000);
    }

    @Override
    public Date read(JsonReader jsonReader) throws IOException {

        try{
            if (jsonReader == null) {
                return null;
            }

           var nextDateValue = jsonReader.toString();
            if (nextDateValue.endsWith("$.stepContribution.stepExecution.endTime") || nextDateValue.endsWith("$.stepContribution.stepExecution.jobExecution.endTime")){
                jsonReader.skipValue();
                return null;
            }
            return new Date(jsonReader.nextLong() * 1000);
        }catch (Exception e){
            return new Date();
        }




    }
}
