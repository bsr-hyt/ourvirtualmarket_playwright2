package com.ourvirtualmarket.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties; //Bu nesne, configuration.properties dosyasındaki anahtar-değer çiftlerini tutmak için kullanılacak.

    static {
        try {
            //static blok içinde configuration.properties dosyasının okunması gerçekleşiyor:
            //Bu blok, configuration.properties dosyasının belirtilen yolunu (path/to/configuration.properties) okuyarak properties nesnesine yüklüyor.
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            //Dosya okuma işlemi tamamlandıktan sonra, input.close() ile dosya kaynağı kapatılıyor.
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Bu metodun parametresi key olarak belirtilen anahtara karşılık gelen değeri döndürür.
    //Örneğin, ConfigurationReader.get("browser") şeklinde kullanarak "browser" anahtarına karşılık gelen değeri alabilirsin
    public static String get(String key) {
        return properties.getProperty(key);
    }

}
