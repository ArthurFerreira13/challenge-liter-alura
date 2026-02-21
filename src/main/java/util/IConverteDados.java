package util;

public interface IConverteDados {
    <T> T converter(String json, Class<T> classe);
}
