package TeamTwo.TeamTwoProject.service.diary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DoubleArraySerializer extends StdSerializer<double[]> {
    public DoubleArraySerializer() {
        super(double[].class);
    }

    @Override
    public void serialize(double[] value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String[] stringValues = new String[value.length];
        for (int i = 0; i < value.length; i++) {
            stringValues[i] = String.valueOf(value[i]);
        }
        gen.writeString(String.join(", ", stringValues));
    }
}