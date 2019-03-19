package com.liao.wxshop.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 毫秒转秒
 *
 * @author liao
 * @date 2019/3/19
 */
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeNumber(date.getTime() / 1000);
    }
}
