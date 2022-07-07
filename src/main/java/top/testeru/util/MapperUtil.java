package top.testeru.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 14:28
 */
public class MapperUtil<T> implements  java.io.Serializable{
    private static final long serialVersionUID = 2L; // as of 2.9
    ObjectMapper objectMapper;

    T readValue ;


    public T getReadValue(File src, String pathName){
        try {
            //兼容json yaml csv 解析
            if(pathName.endsWith(".json")){
                objectMapper = new ObjectMapper(new JsonFactory());
            }else if (pathName.endsWith(".yaml") | pathName.endsWith(".yml")){
                objectMapper = new ObjectMapper(new YAMLFactory());
            }
            TypeReference<T> valueTypeRef =
                    new TypeReference<T>() {};
            //解析数据
            readValue = objectMapper.readValue(src, valueTypeRef);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readValue;
    }
    public T getReadValue(InputStream src, String pathName){
        try {
            //兼容json yaml csv 解析
            if(pathName.endsWith(".json")){
                objectMapper = new ObjectMapper(new JsonFactory());
            }else if (pathName.endsWith(".yaml") | pathName.endsWith(".yml")){
                objectMapper = new ObjectMapper(new YAMLFactory());
            }
            TypeReference<T> valueTypeRef =
                    new TypeReference<T>() {};
            //解析数据
            readValue = objectMapper.readValue(src, valueTypeRef);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readValue;
    }
}

